package Persistencia;

/**
 *
 * @author Enrique Osuna
 */
import Entidades.EquipoMedico;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersistenciaInventarios {
    
    private static final String ARCHIVO_EQUIPOS = "equipos_medicos.csv";
    private final Path archivoEquiposPath;

    public PersistenciaInventarios() {
        this.archivoEquiposPath = Paths.get(ARCHIVO_EQUIPOS);
        // Crear el archivo si no existe
        try {
            if (!Files.exists(archivoEquiposPath)) {
                Files.createFile(archivoEquiposPath);
            }
        } catch (IOException e) {
            System.err.println("Error al crear el archivo de equipos médicos: " + e.getMessage());
        }
    }

    public void agregarEquipoMedico(EquipoMedico equipo) throws Exception {
        EquipoMedico equipoExistente = buscar(equipo.getId());
        
        if (equipoExistente != null) {
            // Si existe, actualizamos la cantidad
            equipoExistente.setCantidad(equipoExistente.getCantidad() + equipo.getCantidad());
            actualizarCantidadEquipo(equipoExistente);
        } else {
            // No existe, lo agregamos al final
            String linea = equipo.getId() + "," + equipo.getNombre() + "," + equipo.getCantidad();
            try {
                Files.write(archivoEquiposPath, 
                    (linea + System.lineSeparator()).getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE, 
                    StandardOpenOption.APPEND);
                System.out.println("Se agrego el equipo");
            } catch (IOException e) {
                System.err.println("Error al escribir en el archivo: " + e.getMessage());
                throw new Exception("No se pudo inventariar el equipo médico", e);
            }
        }
    }

    public EquipoMedico buscar(int id) throws Exception {
        return listarEquiposMedicos().stream()
            .filter(e -> e.getId() == id)
            .findFirst()
            .orElse(null);
    }

    public void desinventariar(int id, int cantidad) throws Exception {
        EquipoMedico equipo = buscar(id);
        
        if (equipo == null) {
            throw new Exception("No existe un equipo médico con el ID " + id);
        }
        
        if (equipo.getCantidad() < cantidad) {
            throw new Exception("No hay suficientes unidades disponibles para desinventariar");
        }
        
        equipo.setCantidad(equipo.getCantidad() - cantidad);
        actualizarCantidadEquipo(equipo);
    }

    public void actualizarCantidadEquipo(EquipoMedico equipoActualizado) throws Exception {
        List<EquipoMedico> equipos = listarEquiposMedicos();
        
        boolean encontrado = false;
        for (int i = 0; i < equipos.size(); i++) {
            if (equipos.get(i).getId() == equipoActualizado.getId()) {
                equipos.set(i, equipoActualizado);
                encontrado = true;
                break;
            }
        }
        
        if (!encontrado) {
            throw new Exception("Equipo médico con ID " + equipoActualizado.getId() + " no encontrado");
        }
        
        guardarListaEquipos(equipos);
    }

    private void guardarListaEquipos(List<EquipoMedico> equipos) throws Exception {
        try {
            List<String> lineas = equipos.stream()
                .map(e -> e.getId() + "," + e.getNombre() + "," + e.getCantidad())
                .collect(Collectors.toList());
            
            Files.write(archivoEquiposPath, 
                lineas, 
                StandardCharsets.UTF_8, 
                StandardOpenOption.CREATE, 
                StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
            throw new Exception("No se pudo guardar la lista de equipos médicos", e);
        }
    }

    public List<EquipoMedico> listarEquiposMedicos() throws Exception {
        List<EquipoMedico> equipos = new ArrayList<>();
        
        try {
            // Verifica si existe el archivo
            if (!Files.exists(archivoEquiposPath)) {
                return equipos;
            }
            
            // Leer todas las líneas, ignorando líneas vacías
            List<String> lineas = Files.readAllLines(archivoEquiposPath, StandardCharsets.UTF_8)
                .stream()
                .filter(linea -> !linea.trim().isEmpty())
                .collect(Collectors.toList());
            
            for (String linea : lineas) {
                try {
                    String[] datos = linea.split(",");
                    if (datos.length == 3) {
                        int id = Integer.parseInt(datos[0]);
                        String nombre = datos[1];
                        int cantidad = Integer.parseInt(datos[2]);
                        
                        equipos.add(new EquipoMedico(id, nombre, cantidad));
                    }
                } catch (Exception e) {
                    System.err.println("Error al procesar línea: " + linea + " - " + e.getMessage());
                    // Continuar con la siguiente línea en caso de error
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            throw new Exception("No se pudo leer el archivo de equipos médicos", e);
        }
        
        return equipos;
    }

    public List<EquipoMedico> buscarPorNombre(String nombre) throws Exception {
        return listarEquiposMedicos().stream()
            .filter(e -> e.getNombre().toLowerCase().contains(nombre.toLowerCase()))
            .collect(Collectors.toList());
    }
    
    public int contarEquipos() throws Exception {
        return listarEquiposMedicos().size();
    }
}
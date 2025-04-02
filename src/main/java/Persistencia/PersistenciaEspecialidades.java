package Persistencia;

import Entidades.Especialidad;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author angelsn
 */
public class PersistenciaEspecialidades {
    private static final String ARCHIVO_ESPECIALIDADES = "especialidades.csv";
    private final Path archivoEspecialidadesPath;

    public PersistenciaEspecialidades() {
        this.archivoEspecialidadesPath = Paths.get(ARCHIVO_ESPECIALIDADES);
        // Crear el archivo si no existe
        try {
            if (!Files.exists(archivoEspecialidadesPath)) {
                Files.createFile(archivoEspecialidadesPath);
            }
        } catch (IOException e) {
            System.err.println("Error al crear el archivo de especialidades: " + e.getMessage());
        }
    }

    public void agregarEspecialidad(Especialidad especialidad) throws Exception {
        // Verificar si ya existe una especialidad con el mismo ID
        if (buscarEspecialidadPorId(especialidad.getId()) != null) {
            throw new Exception("Ya existe una especialidad con ID " + especialidad.getId());
        }

        String linea = especialidad.getId() + "," + especialidad.getNombre();
        try {
            Files.write(archivoEspecialidadesPath, 
                (linea + System.lineSeparator()).getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE, 
                StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
            throw new Exception("No se pudo agregar la especialidad", e);
        }
    }

    public Especialidad buscarEspecialidadPorId(int id) throws Exception {
        return listarEspecialidades().stream()
            .filter(e -> e.getId() == id)
            .findFirst()
            .orElse(null);
    }

    public void actualizarEspecialidad(Especialidad especialidadActualizada) throws Exception {
        List<Especialidad> especialidades = listarEspecialidades();
        
        boolean encontrado = false;
        for (int i = 0; i < especialidades.size(); i++) {
            if (especialidades.get(i).getId() == especialidadActualizada.getId()) {
                especialidades.set(i, especialidadActualizada);
                encontrado = true;
                break;
            }
        }
        
        if (!encontrado) {
            throw new Exception("Especialidad con ID " + especialidadActualizada.getId() + " no encontrada");
        }
        
        guardarListaEspecialidades(especialidades);
    }

    public void eliminarEspecialidad(int id) throws Exception {
        List<Especialidad> especialidades = listarEspecialidades();
        
        boolean eliminado = especialidades.removeIf(e -> e.getId() == id);
        
        if (!eliminado) {
            throw new Exception("Especialidad con ID " + id + " no encontrada");
        }
        
        guardarListaEspecialidades(especialidades);
    }

    public List<Especialidad> listarEspecialidades() throws Exception {
        List<Especialidad> especialidades = new ArrayList<>();
        
        try {
            if (!Files.exists(archivoEspecialidadesPath)) {
                return especialidades;
            }
            
            List<String> lineas = Files.readAllLines(archivoEspecialidadesPath, StandardCharsets.UTF_8)
                .stream()
                .filter(linea -> !linea.trim().isEmpty())
                .collect(Collectors.toList());
            
            for (String linea : lineas) {
                try {
                    String[] datos = linea.split(",");
                    if (datos.length == 2) {
                        int id = Integer.parseInt(datos[0]);
                        String nombre = datos[1];
                        
                        especialidades.add(new Especialidad(id, nombre));
                    }
                } catch (Exception e) {
                    System.err.println("Error al procesar línea: " + linea + " - " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            throw new Exception("No se pudo leer el archivo de especialidades", e);
        }
        
        return especialidades;
    }

    private void guardarListaEspecialidades(List<Especialidad> especialidades) throws Exception {
        try {
            List<String> lineas = especialidades.stream()
                .map(e -> e.getId() + "," + e.getNombre())
                .collect(Collectors.toList());
            
            Files.write(archivoEspecialidadesPath, 
                lineas, 
                StandardCharsets.UTF_8, 
                StandardOpenOption.CREATE, 
                StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
            throw new Exception("No se pudo guardar la lista de especialidades", e);
        }
    }

    public List<Especialidad> buscarEspecialidadesPorNombre(String nombre) throws Exception {
        return listarEspecialidades().stream()
            .filter(e -> e.getNombre().toLowerCase().contains(nombre.toLowerCase()))
            .collect(Collectors.toList());
    }
    
    public int contarEspecialidades() throws Exception {
        return listarEspecialidades().size();
    }
}
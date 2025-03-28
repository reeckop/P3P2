package Persistencia;

import Entidades.Paciente;
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
 * @author Enrique Osuna
 */
public class PersistenciaPacientes {
    private static final String ARCHIVO_PACIENTES = "pacientes.csv";
    private final Path archivoPacientesPath;

    public PersistenciaPacientes() {
        this.archivoPacientesPath = Paths.get(ARCHIVO_PACIENTES);
        // Crear el archivo si no existe
        try {
            if (!Files.exists(archivoPacientesPath)) {
                Files.createFile(archivoPacientesPath);
            }
        } catch (IOException e) {
            System.err.println("Error al crear el archivo de pacientes: " + e.getMessage());
        }
    }

    public void agregarPaciente(Paciente paciente) throws Exception {
        // Verifica si ya existe un paciente con el mismo ID
        if (buscarPacientePorID(paciente.getId()) != null) {
            throw new Exception("Ya existe un paciente con el ID: " + paciente.getId());
        }

        try {
            Files.write(archivoPacientesPath, 
                (paciente.toString() + System.lineSeparator()).getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE, 
                StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
            throw new Exception("No se pudo agregar el paciente", e);
        }
    }

    public void guardarListaPacientes(List<Paciente> pacientes) throws Exception {
        try {
            // Convertierte pacientes a líneas y guarda
            List<String> lineas = pacientes.stream()
                .map(Paciente::toString)
                .collect(Collectors.toList());
            
            // Usar UTF-8 y reemplazar todo el contenido
            Files.write(archivoPacientesPath, 
                lineas, 
                StandardCharsets.UTF_8, 
                StandardOpenOption.CREATE, 
                StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
            throw new Exception("No se pudo guardar la lista de pacientes", e);
        }
    }

    public void actualizarPaciente(Paciente pacienteActualizado) throws Exception {
        List<Paciente> pacientes = listarPacientes();
        
        // Buscar y reemplazar el paciente
        boolean encontrado = false;
        for (int i = 0; i < pacientes.size(); i++) {
            if (pacientes.get(i).getId() == pacienteActualizado.getId()) {
                pacientes.set(i, pacienteActualizado);
                encontrado = true;
                break;
            }
        }
        
        if (!encontrado) {
            throw new Exception("Paciente con ID " + pacienteActualizado.getId() + " no encontrado");
        }
        
        guardarListaPacientes(pacientes);
    }

    public void eliminarPaciente(int id) throws Exception {
        List<Paciente> pacientes = listarPacientes();
        
        // Eliminar paciente y verificar si se eliminó
        boolean eliminado = pacientes.removeIf(p -> p.getId() == id);
        
        if (!eliminado) {
            throw new Exception("Paciente con ID " + id + " no encontrado");
        }
        
        guardarListaPacientes(pacientes);
    }

    public List<Paciente> listarPacientes() throws Exception {
        List<Paciente> pacientes = new ArrayList<>();
        
        try {
            // Leer todas las líneas, ignorando líneas vacías
            List<String> lineas = Files.readAllLines(archivoPacientesPath, StandardCharsets.UTF_8)
                .stream()
                .filter(linea -> !linea.trim().isEmpty())
                .collect(Collectors.toList());
            
            for (String linea : lineas) {
                try {
                    Paciente paciente = new Paciente();
                    paciente.fromString(linea);
                    pacientes.add(paciente);
                } catch (Exception e) {
                    System.err.println("Error al procesar línea: " + linea + " - " + e.getMessage());
                    // Continuar con la siguiente línea en caso de error
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            throw new Exception("No se pudo leer el archivo de pacientes", e);
        }
        
        return pacientes;
    }

    public Paciente buscarPacientePorID(int id) throws Exception {
        return listarPacientes().stream()
            .filter(p -> p.getId() == id)
            .findFirst()
            .orElse(null);
    }

    // Métodos adicionales útiles
    public int contarPacientes() throws Exception {
        return listarPacientes().size();
    }

    public List<Paciente> buscarPacientesPorNombre(String nombre) throws Exception {
        return listarPacientes().stream()
            .filter(p -> p.getNombre().toLowerCase().contains(nombre.toLowerCase()))
            .collect(Collectors.toList());
    }
}
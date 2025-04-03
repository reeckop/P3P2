package Persistencia;

import Entidades.Consulta;
import Entidades.Medico;
import Entidades.Paciente;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * @author angelsn
 */
public class PersistenciaConsultas {
    private static final String ARCHIVO_CONSULTAS = "consultas.csv";
    private final Path archivoConsultasPath;

    public PersistenciaConsultas() {
        this.archivoConsultasPath = Paths.get(ARCHIVO_CONSULTAS);
        // Crear el archivo si no existe
        try {
            if (!Files.exists(archivoConsultasPath)) {
                Files.createFile(archivoConsultasPath);
            }
        } catch (IOException e) {
            System.err.println("Error al crear el archivo de consultas: " + e.getMessage());
        }
    }

    public void programarConsulta(Consulta consulta) throws Exception {
        // Validar que el ID sea único
        if (buscarConsultaPorId(consulta.getId()) != null) {
            throw new Exception("Ya existe una consulta con ID " + consulta.getId());
        }
        
        // Validar fecha (hoy o futuro)
        LocalDate fechaConsulta = LocalDate.parse(consulta.getFecha());
        LocalDate hoy = LocalDate.now();
        if (fechaConsulta.isBefore(hoy)) {
            throw new Exception("La fecha de la consulta no puede ser en el pasado.");
        }

        // Formato: id,paciente_id,medico_id,fecha
        String linea = consulta.getId() + "," 
                     + consulta.getPaciente().getId() + ","
                     + consulta.getMedico().getId() + ","
                     + consulta.getFecha();

        try {
            Files.write(archivoConsultasPath, 
                (linea + System.lineSeparator()).getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE, 
                StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
            throw new Exception("No se pudo programar la consulta", e);
        }
    }

    public List<Consulta> listarConsultas() throws Exception {
        List<Consulta> consultas = new ArrayList<>();
        
        try {
            if (!Files.exists(archivoConsultasPath)) {
                return consultas;
            }
            
            List<String> lineas = Files.readAllLines(archivoConsultasPath, StandardCharsets.UTF_8)
                .stream()
                .filter(linea -> !linea.trim().isEmpty())
                .collect(Collectors.toList());
            
            for (String linea : lineas) {
                try {
                    String[] datos = linea.split(",");
                    if (datos.length == 4) {
                        int id = Integer.parseInt(datos[0]);
                        int pacienteId = Integer.parseInt(datos[1]);
                        int medicoId = Integer.parseInt(datos[2]);
                        String fecha = datos[3];
                        
                        // Necesitarás acceder a las persistencia de Pacientes y Médicos aquí
                        PersistenciaPacientes persistenciaPacientes = new PersistenciaPacientes();
                        PersistenciaMedicos persistenciaMedicos = new PersistenciaMedicos();
                        
                        Paciente paciente = persistenciaPacientes.buscarPacientePorID(pacienteId);
                        Medico medico = persistenciaMedicos.obtenerMedicoPorId(medicoId);
                        
                        if (paciente != null && medico != null) {
                            consultas.add(new Consulta(id, paciente, medico, fecha));
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Error al procesar línea: " + linea + " - " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            throw new Exception("No se pudo leer el archivo de consultas", e);
        }
        
        return consultas;
    }
    
    public boolean cancelarConsultaPorId(int id) throws Exception {
        List<Consulta> consultas = listarConsultas();
        
        boolean eliminado = consultas.removeIf(c -> c.getId() == id);
        
        if (eliminado) {
            guardarListaConsultas(consultas);
            return true;
        }
        return false;
    }
    
    public Consulta buscarConsultaPorId(int id) throws Exception {
        return listarConsultas().stream()
            .filter(c -> c.getId() == id)
            .findFirst()
            .orElse(null);
    }
    
    public Consulta obtenerConsultaPorIdMedico(int id) throws Exception {
        return listarConsultas().stream()
            .filter(c -> c.getMedico().getId() == id)
            .findFirst()
            .orElse(null);
    }
    
    public Consulta obtenerConsultaPorIdPaciente(int id) throws Exception {
        return listarConsultas().stream()
            .filter(c -> c.getPaciente().getId() == id)
            .findFirst()
            .orElse(null);
    }

    private void guardarListaConsultas(List<Consulta> consultas) throws Exception {
        try {
            List<String> lineas = consultas.stream()
                .map(c -> c.getId() + "," 
                        + c.getPaciente().getId() + "," 
                        + c.getMedico().getId() + "," 
                        + c.getFecha())
                .collect(Collectors.toList());
            
            Files.write(archivoConsultasPath, 
                lineas, 
                StandardCharsets.UTF_8, 
                StandardOpenOption.CREATE, 
                StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
            throw new Exception("No se pudo guardar la lista de consultas", e);
        }
    }
}


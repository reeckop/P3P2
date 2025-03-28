package Persistencia;

/**
 *
 * @author Enrique Osuna
 */
import Entidades.Medico;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaMedicos {
    private static final String ARCHIVO_MEDICOS = "medicos.csv";
    private List<Medico> medicos;

    public PersistenciaMedicos() {
        this.medicos = new ArrayList<>();
    }
    
    public static void guardarMedicosEnCSV(List<Medico> medicos, String rutaArchivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaArchivo))) {
            // Escribir encabezados
            writer.println("ID,Nombre,Especialidad");

            // Escribir datos de cada médico
            for (Medico medico : medicos) {
                String lineaCSV = medico.getId() + ","
                        + medico.getNombre() + ","
                        + medico.getEspecialidad();
                writer.println(lineaCSV);
            }

            System.out.println("Médicos guardados en " + rutaArchivo);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo CSV: " + e.getMessage());
        }
    }


    public void agregarMedico(Medico medico) throws Exception {
        // Validar que el ID sea único
        for (Medico m : medicos) {
            if (m.getId() == medico.getId()) {
                throw new Exception("Ya existe un médico con ID " + medico.getId());
            }
        }
        this.medicos.add(medico);
    }

    public Medico obtenerMedicoPorId(int id) throws Exception {
        for (Medico m : medicos) {
            if (m.getId() == id) {
                return m;
            }
        }
        throw new Exception("No se encontró un médico con ID " + id);
    }

    public List<Medico> listarMedicos() {
        return new ArrayList<>(medicos);
    }
}


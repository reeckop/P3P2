package Persistencia;

/**
 *
 * @author Enrique Osuna
 */
import Entidades.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersistenciaFachada implements IPersistenciaFachada {

    private PersistenciaPacientes persistenciaPacientes;
    private PersistenciaMedicos persistenciaMedicos;
    private PersistenciaEspecialidades persistenciaEspecialidades;
    private PersistenciaInventarios persistenciaInventarios;
    private PersistenciaConsultas persistenciaConsultas;
    
    public PersistenciaFachada() {
        this.persistenciaPacientes = new PersistenciaPacientes();
        this.persistenciaMedicos = new PersistenciaMedicos();
        this.persistenciaEspecialidades = new PersistenciaEspecialidades();
        this.persistenciaInventarios = new PersistenciaInventarios();
        this.persistenciaConsultas = new PersistenciaConsultas();
    }

    // =========================
    //       PACIENTES
    // =========================
    @Override
    public void agregarPaciente(Paciente paciente) throws Exception {
        persistenciaPacientes.agregarPaciente(paciente);
    }

    @Override
    public Paciente obtenerPacientePorId(int id) throws Exception {
        return persistenciaPacientes.buscarPacientePorID(id);
    }

    @Override
    public List<Paciente> listarPacientes() throws Exception {
        return persistenciaPacientes.listarPacientes();
    }
    
    public void actualizarPaciente(Paciente paciente) throws Exception {
        persistenciaPacientes.actualizarPaciente(paciente);
    }
    
    public void eliminarPaciente(int id) {
        try {
            persistenciaPacientes.eliminarPaciente(id);
        } catch (Exception ex) {
            Logger.getLogger(PersistenciaFachada.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // =========================
    //       MÉDICOS
    // =========================
    @Override
    public void agregarMedico(Medico medico) throws Exception {
        // Validar que la especialidad exista (se asume que, si existe, no es null)
        if (medico.getEspecialidad() == null) {
            throw new Exception("La especialidad asociada al médico es inválida o no existe.");
        }
        persistenciaMedicos.agregarMedico(medico);
    }

    @Override
    public Medico obtenerMedicoPorId(int id) throws Exception {
        return persistenciaMedicos.obtenerMedicoPorId(id);
    }

    @Override
    public List<Medico> listarMedicos() throws Exception {
        return persistenciaMedicos.listarMedicos();
    }

    // =========================
    //   ESPECIALIDADES
    // =========================
    @Override
    public void agregarEspecialidad(Especialidad especialidad) throws Exception {
        persistenciaEspecialidades.agregarEspecialidad(especialidad);
    }

    @Override
    public Especialidad obtenerEspecialidadPorId(int id) throws Exception {
        return persistenciaEspecialidades.obtenerEspecialidadPorId(id);
    }

    @Override
    public List<Especialidad> listarEspecialidades() throws Exception {
        return persistenciaEspecialidades.listarEspecialidades();
    }

    // =========================
    //  EQUIPOS MÉDICOS
    // =========================
    @Override
    public void agregarEquipoMedico(EquipoMedico equipo) throws Exception {
        persistenciaInventarios.agregarEquipoMedico(equipo);
    }

    @Override
    public void actualizarCantidadEquipo(EquipoMedico equipoActualizado) throws Exception {
        persistenciaInventarios.actualizarCantidadEquipo(equipoActualizado);
    }

    @Override
    public List<EquipoMedico> listarEquiposMedicos() throws Exception {
        return persistenciaInventarios.listarEquiposMedicos();
    }

    // =========================
    //      CONSULTAS
    // =========================
    @Override
    public void programarConsulta(Consulta consulta) throws Exception {
        persistenciaConsultas.programarConsulta(consulta);
    }

    @Override
    public List<Consulta> listarConsultas() throws Exception {
        return persistenciaConsultas.listarConsultas();
    }

    @Override
    public void actualizarCantidadEquipo(int id, int cantidad) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

/**
 *
 * @author Enrique Osuna
 */
import Entidades.Paciente;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaPacientes {
    
    private List<Paciente> pacientes;

    public PersistenciaPacientes() {
        this.pacientes = new ArrayList<>();
    }

    public void agregarPaciente(Paciente paciente) throws Exception {
        // Validar que el ID sea único
        for (Paciente p : pacientes) {
            if (p.getId() == paciente.getId()) {
                throw new Exception("Ya existe un paciente con ID " + paciente.getId());
            }
        }
        // Validar que los datos del paciente sean válidos (ya se validan en el constructor)
        this.pacientes.add(paciente);
    }

    public Paciente obtenerPacientePorId(int id) throws Exception {
        for (Paciente p : pacientes) {
            if (p.getId() == id) {
                return p;
            }
        }
        throw new Exception("No se encontró un paciente con ID " + id);
    }

    public List<Paciente> listarPacientes() {
        return new ArrayList<>(pacientes);
    }
}


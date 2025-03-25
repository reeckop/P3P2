/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

/**
 *
 * @author Enrique Osuna
 */
import Entidades.Medico;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaMedicos {
    private static final String ARCHIVO_MEDICOS = "medicos.csv";
    private List<Medico> medicos;

    public PersistenciaMedicos() {
        this.medicos = new ArrayList<>();
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


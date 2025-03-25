/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

/**
 *
 * @author Enrique Osuna
 */
import Entidades.EquipoMedico;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaInventarios {
    
    private List<EquipoMedico> inventarios;

    public PersistenciaInventarios() {
        this.inventarios = new ArrayList<>();
    }

    public void agregarEquipoMedico(EquipoMedico equipo) throws Exception {
        // Validar que el ID sea único
        for (EquipoMedico e : inventarios) {
            if (e.getId() == equipo.getId()) {
                throw new Exception("Ya existe un equipo médico con ID " + equipo.getId());
            }
        }
        if (equipo.getCantidad() < 0) {
            throw new Exception("La cantidad no puede ser negativa.");
        }
        this.inventarios.add(equipo);
    }

    public void actualizarCantidadEquipo(int id, int cantidad) throws Exception {
        // Validar que el equipo exista y la nueva cantidad sea válida
        for (EquipoMedico e : inventarios) {
            if (e.getId() == id) {
                if (cantidad < 0) {
                    throw new Exception("La cantidad no puede ser negativa.");
                }
                e.setCantidad(cantidad);
                return;
            }
        }
        throw new Exception("No se encontró equipo médico con ID " + id);
    }

    public List<EquipoMedico> listarEquiposMedicos() {
        return new ArrayList<>(inventarios);
    }
}


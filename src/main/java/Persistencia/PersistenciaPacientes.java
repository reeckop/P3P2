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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

public class PersistenciaPacientes {
    private static final String ARCHIVO_PACIENTES = "pacientes.txt";
    private List<Paciente> pacientes;

    public PersistenciaPacientes() {
        this.pacientes = new ArrayList<>();
    }

    public void agregarPaciente(Paciente paciente) throws Exception {
        try {
        Files.write(Paths.get(ARCHIVO_PACIENTES), (paciente.toString() + System.lineSeparator()).getBytes(),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public void guardarListaPacientes(List<Paciente> pacientes) {
        try {
            List<String> lineas = pacientes.stream().map(Paciente::toString).collect(Collector.toList());
            Files.write(Paths.get(ARCHIVO_PACIENTES), lineas);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
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


package Entidades;

/**
 *
 * @author Ricardo
 */
public class Paciente {
    
    private int id;
    private String nombre;
    private int edad;
    private String direccion;

    public Paciente(){

    }
    
    public Paciente(int id, String nombre, int edad, String direccion) throws Exception {
        if (id <= 0) {
            throw new Exception("El ID del paciente debe ser positivo.");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre del paciente no puede estar vacío.");
        }
        if (edad <= 0) {
            throw new Exception("La edad del paciente debe ser positiva.");
        }
        if (direccion == null || direccion.trim().isEmpty()) {
            throw new Exception("La dirección del paciente no puede estar vacía.");
        }
        
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void fromString(String linea) {
    String[] campos = linea.split("\\s*,\\s*");
    
    if (campos.length >= 4) {
        try {
            this.id = Integer.parseInt(campos[0]);
            this.nombre = campos[1];
            this.edad = Integer.parseInt(campos[2]);
            this.direccion = campos[3];
        } catch (NumberFormatException e) {
            System.err.println("Error al convertir números en la línea: " + linea);
        }
        } else {
            System.err.println("Línea no tiene suficientes campos: " + linea);
        }
    }
       
    @Override
    public String toString() {
        return id + "," + nombre + "," + edad + "," + direccion;
    }
}

package scr;

public class Estudiante {
    private String nombre;  // nombre del estudiante
    String id;              // identificación del estudiante (visible dentro del paquete)
    private String grado;   // grado escolar
    private String grupo;   // grupo o sección

    // Constructor para crear un estudiante con sus datos
    public Estudiante(String nombre, String id, String grado, String grupo) {
        this.nombre = nombre;
        this.id = id;
        this.grado = grado;
        this.grupo = grupo;
    }

    // Método para mostrar la información del estudiante en texto
    @Override
    public String toString() {
        return "Nombre: " + nombre + ", ID: " + id + ", Grado: " + grado + ", Grupo: " + grupo;
    }
}

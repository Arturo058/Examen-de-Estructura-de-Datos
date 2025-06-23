package scr;

public class Libro {
    private String titulo; // título del libro
    private String autor;  // autor del libro

    // Constructor para crear un libro con título y autor
    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    // Devuelve la información del libro en formato texto
    @Override
    public String toString() {
        return "Título: " + titulo + ", Autor: " + autor;
    }
}

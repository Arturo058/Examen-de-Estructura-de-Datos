package scr;

public class NodoCalificacion {
    int calificacion;         // valor de la calificación
    NodoCalificacion izquierda, derecha; // nodos hijos izquierdo y derecho

    public NodoCalificacion(int calificacion) {
        this.calificacion = calificacion;
        izquierda = derecha = null; // inicia sin hijos
    }
}

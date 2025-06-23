package scr;

public class ArbolCalificaciones {
    private NodoCalificacion raiz; // raíz del árbol

    public void insertar(int calificacion) {
        raiz = insertarRec(raiz, calificacion);
    }

    // inserta recursivamente la calificación en su lugar correcto
    private NodoCalificacion insertarRec(NodoCalificacion nodo, int calificacion) {
        if (nodo == null) return new NodoCalificacion(calificacion);

        if (calificacion < nodo.calificacion)
            nodo.izquierda = insertarRec(nodo.izquierda, calificacion);
        else
            nodo.derecha = insertarRec(nodo.derecha, calificacion);

        return nodo;
    }

    // muestra las calificaciones en orden ascendente
    public void mostrarInorden() {
        System.out.print("Calificaciones (Inorden): ");
        inorden(raiz);
        System.out.println();
    }

    private void inorden(NodoCalificacion nodo) {
        if (nodo != null) {
            inorden(nodo.izquierda);
            System.out.print(nodo.calificacion + " ");
            inorden(nodo.derecha);
        }
    }
}

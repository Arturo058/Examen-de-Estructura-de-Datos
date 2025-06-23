package scr;

import java.util.LinkedList;
import java.util.Queue;

// Clase que simula una cola de atención usando la interfaz Queue
public class ColaAtencion {
    private Queue<String> cola; // cola para almacenar nombres

    public ColaAtencion() {
        cola = new LinkedList<>(); // inicializa la cola
    }

    // Agrega un nombre al final de la cola
    public void agregar(String nombre) {
        cola.offer(nombre);
    }

    // Atiende (quita) el primer nombre de la cola y lo devuelve
    public String atender() {
        return cola.poll();
    }

    // Revisa si la cola está vacía
    public boolean estaVacia() {
        return cola.isEmpty();
    }

    // Muestra los nombres que están actualmente en la cola
    public void mostrarCola() {
        System.out.println("En cola: " + cola);
    }
}

package scr;

import java.util.Stack;

public class PilaDevoluciones {
    private Stack<String> pila = new Stack<>(); // pila para guardar devoluciones

    // Agrega un libro a la pila (devolución más reciente)
    public void registrarDevolucion(String libro) {
        pila.push(libro);
    }

    // Muestra los libros devueltos, del más reciente al más antiguo
    public void mostrarDevoluciones() {
        if (pila.isEmpty()) {
            System.out.println("📭 No hay devoluciones registradas.");
            return;
        }

        System.out.println("📦 Pila de devoluciones (de más reciente a más antigua):");
        for (int i = pila.size() - 1; i >= 0; i--) {
            System.out.println("🔹 " + pila.get(i));
        }
    }
}

package scr;

import java.util.*;

public class MapaSalones {
    private Map<String, List<String>> conexiones; // mapa que guarda las conexiones entre salones

    public MapaSalones() {
        conexiones = new HashMap<>(); // inicializa el mapa vacío
    }

    // Conecta dos salones de forma bidireccional (no dirigido)
    public void conectar(String salon1, String salon2) {
        conexiones.putIfAbsent(salon1, new ArrayList<>()); // crea lista si no existe
        conexiones.putIfAbsent(salon2, new ArrayList<>());
        conexiones.get(salon1).add(salon2); // agrega conexión de salon1 a salon2
        conexiones.get(salon2).add(salon1); // agrega conexión de salon2 a salon1
    }

    // Muestra los salones conectados a uno dado
    public void verConexiones(String salon) {
        List<String> conectados = conexiones.get(salon);
        if (conectados == null) {
            System.out.println("No hay conexiones registradas para " + salon);
        } else {
            System.out.println("Conexiones desde " + salon + ": " + conectados);
        }
    }
}

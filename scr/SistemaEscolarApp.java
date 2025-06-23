package scr;

import java.util.*;

public class SistemaEscolarApp {
    private static ArrayList<Estudiante> estudiantes = new ArrayList<>();
    private static ArrayList<Libro> libros = new ArrayList<>();
    private static ColaAtencion cola = new ColaAtencion();
    private static PilaDevoluciones pila = new PilaDevoluciones();
    private static HashMap<String, String> materias = new HashMap<>();
    private static MapaSalones grafoSalones = new MapaSalones();
    private static Map<String, ArbolCalificaciones> calificacionesPorMateria = new HashMap<>();

    public static void iniciarSistema() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> registrarEstudiante(scanner);
                case 2 -> verEstudiantes();
                case 3 -> agregarLibro(scanner);
                case 4 -> verLibros();
                case 5 -> registrarAtencion(scanner);
                case 6 -> atenderEstudiante();
                case 7 -> registrarDevolucion(scanner);
                case 8 -> verDevoluciones();
                case 9 -> insertarCalificacion(scanner);
                case 10 -> mostrarTodasLasCalificaciones();
                case 11 -> registrarMateria(scanner);
                case 12 -> verMaterias();
                case 13 -> conectarSalones(scanner);
                case 14 -> verConexiones(scanner);
                case 15 -> System.out.println("👋 Saliendo del sistema...");
                default -> System.out.println("❌ Opción inválida.");
            }
        } while (opcion != 15);
    }

    private static void mostrarMenu() {
        System.out.println("""
        \n📚 MENÚ DEL SISTEMA ESCOLAR
        1. Registrar estudiante
        2. Ver lista de estudiantes
        3. Agregar libro nuevo
        4. Ver libros nuevos
        5. Registrar atención en ventanilla
        6. Atender estudiante
        7. Registrar devolución de libro
        8. Ver pila de devoluciones
        9. Insertar calificación
        10. Mostrar calificaciones ordenadas (inorden)
        11. Registrar materia (código y nombre)
        12. Ver materias registradas
        13. Conectar salones
        14. Ver conexiones de un salón
        15. Salir
        Selecciona una opción:
        """);
    }

    private static void registrarEstudiante(Scanner scanner) {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Grado: ");
        String grado = scanner.nextLine();
        System.out.print("Grupo: ");
        String grupo = scanner.nextLine();

        estudiantes.add(new Estudiante(nombre, id, grado, grupo));
        System.out.println("✅ Estudiante registrado.");
    }

    private static void verEstudiantes() {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
        } else {
            estudiantes.forEach(System.out::println);
        }
    }

    private static void agregarLibro(Scanner scanner) {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        libros.add(new Libro(titulo, autor));
        System.out.println("✅ Libro agregado.");
    }

    private static void verLibros() {
        if (libros.isEmpty()) {
            System.out.println("No hay libros.");
        } else {
            libros.forEach(System.out::println);
        }
    }

    private static void registrarAtencion(Scanner scanner) {
        System.out.print("Nombre del estudiante en ventanilla: ");
        String nombre = scanner.nextLine();
        cola.agregar(nombre);
    }

    private static void atenderEstudiante() {
        String atendido = cola.atender();
        if (atendido == null) {
            System.out.println("No hay estudiantes en cola.");
        } else {
            System.out.println("Atendiendo a: " + atendido);
        }
    }

    private static void registrarDevolucion(Scanner scanner) {
        System.out.print("Nombre del libro devuelto: ");
        String libro = scanner.nextLine();
        pila.registrarDevolucion(libro);
    }

    private static void verDevoluciones() {
        pila.mostrarDevoluciones();
    }

    private static void insertarCalificacion(Scanner scanner) {
        if (materias.isEmpty()) {
            System.out.println("⚠️ Por favor, registre primero las materias a calificar.");
            return;
        }

        if (estudiantes.isEmpty()) {
            System.out.println("⚠️ Por favor, registre primero a los estudiantes.");
            return;
        }

        System.out.println("\n📘 Materias registradas:");
        materias.forEach((codigo, nombre) -> System.out.println("Código: " + codigo + " - " + nombre));

        System.out.print("Ingrese el código de la materia: ");
        String codigoMateria = scanner.nextLine().trim();

        if (!materias.containsKey(codigoMateria)) {
            System.out.println("❌ Código de materia inválido.");
            return;
        }

        System.out.println("\n👨‍🎓 Estudiantes registrados:");
        for (Estudiante est : estudiantes) {
            System.out.println("ID: " + est.id + " - " + est);
        }

        System.out.print("Ingrese el ID del estudiante: ");
        String idEstudiante = scanner.nextLine().trim();

        Estudiante estudianteSeleccionado = null;
        for (Estudiante est : estudiantes) {
            if (est.id.equals(idEstudiante)) {
                estudianteSeleccionado = est;
                break;
            }
        }

        if (estudianteSeleccionado == null) {
            System.out.println("❌ ID de estudiante no encontrado.");
            return;
        }

        int calificacion = -1;
        boolean entradaValida = false;
        while (!entradaValida) {
            System.out.print("Ingrese la calificación (solo números enteros): ");
            if (scanner.hasNextInt()) {
                calificacion = scanner.nextInt();
                entradaValida = true;
            } else {
                System.out.println("❗ Entrada inválida. Solo se permiten números enteros. Intente de nuevo.");
                scanner.next(); // limpiar entrada inválida
            }
        }
        scanner.nextLine(); // limpiar buffer

        // Clave agrupada solo por materia
        String clave = codigoMateria;
        calificacionesPorMateria.putIfAbsent(clave, new ArbolCalificaciones());
        calificacionesPorMateria.get(clave).insertar(calificacion);

        System.out.println("✅ Calificación registrada para " + estudianteSeleccionado + " en " + materias.get(codigoMateria));
    }

    private static void mostrarTodasLasCalificaciones() {
        if (calificacionesPorMateria.isEmpty()) {
            System.out.println("⚠️ No hay calificaciones registradas.");
            return;
        }

        System.out.println("📊 Calificaciones ordenadas (inorden) por materia:");
        for (String materia : calificacionesPorMateria.keySet()) {
            System.out.println("\n➡️ " + materia);
            ArbolCalificaciones arbol = calificacionesPorMateria.get(materia);
            arbol.mostrarInorden();
        }
    }

    private static void registrarMateria(Scanner scanner) {
        System.out.print("Código de materia: ");
        String codigo = scanner.nextLine();
        System.out.print("Nombre de materia: ");
        String nombre = scanner.nextLine();
        materias.put(codigo, nombre);
        System.out.println("✅ Materia registrada.");
    }

    private static void verMaterias() {
        if (materias.isEmpty()) {
            System.out.println("No hay materias registradas.");
        } else {
            materias.forEach((k, v) -> System.out.println(k + " - " + v));
        }
    }

    private static void conectarSalones(Scanner scanner) {
        System.out.print("Salón 1: ");
        String s1 = scanner.nextLine();
        System.out.print("Salón 2: ");
        String s2 = scanner.nextLine();
        grafoSalones.conectar(s1, s2);
        System.out.println("✅ Salones conectados.");
    }

    private static void verConexiones(Scanner scanner) {
        System.out.print("Salón a consultar: ");
        String salon = scanner.nextLine();
        grafoSalones.verConexiones(salon);
    }
}

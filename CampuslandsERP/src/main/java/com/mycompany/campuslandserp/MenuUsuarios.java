/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.campuslandserp;
/**
 *
 * @author chanaga
 */

import java.util.*;

public class MenuUsuarios {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Map<String, List<String>> usuarios = new HashMap<>();

            // usuarios
            usuarios.put("Camper", Arrays.asList("Desea realizar el pre registro a campus? (Si / No)"));
            usuarios.put("Trainer", Arrays.asList("Ver grupos según entrenador asignado", "Ingresar notas de campers", "Regresar al menú principal"));
            usuarios.put("Coordinacion", Arrays.asList("Ver opciones sobre los campers", "Ver opciones sobre los trainers", "Grupos (creacion, vista...)", "Regresar al menú principal"));
            usuarios.put("Reportes", Arrays.asList(
                    "Listar los campers que se encuentren en estado de inscrito.",
                    "Listar los campers que aprobaron el examen inicial.",
                    "Listar los entrenadores que se encuentran trabajando con CampusLands.",
                    "Listar los campers que cuentan con bajo rendimiento.",
                    "Listar los campers y trainers que se encuentren asociados a una ruta de entrenamiento.",
                    "Mostrar cuantos campers perdieron y aprobaron cada uno de los módulos teniendo en cuenta la ruta de entrenamiento y el entrenador encargado.",
                    "Regresar al menú principal"
            ));

            while (true) {
                System.out.println("Seleccione un usuario:");
                System.out.println("1. Camper");
                System.out.println("2. Trainer");
                System.out.println("3. Coordinacion");
                System.out.println("4. Reportes");
                System.out.println("5. Salir");
                int opcionUsuario = scanner.nextInt();
                scanner.nextLine();

                switch (opcionUsuario) {
                    case 5 -> {
                        System.out.println("Saliendo...");
                        return;
                    }
                    case 1 -> mostrarMenuUsuario(scanner, "Camper", usuarios.get("Camper"));
                    case 2 -> mostrarMenuUsuario(scanner, "Trainer", usuarios.get("Trainer"));
                    case 3 -> mostrarMenuUsuario(scanner, "Coordinacion", usuarios.get("Coordinacion"));
                    case 4 -> mostrarMenuUsuario(scanner, "Reportes", usuarios.get("Reportes"));
                    default -> System.out.println("Opción no válida. Intente nuevamente.");
                }
            }
        }
    }

    private static void mostrarMenuUsuario(Scanner scanner, String usuario, List<String> opciones) {
        while (true) {
            System.out.println("Menú de " + usuario + ":");
            for (int i = 0; i < opciones.size(); i++) {
                System.out.println((i + 1) + ". " + opciones.get(i));
            }

            int opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == opciones.size()) {
                break;  // Regresar al menú principal
            } else if (opcion >= 1 && opcion < opciones.size()) {
                System.out.println("Ha seleccionado: " + opciones.get(opcion - 1));
                ejecutarFuncion(usuario, opcion);
            } else {
                System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    private static void ejecutarFuncion(String usuario, int opcion) {
        switch (usuario) {
            case "Camper" -> {
                if (opcion == 1) {
                    System.out.println("Ingrese 'Si' o 'No' para realizar el pre registro a campus:");
                    Scanner scanner = new Scanner(System.in);
                    String respuesta = scanner.nextLine();
                    System.out.println("Respuesta registrada: " + respuesta);
                }
            }
            case "Trainer" -> {
                // falta implementar las funciones de cada cosa
            }
            case "Coordinacion" -> {
                // falta implementar las funciones de cada cosa
            }
            case "Reportes" -> {
                // falta implementar las funciones de cada cosa
            }
            default -> System.out.println("Usuario no reconocido.");
        }

            }
}

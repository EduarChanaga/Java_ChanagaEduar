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
            usuarios.put("Camper", Arrays.asList(
                    "Agregar nuevo usuario",
                    "Regresar al menú principal"));
            
            usuarios.put("Trainer", Arrays.asList(
                    "Ver grupos según entrenador asignado",
                    "Ingresar notas de campers",
                    "Regresar al menú principal"));
            
            usuarios.put("Coordinacion", Arrays.asList(
                    "Ver opciones sobre los campers",
                    "Ver opciones sobre los trainers",
                    "Grupos (creacion, vista...)",
                    "Regresar al menú principal"));
            
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
                scanner.nextLine(); // Consumir el salto de línea después de nextInt()

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
            scanner.nextLine(); // Consumir el salto de línea después de nextInt()

            if (opcion == opciones.size() + 1) {
                break;  // Regresar al menú principal
            } else if (opcion >= 1 && opcion <= opciones.size()) {
                System.out.println("Ha seleccionado: " + opciones.get(opcion - 1));
                ejecutarFuncion(scanner, usuario, opcion);
            } else {
                System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }
    
    private static void ejecutarFuncion(Scanner scanner, String usuario, int opcion) {

        switch (usuario) {
            case "Camper" -> {
                if (opcion == 1) {
                    System.out.println("----- Agregar nuevo camper -----");
                    System.out.println("Numero de identificacion: ");
                    int identificacion = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.println("ID (" + identificacion + ") ");
                } else {
                    // Manejar otras opciones para Camper si es necesario
                }
            }

            case "Trainer" -> {
            }

            case "Coordinacion" -> {
            }

            case "Reportes" -> {
            }

            default -> System.out.println("Usuario no reconocido.");
        }
        // Implementar funciones para Trainer
        // Implementar funciones para Coordinacion
        // Implementar funciones para Reportes
            }
}

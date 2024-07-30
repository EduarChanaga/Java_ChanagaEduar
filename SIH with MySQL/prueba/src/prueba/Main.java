/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package prueba;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean repeticion = true;
        Scanner scanner = new Scanner(System.in);
        while (repeticion) {
            // Mostrar opciones al usuario
            System.out.println("Selecciona una opción:");
            System.out.println("1 - Opciones del hospital");
            System.out.println("2 - Opciones del departamento");
            System.out.println("3 - Opciones de pabellones");
            System.out.println("4 - Opciones de pacientes");
            System.out.println("5 - Opciones de personal");
            System.out.println("6 - Salir");

            // Capturar entrada del usuario
            int opcion = scanner.nextInt();
            int i = 0;
            while (i != 10) {
                System.out.println("");
                i = 1 + i;
            }

            // Ejecutar la clase correspondiente según la opción
            switch (opcion) {
                case 1 -> {
                    System.out.println("----- Hospital -----");
                    System.out.println("1 - Agregar hospital");
                    System.out.println("2 - Ver hospitales");
                    System.out.println("3 - Eliminar hospital");
                    int opcion2 = scanner.nextInt();
                    i = 0;
                    while (i != 10) {
                        System.out.println("");
                        i = 1 + i;
                    }

                    switch (opcion2) {
                        case 1 -> Hospital.Ingresar_hospital();
                        case 2 -> Hospital.Ver_hospital();
                        case 3 -> Hospital.Eliminar_hospital();
                        default -> System.out.println("Opción no válida.");
                    }
                }
                case 2 -> {
                    System.out.println("----- Departamento -----");
                    System.out.println("1 - Agregar departamento");
                    System.out.println("2 - Ver departamentos");
                    System.out.println("3 - Eliminar departamento");
                    int opcion2 = scanner.nextInt();
                    i = 0;
                    while (i != 10) {
                        System.out.println("");
                        i = 1 + i;
                    }

                    switch (opcion2) {
                        case 1 -> Departamento.Ingresar_departamento();
                        case 2 -> Departamento.Ver_departamento();
                        case 3 -> Departamento.Eliminar_departamento();
                        default -> System.out.println("Opción no válida.");
                    }
                }
                case 3 -> gestionarPabellones(scanner);
                case 4 -> gestionarPacientes(scanner);
                case 5 -> gestionarPersonal(scanner);
                case 6 -> {
                    System.out.println("Saliendo...");
                    repeticion = false;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }



    private static void gestionarPabellones(Scanner scanner) {
        boolean salir = false;

        while (!salir) {
            System.out.println("Seleccione una opción de Pabellones:");
            System.out.println("1. Ingresar Pabellón");
            System.out.println("2. Ver Pabellones");
            System.out.println("3. Eliminar Pabellón");
            System.out.println("4. Volver al menú principal");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1 -> Pabellon.Ingresar_pabellon();
                case 2 -> Pabellon.Ver_pabellon();
                case 3 -> Pabellon.Eliminar_pabellon();
                case 4 -> salir = true;
                default -> System.out.println("Opción no válida. Por favor, intente nuevamente.");
            }
        }
    }

    private static void gestionarPacientes(Scanner scanner) {
        boolean salir = false;

        while (!salir) {
            System.out.println("Seleccione una opción de Pacientes:");
            System.out.println("1. Ingresar Paciente");
            System.out.println("2. Ver Pacientes");
            System.out.println("3. Eliminar Paciente");
            System.out.println("4. Volver al menú principal");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1 -> Paciente.Ingresar_paciente();
                case 2 -> Paciente.Ver_paciente();
                case 3 -> Paciente.Eliminar_paciente();
                case 4 -> salir = true;
                default -> System.out.println("Opción no válida. Por favor, intente nuevamente.");
            }
        }
    }

    private static void gestionarPersonal(Scanner scanner) {
        boolean salir = false;

        while (!salir) {
            System.out.println("Seleccione una opción de Personal:");
            System.out.println("1. Ingresar Personal");
            System.out.println("2. Ver Personal");
            System.out.println("3. Eliminar Personal");
            System.out.println("4. Volver al menú principal");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1 -> Personal.Ingresar_personal();
                case 2 -> Personal.Ver_personal();
                case 3 -> Personal.Eliminar_personal();
                case 4 -> salir = true;
                default -> System.out.println("Opción no válida. Por favor, intente nuevamente.");
            }
        }
    }
}

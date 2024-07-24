/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<Hospital> hospitales = new ArrayList<>();

        while (true) {
            System.out.println("1. Agregar Hospital");
            System.out.println("2. Agregar Departamento");
            System.out.println("3. Agregar Persona");
            System.out.println("4. Agregar Paciente");
            System.out.println("5. Agregar Personal");
            System.out.println("6. Mostrar Información");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1 -> agregarHospital(hospitales);
                case 2 -> agregarDepartamento(hospitales);
                case 3 -> agregarPersona(hospitales);
                case 4 -> agregarPaciente(hospitales);
                case 5 -> agregarPersonal(hospitales);
                case 6 -> mostrarInformacion(hospitales);
                case 0 -> {
                    System.out.println("Saliendo...");
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private static void agregarHospital(List<Hospital> hospitales) {
        System.out.print("Nombre del Hospital: ");
        String nombre = scanner.nextLine();
        hospitales.add(new Hospital(nombre));
        System.out.println("Hospital agregado.");
    }

    private static void agregarDepartamento(List<Hospital> hospitales) {
        System.out.print("Nombre del Hospital: ");
        String nombreHospital = scanner.nextLine();
        Hospital hospital = buscarHospital(hospitales, nombreHospital);
        if (hospital != null) {
            System.out.print("Nombre del Departamento: ");
            String nombreDepto = scanner.nextLine();
            Departamento departamento = new Departamento(nombreDepto);
            hospital.agregarDepartamento(departamento);
            System.out.println("Departamento agregado.");
        } else {
            System.out.println("Hospital no encontrado.");
        }
    }

    private static void agregarPersona(List<Hospital> hospitales) {
        System.out.print("Nombre completo de la Persona: ");
        String nombreCompleto = scanner.nextLine();
        System.out.print("Dirección de la Persona: ");
        String direccion = scanner.nextLine();
        System.out.print("Fecha de nacimiento (yyyy-MM-dd): ");
        LocalDate fechaNacimiento = LocalDate.parse(scanner.nextLine(), formatter);
        // Aquí puedes agregar la persona a una lista o hacer lo que necesites con ella
        System.out.println("Persona agregada.");
    }

    private static void agregarPaciente(List<Hospital> hospitales) {
        System.out.print("Nombre completo del Paciente: ");
        String nombreCompleto = scanner.nextLine();
        System.out.print("Dirección del Paciente: ");
        String direccion = scanner.nextLine();
        System.out.print("Fecha de nacimiento (yyyy-MM-dd): ");
        LocalDate fechaNacimiento = LocalDate.parse(scanner.nextLine(), formatter);
        System.out.print("Pabellón: ");
        String pabellon = scanner.nextLine();
        System.out.print("Diagnóstico: ");
        String diagnostico = scanner.nextLine();
        Paciente paciente = new Paciente(nombreCompleto, direccion, fechaNacimiento, pabellon, diagnostico);
        // Aquí puedes agregar el paciente a una lista o hacer lo que necesites con él
        System.out.println("Paciente agregado.");
    }

    private static void agregarPersonal(List<Hospital> hospitales) {
        System.out.print("Nombre completo del Personal: ");
        String nombreCompleto = scanner.nextLine();
        System.out.print("Dirección del Personal: ");
        String direccion = scanner.nextLine();
        System.out.print("Fecha de nacimiento (yyyy-MM-dd): ");
        LocalDate fechaNacimiento = LocalDate.parse(scanner.nextLine(), formatter);
        System.out.print("Fecha de vinculación (yyyy-MM-dd): ");
        LocalDate fechaVinculacion = LocalDate.parse(scanner.nextLine(), formatter);
        System.out.print("Salario: ");
        double salario = scanner.nextDouble();
        scanner.nextLine(); // Consumir el salto de línea
        System.out.print("Tipo de Personal (Administrativo/Tecnico/Operaciones): ");
        String tipo = scanner.nextLine();
        Departamento departamento = seleccionarDepartamento(hospitales);
        if (departamento != null) {
            Personal personal = null;
            switch (tipo.toLowerCase()) {
                case "administrativo":
                    personal = new PersonalAdministrativo(nombreCompleto, direccion, fechaNacimiento, fechaVinculacion, salario);
                    break;
                case "tecnico":
                    personal = new PersonalTecnico(nombreCompleto, direccion, fechaNacimiento, fechaVinculacion, salario);
                    break;
                case "operaciones":
                    personal = new PersonalOperaciones(nombreCompleto, direccion, fechaNacimiento, fechaVinculacion, salario);
                    break;
                default:
                    System.out.println("Tipo de personal no válido.");
                    return;
            }
            departamento.agregarPersonal(personal);
            System.out.println("Personal agregado.");
        } else {
            System.out.println("Departamento no encontrado.");
        }
    }

    private static void mostrarInformacion(List<Hospital> hospitales) {
        for (Hospital hospital : hospitales) {
            System.out.println("Hospital: " + hospital.getNombre());
            for (Departamento departamento : hospital.getDepartamentos()) {
                System.out.println("  Departamento: " + departamento.getNombre());
                for (Personal personal : departamento.getPersonal()) {
                    System.out.println("    Personal: " + personal.getNombreCompleto() + ", Salario: " + personal.getSalario());
                }
            }
        }
    }

    private static Hospital buscarHospital(List<Hospital> hospitales, String nombre) {
        for (Hospital hospital : hospitales) {
            if (hospital.getNombre().equalsIgnoreCase(nombre)) {
                return hospital;
            }
        }
        return null;
    }

    private static Departamento seleccionarDepartamento(List<Hospital> hospitales) {
        System.out.print("Nombre del Departamento: ");
        String nombreDepto = scanner.nextLine();
        for (Hospital hospital : hospitales) {
            for (Departamento depto : hospital.getDepartamentos()) {
                if (depto.getNombre().equalsIgnoreCase(nombreDepto)) {
                    return depto;
                }
            }
        }
        return null;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.campuslandserp;

import java.util.*;

public class MenuUsuarios {
    
    static Map<Integer, Camper> campers = new HashMap<>();
    static List<Trainer> trainers = new ArrayList<>();
    static Map<Integer, Grupo> grupos = new HashMap<>();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Map<String, List<String>> usuarios = new HashMap<>();

            // Opciones de usuarios
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

            if (opcion == opciones.size()) {
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
                    agregarNuevoCamper(scanner);
                }
            }

            case "Trainer" -> {
                if (opcion == 1) {
                    verGruposSegunTrainer(scanner);
                } else if (opcion == 2) {
                    ingresarNotasCamper(scanner);
                }
            }

            case "Coordinacion" -> {
            switch (opcion) {
                case 1 -> opcionesSobreCampers(scanner);
                case 2 -> opcionesSobreTrainers(scanner);
                case 3 -> opcionesGrupo(scanner);
                default -> {
                }
            }
            }

            case "Reportes" -> {
            switch (opcion) {
                case 1 -> listarCampersInscritos();
                case 2 -> listarCampersAprobados();
                case 3 -> listarTrainers();
                case 4 -> listarCampersBajoRendimiento();
                case 5 -> listarCampersTrainersPorRuta();
                case 6 -> mostrarResultadosModulos();
                default -> {
                }
            }
            }

            default -> System.out.println("Usuario no reconocido.");
        }
    }

    // Métodos para opciones de Camper
    private static void agregarNuevoCamper(Scanner scanner) {
        System.out.println("----- Agregar nuevo camper -----");
        System.out.println("Numero de identificacion: ");
        int identificacion = scanner.nextInt();
        scanner.nextLine(); 
        
        System.out.println("Nombres: ");
        String nombres = scanner.nextLine(); 
        
        System.out.println("Apellidos: ");
        String apellidos = scanner.nextLine();
        
        System.out.println("Direccion: ");
        String direccion =  scanner.nextLine();
        
        System.out.println("Acudiente: ");
        String acudiente =  scanner.nextLine();
        
        System.out.println("Numero celular: ");
        int celular = scanner.nextInt();
        
        System.out.println("Numero fijo: ");
        int fijo = scanner.nextInt();
        scanner.nextLine(); 
        
        System.out.println("Estado: ");
        String estado =  scanner.nextLine();
        
        System.out.println("Riesgo: ");
        String riesgo =  scanner.nextLine();
        
        // Crear el objeto camper y agregar al mapa
        Camper camper = new Camper(identificacion, nombres, apellidos, direccion, acudiente, celular, fijo, estado, riesgo);
        campers.put(identificacion, camper);
        System.out.println("Camper agregado: " + camper);
    }

    // Métodos para opciones de Trainer
    private static void verGruposSegunTrainer(Scanner scanner) {
        System.out.println("----- Ver grupos según entrenador asignado -----");
        // Implementar lógica para ver grupos según entrenador
        for (Trainer trainer : trainers) {
            System.out.println("Trainer: " + trainer.getNombre());
            for (Grupo grupo : grupos.values()) {
                if (grupo.getTrainer().equals(trainer)) {
                    System.out.println("  Grupo: " + grupo.getNombre());
                }
            }
        }
    }

    private static void ingresarNotasCamper(Scanner scanner) {
        System.out.println("----- Ingresar notas de campers -----");
        System.out.println("Numero de identificacion del camper: ");
        int identificacion = scanner.nextInt();
        scanner.nextLine(); 

        Camper camper = campers.get(identificacion);
        if (camper != null) {
            System.out.println("Ingrese la nota: ");
            int nota = scanner.nextInt();
            scanner.nextLine(); 
            camper.setNota(nota);
            System.out.println("Nota ingresada para " + camper.getNombre() + ": " + nota);
        } else {
            System.out.println("Camper no encontrado.");
        }
    }

    // Métodos para opciones de Coordinacion
    private static void opcionesSobreCampers(Scanner scanner) {
        while (true) {
            System.out.println("Opciones sobre campers:");
            System.out.println("1. Inscripcion (Autorizar campers para realizar la prueba de ingreso)");
            System.out.println("2. Ingresar la nota de los campers que se han registrado");
            System.out.println("3. Matriculas (Asignar grupo a camper Aprobado)");
            System.out.println("4. Graduar campers (comprueba que todos los modulos fueron realizados)");
            System.out.println("5. Expulsar campers");
            System.out.println("6. Modificar nota de modulo de camper");
            System.out.println("7. Regresar al menú principal");

            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1 -> inscribirCamper(scanner);
                case 2 -> ingresarNotasCamper(scanner);
                case 3 -> asignarGrupo(scanner);
                case 4 -> graduarCamper(scanner);
                case 5 -> expulsarCamper(scanner);
                case 6 -> modificarNotaCamper(scanner);
                case 7 -> {
                    return;
                }
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    private static void inscribirCamper(Scanner scanner) {
        System.out.println("----- Inscribir camper -----");
        // Implementar lógica para inscribir camper
        System.out.println("Numero de identificacion del camper: ");
        int identificacion = scanner.nextInt();
        scanner.nextLine(); 

        Camper camper = campers.get(identificacion);
        if (camper != null) {
            camper.setEstado("Inscrito");
            System.out.println("Camper " + camper.getNombre() + " inscrito.");
        } else {
            System.out.println("Camper no encontrado.");
        }
    }

    private static void asignarGrupo(Scanner scanner) {
        System.out.println("----- Asignar grupo a camper -----");
        System.out.println("Numero de identificacion del camper: ");
        int identificacion = scanner.nextInt();
        scanner.nextLine(); 

        Camper camper = campers.get(identificacion);
        if (camper != null && camper.getNota() >= 50) {
            System.out.println("Ingrese el ID del grupo: ");
            int idGrupo = scanner.nextInt();
            scanner.nextLine(); 

            Grupo grupo = grupos.get(idGrupo);
            if (grupo != null) {
                grupo.agregarCamper(camper);
                camper.setEstado("Matriculado");
                System.out.println("Camper " + camper.getNombre() + " asignado al grupo " + grupo.getNombre());
            } else {
                System.out.println("Grupo no encontrado.");
            }
        } else {
            System.out.println("Camper no encontrado o no aprobado.");
        }
    }

    private static void graduarCamper(Scanner scanner) {
        System.out.println("----- Graduar camper -----");
        System.out.println("Numero de identificacion del camper: ");
        int identificacion = scanner.nextInt();
        scanner.nextLine(); 

        Camper camper = campers.get(identificacion);
        if (camper != null && camper.getEstado().equals("Matriculado")) {
            camper.setEstado("Graduado");
            System.out.println("Camper " + camper.getNombre() + " ha sido graduado.");
        } else {
            System.out.println("Camper no encontrado o no matriculado.");
        }
    }

    private static void expulsarCamper(Scanner scanner) {
        System.out.println("----- Expulsar camper -----");
        System.out.println("Numero de identificacion del camper: ");
        int identificacion = scanner.nextInt();
        scanner.nextLine(); 

        Camper camper = campers.get(identificacion);
        if (camper != null) {
            camper.setEstado("Expulsado");
            System.out.println("Camper " + camper.getNombre() + " ha sido expulsado.");
        } else {
            System.out.println("Camper no encontrado.");
        }
    }

    private static void modificarNotaCamper(Scanner scanner) {
        System.out.println("----- Modificar nota de camper -----");
        System.out.println("Numero de identificacion del camper: ");
        int identificacion = scanner.nextInt();
        scanner.nextLine(); 

        Camper camper = campers.get(identificacion);
        if (camper != null) {
            System.out.println("Ingrese la nueva nota: ");
            int nuevaNota = scanner.nextInt();
            scanner.nextLine(); 
            camper.setNota(nuevaNota);
            System.out.println("Nota modificada para " + camper.getNombre() + ": " + nuevaNota);
        } else {
            System.out.println("Camper no encontrado.");
        }
    }

    private static void opcionesSobreTrainers(Scanner scanner) {
        while (true) {
            System.out.println("Opciones sobre trainers:");
            System.out.println("1. Ingreso de trainer");
            System.out.println("2. Ver rutas según trainers");
            System.out.println("3. Ver información de trainer");
            System.out.println("4. Regresar al menú principal");

            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1 -> ingresarTrainer(scanner);
                case 2 -> verRutasSegunTrainers();
                case 3 -> verInformacionTrainer(scanner);
                case 4 -> {
                    return;
                }
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    private static void ingresarTrainer(Scanner scanner) {
        System.out.println("----- Ingreso de trainer -----");
        System.out.println("Numero de identificacion: ");
        int identificacion = scanner.nextInt();
        scanner.nextLine(); 

        System.out.println("Nombre: ");
        String nombre = scanner.nextLine();

        // Crear objeto trainer y agregar a la lista
        Trainer trainer = new Trainer(identificacion, nombre);
        trainers.add(trainer);
        System.out.println("Trainer agregado: " + trainer);
    }

    private static void verRutasSegunTrainers() {
        System.out.println("----- Ver rutas según trainers -----");
        for (Trainer trainer : trainers) {
            System.out.println("Trainer: " + trainer.getNombre());
            for (Grupo grupo : grupos.values()) {
                if (grupo.getTrainer().equals(trainer)) {
                    System.out.println("  Grupo: " + grupo.getNombre());
                }
            }
        }
    }

    private static void verInformacionTrainer(Scanner scanner) {
        System.out.println("----- Ver información de trainer -----");
        System.out.println("Numero de identificacion del trainer: ");
        int identificacion = scanner.nextInt();
        scanner.nextLine(); 

        Trainer trainer = trainers.stream()
            .filter(t -> t.getIdentificacion() == identificacion)
            .findFirst()
            .orElse(null);

        if (trainer != null) {
            System.out.println("Trainer: " + trainer);
        } else {
            System.out.println("Trainer no encontrado.");
        }
    }

    private static void opcionesGrupo(Scanner scanner) {
        while (true) {
            System.out.println("Opciones de grupo:");
            System.out.println("1. Crear grupo");
            System.out.println("2. Ver grupos");
            System.out.println("3. Regresar al menú principal");

            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1 -> crearGrupo(scanner);
                case 2 -> verGrupos();
                case 3 -> {
                    return;
                }
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    private static void crearGrupo(Scanner scanner) {
        System.out.println("----- Crear grupo -----");
        System.out.println("ID del grupo: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        System.out.println("Nombre del grupo: ");
        String nombre = scanner.nextLine();

        System.out.println("ID del trainer asignado: ");
        int idTrainer = scanner.nextInt();
        scanner.nextLine(); 

        Trainer trainer = trainers.stream()
            .filter(t -> t.getIdentificacion() == idTrainer)
            .findFirst()
            .orElse(null);

        if (trainer != null) {
            Grupo grupo = new Grupo(id, nombre, trainer);
            grupos.put(id, grupo);
            System.out.println("Grupo creado: " + grupo);
        } else {
            System.out.println("Trainer no encontrado.");
        }
    }

    private static void verGrupos() {
        System.out.println("----- Ver grupos -----");
        for (Grupo grupo : grupos.values()) {
            System.out.println(grupo);
        }
    }

    // Métodos para opciones de Reportes
    private static void listarCampersInscritos() {
        System.out.println("----- Listar campers inscritos -----");
        campers.values().stream()
            .filter(c -> c.getEstado().equals("Inscrito"))
            .forEach(System.out::println);
    }

    private static void listarCampersAprobados() {
        System.out.println("----- Listar campers aprobados -----");
        campers.values().stream()
            .filter(c -> c.getNota() >= 50)
            .forEach(System.out::println);
    }

    private static void listarTrainers() {
        System.out.println("----- Listar trainers -----");
        trainers.forEach(System.out::println);
    }

    private static void listarCampersBajoRendimiento() {
        System.out.println("----- Listar campers con bajo rendimiento -----");
        campers.values().stream()
            .filter(c -> c.getNota() < 50)
            .forEach(System.out::println);
    }

    private static void listarCampersTrainersPorRuta() {
        System.out.println("----- Listar campers y trainers por ruta -----");
        for (Grupo grupo : grupos.values()) {
            System.out.println("Grupo: " + grupo.getNombre());
            System.out.println("  Trainer: " + grupo.getTrainer().getNombre());
            grupo.getCampers().forEach(c -> System.out.println("    Camper: " + c.getNombre()));
        }
    }

    private static void mostrarResultadosModulos() {
        System.out.println("----- Resultados de módulos -----");
        for (Grupo grupo : grupos.values()) {
            System.out.println("Grupo: " + grupo.getNombre());
            System.out.println("  Trainer: " + grupo.getTrainer().getNombre());
            long aprobados = grupo.getCampers().stream()
                .filter(c -> c.getNota() >= 50)
                .count();
            long perdidos = grupo.getCampers().size() - aprobados;
            System.out.println("  Aprobados: " + aprobados);
            System.out.println("  Perdidos: " + perdidos);
        }
    }
}
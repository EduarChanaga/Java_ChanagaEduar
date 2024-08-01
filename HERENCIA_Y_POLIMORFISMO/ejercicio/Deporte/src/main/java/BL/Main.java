package BL;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Partido partido1 = new Partido("EquipoA", "EquipoB", 0, 0, "En juego", "2013-08-12");
        PlayOffs partidoPlayoff = new PlayOffs("EquipoC", "EquipoD", 0, 0, "En juego", "2006-12-08", "Octavos");
        Liga partidoLiga = new Liga("EquipoE", "EquipoF", 0, 0, "En juego", "2023-10-10", 1);

        while (true) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Registrar puntos para el primer partido");
            System.out.println("2. Registrar puntos para el partido PlayOffs");
            System.out.println("3. Registrar puntos para el partido de Liga");
            System.out.println("4. Finalizar el primer partido");
            System.out.println("5. Finalizar el partido PlayOffs");
            System.out.println("6. Finalizar el partido de Liga");
            System.out.println("7. Mostrar información del primer partido");
            System.out.println("8. Mostrar información del partido PlayOffs");
            System.out.println("9. Mostrar información del partido de Liga");
            System.out.println("10. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese puntos para el equipo local:");
                    int puntosLocal1 = scanner.nextInt();
                    System.out.println("Ingrese puntos para el equipo visitante:");
                    int puntosVisitante1 = scanner.nextInt();
                    partido1.registrarPuntos(puntosLocal1, puntosVisitante1);
                    break;
                case 2:
                    System.out.println("Ingrese puntos para el equipo local:");
                    int puntosLocal2 = scanner.nextInt();
                    System.out.println("Ingrese puntos para el equipo visitante:");
                    int puntosVisitante2 = scanner.nextInt();
                    partidoPlayoff.registrarPuntos(puntosLocal2, puntosVisitante2);
                    break;
                case 3:
                    System.out.println("Ingrese puntos para el equipo local:");
                    int puntosLocal3 = scanner.nextInt();
                    System.out.println("Ingrese puntos para el equipo visitante:");
                    int puntosVisitante3 = scanner.nextInt();
                    partidoLiga.registrarPuntos(puntosLocal3, puntosVisitante3);
                    break;
                case 4:
                    partido1.finalizarPartido();
                    System.out.println("Ganador: " + partido1.obtenerGanador());
                    break;
                case 5:
                    partidoPlayoff.finalizarPartido();
                    System.out.println("Ganador: " + partidoPlayoff.obtenerGanador());
                    break;
                case 6:
                    partidoLiga.finalizarPartido();
                    System.out.println("Ganador: " + partidoLiga.obtenerGanador());
                    break;
                case 7:
                    partido1.verInformacion();
                    break;
                case 8:
                    partidoPlayoff.verInformacion();
                    break;
                case 9:
                    partidoLiga.verInformacion();
                    break;
                case 10:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }
}

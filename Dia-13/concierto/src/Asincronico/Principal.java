package Asincronico;
public class Principal {
    public static void main(String[] args) {
        Concierto concierto = new Concierto(100, 10); // 100 entradas disponibles, 10 de overbooking

        // Cantidades diferentes de entradas que cada hilo intentará reservar
        int[] cantidades = {5, 10, 7, 8, 12, 6, 4, 9, 11, 55}; // Por ejemplo, 10 hilos con diferentes cantidades

        // Crear y lanzar hilos
        Thread[] hilos = new Thread[cantidades.length];

        for (int i = 0; i < hilos.length; i++) {
            // Cada hilo intenta reservar una cantidad diferente de entradas
            hilos[i] = new Thread(new TareaReserva(concierto, cantidades[i]), "Hilo-" + (i + 1));
            hilos[i].start();
        }

        // Esperar a que todos los hilos terminen
        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Mostrar resultados finales
        System.out.println("Entradas reservadas finales: " + concierto.obtenerTotalEntradasReservadas());
        System.out.println("Entradas disponibles finales: " + concierto.obtenerEntradasDisponibles());
    }
}

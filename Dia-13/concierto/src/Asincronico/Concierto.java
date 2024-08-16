package Asincronico;
public class Concierto {
    private int availableTickets; // Entradas disponibles
    private int overbookingLimit; // Límite de overbooking
    private int reservedTickets;  // Entradas reservadas

    public Concierto(int totalTickets, int overbookingLimit) {
        this.availableTickets = totalTickets;
        this.overbookingLimit = overbookingLimit;
        this.reservedTickets = 0;
    }

    public synchronized boolean reservarEntradas(int cantidad) {
        if (reservedTickets + cantidad <= availableTickets + overbookingLimit) {
            reservedTickets += cantidad;
            System.out.println(Thread.currentThread().getName() + ": Entradas reservadas con éxito. Entradas reservadas: " + reservedTickets);
            return true;
        } else {
            System.out.println(Thread.currentThread().getName() + ": No se pudieron reservar las entradas. No hay suficientes entradas disponibles.");
            return false;
        }
    }

    public synchronized int obtenerEntradasDisponibles() {
        return (availableTickets + overbookingLimit) - reservedTickets;
    }

    public synchronized int obtenerTotalEntradasReservadas() {
        return reservedTickets;
    }
}

package Asincronico;
public class TareaReserva implements Runnable {
    private Concierto concierto;
    private int cantidad; // Cantidad de entradas a reservar por hilo

    public TareaReserva(Concierto concierto, int cantidad) {
        this.concierto = concierto;
        this.cantidad = cantidad;
    }

    @Override
    public void run() {
        boolean exito = concierto.reservarEntradas(cantidad);
        if (exito) {
            try {
                Thread.sleep(50); // Simula el tiempo de reserva
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

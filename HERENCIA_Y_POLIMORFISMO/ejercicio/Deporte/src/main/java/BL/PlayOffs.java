package BL;

public class PlayOffs extends Partido {
    private String ronda;

    public PlayOffs(String equipoLocal, String equipoVisitante, int cestasLocal, int cestasVisitante, String estado, String fechaPartido, String ronda) {
        super(equipoLocal, equipoVisitante, cestasLocal, cestasVisitante, estado, fechaPartido);
        this.ronda = ronda != null ? ronda : "No registrado";
    }

    @Override
    public void registrarPuntos(int puntosLocal, int puntosVisitante) {
        if (estado.equals("En juego")) {
            if (puntosLocal == puntosVisitante) {
                System.out.println("No se puede finalizar el partido con empate en PlayOffs.");
                return;
            }
            this.cestasLocal = puntosLocal;
            this.cestasVisitante = puntosVisitante;
        } else {
            System.out.println("El partido ya está finalizado.");
        }
    }

    @Override
    public void verInformacion() {
        super.verInformacion();
        System.out.println("Ronda: " + ronda);
    }
}

package BL;

public class Liga extends Partido {
    private int jornada;

    public Liga(String equipoLocal, String equipoVisitante, int cestasLocal, int cestasVisitante, String estado, String fechaPartido, int jornada) {
        super(equipoLocal, equipoVisitante, cestasLocal, cestasVisitante, estado, fechaPartido);
        this.jornada = (jornada >= 0) ? jornada : 0;
    }

    @Override
    public void verInformacion() {
        super.verInformacion();
        System.out.println("Jornada: " + jornada);
    }
}

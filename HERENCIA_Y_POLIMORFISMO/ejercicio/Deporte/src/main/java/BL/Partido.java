package BL;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Partido {
    protected String equipoLocal;
    protected String equipoVisitante;
    protected int cestasLocal;
    protected int cestasVisitante;
    protected String estado; // "En juego" o "Finalizado"
    protected Date fechaPartido;

    public Partido(String equipoLocal, String equipoVisitante, int cestasLocal, int cestasVisitante, String estado, String fechaPartido) {
        this.equipoLocal = equipoLocal != null ? equipoLocal : "No registrado";
        this.equipoVisitante = equipoVisitante != null ? equipoVisitante : "No registrado";
        this.cestasLocal = cestasLocal >= 0 ? cestasLocal : 0;
        this.cestasVisitante = cestasVisitante >= 0 ? cestasVisitante : 0;
        this.estado = estado != null ? estado : "En juego";
        setFechaPartido(fechaPartido);
    }

    public void setFechaPartido(String fechaPartido) {
        if (fechaPartido == null || fechaPartido.isEmpty()) {
            System.out.println("Fecha del partido no proporcionada.");
            this.fechaPartido = null;
            return;
        }
        try {
            DateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd");
            this.fechaPartido = sourceFormat.parse(fechaPartido);
        } catch (ParseException e) {
            System.out.println("Formato de fecha no válido: " + fechaPartido);
            this.fechaPartido = null;
        }
    }

    public void registrarPuntos(int puntosLocal, int puntosVisitante) {
        if (estado.equals("En juego")) {
            this.cestasLocal = puntosLocal;
            this.cestasVisitante = puntosVisitante;
        } else {
            System.out.println("El partido ya está finalizado.");
        }
    }

    public void finalizarPartido() {
        if (estado.equals("En juego")) {
            estado = "Finalizado";
        } else {
            System.out.println("El partido ya está finalizado.");
        }
    }

    public String obtenerGanador() {
        if (!estado.equals("Finalizado")) {
            return "El partido aún no ha finalizado.";
        }
        if (cestasLocal > cestasVisitante) {
            return equipoLocal;
        } else if (cestasVisitante > cestasLocal) {
            return equipoVisitante;
        } else {
            return "Empate";
        }
    }

    public void verInformacion() {
        String fechaStr = (fechaPartido != null) ? new SimpleDateFormat("yyyy-MM-dd").format(fechaPartido) : "No registrado";
        System.out.println("Equipo Local: " + equipoLocal);
        System.out.println("Equipo Visitante: " + equipoVisitante);
        System.out.println("Cestas Local: " + cestasLocal);
        System.out.println("Cestas Visitante: " + cestasVisitante);
        System.out.println("Estado: " + estado);
        System.out.println("Fecha del Partido: " + fechaStr);
    }
}

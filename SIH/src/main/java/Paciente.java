
import java.time.LocalDate;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class Paciente extends Persona {
    private String pabellon;
    private String diagnostico;

    public Paciente(String nombreCompleto, String direccion, LocalDate fechaNacimiento, String pabellon, String diagnostico) {
        super(nombreCompleto, direccion, fechaNacimiento);
        this.pabellon = pabellon;
        this.diagnostico = diagnostico;
    }

    public String getPabellon() {
        return pabellon;
    }

    public String getDiagnostico() {
        return diagnostico;
    }
}

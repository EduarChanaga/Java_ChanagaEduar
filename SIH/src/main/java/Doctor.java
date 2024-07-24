
import java.time.LocalDate;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class Doctor extends PersonalOperaciones {
    private final boolean esDoctorAsociado;
    private final boolean esDoctorJunior;

    public Doctor(String nombreCompleto, String direccion, LocalDate fechaNacimiento, LocalDate fechaVinculacion, double salario, boolean esDoctorAsociado, boolean esDoctorJunior) {
        super(nombreCompleto, direccion, fechaNacimiento, fechaVinculacion, salario);
        this.esDoctorAsociado = esDoctorAsociado;
        this.esDoctorJunior = esDoctorJunior;
    }

    public boolean isEsDoctorAsociado() {
        return esDoctorAsociado;
    }

    public boolean isEsDoctorJunior() {
        return esDoctorJunior;
    }
}

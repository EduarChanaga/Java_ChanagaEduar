
import java.time.LocalDate;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
public class Personal extends Persona {
    private LocalDate fechaVinculacion;
    private double salario;

    public Personal(String nombreCompleto, String direccion, LocalDate fechaNacimiento, LocalDate fechaVinculacion, double salario) {
        super(nombreCompleto, direccion, fechaNacimiento);
        this.fechaVinculacion = fechaVinculacion;
        this.salario = salario;
    }

    public LocalDate getFechaVinculacion() {
        return fechaVinculacion;
    }

    public double getSalario() {
        return salario;
    }
}



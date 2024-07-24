/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.time.LocalDate;

public class Persona {
    private String nombreCompleto;
    private String direccion;
    private LocalDate fechaNacimiento;

    public Persona(String nombreCompleto, String direccion, LocalDate fechaNacimiento) {
        this.nombreCompleto = nombreCompleto;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getDireccion() {
        return direccion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
}

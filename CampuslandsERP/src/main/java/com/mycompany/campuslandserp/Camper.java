/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.campuslandserp;

public class Camper {
    private int identificacion;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String acudiente;
    private int celular;
    private int fijo;
    private String estado;
    private String riesgo;
    private int nota;

    public Camper(int identificacion, String nombre, String apellidos, String direccion, String acudiente, int celular, int fijo, String estado, String riesgo) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.acudiente = acudiente;
        this.celular = celular;
        this.fijo = fijo;
        this.estado = estado;
        this.riesgo = riesgo;
        this.nota = 0;
    }

    @Override
    public String toString() {
        return "Camper{" +
                "identificacion=" + identificacion +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", direccion='" + direccion + '\'' +
                ", acudiente='" + acudiente + '\'' +
                ", celular=" + celular +
                ", fijo=" + fijo +
                ", estado='" + estado + '\'' +
                ", riesgo='" + riesgo + '\'' +
                ", nota=" + nota +
                '}';
    }

    // Getters y Setters
    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getAcudiente() {
        return acudiente;
    }

    public void setAcudiente(String acudiente) {
        this.acudiente = acudiente;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public int getFijo() {
        return fijo;
    }

    public void setFijo(int fijo) {
        this.fijo = fijo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRiesgo() {
        return riesgo;
    }

    public void setRiesgo(String riesgo) {
        this.riesgo = riesgo;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
}
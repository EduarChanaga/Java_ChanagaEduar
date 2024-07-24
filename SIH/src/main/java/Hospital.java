/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.ArrayList;
import java.util.List;

public class Hospital {
    private String nombre;
    private List<Departamento> departamentos = new ArrayList<>();

    public Hospital(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarDepartamento(Departamento departamento) {
        departamentos.add(departamento);
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }
}

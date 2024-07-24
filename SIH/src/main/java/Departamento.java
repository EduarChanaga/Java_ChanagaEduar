/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.ArrayList;
import java.util.List;

public class Departamento {
    private String nombre;
    private List<Personal> personal = new ArrayList<>();

    public Departamento(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarPersonal(Personal personal) {
        this.personal.add(personal);
    }

    public List<Personal> getPersonal() {
        return personal;
    }
}

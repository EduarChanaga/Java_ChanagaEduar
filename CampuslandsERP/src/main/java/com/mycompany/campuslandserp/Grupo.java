/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.campuslandserp;

import java.util.ArrayList;
import java.util.List;

public class Grupo {
    private int id;
    private String nombre;
    private Trainer trainer;
    private List<Camper> campers;

    public Grupo(int id, String nombre, Trainer trainer) {
        this.id = id;
        this.nombre = nombre;
        this.trainer = trainer;
        this.campers = new ArrayList<>();
    }

    public void agregarCamper(Camper camper) {
        campers.add(camper);
    }

    @Override
    public String toString() {
        return "Grupo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", trainer=" + trainer +
                ", campers=" + campers +
                '}';
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public List<Camper> getCampers() {
        return campers;
    }

    public void setCampers(List<Camper> campers) {
        this.campers = campers;
    }
}
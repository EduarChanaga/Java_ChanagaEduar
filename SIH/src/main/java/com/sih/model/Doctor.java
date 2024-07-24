/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sih.model;

public class Doctor extends Personal {
    private String tipoDoctor; // Asociado o Junior
    private int equipoId; // ID del equipo al que pertenece

    public Doctor() {}

    public String getTipoDoctor() {
        return tipoDoctor;
    }

    public void setTipoDoctor(String tipoDoctor) {
        this.tipoDoctor = tipoDoctor;
    }

    public int getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(int equipoId) {
        this.equipoId = equipoId;
    }
}

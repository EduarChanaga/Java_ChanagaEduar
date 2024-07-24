
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class Equipo {
    private Doctor lider;
    private List<Doctor> doctores = new ArrayList<>();

    public Equipo(Doctor lider) {
        this.lider = lider;
    }

    public void agregarDoctor(Doctor doctor) {
        if (doctor.esDoctorJunior()) {
            doctores.add(doctor);
        }
    }

    public Doctor getLider() {
        return lider;
    }

    public List<Doctor> getDoctores() {
        return doctores;
    }
}

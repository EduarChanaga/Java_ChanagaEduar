/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sih.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.sih.model.Persona;

public class PersonaDAO {
    public void addPersona(Persona persona) {
        String sql = "INSERT INTO Persona (nombre, direccion, titulo, nombrePila, segundoNombre, apellidos) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getDireccion());
            stmt.setString(3, persona.getTitulo());
            stmt.setString(4, persona.getNombrePila());
            stmt.setString(5, persona.getSegundoNombre());
            stmt.setString(6, persona.getApellidos());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Otros métodos CRUD (leer, actualizar, eliminar) pueden agregarse aquí
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba;

import MySQL_Conection.ConexionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.ResultSet;

public class Paciente {
    private final ConexionMySQL conexion;

    public Paciente() {
        conexion = new ConexionMySQL();
    }

    public static void Ingresar_paciente() {
    Paciente dao = new Paciente();
    Scanner scanner = new Scanner(System.in);

    System.out.println("Ingreso de datos del paciente:");
    System.out.print("Nombre: ");
    String nombre = scanner.nextLine();
    System.out.print("Apellido: ");
    String apellido = scanner.nextLine();
    System.out.print("Dirección: ");
    String direccion = scanner.nextLine();
    System.out.print("Fecha de nacimiento (YYYY-MM-DD): ");
    String fechaNacimiento = scanner.nextLine();
    System.out.print("Fecha de ingreso (YYYY-MM-DD): ");
    String fechaIngreso = scanner.nextLine();
    Personal.Ver_personal();
    System.out.print("ID del doctor: ");
    int idDoctor = scanner.nextInt();
    scanner.nextLine();
    Pabellon.Ver_pabellon();
    System.out.print("ID del pabellón: ");
    int idPabellon = scanner.nextInt();
    scanner.nextLine();

    // Insertar paciente en la base de datos
    dao.insertarPaciente(nombre, apellido, direccion, fechaNacimiento, fechaIngreso, idDoctor, idPabellon);
}


    public void insertarPaciente(String nombre, String apellido, String direccion, String fechaNacimiento, String fechaIngreso, int idDoctor, int idPabellon) {
    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
        conn = conexion.conectarMySQL();
        String query = "INSERT INTO paciente (nombres, apellidos, direccion, fecha_nacimiento, fecha_ingreso, id_doctor, id_pabellon) VALUES (?, ?, ?, ?, ?, ?, ?)";
        pstmt = conn.prepareStatement(query);
        pstmt.setString(1, nombre);
        pstmt.setString(2, apellido);
        pstmt.setString(3, direccion);
        pstmt.setDate(4, java.sql.Date.valueOf(fechaNacimiento));  // Convertir String a Date
        pstmt.setDate(5, java.sql.Date.valueOf(fechaIngreso));      // Convertir String a Date
        pstmt.setInt(6, idDoctor);
        pstmt.setInt(7, idPabellon);
        pstmt.executeUpdate();
        System.out.println("Paciente agregado correctamente.");
    } catch (SQLException e) {
        System.out.println("Error al agregar paciente: " + e.getMessage());
    } finally {
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                // Manejo de excepción
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // Manejo de excepción
            }
        }
    }
}


    public static void Ver_paciente() {
        Paciente dao = new Paciente();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dao.conexion.conectarMySQL();
            String query = "SELECT * FROM paciente";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            System.out.println("Lista de pacientes:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                int edad = rs.getInt("edad");
                String genero = rs.getString("genero");
                String diagnostico = rs.getString("diagnostico");
                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Apellido: " + apellido + ", Edad: " + edad + ", Genero: " + genero + ", Diagnostico: " + diagnostico);
            }
            System.out.println("----------");
        } catch (SQLException e) {
            System.out.println("Error al recuperar los datos del paciente: " + e.getMessage());
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar el PreparedStatement: " + e.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }

    public static void Eliminar_paciente() {
        Paciente.Ver_paciente();
        Paciente dao = new Paciente();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el ID del paciente a eliminar: ");
        int id = scanner.nextInt();

        dao.eliminarPaciente(id);
    }

    public void eliminarPaciente(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = conexion.conectarMySQL();
            String query = "DELETE FROM paciente WHERE id = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            int i = 0;
            while (i != 10){
                System.out.println("");
                i = 1 + i;
            }

            if (rowsAffected > 0) {
                System.out.println("Paciente eliminado correctamente.");
            } else {
                System.out.println("No se encontró el paciente con el ID especificado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el paciente: " + e.getMessage());
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar el PreparedStatement: " + e.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }
}

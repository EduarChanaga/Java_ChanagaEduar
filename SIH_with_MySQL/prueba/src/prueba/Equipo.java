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

public class Equipo {
    private final ConexionMySQL conexion;

    public Equipo() {
        conexion = new ConexionMySQL();
    }

    public static void Ingresar_equipo() {
        Equipo dao = new Equipo();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingresar datos del equipo:");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();

        dao.insertarEquipo(nombre, descripcion);
    }

    public void insertarEquipo(String nombre, String descripcion) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = conexion.conectarMySQL();
            String query = "INSERT INTO equipo (nombre, descripcion) VALUES (?, ?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nombre);
            pstmt.setString(2, descripcion);
            pstmt.executeUpdate();
            System.out.println("Equipo agregado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al agregar equipo: " + e.getMessage());
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public static void Ver_equipo() {
        Equipo dao = new Equipo();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dao.conexion.conectarMySQL();
            String query = "SELECT * FROM equipo";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            System.out.println("Lista de equipos:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Descripción: " + descripcion);
            }
            System.out.println("----------");
        } catch (SQLException e) {
            System.out.println("Error al recuperar los datos del equipo: " + e.getMessage());
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

    public static void Eliminar_equipo() {
        Equipo.Ver_equipo();
        Equipo dao = new Equipo();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el ID del equipo a eliminar: ");
        int id = scanner.nextInt();

        dao.eliminarEquipo(id);
    }

    public void eliminarEquipo(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = conexion.conectarMySQL();
            String query = "DELETE FROM equipo WHERE id = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            int i = 0;
            while (i != 10){
                System.out.println("");
                i = 1 + i;
            }

            if (rowsAffected > 0) {
                System.out.println("Equipo eliminado correctamente.");
            } else {
                System.out.println("No se encontró el equipo con el ID especificado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el equipo: " + e.getMessage());
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

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

public class Pabellon {
    private final ConexionMySQL conexion;

    public Pabellon() {
        conexion = new ConexionMySQL();
    }

    public static void Ingresar_pabellon() {
        Pabellon dao = new Pabellon();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingresar datos del pabellon:");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ubicación: ");
        String ubicacion = scanner.nextLine();

        dao.insertarPabellon(nombre, ubicacion);
    }

    public void insertarPabellon(String nombre, String ubicacion) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = conexion.conectarMySQL();
            String query = "INSERT INTO pabellon (nombre, ubicacion) VALUES (?, ?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nombre);
            pstmt.setString(2, ubicacion);
            pstmt.executeUpdate();
            System.out.println("Pabellon agregado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al agregar pabellon: " + e.getMessage());
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

    public static void Ver_pabellon() {
        Pabellon dao = new Pabellon();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dao.conexion.conectarMySQL();
            String query = "SELECT * FROM pabellon";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            System.out.println("Lista de pabellones:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String ubicacion = rs.getString("ubicacion");
                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Ubicación: " + ubicacion);
            }
            System.out.println("----------");
        } catch (SQLException e) {
            System.out.println("Error al recuperar los datos del pabellon: " + e.getMessage());
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

    public static void Eliminar_pabellon() {
        Pabellon.Ver_pabellon();
        Pabellon dao = new Pabellon();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el ID del pabellon a eliminar: ");
        int id = scanner.nextInt();

        dao.eliminarPabellon(id);
    }

    public void eliminarPabellon(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = conexion.conectarMySQL();
            String query = "DELETE FROM pabellon WHERE id = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            int i = 0;
            while (i != 10){
                System.out.println("");
                i = 1 + i;
            }

            if (rowsAffected > 0) {
                System.out.println("Pabellon eliminado correctamente.");
            } else {
                System.out.println("No se encontró el pabellon con el ID especificado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el pabellon: " + e.getMessage());
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

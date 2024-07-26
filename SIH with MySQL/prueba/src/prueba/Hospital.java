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
/**
 *
 * @author camper
 */
public class Hospital {
    private final ConexionMySQL conexion;
    public Hospital() {
        conexion = new ConexionMySQL();
    }

        
    public static void Ingresar_hospital() {
        Hospital dao = new Hospital();
        Scanner scanner = new Scanner(System.in);
   
            System.out.println("Ingresar datos del hospital:");
            System.out.print("Nombre del hospital: ");
            String nombre = scanner.nextLine();
            
            System.out.print("Direccion: ");
            String direccion = scanner.nextLine();
            int i = 0;
            while (i != 10){
                    System.out.println("");
                    i = 1 + i;
                }
            
           
            dao.insertarHospital(nombre, direccion);
            // Cerramos el scanner al finalizar
 
    }
    
    
    public void insertarHospital(String nombre, String direccion) {
        Connection conn = null;
        PreparedStatement pstmt = null;
 
        try {
            conn = conexion.conectarMySQL();
            String query = "INSERT INTO hospital (nombre,direccion) VALUES (?,?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nombre);
            pstmt.setString(2, direccion);
            pstmt.executeUpdate();
            System.out.println("Hospital agregado correctamente.");
        } catch (SQLException e) {
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





public static void Ver_hospital() {
        Hospital dao = new Hospital();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dao.conexion.conectarMySQL();
            String query = "SELECT * FROM hospital";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            System.out.println("Lista de hospitales:");
            while (rs.next()) {
                int id = rs.getInt("id"); 
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Dirección: " + direccion);
            }
            System.out.println("----------");
        } catch (SQLException e) {
            System.out.println("Error al recuperar los datos del hospital: " + e.getMessage());
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
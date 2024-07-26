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
/**
 *
 * @author camper
 */
public class Hospital {
    private final ConexionMySQL conexion;
    public Hospital() {
        conexion = new ConexionMySQL();
    }

        
    public static void HOSPITAL() {
        Hospital dao = new Hospital();
        Scanner scanner = new Scanner(System.in);
   
            System.out.println("Ingresar datos del hospital:");
            System.out.print("Nombre del hospital: ");
            String nombre = scanner.nextLine();
            
            System.out.print("Direccion: ");
            String direccion = scanner.nextLine();
            
           
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
}

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
public class Departamento {
    private final ConexionMySQL conexion;
    public Departamento() {
        conexion = new ConexionMySQL();
    }

        
    public static void Ingresar_departamento() {
        Departamento dao = new Departamento();
        Scanner scanner = new Scanner(System.in);
   
            System.out.println("Ingresar datos del departamento:");
            Hospital.Ver_hospital();
            
            System.out.print("id del hospital: ");
            int hospital = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("Nombre del departamento: ");
            String nombre = scanner.nextLine();
            

            int i = 0;
            while (i != 10){
                    System.out.println("");
                    i = 1 + i;
                }
            
           
            dao.insertarDepartamento(hospital,nombre);
            // Cerramos el scanner al finalizar
 
    }
    
    
    public void insertarDepartamento(int hospital, String nombre) {
        Connection conn = null;
        PreparedStatement pstmt = null;
 
        try {
            conn = conexion.conectarMySQL();
            String query = "INSERT INTO departamento (id_hospital,nombre) VALUES (?,?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, hospital);
            pstmt.setString(2, nombre);
            pstmt.executeUpdate();
            System.out.println("Departamento agregado correctamente.");
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



public static void Ver_departamento() {
        Departamento dao = new Departamento();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dao.conexion.conectarMySQL();
            String query = "SELECT * FROM departamento";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            System.out.println("Lista de departamentos:");
            while (rs.next()) {
                int id = rs.getInt("id"); 
                int id_hospital = rs.getInt("id_hospital"); 
                String nombre = rs.getString("nombre");
                System.out.println("ID: " + id + ",Hospital:" + id_hospital + ", Nombre: " + nombre);
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

public static void Eliminar_departamento() {
        Departamento.Ver_departamento();
        Departamento dao = new Departamento();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el ID del departamento a eliminar: ");
        int id = scanner.nextInt();

        dao.eliminarDepartamento(id);
    }

    public void eliminarDepartamento(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = conexion.conectarMySQL();
            String query = "DELETE FROM departamento WHERE id = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            int i = 0;
            while (i != 10){
                    System.out.println("");
                    i = 1 + i;
                }

            if (rowsAffected > 0) {
                System.out.println("Departamento eliminado correctamente.");
            } else {
                System.out.println("No se encontró el Departamento con el ID especificado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el Departamento: " + e.getMessage());
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




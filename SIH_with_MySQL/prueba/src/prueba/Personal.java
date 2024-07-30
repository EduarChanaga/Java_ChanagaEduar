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

public class Personal {
    private final ConexionMySQL conexion;

    public Personal() {
        conexion = new ConexionMySQL();
    }

    public static void Ingresar_personal() {
        Personal dao = new Personal();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingresar datos del personal:");
        Departamento.Ver_departamento();

        System.out.print("ID del departamento: ");
        int departamento = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Fecha de vinculación (YYYY-MM-DD): ");
        String fechaVinculacion = scanner.nextLine();

        System.out.print("Nombres: ");
        String nombres = scanner.nextLine();

        System.out.print("Apellidos: ");
        String apellidos = scanner.nextLine();

        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();

        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Tipo (administrativo/tecnico/operaciones): ");
        String tipo = scanner.nextLine();

        System.out.print("Salario: ");
        double salario = scanner.nextDouble();

        dao.insertarPersonal(departamento, fechaVinculacion, nombres, apellidos, direccion, titulo, tipo, salario);
    }

    public void insertarPersonal(int departamento, String fechaVinculacion, String nombres, String apellidos, String direccion, String titulo, String tipo, double salario) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = conexion.conectarMySQL();
            String query = "INSERT INTO personal (id_departamento, fecha_vinculacion, nombres, apellidos, direccion, titulo, tipo, salario) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, departamento);
            pstmt.setString(2, fechaVinculacion);
            pstmt.setString(3, nombres);
            pstmt.setString(4, apellidos);
            pstmt.setString(5, direccion);
            pstmt.setString(6, titulo);
            pstmt.setString(7, tipo);
            pstmt.setDouble(8, salario);
            pstmt.executeUpdate();
            System.out.println("Personal agregado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al agregar personal: " + e.getMessage());
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

    public static void Ver_personal() {
        Personal dao = new Personal();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dao.conexion.conectarMySQL();
            String query = "SELECT * FROM personal";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            System.out.println("Lista de personal:");
            while (rs.next()) {
                int id = rs.getInt("id");
                int id_departamento = rs.getInt("id_departamento");
                String fechaVinculacion = rs.getString("fecha_vinculacion");
                String nombres = rs.getString("nombres");
                String apellidos = rs.getString("apellidos");
                String direccion = rs.getString("direccion");
                String titulo = rs.getString("titulo");
                String tipo = rs.getString("tipo");
                double salario = rs.getDouble("salario");
                System.out.println("ID: " + id + ", Departamento: " + id_departamento + ", Fecha Vinculación: " + fechaVinculacion + ", Nombres: " + nombres + ", Apellidos: " + apellidos + ", Dirección: " + direccion + ", Título: " + titulo + ", Tipo: " + tipo + ", Salario: " + salario);
            }
            System.out.println("----------");
        } catch (SQLException e) {
            System.out.println("Error al recuperar los datos del personal: " + e.getMessage());
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

    public static void Eliminar_personal() {
        Personal.Ver_personal();
        Personal dao = new Personal();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el ID del personal a eliminar: ");
        int id = scanner.nextInt();

        dao.eliminarPersonal(id);
    }

    public void eliminarPersonal(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = conexion.conectarMySQL();
            String query = "DELETE FROM personal WHERE id = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            int i = 0;
            while (i != 10){
                System.out.println("");
                i = 1 + i;
            }

            if (rowsAffected > 0) {
                System.out.println("Personal eliminado correctamente.");
            } else {
                System.out.println("No se encontró el personal con el ID especificado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el personal: " + e.getMessage());
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

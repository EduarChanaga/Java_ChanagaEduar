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

public class EquipoPersonal {
    private final ConexionMySQL conexion;

    public EquipoPersonal() {
        conexion = new ConexionMySQL();
    }

    public static void Ingresar_equipoPersonal() {
        EquipoPersonal dao = new EquipoPersonal();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingresar datos de equipo_personal:");
        Equipo.Ver_equipo();
        Personal.Ver_personal();

        System.out.print("ID del equipo: ");
        int id_equipo = scanner.nextInt();

        System.out.print("ID del personal: ");
        int id_personal = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Fecha de asignación (YYYY-MM-DD): ");
        String fechaAsignacion = scanner.nextLine();

        dao.insertarEquipoPersonal(id_equipo, id_personal, fechaAsignacion);
    }

    public void insertarEquipoPersonal(int id_equipo, int id_personal, String fechaAsignacion) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = conexion.conectarMySQL();
            String query = "INSERT INTO equipo_personal (id_equipo, id_personal, fecha_asignacion) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id_equipo);
            pstmt.setInt(2, id_personal);
            pstmt.setString(3, fechaAsignacion);
            pstmt.executeUpdate();
            System.out.println("EquipoPersonal agregado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al agregar equipo_personal: " + e.getMessage());
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

    public static void Ver_equipoPersonal() {
        EquipoPersonal dao = new EquipoPersonal();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dao.conexion.conectarMySQL();
            String query = "SELECT * FROM equipo_personal";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            System.out.println("Lista de equipo_personal:");
            while (rs.next()) {
                int id = rs.getInt("id");
                int id_equipo = rs.getInt("id_equipo");
                int id_personal = rs.getInt("id_personal");
                String fechaAsignacion = rs.getString("fecha_asignacion");
                System.out.println("ID: " + id + ", ID Equipo: " + id_equipo + ", ID Personal: " + id_personal + ", Fecha Asignación: " + fechaAsignacion);
            }
            System.out.println("----------");
        } catch (SQLException e) {
            System.out.println("Error al recuperar los datos de equipo_personal: " + e.getMessage());
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

    public static void Eliminar_equipoPersonal() {
        EquipoPersonal.Ver_equipoPersonal();
        EquipoPersonal dao = new EquipoPersonal();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el ID de equipo_personal a eliminar: ");
        int id = scanner.nextInt();

        dao.eliminarEquipoPersonal(id);
    }

    public void eliminarEquipoPersonal(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = conexion.conectarMySQL();
            String query = "DELETE FROM equipo_personal WHERE id = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            int i = 0;
            while (i != 10){
                System.out.println("");
                i = 1 + i;
            }

            if (rowsAffected > 0) {
                System.out.println("EquipoPersonal eliminado correctamente.");
            } else {
                System.out.println("No se encontró equipo_personal con el ID especificado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar equipo_personal: " + e.getMessage());
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

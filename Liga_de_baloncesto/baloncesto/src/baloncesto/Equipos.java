package baloncesto;

import MySQL_Conection.ConexionMySQL;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Equipos {
    private final ConexionMySQL conexion;
    private final Scanner scanner;

    public Equipos() {
        this.conexion = new ConexionMySQL();
        this.scanner = new Scanner(System.in);
    }

    public void crearEquipo() {
        System.out.print("Ingrese el nombre del equipo: ");
        String nombre = scanner.nextLine();

        String sql = "INSERT INTO equipos (nombre) VALUES (?)";

        try {
            Connection conn = conexion.conectarMySQL();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nombre);
            pstmt.executeUpdate();
            System.out.println("Equipo agregado correctamente");
        } catch (SQLException e) {
  
            
        }
    }
    
    public void verEquipos() {
        String sql = "select * from equipos";
        try {
            Connection conn = conexion.conectarMySQL();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                System.out.println("ID: " + id + ", Nombre: " + nombre);
            }

    
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error en la vista de los datos");
          
        }
    }

}
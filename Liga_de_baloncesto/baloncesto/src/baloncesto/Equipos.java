package baloncesto;

import MySQL_Conection.ConexionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

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
}

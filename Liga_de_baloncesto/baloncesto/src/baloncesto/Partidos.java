package baloncesto;

import MySQL_Conection.ConexionMySQL;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Partidos {
    Equipos equipos = new Equipos();
    
    private final ConexionMySQL conexion;
    private final Scanner scanner;

    public Partidos() {
        this.conexion = new ConexionMySQL();
        this.scanner = new Scanner(System.in);
    }

    
    public void crearPartido() {
        System.out.println("Equipos: ");
        equipos.verEquipos();
        System.out.print("Ingrese el id del equipo local: ");
        int equipo_local = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Ingrese el id del equipo visitante: ");
        int equipo_visitante = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Ingrese el año del partido: ");
        int año = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Ingrese el mes del partido (numero): ");
        int mes = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Ingrese el dia del partido: ");
        int dia = scanner.nextInt();
        scanner.nextLine();
        
        String fecha = año + "-" + mes + "-" + dia;

        String sql = "INSERT INTO partidos (id_equipo_local,id_equipo_visitante,fecha) VALUES (?,?,?)";

        try {
            Connection conn = conexion.conectarMySQL();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, equipo_local);
            pstmt.setInt(2, equipo_visitante);
            pstmt.setString(3, fecha);
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
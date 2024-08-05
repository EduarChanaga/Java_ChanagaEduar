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
        
        int equipo_visitante = equipo_local;
        while (equipo_local == equipo_visitante) {
            System.out.print("Ingrese el id del equipo visitante: ");
            equipo_visitante = scanner.nextInt();
            scanner.nextLine();
        }
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
            System.out.println("Partido agregado correctamente");
            
        } catch (SQLException e) {
  
            
        }
    }
    
    
    
    
    public void verPartidos() {
        String sql = "select p.id as id_partido, p.id_equipo_local as id_local, e.nombre as equipo_local, p.id_equipo_visitante as id_visitante, eq.nombre as equipo_visitante, p.fecha as fecha from partidos p join equipos e on e.id = p.id_equipo_local join equipos eq on eq.id = p.id_equipo_visitante;";
        try {
            Connection conn = conexion.conectarMySQL();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id_partido");
                int id_local = rs.getInt("id_local");
                String nombre_local = rs.getString("equipo_local");
                
   
                int id_visitante = rs.getInt("id_visitante");
                String nombre_visitante = rs.getString("equipo_visitante");
                
                String fecha = rs.getString("fecha");
                
                System.out.println("----------");
                System.out.println("Partido (id): " + id);
                System.out.println("Equipo local"); 
                System.out.println("ID: " + id_local + ", Nombre:" + nombre_local);
                System.out.println("");
                System.out.println("Equipo visitante");
                System.out.println("ID: " + id_visitante + ", Nombre:" + nombre_visitante);
                System.out.println("");
                System.out.println("Fecha: "+fecha);
            }

            System.out.println("----------");
            System.out.println("Enter para salir");
            scanner.nextInt();
            scanner.nextLine();
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error en la vista de los datos");
          
        }
    }

}
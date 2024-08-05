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
        System.out.println("Tipo de partido: ");
        System.out.println("1 - Liga ");
        System.out.println("2 - Playoffs");
        int tipo = scanner.nextInt();
        scanner.nextLine();
        
        String tipo_partido = "";
        if (tipo == 1){
        tipo_partido = "Liga";
        }
        else if(tipo ==2){
        tipo_partido = "playoffs";
        }
        
        
        String fecha = año + "-" + mes + "-" + dia;
        String estado = "Pendiente";
        String sql = "INSERT INTO partidos (id_equipo_local,id_equipo_visitante,fecha,estado_del_partido,tipo) VALUES (?,?,?,?,?)";

        try {
            Connection conn = conexion.conectarMySQL();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, equipo_local);
            pstmt.setInt(2, equipo_visitante);
            pstmt.setString(3, fecha);
            pstmt.setString(4, estado);
            pstmt.setString(5, tipo_partido);
            pstmt.executeUpdate();
            System.out.println("Partido agregado correctamente");
            
        } catch (SQLException e) {
  
            
        }
    }
    
    
    
    
    public void verPartidos() {
        String sql = "select p.id as id_partido, p.id_equipo_local as id_local, e.nombre as equipo_local, p.id_equipo_visitante as id_visitante, eq.nombre as equipo_visitante, p.fecha as fecha, p.estado_del_partido as estado_partido, p.tipo as tipo_partido from partidos p join equipos e on e.id = p.id_equipo_local join equipos eq on eq.id = p.id_equipo_visitante;";
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
                
                String estado = rs.getString("estado_partido");
                
                String tipo = rs.getString("tipo_partido");
                
                System.out.println("----------");
                System.out.println("Partido (id): " + id);
                System.out.println("Equipo local"); 
                System.out.println("ID: " + id_local + ", Nombre:" + nombre_local);
                System.out.println("");
                System.out.println("Equipo visitante");
                System.out.println("ID: " + id_visitante + ", Nombre:" + nombre_visitante);
                System.out.println("");
                System.out.println("Fecha: "+fecha);
                System.out.println("Estado: "+estado);
                System.out.println("Tipo: "+tipo);
            }

            System.out.println("----------");
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error en la vista de los datos");
          
        }
    }

    
    public void comenzarPartido(){
        System.out.println("----------");
        verPartidos();
        System.out.print("ID del partido a comenzar: ");
        int partido = scanner.nextInt();
        scanner.nextLine();
        
        int cestas_local = 0;
        int cestas_visitante = 0;
        
        boolean cestas = false;
        System.out.println("Registro de cestas:");
        while (cestas == false){
            System.out.println("----------------------");
            System.out.println("Quien realizo cesta? ");
            System.out.println("1. Equipo local");
            System.out.println("2. Equipo visitante");
            System.out.println("3. Finalizar partido");
            int cesta = scanner.nextInt();
            scanner.nextLine();
            
            switch (cesta) {
                case 1 -> cestas_local = cestas_local + 1;
                case 2 -> cestas_visitante = cestas_visitante + 1;
                case 3 -> cestas = true;
                default -> {
                }
            }
        }
        String ganador = "";
        
        if (cestas_local>cestas_visitante){
            ganador = "Local";
        }
        else if (cestas_visitante > cestas_local){
            ganador = "Visitante";
        }
        else if (cestas_local == cestas_visitante){
            ganador = "Empate";
        }   
        
        System.out.println("----------------------");
            System.out.println("----- Resultados -----");
            System.out.println("Equipo local: "+ cestas_local);
            System.out.println("Equipo visitante: " + cestas_visitante);
            
        String sql = "INSERT INTO info_partido (partido,cestas_equipo_local,cestas_equipo_visitante,ganador) VALUES (?,?,?,?)";

        try {
            Connection conn = conexion.conectarMySQL();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, partido);
            pstmt.setInt(2, cestas_local);
            pstmt.setInt(3, cestas_visitante);
            pstmt.setString(4, ganador);
            pstmt.executeUpdate();
            System.out.println("Partido agregado correctamente");
            
        } catch (SQLException e) {
            System.out.println("No se a podido guardar el registro del partido");
        }
    }
}
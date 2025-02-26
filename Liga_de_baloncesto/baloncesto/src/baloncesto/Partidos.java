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

    
public void comenzarPartido() {
    System.out.println("----------");
    verPartidos();
    System.out.print("ID del partido a comenzar: ");
    int partido = scanner.nextInt();
    scanner.nextLine();

    // Verificar si el partido ya está finalizado
    String estadoPartido = "";
    String sqlEstadoPartido = "SELECT estado_del_partido FROM partidos WHERE id = ?";
    try {
        Connection conn = conexion.conectarMySQL();
        PreparedStatement pstmtEstado = conn.prepareStatement(sqlEstadoPartido);
        pstmtEstado.setInt(1, partido);
        ResultSet rsEstado = pstmtEstado.executeQuery();
        if (rsEstado.next()) {
            estadoPartido = rsEstado.getString("estado_del_partido");
        }
        rsEstado.close();
        pstmtEstado.close();
        conn.close();
    } catch (SQLException e) {
        System.out.println("Error al obtener el estado del partido");
        return;
    }

    if ("Finalizado".equalsIgnoreCase(estadoPartido)) {
        System.out.println("El partido ya está finalizado. No se puede comenzar un partido que ya ha sido finalizado.");
        return;
    }

    int cestas_local = 0;
    int cestas_visitante = 0;
    
    // Obtener el tipo de partido (Liga o Playoffs) para saber si es necesario hacer algo específico
    String tipoPartido = "";
    String sqlTipoPartido = "SELECT tipo FROM partidos WHERE id = ?";
    try {
        Connection conn = conexion.conectarMySQL();
        PreparedStatement pstmtTipo = conn.prepareStatement(sqlTipoPartido);
        pstmtTipo.setInt(1, partido);
        ResultSet rsTipo = pstmtTipo.executeQuery();
        if (rsTipo.next()) {
            tipoPartido = rsTipo.getString("tipo");
        }
        rsTipo.close();
        pstmtTipo.close();
        conn.close();
    } catch (SQLException e) {
        System.out.println("Error al obtener el tipo de partido");
        return;
    }

    // Variable para manejar el estado de los cestas
    boolean terminado = false;

    System.out.println("Registro de cestas:");
    while (!terminado) {
        System.out.println("----------------------");
        System.out.println("¿Quién realizó la cesta?");
        System.out.println("1. Equipo local");
        System.out.println("2. Equipo visitante");
        System.out.println("3. Finalizar partido");
        int cesta = scanner.nextInt();
        scanner.nextLine();
        
        switch (cesta) {
            case 1 -> cestas_local++;
            case 2 -> cestas_visitante++;
            case 3 -> {
                // Solo permitir finalizar el partido si no es de playoffs o si hay un claro ganador
                if ("playoffs".equalsIgnoreCase(tipoPartido)) {
                    if (cestas_local == cestas_visitante) {
                        System.out.println("El partido está empatado. No se puede finalizar el partido en empate durante los playoffs.");
                        System.out.println("Continúe registrando cestas hasta que haya un ganador.");
                    } else {
                        terminado = true;
                    }
                } else {
                    terminado = true;
                }
            }
            default -> System.out.println("Opción no válida.");
        }
    }

    // Determinar el ganador final
    String ganador;
    if (cestas_local > cestas_visitante) {
        ganador = "Local";
    } else {
        ganador = "Visitante";
    }

    System.out.println("----------------------");
    System.out.println("----- Resultados -----");
    System.out.println("Equipo local: " + cestas_local);
    System.out.println("Equipo visitante: " + cestas_visitante);
    System.out.println("Ganador: " + ganador);

    // Guardar los resultados en la base de datos
    String sqlInsertInfo = "INSERT INTO info_partido (partido, cestas_equipo_local, cestas_equipo_visitante, ganador) VALUES (?,?,?,?)";
    String sqlUpdateEstado = "UPDATE partidos SET estado_del_partido = 'Finalizado' WHERE id = ?";

    try {
        Connection conn = conexion.conectarMySQL();
        conn.setAutoCommit(false); // Iniciar la transacción

        // Insertar la información del partido
        PreparedStatement pstmtInsert = conn.prepareStatement(sqlInsertInfo);
        pstmtInsert.setInt(1, partido);
        pstmtInsert.setInt(2, cestas_local);
        pstmtInsert.setInt(3, cestas_visitante);
        pstmtInsert.setString(4, ganador);
        pstmtInsert.executeUpdate();

        // Actualizar el estado del partido a Finalizado
        PreparedStatement pstmtUpdate = conn.prepareStatement(sqlUpdateEstado);
        pstmtUpdate.setInt(1, partido);
        pstmtUpdate.executeUpdate();

        conn.commit(); // Confirmar la transacción

        System.out.println("Partido finalizado correctamente");

        pstmtInsert.close();
        pstmtUpdate.close();
        conn.close();
    } catch (SQLException e) {
        System.out.println("No se ha podido guardar el registro del partido o actualizar el estado");
        e.printStackTrace();
        try {
            if (conexion.conectarMySQL() != null) {
                conexion.conectarMySQL().rollback(); // Deshacer la transacción en caso de error
            }
        } catch (SQLException rollbackEx) {
            System.out.println("Error al deshacer la transacción");
        }
    }
}


public void verResultados() {
    verPartidos();
    int partido;
    System.out.println("De cual partido quiere ver los resultados?");
    partido = scanner.nextInt();
    scanner.nextLine();
    
    // Actualizamos la consulta SQL para obtener la información correcta
    String sql = "SELECT ip.partido, ip.cestas_equipo_local, ip.cestas_equipo_visitante, ip.ganador " +
                 "FROM info_partido ip WHERE ip.partido = ?";

    try {
        Connection conn = conexion.conectarMySQL();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, partido); // Se establece el parámetro de la consulta

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            // Extraemos los datos del ResultSet según la consulta SQL
            String partidoNombre = rs.getString("partido");
            int cestasEquipoLocal = rs.getInt("cestas_equipo_local");
            int cestasEquipoVisitante = rs.getInt("cestas_equipo_visitante");
            String ganador = rs.getString("ganador");

            // Mostramos los resultados
            System.out.println("----------");
            System.out.println("Partido: " + partidoNombre);
            System.out.println("Cestas equipo local: " + cestasEquipoLocal);
            System.out.println("Cestas equipo visitante: " + cestasEquipoVisitante);
            System.out.println("Ganador: " + ganador);
        }

        System.out.println("----------");
        rs.close();
        pstmt.close();
        conn.close();

    } catch (SQLException e) {
        System.out.println("Error en la vista de los datos");
        e.printStackTrace(); // Es útil para depurar el error
    }
}

}



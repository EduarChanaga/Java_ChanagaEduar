/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package baloncesto;

import MySQL_Conection.ConexionMySQL;
import java.sql.Connection;

public class Baloncesto {

    public static void main(String[] args) {
    ConexionMySQL conexion = new ConexionMySQL();
    Connection conn = conexion.conectarMySQL();
    if (conn != null) {
        System.out.println("Conexión establecida correctamente");
        
        Equipos equipos = new Equipos();
        equipos.verEquipos();
        
 
        equipos.crearEquipo();
        
    } else {
        System.out.println("No se pudo establecer la conexión");
    }
}

}

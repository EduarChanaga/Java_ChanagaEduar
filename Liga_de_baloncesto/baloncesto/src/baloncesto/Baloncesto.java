/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package baloncesto;

import MySQL_Conection.ConexionMySQL;
import java.sql.Connection;
import java.util.Scanner;

public class Baloncesto {
    private final Scanner scanner;

    public Baloncesto() {
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        boolean bucle = true;
        while (bucle != false){
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.conectarMySQL();
        if (conn != null) {
            
            /* System.out.println("Conexión establecida correctamente"); */

            Equipos equipos = new Equipos();
            Baloncesto baloncesto = new Baloncesto(); 
            Partidos partidos = new Partidos();
            
            System.out.println("Opciones de:");
            System.out.println("1 - Equipos");
            System.out.println("2 - Partidos");
            int op1 = baloncesto.scanner.nextInt();
            baloncesto.scanner.nextLine();
            
            if (op1 == 1){
                System.out.println("Opciones de equipo: ");
            System.out.println("1 - Ver equipos disponibles. ");
            System.out.println("2 - Agregar equipo. ");
            int nombre = baloncesto.scanner.nextInt();
            baloncesto.scanner.nextLine(); 

            if (nombre == 1) { 
                equipos.verEquipos();
            }
            else if (nombre ==2){
                equipos.crearEquipo();
            }
            
           } 
            
            
            
            if (op1 == 2){
                System.out.println("Opciones de equipo: ");
            System.out.println("1 - Ver partidos. ");
            System.out.println("2 - Agregar partido. ");
            System.out.println("3 - Comenzar partido. ");
            int nombre = baloncesto.scanner.nextInt();
            baloncesto.scanner.nextLine(); 

                switch (nombre) {
                    case 1 -> partidos.verPartidos();
                    case 2 -> partidos.crearPartido();
                    case 3 -> partidos.comenzarPartido();
                    default -> {
                    }
                }
            
           } 

        } else {
            System.out.println("No se pudo establecer la conexión");
        }
    }
  }
}
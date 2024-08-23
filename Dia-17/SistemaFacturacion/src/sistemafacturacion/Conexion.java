/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemafacturacion;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {
    Connection con;
    
    public Connection establecerConexion(){
        Properties props = new Properties();
        
        try(InputStream input = getClass().getClassLoader().getResourceAsStream("Config.properties")){
            if(input == null){
                throw new IllegalStateException("Archivo Config.properties no encontrado");
            }
            props.load(input);
            
            String url = props.getProperty("Url");
            String user = props.getProperty("User");
            String password = props.getProperty("Password");
            
            if(url == null || user == null || password == null){
                throw new IllegalStateException("Uno o mas propiedades no se encuentran definidas");
            }
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            
        } catch(IOException | ClassNotFoundException | SQLException | IllegalStateException e) {
            System.out.println("Error en la conexión :(, error: " + e);
        }
        return con;
    }
}

package sistemafacturacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Cliente {
    private int id;
    private String nombre;
    private String tipo;

    public Cliente(int id, String nombre, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public static Cliente obtenerClientePorId(int id) {
        String query = "SELECT * FROM Cliente WHERE id = ?";
        try (Connection conn = new Conexion().establecerConexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cliente(rs.getInt("id"), rs.getString("nombre"), rs.getString("tipo"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el cliente: " + e.getMessage());
        }
        return null;
    }

    public void insertarCliente() {
        String query = "INSERT INTO Cliente (nombre, tipo) VALUES (?, ?)";
        try (Connection conn = new Conexion().establecerConexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, this.nombre);
            stmt.setString(2, this.tipo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar el cliente: " + e.getMessage());
        }
    }

    public static void listarClientes() {
        String query = "SELECT * FROM Cliente";
        try (Connection conn = new Conexion().establecerConexion();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            System.out.println("Lista de Clientes:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Nombre: " + rs.getString("nombre") + ", Tipo: " + rs.getString("tipo"));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar clientes: " + e.getMessage());
        }
    }
}

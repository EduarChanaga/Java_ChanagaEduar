package sistemafacturacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Producto {
    private int id;
    private String nombre;
    private double precio;

    public Producto(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public static Producto obtenerProductoPorId(int id) {
        String query = "SELECT * FROM Producto WHERE id = ?";
        try (Connection conn = new Conexion().establecerConexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Producto(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el producto: " + e.getMessage());
        }
        return null;
    }

    public void insertarProducto() {
        String query = "INSERT INTO Producto (nombre, precio) VALUES (?, ?)";
        try (Connection conn = new Conexion().establecerConexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, this.nombre);
            stmt.setDouble(2, this.precio);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar el producto: " + e.getMessage());
        }
    }

    public static void listarProductos() {
        String query = "SELECT * FROM Producto";
        try (Connection conn = new Conexion().establecerConexion();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            System.out.println("Lista de Productos:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Nombre: " + rs.getString("nombre") + ", Precio: $" + rs.getDouble("precio"));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar productos: " + e.getMessage());
        }
    }
}


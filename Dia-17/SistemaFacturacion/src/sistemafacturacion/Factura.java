package sistemafacturacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Factura {
    private int id;
    private Cliente cliente;
    private List<DetalleFactura> detalles;
    private double total;
    private double totalConDescuento;

    public Factura(Cliente cliente) {
        this.cliente = cliente;
        this.detalles = new ArrayList<>();
        this.total = 0;
        this.totalConDescuento = 0;
    }

    public void agregarProducto(Producto producto, int cantidad) {
        DetalleFactura detalle = new DetalleFactura(producto, cantidad);
        detalles.add(detalle);
        total += producto.getPrecio() * cantidad;

        String query = "INSERT INTO Factura_Producto (factura_id, producto_id, cantidad) VALUES (?, ?, ?)";
        try (Connection conn = new Conexion().establecerConexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.setInt(2, producto.getId());
            stmt.setInt(3, cantidad);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al agregar producto a la factura: " + e.getMessage());
        }
    }

    public void calcularTotalConDescuento(Function<Double, Double> descuento) {
        this.totalConDescuento = descuento.apply(this.total);
    }

    public double getTotalConDescuento() {
        return totalConDescuento;
    }

    public void guardarFactura() {
        String query = "INSERT INTO Factura (cliente_id, total, total_con_descuento) VALUES (?, ?, ?)";
        try (Connection conn = new Conexion().establecerConexion();
             PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, cliente.getId());
            stmt.setDouble(2, total);
            stmt.setDouble(3, totalConDescuento);
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                this.id = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error al guardar la factura: " + e.getMessage());
        }
    }

    public void mostrarFactura() {
        System.out.println("Cliente: " + cliente.getNombre() + " (" + cliente.getTipo() + ")");
        System.out.println("Productos:");
        for (DetalleFactura detalle : detalles) {
            System.out.println("- " + detalle.getProducto().getNombre() + " x" + detalle.getCantidad() + " - $" + detalle.getProducto().getPrecio());
        }
        System.out.println("Total sin descuento: $" + total);
        System.out.println("Total con descuento: $" + totalConDescuento);
    }

    public static Factura obtenerFacturaPorId(int id) {
        String query = "SELECT * FROM Factura WHERE id = ?";
        try (Connection conn = new Conexion().establecerConexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int clienteId = rs.getInt("cliente_id");
                Cliente cliente = Cliente.obtenerClientePorId(clienteId);
                if (cliente != null) {
                    Factura factura = new Factura(cliente);
                    factura.id = id;
                    return factura;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener la factura: " + e.getMessage());
        }
        return null;
    }

    public static void listarFacturas() {
        String query = "SELECT Factura.id, Cliente.nombre, Factura.total FROM Factura JOIN Cliente ON Factura.cliente_id = Cliente.id";
        try (Connection conn = new Conexion().establecerConexion();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            System.out.println("Lista de Facturas:");
            while (rs.next()) {
                System.out.println("Factura ID: " + rs.getInt("id") + ", Cliente: " + rs.getString("nombre") + ", Total: $" + rs.getDouble("total"));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar facturas: " + e.getMessage());
        }
    }
}


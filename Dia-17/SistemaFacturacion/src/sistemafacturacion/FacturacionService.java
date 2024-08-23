package sistemafacturacion;

import java.util.Scanner;

public class FacturacionService {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Sistema de Facturación ===");
        System.out.println("1. Crear Factura");
        System.out.println("2. Listar Facturas");
        System.out.println("3. Listar Clientes");
        System.out.println("4. Listar Productos");
        System.out.println("5. Salir");

        boolean salir = false;
        while (!salir) {
            System.out.print("\nSeleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese ID del cliente: ");
                    int clienteId = scanner.nextInt();
                    Cliente cliente = Cliente.obtenerClientePorId(clienteId);
                    if (cliente != null) {
                        Factura factura = new Factura(cliente);
                        boolean agregarProductos = true;
                        while (agregarProductos) {
                            System.out.print("Ingrese ID del producto: ");
                            int productoId = scanner.nextInt();
                            Producto producto = Producto.obtenerProductoPorId(productoId);
                            if (producto != null) {
                                System.out.print("Ingrese cantidad: ");
                                int cantidad = scanner.nextInt();
                                factura.agregarProducto(producto, cantidad);
                                System.out.print("¿Desea agregar más productos? (s/n): ");
                                char respuesta = scanner.next().charAt(0);
                                agregarProductos = (respuesta == 's' || respuesta == 'S');
                            } else {
                                System.out.println("Producto no encontrado.");
                            }
                        }
                        System.out.print("Aplicar descuento VIP (s/n): ");
                        char aplicarDescuento = scanner.next().charAt(0);
                        if (aplicarDescuento == 's' || aplicarDescuento == 'S') {
                            factura.calcularTotalConDescuento(Descuentos.descuentoVIP());
                        } else {
                            factura.calcularTotalConDescuento(total -> total); // Sin descuento
                        }
                        factura.guardarFactura();
                        factura.mostrarFactura();
                    } else {
                        System.out.println("Cliente no encontrado.");
                    }
                }
                case 2 -> Factura.listarFacturas();
                case 3 -> Cliente.listarClientes();
                case 4 -> Producto.listarProductos();
                case 5 -> salir = true;
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        }

        scanner.close();
        System.out.println("¡Gracias por usar el sistema de facturación!");
    }
}


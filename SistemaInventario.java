// Interfaz para comportamiento común de productos
interface Gestionable {
    String generarEtiqueta();
    double calcularPrecioFinal();
}

// Clase abstracta base para todos los productos
abstract class Producto {
    protected String nombre;
    protected double precioBase;
    protected int cantidad;

    public Producto(String nombre, double precioBase, int cantidad) {
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.cantidad = cantidad;
    }

    // Métodos abstractos que las subclases deben implementar
    abstract double calcularPrecioFinal();
    abstract String generarEtiqueta();

    // Método común para todos los productos
    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }
}

// Subclase para productos electrónicos
class Electronico extends Producto implements Gestionable {
    private int garantiaMeses;

    public Electronico(String nombre, double precioBase, int cantidad, int garantiaMeses) {
        super(nombre, precioBase, cantidad);
        this.garantiaMeses = garantiaMeses;
    }

    @Override
    public double calcularPrecioFinal() {
        // Añade un 15% al precio base por garantía extendida
        return precioBase * 1.15;
    }

    @Override
    public String generarEtiqueta() {
        return String.format("Electrónico: %s - Precio: %.2f - Garantía: %d meses",
                nombre, calcularPrecioFinal(), garantiaMeses);
    }
}

// Subclase para alimentos
class Alimento extends Producto implements Gestionable {
    private String fechaExpiracion;

    public Alimento(String nombre, double precioBase, int cantidad, String fechaExpiracion) {
        super(nombre, precioBase, cantidad);
        this.fechaExpiracion = fechaExpiracion;
    }

    @Override
    public double calcularPrecioFinal() {
        // Añade un 10% por manejo especial
        return precioBase * 1.10;
    }

    @Override
    public String generarEtiqueta() {
        return String.format("Alimento: %s - Precio: %.2f - Expira: %s",
                nombre, calcularPrecioFinal(), fechaExpiracion);
    }
}

// Subclase para ropa
class Ropa extends Producto implements Gestionable {
    private String talla;

    public Ropa(String nombre, double precioBase, int cantidad, String talla) {
        super(nombre, precioBase, cantidad);
        this.talla = talla;
    }

    @Override
    public double calcularPrecioFinal() {
        // Precio base sin modificaciones adicionales
        return precioBase;
    }

    @Override
    public String generarEtiqueta() {
        return String.format("Ropa: %s - Precio: %.2f - Talla: %s",
                nombre, calcularPrecioFinal(), talla);
    }
}

// Clase principal para probar el sistema
public class SistemaInventario {
    public static void main(String[] args) {
        // Crear array de productos para demostrar polimorfismo
        Gestionable[] inventario = new Gestionable[3];

        // Instanciar diferentes tipos de productos
        inventario[0] = new Electronico("Televisor", 500.0, 10, 24);
        inventario[1] = new Alimento("Leche", 2.5, 50, "2025-06-30");
        inventario[2] = new Ropa("Camiseta", 15.0, 30, "M");

        // Demostrar polimorfismo recorriendo el inventario
        System.out.println("=== Inventario Actual ===");
        for (Gestionable producto : inventario) {
            System.out.println(producto.generarEtiqueta());
        }

        // Prueba de actualización de cantidad
        Producto primerProducto = (Producto) inventario[0];
        System.out.println("\nCantidad inicial de " + primerProducto.getNombre() + ": " + 
                primerProducto.getCantidad());
    }
}
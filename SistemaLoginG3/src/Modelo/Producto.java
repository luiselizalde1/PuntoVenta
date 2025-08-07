package Modelo;

public class Producto {
    // Atributos
    private int idProducto;
    private String nombreProduco;
    private int stockProducto;
    private float precioProducto;
    
    // Constructor
    public Producto() {}

    public Producto(int idProducto, String nombreProduco, int stockProducto, float precioProducto) {
        this.idProducto = idProducto;
        this.nombreProduco = nombreProduco;
        this.stockProducto = stockProducto;
        this.precioProducto = precioProducto;
    }
    
    // Metodos getters y setters
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProduco() {
        return nombreProduco;
    }

    public void setNombreProduco(String nombreProduco) {
        this.nombreProduco = nombreProduco;
    }

    public int getStockProducto() {
        return stockProducto;
    }

    public void setStockProducto(int stockProducto) {
        this.stockProducto = stockProducto;
    }

    public float getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(float precioProducto) {
        this.precioProducto = precioProducto;
    }
    
    // Metodo toString
    @Override
    public String toString() {
        return "Producto{" + "idProducto=" + idProducto + ", nombreProduco=" + nombreProduco + ", stockProducto=" + stockProducto + ", precioProducto=" + precioProducto + '}';
    }   
}

package Controlador;

import Modelo.Producto;
import Vista.PuntodVenta;
import javax.swing.table.DefaultTableModel;

public class ControladorPuntodVenta {
    // Atributos
    private PuntodVenta vista;
    private Producto modelo;
    
    // Constructor
    public ControladorPuntodVenta() {
        // Crear objetos vista y modelo
        this.vista = new PuntodVenta();
        this.modelo = new Producto();
        // Llamar al metodo manejadorEventos
        manejadorEventos();
        llenarTablaProductos();
    }
    
    // Metodos getters y setters
    public PuntodVenta getVista() {
        return vista;
    }

    public void setVista(PuntodVenta vista) {
        this.vista = vista;
    }

    public Producto getModelo() {
        return modelo;
    }

    public void setModelo(Producto modelo) {
        this.modelo = modelo;
    }
    
    // Metodo para manejar eventos
    private void manejadorEventos() {
    }
    
    // Metodo para agregar el ArrayList a la tabla productos
    public void llenarTablaProductos() {
        this.vista.tblProductos.setModel(obtenerModeloTabla());
    } // Fin del metodo llenarTablaProductos
    
    // Metodo para obtener el modelo tabla (llenar el ArrayList al DefaultTableModel)
    public DefaultTableModel obtenerModeloTabla() {
        String encabezadoTabla[] = {"ID", "Nombre", "Descripcion", "Costo"};
        DefaultTableModel modelotabla = new DefaultTableModel(encabezadoTabla, 0);
        
        Object fila[] = new Object[modelotabla.getColumnCount()];
        
        for (Producto producto : this.modelo.buscar()) {
            fila[0] = producto.getIdProducto();
            fila[1] = producto.getNombreProducto();
            fila[2] = producto.getDescripcionProducto();
            fila[3] = producto.getPrecioProducto();
            
            modelotabla.addRow(fila);
        }
        
        return modelotabla;
    }
} // Fin de la clase ControladorPuntodVenta

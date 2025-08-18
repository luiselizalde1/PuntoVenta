package Controlador;

import Modelo.Producto;
import Vista.PanelListaProductos;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorPanelListaProductos {

    // Atributos
    private PanelListaProductos vista;
    private Producto modelo;

    // Constructor
    public ControladorPanelListaProductos() {
        // Crear objetos vista y modelo
        this.vista = new PanelListaProductos();
        this.modelo = new Producto();
        // Llenar la tabla al iniciar el Panel
        llenarTablaProductos();
        // Llamar al metodo manejador de eventos
        manejadorEventos();
    }

    // Metodos getters y setters
    public PanelListaProductos getVista() {
        return vista;
    }

    public void setVista(PanelListaProductos vista) {
        this.vista = vista;
    }

    public Producto getModelo() {
        return modelo;
    }

    public void setModelo(Producto modelo) {
        this.modelo = modelo;
    }

    // Metodos
    // Metodo para manejar eventos
    public void manejadorEventos() {
        // Agregar evento al boton de buscar
        this.vista.btnBuscar.addActionListener(e -> buscarProductoPorID());
        // Agregar evento al boton de nuevo
        this.vista.btnLimpiar.addActionListener(e -> limpiarInput());
        // Agregar evento al boton de salir
        this.vista.btnSalir.addActionListener(e -> salir());
    }

    // Metodo para buscar los datos de un producto mediante su id
    public void buscarProductoPorID() {
        // Obtener el ID de la vista y agregarlo al modelo
        this.modelo.setIdProducto(Integer.parseInt(this.vista.txtIdProducto.getText()));

        // Validar la consulta por ID
        if (this.modelo.buscarPorId(this.modelo.getIdProducto())) {
            // Mostrar los datos en la tabla
            String encabezadoTabla[] = {"ID", "Nombre", "Descripcion", "Costo"};
            DefaultTableModel modelotabla = new DefaultTableModel(encabezadoTabla, 0);

            Object fila[] = new Object[modelotabla.getColumnCount()];

            fila[0] = this.modelo.getIdProducto();
            fila[1] = this.modelo.getNombreProducto();
            fila[2] = this.modelo.getDescripcionProducto();
            fila[3] = this.modelo.getPrecioProducto();

            modelotabla.addRow(fila);

            this.vista.tblProducto.setModel(modelotabla);
        } else {
            JOptionPane.showMessageDialog(this.vista, this.modelo.getMensajes());
        }
    }

    // Metodo para agregar el ArrayList a la tabla productos
    public void llenarTablaProductos() {
        this.vista.tblListaProductos.setModel(obtenerModeloTabla());
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

    public void limpiarInput() {
        this.vista.txtIdProducto.setText("");
        this.vista.txtIdProducto.requestFocus();
    }

    public void salir() {
        this.vista.setVisible(false);
    }
}

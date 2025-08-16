package Controlador;

import Modelo.Producto;
import Vista.PanelProductos;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorPanelProductos {

    // Atributos
    private PanelProductos vista;
    private Producto modelo;

    // Constructor
    public ControladorPanelProductos() {
        // Crear objetos vista y modelo
        this.vista = new PanelProductos();
        this.modelo = new Producto();
        // Llenar la tabla al iniciar el Panel
        llenarTablaProductos();
        // Llamar al metodo manejador de eventos
        manejadorEventos();
    }

    // Metodos getters y setters
    public PanelProductos getVista() {
        return vista;
    }
    
    public void setVista(PanelProductos vista) {
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
        // Agregar evento al boton de registrar
        this.vista.btnRegistrar.addActionListener(e -> registrarNuevoProducto());
        // Agregar evento al boton de buscar
        this.vista.btnBuscar.addActionListener(e -> buscarProductoPorID());
        // Agregar evento al boton de editar
        this.vista.btnEditar.addActionListener(e -> modificarDatosProducto());
        // Agregar evento al boton de eliminar
        // Agregar evento al boton de nuevo
        this.vista.btnNuevo.addActionListener(e -> nuevoProducto());
        // Agregar evento al boton de salir
        this.vista.btnSalir.addActionListener(e -> salir());
    }

    // Metodo para registrar un nuevo producto
    public void registrarNuevoProducto() {
        // Obtener los datos del producto de la vista y agregarlos al modelo
        this.modelo.setNombreProducto(this.vista.txtNombreProducto.getText());
        this.modelo.setDescripcionProducto(this.vista.txtDescripcionProducto.getText());
        this.modelo.setPrecioProducto(Float.parseFloat(this.vista.txtPrecioProducto.getText()));

        // Validar si se inserta el nuevo producto
        if (this.modelo.insertar()) {
            JOptionPane.showMessageDialog(this.vista, this.modelo.getMensajes());
            // Llamar al metodo llenarTablaProductos para que muestre el nuevo producto
            llenarTablaProductos();
        } else {
            JOptionPane.showMessageDialog(this.vista, this.modelo.getMensajes());
        }
    } // Fin del metodo registrarNuevoProducto

    // Metodo para buscar los datos de un producto mediante su id
    public void buscarProductoPorID() {
        // Obtener el ID de la vista y agregarlo al modelo
        this.modelo.setIdProducto(Integer.parseInt(this.vista.txtIdProducto.getText()));

        // Validar la consulta por ID
        if (this.modelo.buscarPorId(this.modelo.getIdProducto())) {
            // Mostrar los datos en las cajas de texto de la vista
            this.vista.txtNombreProducto.setText(this.modelo.getNombreProducto());
            this.vista.txtDescripcionProducto.setText(this.modelo.getDescripcionProducto());
            this.vista.txtPrecioProducto.setText("" + this.modelo.getPrecioProducto());
        } else {
            JOptionPane.showMessageDialog(this.vista, this.modelo.getMensajes());
        }
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

    // Metodo para editar los datos de un producto
    public void modificarDatosProducto() {
        // Obtener los datos del producto de la vista y agregarlos al modelo
        this.modelo.setIdProducto(Integer.parseInt(this.vista.txtIdProducto.getText()));
        this.modelo.setNombreProducto(this.vista.txtNombreProducto.getText());
        this.modelo.setDescripcionProducto(this.vista.txtDescripcionProducto.getText());
        this.modelo.setPrecioProducto(Float.parseFloat(this.vista.txtPrecioProducto.getText()));

        // Mandar a llamar el metodo modificar del modelo
        if (this.modelo.modificar(this.modelo.getIdProducto())) {
            JOptionPane.showMessageDialog(this.vista, this.modelo.getMensajes());
            // Llamar al metodo llenarTablaProductos para que muestre el nuevo producto
            llenarTablaProductos();
        } else {
            JOptionPane.showMessageDialog(this.vista, this.modelo.getMensajes());
        }
    }

    // Metodo para eliminar un producto
    public void eliminarProducto() {
    }
    
    public void nuevoProducto() {
        limpiarInputs();
        this.vista.txtNombreProducto.requestFocus();
    }
    
    public void limpiarInputs() {
        this.vista.txtIdProducto.setText("");
        this.vista.txtNombreProducto.setText("");
        this.vista.txtDescripcionProducto.setText("");
        this.vista.txtPrecioProducto.setText("");
    }
    
    public void salir() {
        this.vista.setVisible(false);
    }
} // Fin de la clase ControladorPanelProductos

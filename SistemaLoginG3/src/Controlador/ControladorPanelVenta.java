package Controlador;

import Modelo.DetalleVenta;
import Vista.PanelVenta;

public class ControladorPanelVenta {

    // Atributos
    PanelVenta vista;
    DetalleVenta modelo;

    // Constructor
    public ControladorPanelVenta() {
        // Crear objetos vista y modelo
        this.vista = new PanelVenta();
        this.modelo = new DetalleVenta();
        // Llamar al metodo manejador de eventos
        manejadorEventos();
    }

    // Metodos getters y setters
    public PanelVenta getVista() {
        return vista;
    }

    public void setVista(PanelVenta vista) {
        this.vista = vista;
    }

    public DetalleVenta getModelo() {
        return modelo;
    }

    public void setModelo(DetalleVenta modelo) {
        this.modelo = modelo;
    }

    // Metodos
    // Metodo para manejar eventos
    public void manejadorEventos() {
        /* // Agregar evento al boton de registrar
        this.vista.btnRegistrar.addActionListener(e -> registrarNuevoProducto());
        // Agregar evento al boton de buscar
        this.vista.btnBuscar.addActionListener(e -> buscarProductoPorID());
        // Agregar evento al boton de editar
        this.vista.btnEditar.addActionListener(e -> modificarDatosProducto());
        // Agregar evento al boton de eliminar
        this.vista.btnEliminar.addActionListener(e -> eliminarProducto());
        // Agregar evento al boton de nuevo
        this.vista.btnNuevo.addActionListener(e -> nuevoProducto());
        // Agregar evento al boton de salir
        this.vista.btnSalir.addActionListener(e -> salir()); */
    }
    
    // Metodo para registrar un nuevo detalle en la venta
    public void registrarNuevoDetalle() {
        // Obtener los datos del detalle de la vista y agregarlos al modelo
    }
}

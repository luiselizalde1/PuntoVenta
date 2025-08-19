package Controlador;

import Modelo.DetalleVenta;
import Modelo.Venta;
import Vista.DashBoardPuntoVenta;
import javax.swing.JOptionPane;

public class ControladorDashBoardPuntoVenta {

    // Atributos
    private DashBoardPuntoVenta vista;
    private Venta modeloVenta;
    private DetalleVenta modeloDetalleVenta;

    // Constructor
    public ControladorDashBoardPuntoVenta() {
        // Crear el objeto vista
        this.vista = new DashBoardPuntoVenta();
        this.modeloVenta = new Venta();
        this.modeloDetalleVenta = new DetalleVenta();
        manejadorEventos();
    }

    // Metodos getters y setters
    /* public DashBoardPuntoVenta getVista() {
        return vista;
    }

    public void setVista(DashBoardPuntoVenta vista) {
        this.vista = vista;
    } */
    public DashBoardPuntoVenta getVista() {
        return vista;
    }

    public void setVista(DashBoardPuntoVenta vista) {
        this.vista = vista;
    }

    public Venta getModeloVenta() {
        return modeloVenta;
    }

    public void setModeloVenta(Venta modeloVenta) {
        this.modeloVenta = modeloVenta;
    }

    public DetalleVenta getModeloDetalleVenta() {
        return modeloDetalleVenta;
    }

    public void setModeloDetalleVenta(DetalleVenta modeloDetalleVenta) {
        this.modeloDetalleVenta = modeloDetalleVenta;
    }
    

    // Metodos
    // Metodo manejador de eventos
    public void manejadorEventos() {
        // Agregar evento al boton btnBuscar
        this.vista.btnBuscar.addActionListener(e -> mostrarPanelListaProductos());
        // Agregar evento al boton btnNuevaVenta
        this.vista.btnNuevaVenta.addActionListener(e -> mostrarPanelVenta());
    }

    // Metodo para mostrar el panel de lista productos
    public void mostrarPanelListaProductos() {
        ControladorPanelListaProductos controladorPanelListaProductos = new ControladorPanelListaProductos();

        controladorPanelListaProductos.getVista().setSize(800, 560);
        controladorPanelListaProductos.getVista().setLocation(0, 0);

        this.vista.panContenido.removeAll();
        this.vista.panContenido.add(controladorPanelListaProductos.getVista());
        this.vista.panContenido.revalidate();
        this.vista.panContenido.repaint();
    }

    // Metodo para mostrar el panel de venta
    public void mostrarPanelVenta() {
        ControladorPanelVenta controladorPanelVenta = new ControladorPanelVenta();

        controladorPanelVenta.getVista().setSize(800, 560);
        controladorPanelVenta.getVista().setLocation(0, 0);

        this.vista.panContenido.removeAll();
        this.vista.panContenido.add(controladorPanelVenta.getVista());
        this.vista.panContenido.revalidate();
        this.vista.panContenido.repaint();

        registrarNuevaVenta();
    }

    // Metodo para registrar una nueva venta
    public void registrarNuevaVenta() {
        // Validar si se inserta la nueva venta
        if (this.modeloVenta.insertar()) {
            JOptionPane.showMessageDialog(this.vista, this.modeloVenta.getMensajes());
        } else {
            JOptionPane.showMessageDialog(this.vista, this.modeloVenta.getMensajes());
        }
        
        this.modeloDetalleVenta.setIdVenta(modeloVenta.getIdVenta());
    } // Fin del metodo registrarNuevaVenta

    // Metodo main
    public static void main(String[] args) {
        ControladorDashBoardPuntoVenta controlador = new ControladorDashBoardPuntoVenta();
        controlador.vista.setVisible(true);
        controlador.vista.setLocationRelativeTo(null);
    }
}

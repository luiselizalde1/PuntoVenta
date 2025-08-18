package Controlador;

import Vista.DashBoardPuntoVenta;

public class ControladorDashBoardPuntoVenta {

    // Atributos
    private DashBoardPuntoVenta vista;

    // Constructor
    public ControladorDashBoardPuntoVenta() {
        // Crear el objeto vista
        this.vista = new DashBoardPuntoVenta();
        manejadorEventos();
    }

    // Metodos getters y setters
    public DashBoardPuntoVenta getVista() {
        return vista;
    }

    public void setVista(DashBoardPuntoVenta vista) {
        this.vista = vista;
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
    }

    // Metodo main
    public static void main(String[] args) {
        ControladorDashBoardPuntoVenta controlador = new ControladorDashBoardPuntoVenta();
        controlador.vista.setVisible(true);
        controlador.vista.setLocationRelativeTo(null);
    }
}

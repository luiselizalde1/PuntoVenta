package Controlador;

import Vista.DashBoardAdmin;

public class ControladorDashBoardAdmin {

    //Atributos
    private DashBoardAdmin vista;
    private usuario modelo;

    //Controlador
    public ControladorDashBoardAdmin() {
        //Crear el objeto vista
        this.vista = new DashBoardAdmin();
        manejadorEventos();

    }
    //Metodo set y get

    public DashBoardAdmin getVista() {
        return vista;
    }

    public void setVista(DashBoardAdmin vista) {
        this.vista = vista;
    }

    //Metodo manejador de eventos 
    public void manejadorEventos() {
        //Agregar evento al oton btnUsuarios
        this.vista.btnUsuarios.addActionListener(e -> mostrarPanelUsuarios());

        this.vista.btnRolUsuarios.addActionListener(e -> mostrarPanelRolUsuarios());

        // Agregar evento al boton btnProductos
        this.vista.btnProductos.addActionListener(e -> mostrarPanelProductos());
    }

    public void mostrarPanelRolUsuarios() {
        ControladorPanelRolUsuarios controladorPanelRolUsuarios = new ControladorPanelRolUsuarios();

        controladorPanelRolUsuarios.getVista().setSize(800, 560);
        controladorPanelRolUsuarios.getVista().setLocation(0, 0);

        this.vista.panelContenido.removeAll();
        this.vista.panelContenido.add(controladorPanelRolUsuarios.getVista());
        this.vista.panelContenido.revalidate();
        this.vista.panelContenido.repaint();

    }

    //Metodo para mostrar el panel de usuarios
    public void mostrarPanelUsuarios() {
        //Crear el objeto del controlador panel usuario
        ControladorPanelUsuarios controladorPanelUsuarios = new ControladorPanelUsuarios();

        controladorPanelUsuarios.getVista().setSize(800, 560);
        controladorPanelUsuarios.getVista().setLocation(0, 0);

        //Agregar eel panelUsuarios al panelControl
        this.vista.panelContenido.removeAll();
        this.vista.panelContenido.add(controladorPanelUsuarios.getVista());
        this.vista.panelContenido.revalidate();
        this.vista.panelContenido.repaint();
    }

    // Metodo para mostrar el panel de productos
    public void mostrarPanelProductos() {
        // Crear el objeto del controlador panel productos
        ControladorPanelProductos controladorpanelproductos = new ControladorPanelProductos();

        controladorpanelproductos.getVista().setSize(800, 560);
        controladorpanelproductos.getVista().setLocation(0, 0);

        // Agregar el panelProductos al panelControl
        this.vista.panelContenido.removeAll();
        this.vista.panelContenido.add(controladorpanelproductos.getVista());
        this.vista.panelContenido.revalidate();
        this.vista.panelContenido.repaint();
    }

    //Metodo main
    public static void main(String[] args) {
        ControladorDashBoardAdmin controlador = new ControladorDashBoardAdmin();
        controlador.vista.setVisible(true);
        controlador.vista.setLocationRelativeTo(null);
    }
}

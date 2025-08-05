/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.RolUsuario;
import Vista.PanelRolUsuarios;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 
 */
    public class ControladorPanelRolUsuarios {
       // Atributos
    private PanelRolUsuarios vista;
    private RolUsuario modelo;

    // Constructor
    public ControladorPanelRolUsuarios() {
        this.vista = new PanelRolUsuarios();
        this.modelo = new RolUsuario();
        manejadorEventos();
        llenarTblRolUsuarios();
    }

    // Metodos getters y setters
    public PanelRolUsuarios getVista() {
        return vista;
    }

    public void setVista(PanelRolUsuarios vista) {
        this.vista = vista;
    }

    public RolUsuario getModelo() {
        return modelo;
    }

    public void setModelo(RolUsuario modelo) {
        this.modelo = modelo;
    }

    // Metodos
    public void manejadorEventos() {
        this.vista.btnRolRegistrar.addActionListener(e -> registrarRolUsuario());
        this.vista.btnRolEditar.addActionListener(e -> editarRolUsuario());
        this.vista.btnRolBuscar.addActionListener(e -> buscarRolUsuario());
        this.vista.btnRolEliminar.addActionListener(e -> eliminarRolUsuario());
        this.vista.btnRolNuevo.addActionListener(e -> nuevoRolUsuario());
        this.vista.btnRolSalir.addActionListener(e -> salir());
    }

    public void registrarRolUsuario() {
        this.modelo.setNombreRolUsuario(this.vista.TxtRolNombre.getText());
        this.modelo.setDescripcionRolusuario(this.vista.TxtRolObservaciones.getText());
        if (this.modelo.insertar()) {
            llenarTblRolUsuarios();
        } else {
            JOptionPane.showMessageDialog(this.vista, this.modelo.getMensajes());
        }
    }

    public void editarRolUsuario() {
        this.modelo.setIdRolUsuario(Integer.parseInt(this.vista.txtRolUsuario.getText()));
        this.modelo.setNombreRolUsuario(this.vista.TxtRolNombre.getText());
        this.modelo.setDescripcionRolusuario(this.vista.TxtRolObservaciones.getText());
        
        if (this.modelo.modificar(this.modelo.getNombreRolUsuario())) {

            JOptionPane.showMessageDialog(this.vista, this.modelo.getMensajes());
            llenarTblRolUsuarios();
        } else {
            JOptionPane.showMessageDialog(this.vista, this.modelo.getMensajes());
        }
    }

    public void buscarRolUsuario() {
        this.modelo.setIdRolUsuario(Integer.parseInt(this.vista.txtRolUsuario.getText()));
        if (this.modelo.buscarPorId(this.modelo.getIdRolUsuario())) {
            this.vista.TxtRolNombre.setText(this.modelo.getNombreRolUsuario());
            this.vista.TxtRolObservaciones.setText(this.modelo.getDescripcionRolusuario());
        } else {
            JOptionPane.showMessageDialog(this.vista, this.modelo.getMensajes());
        }
        this.llenarTblRolUsuarios();
    }

    public void eliminarRolUsuario() {
        this.modelo.setIdRolUsuario(Integer.parseInt(this.vista.txtRolUsuario.getText()));
        if (this.modelo.eliminar(this.modelo.getIdRolUsuario())) {
            JOptionPane.showMessageDialog(this.vista, this.modelo.getMensajes());
            llenarTblRolUsuarios();
        } else {
            JOptionPane.showMessageDialog(this.vista, this.modelo.getMensajes());
        }
    }

    public void nuevoRolUsuario () {
        limpiarInputs();
        this.vista.txtRolUsuario.requestFocus();
    }

    public void salir () {this.vista.setVisible(false);}

    public void limpiarInputs() {
        this.vista.txtRolUsuario.setText("");
        this.vista.TxtRolNombre.setText("");
        this.vista.TxtRolObservaciones.setText("");
    }

    public void llenarTblRolUsuarios() {
    ArrayList<RolUsuario> lista = this.modelo.buscar();
    System.out.println("Cantidad de roles encontrados: " + lista.size()); // Depuración
    this.vista.TablaRol.setModel(obtenerModeloTbl(lista));
}

public DefaultTableModel obtenerModeloTbl(ArrayList<RolUsuario> lista) {
    String[] encabezado = { "ID", "Nombre", "Descripción" };
    DefaultTableModel modeloTabla = new DefaultTableModel(encabezado, 0);
    
    for (RolUsuario rol : lista) {
        Object[] fila = new Object[3];
        fila[0] = rol.getIdRolUsuario();
        fila[1] = rol.getNombreRolUsuario();
        fila[2] = rol.getDescripcionRolusuario();
        modeloTabla.addRow(fila);
    }

    return modeloTabla;
}
    }
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Login;
import Vista.DashBoardAdmin;
import Vista.VistaLogin;
import javax.swing.JOptionPane;

/**
 *
 * @author LuisE
 */
public class ControladorVistaLogin {
    //Atributos
    private VistaLogin vista;
    private Login modelo;
    
    //Cnstructor

    public ControladorVistaLogin() {
    //Crear objetos
    this.vista=new VistaLogin();
    this.modelo=new Login();
    //Llamar al metodo manejadorEvenos
    manejadorEventos();
    }
    
    //Metodo para el manejador de evento
    public void manejadorEventos(){
        this.vista.btnIniciar.addActionListener(e->iniciarSesion());
    }
    
    //Metodo para iniciar Sesio
    public void iniciarSesion(){
        //Obtener los datos de las cajas de texto de la vista
        String user=this.vista.TxtUsuario.getText();
        String pass=String.valueOf(this.vista.TxtPassword.getPassword());
        //String typeUser="admin";
        
        //Agregar los datos al modelo
        this.modelo.getUsuario().setNombreUsuario(user);
        this.modelo.setContraseniaLogin(pass);
        //this.modelo.getRolUsuario().setNombreRolUsuario(typeUser);
        
        if (this.modelo.validarLogin()&& this.modelo.getRolUsuario().getNombreRolUsuario().equals("admin")){
            //JOptionPane.showMessageDialog(this.vista, "Usuario y/o Password Correcto");
            //Crear ojeto de bahBoardAdmin
            ControladorDashBoardAdmin dashBoardAdmin=new ControladorDashBoardAdmin();
            dashBoardAdmin.getVista().setVisible(true);
            dashBoardAdmin.getVista().setLocationRelativeTo(null);
            
            //Ocultar la vista de login
            this.vista.dispose();
            
            
        } else if(this.modelo.validarLogin()&& this.modelo.getRolUsuario().getNombreRolUsuario().equals("cajero")){
            ControladorDashBoardAdmin dashBoardAdmin=new ControladorDashBoardAdmin();
            dashBoardAdmin.getVista().setVisible(true);
            dashBoardAdmin.getVista().setLocationRelativeTo(null);
            
            this.vista.dispose();
        }
        
        {
            JOptionPane.showMessageDialog(this.vista, "Usuario y/o Password InCorrect");
        }
    }
    
    //Metodo rincipal main
    public static void main(String[] args) {
        ControladorVistaLogin controlador=new ControladorVistaLogin();
        controlador.vista.setVisible(true);
        controlador.vista.setLocationRelativeTo(null);
    }
    
}

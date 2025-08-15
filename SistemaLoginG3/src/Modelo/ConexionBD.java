/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

/**
 *
 * @author LuisE
 */
public class ConexionBD {
    //Atributos
    private String driverBD;
    private String usuarioBD;
    private String passwordUsuarioBD;
    private String nombreBD;
    
    private Connection conexion;
    private String mensajes;
    
    //Constructures

    public ConexionBD() {
        this.driverBD="com.mysql.cj.jdbc.Driver";
        this.usuarioBD="root";
        this.passwordUsuarioBD="12345678";
        this.nombreBD="jdbc:mysql://localhost:3306/bd_sistema_login";
    }

    public ConexionBD(String driverBD, String usuarioBD, String passwordUsuarioBD, String nombreBD) {
        this.driverBD = driverBD;
        this.usuarioBD = usuarioBD;
        this.passwordUsuarioBD = passwordUsuarioBD;
        this.nombreBD = nombreBD;
    }
    
    //metodo set y get

    public String getDriverBD() {
        return driverBD;
    }

    public void setDriverBD(String driverBD) {
        this.driverBD = driverBD;
    }

    public String getUsuarioBD() {
        return usuarioBD;
    }

    public void setUsuarioBD(String usuarioBD) {
        this.usuarioBD = usuarioBD;
    }

    public String getPasswordUsuarioBD() {
        return passwordUsuarioBD;
    }

    public void setPasswordUsuarioBD(String passwordUsuarioBD) {
        this.passwordUsuarioBD = passwordUsuarioBD;
    }

    public String getNombreBD() {
        return nombreBD;
    }

    public void setNombreBD(String nombreBD) {
        this.nombreBD = nombreBD;
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public String getMensajes() {
        return mensajes;
    }

    public void setMensajes(String mensajes) {
        this.mensajes = mensajes;
    }
    
    //metodo para establecer conexxion a la BD
    public boolean openConexcionBD(){
        
        try {
            Class.forName(this.driverBD);
            
            this.conexion=DriverManager.getConnection(this.nombreBD, this.usuarioBD, this.passwordUsuarioBD);
            if(this.conexion!=null){
                this.mensajes="Se establecio la conexcion al servidor de BD";
                return true;
                
            }
            
        } catch (Exception e) {
            mensajes="Error: " + e.getMessage();
        }
        
        return false;
        
    }
    
    //Mertodo para cerrar la conexion a la base de datos
    public boolean closeConexcionBD(){
        
        if (this.conexion!=null) {
            try {
                this.conexion.close();
            } catch (SQLException ex) {
                this.mensajes="Error: " + ex.getMessage();
            }
            return true;
        }
        
        
        return false;
    }
    
    //Metodo toString

    @Override
    public String toString() {
        return "ConexionBD{" + "mensajes=" + mensajes + '}';
    }  
}
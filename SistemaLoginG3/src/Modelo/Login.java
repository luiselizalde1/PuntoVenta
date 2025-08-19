/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import com.mysql.cj.jdbc.CallableStatement;
import com.mysql.cj.xdevapi.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author LuisE
 */
public class Login extends ConexionBD {

    //Atributos
    private int idLogin;
    private String nombreLogin;
    private Date fechaCreacionLogin;
    private boolean estatusLogin;
    private String contraseniaLogin;

    private Usuario usuario;
    private RolUsuario rolUsuario;

    CallableStatement cstmt;
    ResultSet result;

    //Constructor
    public Login() {
        //Crear objetos de usuario y su rol
        this.usuario = new Usuario();
        this.rolUsuario = new RolUsuario();

    }

    public Login(int idLogin, String nombreLogin, String contraseñaLogin) {
        this.idLogin = idLogin;
        this.nombreLogin = nombreLogin;
        this.contraseniaLogin = contraseñaLogin;
        this.usuario = new Usuario();
        this.rolUsuario = new RolUsuario();
    }

    //Metodo set y get
    public String getContraseniaLogin() {
        return contraseniaLogin;
    }

    public void setContraseniaLogin(String contraseniaLogin) {
        this.contraseniaLogin = contraseniaLogin;
    }

    public int getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(int idLogin) {
        this.idLogin = idLogin;
    }

    public String getNombreLogin() {
        return nombreLogin;
    }

    public void setNombreLogin(String nombreLogin) {
        this.nombreLogin = nombreLogin;
    }

    public Date getFechaCreacionLogin() {
        return fechaCreacionLogin;
    }

    public void setFechaCreacionLogin(Date fechaCreacionLogin) {
        this.fechaCreacionLogin = fechaCreacionLogin;
    }

    public boolean isEstatusLogin() {
        return estatusLogin;
    }

    public void setEstatusLogin(boolean estatusLogin) {
        this.estatusLogin = estatusLogin;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public RolUsuario getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(RolUsuario rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    //Metodo para  validar el inicio de sesion 
    public boolean validarLogin() {

        if (super.openConexcionBD()) {

            try {
                //Llamar al procedimiento de almacenado
                this.cstmt = (CallableStatement) super.getConexion().prepareCall("call bd_sistema_login.sp_validar_login(?,?);");
                this.cstmt.setString(1, this.getUsuario().getNombreUsuario());
                this.cstmt.setString(2, this.getContraseniaLogin());

                //Ejecutar el procedimiento alamcenado y agregar los datos de result
                this.result = this.cstmt.executeQuery();

                boolean existeUsuario = false;

                //Recorrer la consulta
                while (this.result.next()) {
                    existeUsuario = true;
                    System.out.println("Rol encontrado: " + this.result.getString(1));
                }

                //Cerrar sesion
                this.cstmt.close();
                super.getConexion().close();

                if (existeUsuario) {
                    super.setMensajes("Si existe el usuario");
                    return true;

                } else {
                    super.setMensajes("No existe el usuario");
                    return false;

                }

            } catch (SQLException e) {
                super.setMensajes("Error de SQL" + e.getMessage());
            }

        } else {
            JOptionPane.showMessageDialog(null, super.getDriverBD());
        }
        return false;
    }

    //Metodo para validar el inicio de sesion
    //public boolean validarLogin(){
    // String usuarioLogin="Luis";
    // String passwordUsuario="mikecrack.1";
    // String tipoUsuario="admin";
    // if ((this.usuario.getNombreUsuario().equals(usuarioLogin))
    /// &&(this.contraseniaLogin.equals(passwordUsuario))
               // &&(this.rolUsuario.getNombreRolUsuario().equals(tipoUsuario))){
           // return true;
     //   } else {
        //    return false;
            
       // }
  //  }
    
    //Metodo String

    @Override
    public String toString() {
        return "Login{" + "idLogin=" + idLogin + ", nombreLogin=" + nombreLogin + ", fechaCreacionLogin=" + fechaCreacionLogin + ", estatusLogin=" + estatusLogin + ", contraseniaLogin=" + contraseniaLogin + ", usuario=" + usuario + ", rolUsuario=" + rolUsuario + '}';
    }

}

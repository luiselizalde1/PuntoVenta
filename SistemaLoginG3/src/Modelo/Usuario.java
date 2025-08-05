/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author LuisE
 */
public class Usuario extends ConexionBD implements CRUDInterface {

    //Seccion Atributos
    private int idUsuario;
    private String nombreUsuario;
    private String apellidoParternoUsuario;
    private String apellidoMaternoUsuario;
    private String emailUsuario;
    private String telefonoCleluarUSuario;

    CallableStatement cstmt;
    ResultSet resultado;

    //Constructor= Sirve a para inicializar los atrivbutos
    public Usuario() {
    }

    public Usuario(int idUsuario, String nombreUsuario, String apellidoParternoUsuario, String apellidoMaternoUsuario, String emailUsuario, String telefonoCleluarUSuario) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellidoParternoUsuario = apellidoParternoUsuario;
        this.apellidoMaternoUsuario = apellidoMaternoUsuario;
        this.emailUsuario = emailUsuario;
        this.telefonoCleluarUSuario = telefonoCleluarUSuario;
    }
    
    

    //Metodos SET y GET= Get obtiene el valor del atributo, SET establece el nombre del atributo
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoParternoUsuario() {
        return apellidoParternoUsuario;
    }

    public void setApellidoParternoUsuario(String apellidoParternoUsuario) {
        this.apellidoParternoUsuario = apellidoParternoUsuario;
    }

    public String getApellidoMaternoUsuario() {
        return apellidoMaternoUsuario;
    }

    public void setApellidoMaternoUsuario(String apellidoMaternoUsuario) {
        this.apellidoMaternoUsuario = apellidoMaternoUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getTelefonoCleluarUSuario() {
        return telefonoCleluarUSuario;
    }

    public void setTelefonoCleluarUSuario(String telefonoCleluarUSuario) {
        this.telefonoCleluarUSuario = telefonoCleluarUSuario;
    }

    //Metodo toString= 
    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ",\n nombreUsuario=" + nombreUsuario + ",\n apellidoParternoUsuario=" + apellidoParternoUsuario + ",\n apellidoMaternoUsuario=" + apellidoMaternoUsuario + ",\n emailUsuario=" + emailUsuario + ",\n telefonoCleluarUSuario=" + telefonoCleluarUSuario + '}';
    }

    @Override
    public boolean insertar() {

        if (super.openConexcionBD()) {
            //JOptionPane.showConfirmDialog(null, "Se conecto al servidor de BD" + super.getMensajes());

            try {

                //Llamar el procedimiento almacenado
                this.cstmt = super.getConexion().prepareCall("call bd_sistema_login.nsp_insetar_usuario(?, ?, ?, ?, ?);");
                this.cstmt.setString(1, this.nombreUsuario);
                this.cstmt.setString(2, this.apellidoParternoUsuario);
                this.cstmt.setString(3, this.apellidoMaternoUsuario);
                this.cstmt.setString(4, this.emailUsuario);
                this.cstmt.setString(5, this.telefonoCleluarUSuario);

                //Ejecute el procedimento almacenado
                this.cstmt.execute();

                //Cerrar conexion
                this.cstmt.close();
                super.getConexion().close();

                super.setMensajes("Se guaradaron correctamente los datos del Usuario");

                return true;

            } catch (SQLException e) {
                super.setMensajes("Error de SQL: " + e.getMessage());
            }

        } else {
            JOptionPane.showConfirmDialog(null, "No s epudo conectar al servidor de BD " + super.getMensajes());
        }
        return false;
    }

    @Override
    public ArrayList<Usuario> buscar() {
        ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();

        if (super.openConexcionBD()) {
            //JOptionPane.showConfirmDialog(null, "Se conecto al servidor de BD" + super.getMensajes());

            try {

                //Llamar el procedimiento almacenado
                this.cstmt = super.getConexion().prepareCall("call bd_sistema_login.sp_consulktar_usuarios();");

                //Ejecute el procedimento almacenado
                this.resultado = this.cstmt.executeQuery();

                //Recorrer la consulta
                while (this.resultado.next()) {

                    //Crear objeti de ususario
                    Usuario usuario = new Usuario();

                    //Agregar los datos de la consulta al los atributos del usuario
                    usuario.idUsuario = Integer.parseInt(this.resultado.getString(1));
                    usuario.nombreUsuario = this.resultado.getString(2);
                    usuario.apellidoParternoUsuario = this.resultado.getString(3);
                    usuario.apellidoMaternoUsuario = this.resultado.getString(4);
                    usuario.emailUsuario = this.resultado.getString(5);
                    usuario.telefonoCleluarUSuario = this.resultado.getString(6);

                    //Agregar el objeto usuario a la lista
                    listaUsuarios.add(usuario);
                }

                //Cerrar conexion
                this.cstmt.close();
                super.getConexion().close();

                super.setMensajes("Se Consultaron correctamente los datos del Usuario");

            } catch (SQLException e) {
                super.setMensajes("Error de SQL: " + e.getMessage());
            }

        } else {
            JOptionPane.showConfirmDialog(null, "No s epudo conectar al servidor de BD " + super.getMensajes());
        }

        return listaUsuarios;
    }

    @Override
    public boolean buscarPorId(int id) {
        if (super.openConexcionBD()) {
            //JOptionPane.showConfirmDialog(null, "Se conecto al servidor de BD" + super.getMensajes());

            try {

                //Llamar el procedimiento almacenado
                this.cstmt = super.getConexion().prepareCall("call bd_sistema_login.sp_consultar_id_Usuario(?);");
                this.cstmt.setInt(1, this.idUsuario);

                //Ejecute el procedimento almacenado
                this.resultado = this.cstmt.executeQuery();

                //Recorrer la consulta
                while (this.resultado.next()) {

                    //Agregar los datos de la consulta al los atributos del usuario
                    this.idUsuario = Integer.parseInt(this.resultado.getString(1));
                    this.nombreUsuario = this.resultado.getString(2);
                    this.apellidoParternoUsuario = this.resultado.getString(3);
                    this.apellidoMaternoUsuario = this.resultado.getString(4);
                    this.emailUsuario = this.resultado.getString(5);
                    this.telefonoCleluarUSuario = this.resultado.getString(6);

                }

                //Cerrar conexion
                this.cstmt.close();
                super.getConexion().close();

                super.setMensajes("Se Consultaron correctamente los datos del Usuario");

                return true;

            } catch (SQLException e) {
                super.setMensajes("Error de SQL: " + e.getMessage());
            }

        } else {
            JOptionPane.showConfirmDialog(null, "No s epudo conectar al servidor de BD " + super.getMensajes());
        }
        return false;

    }

    @Override
    public boolean modificar() {
        if (super.openConexcionBD()) {
            
            try {
                this.cstmt = super.getConexion().prepareCall("call bd_sistema_login.sp_actualizar_usuario(?, ?, ?, ?, ?, ?);");
                this.cstmt.setInt(1, this.idUsuario);
                this.cstmt.setString(2, this.nombreUsuario);
                this.cstmt.setString(3, this.apellidoParternoUsuario);
                this.cstmt.setString(4, this.apellidoMaternoUsuario);
                this.cstmt.setString(5, this.emailUsuario);
                this.cstmt.setString(6, this.telefonoCleluarUSuario);
                
                this.cstmt.execute();
                
                super.setMensajes("Usuario actualizado correctamente.");
                
                this.cstmt.close();
                super.getConexion().close();
                
                return true;
                
            } catch (SQLException e) {
                super.setMensajes("Error SQL: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo conectar: " + super.getMensajes());
        }
    return false;
}
    

    @Override
    public boolean eliminar(int id) {
        if (super.openConexcionBD()) {
        try {
            this.cstmt = super.getConexion().prepareCall("call bd_sistema_login.sp_eliminar_usuario(?);");
            this.cstmt.setInt(1, id);

            this.cstmt.execute();
           
            super.setMensajes("Usuario eliminado correctamente.");
            
            this.cstmt.close();
            super.getConexion().close();

            return true;

        } catch (SQLException e) {
            super.setMensajes("Error SQL: " + e.getMessage());
        }
    } else {
        JOptionPane.showMessageDialog(null, "No se pudo conectar: " + super.getMensajes());
    }
    return false;
}
        
    }


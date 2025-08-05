/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class RolUsuario extends ConexionBD implements CRUDRolUsuario {

    private int idRolUsuario;
    private String nombreRolUsuario;
    private String descripcionRolusuario;

    CallableStatement cstmt;
    ResultSet result;
    public Iterable<RolUsuario> buscarRol;

    public RolUsuario() {
    }

    public RolUsuario(int idRolUsuario, String nombreRolUsuario, String descripcionRolusuario) {
        this.idRolUsuario = idRolUsuario;
        this.nombreRolUsuario = nombreRolUsuario;
        this.descripcionRolusuario = descripcionRolusuario;
    }

    // Getters y setters
    public int getIdRolUsuario() {
        return idRolUsuario;
    }

    public void setIdRolUsuario(int idRolUsuario) {
        this.idRolUsuario = idRolUsuario;
    }

    public String getNombreRolUsuario() {
        return nombreRolUsuario;
    }

    public void setNombreRolUsuario(String nombreRolUsuario) {
        this.nombreRolUsuario = nombreRolUsuario;
    }

    public String getDescripcionRolusuario() {
        return descripcionRolusuario;
    }

    public void setDescripcionRolusuario(String descripcionRolusuario) {
        this.descripcionRolusuario = descripcionRolusuario;
    }

    @Override
    public String toString() {
        return "RolUsuario{"
                + "idRolUsuario=" + idRolUsuario
                + ", nombreRolUsuario='" + nombreRolUsuario + '\''
                + ", descripcionRolusuario='" + descripcionRolusuario + '\''
                + '}';
    }

    @Override
    public boolean insertar() {
        if (super.openConexcionBD()) {
            try {
                this.cstmt = super.getConexion().prepareCall("call bd_sistema_login.sp_insertrar_rolusuario(?,?);");
                this.cstmt.setString(1, this.nombreRolUsuario);          // primer parámetro
                this.cstmt.setString(2, this.descripcionRolusuario);     // segundo parámetro
                this.cstmt.execute(); // Falta ejecutar el procedimiento

                this.cstmt.close();
                super.getConexion().close();

                super.setMensajes("Rol registrado correctamente.");
                return true;
            } catch (SQLException e) {
                super.setMensajes("Error al insertar rol: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, super.getMensajes());
        }
        return false;
    }

    @Override
    public ArrayList<RolUsuario> buscar() {
        ArrayList<RolUsuario> lista = new ArrayList<>();
        if (super.openConexcionBD()) {
            try {
                this.cstmt = super.getConexion().prepareCall("call bd_sistema_login.sp_Consultar_roUsuario();"); // sin parámetro
                this.result = this.cstmt.executeQuery();

                while (result.next()) {
                    RolUsuario rol = new RolUsuario();
                    rol.setIdRolUsuario(result.getInt(1));
                    rol.setNombreRolUsuario(result.getString(2));
                    rol.setDescripcionRolusuario(result.getString(3));
                    lista.add(rol);
                }

                this.result.close();
                this.cstmt.close();
                super.getConexion().close();
                super.setMensajes("Consulta realizada correctamente.");
            } catch (SQLException e) {
                super.setMensajes("Error al consultar roles: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, super.getMensajes());
        }
        return lista;
    }

    @Override
    public boolean buscarPorId(int id) {
        if (super.openConexcionBD()) {
            try {
                this.cstmt = super.getConexion().prepareCall("call bd_sistema_login.sp_buscarid_rol_Usuario(?);");
                this.cstmt.setInt(1, id);
                this.result = this.cstmt.executeQuery();

                if (result.next()) {
                    this.idRolUsuario = result.getInt(1);
                    this.nombreRolUsuario = result.getString(2);
                    this.descripcionRolusuario = result.getString(3);
                    return true;
                }

                this.cstmt.close();
                super.getConexion().close();

                super.setMensajes("Búsqueda por ID completada.");
            } catch (SQLException e) {
                super.setMensajes("Error al buscar por ID: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, super.getMensajes());
        }
        return false;
    }

    @Override
    public boolean modificar(String nombreRolUsuario1) {
        if (super.openConexcionBD()) {
            try {
                this.cstmt = super.getConexion().prepareCall("call bd_sistema_login.sp_editar_rolusuario(?,?, ?);");
                this.cstmt.setInt(1, this.idRolUsuario);
                this.cstmt.setString(2, this.nombreRolUsuario);
                this.cstmt.setString(3, this.descripcionRolusuario);
                this.cstmt.execute();

                this.cstmt.close();
                super.getConexion().close();

                super.setMensajes("Rol modificado correctamente.");
                return true;
            } catch (SQLException e) {
                super.setMensajes("Error al modificar rol: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, super.getMensajes());
        }
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        if (super.openConexcionBD()) {
            try {
                this.cstmt = super.getConexion().prepareCall("call bd_sistema_login.sp_eliminar_rolusuario(?);");
                this.cstmt.setInt(1, id);
                this.cstmt.execute();

                this.cstmt.close();
                super.getConexion().close();

                super.setMensajes("Rol eliminado correctamente.");
                return true;
            } catch (SQLException e) {
                super.setMensajes("Error al eliminar rol: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, super.getMensajes());
        }
        return false;
    }

    public boolean modificarRol() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean eliminarRol(int idRolUsuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean insertarRol() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

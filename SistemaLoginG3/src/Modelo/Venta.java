package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class Venta extends ConexionBD implements CRUDVenta {

    private int idVenta;
    private Date fechaVenta;

    // Constructor vacío
    public Venta() {
    }

    // Constructor completo
    public Venta(int idVenta, Date fechaVenta) {
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
    }

    // Getters y setters
    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    @Override
    public String toString() {
        return "Venta{" + "idVenta=" + idVenta + ", fechaVenta=" + fechaVenta + '}';
    }

    // INSERTAR venta y recuperar ID generado
    @Override
    public boolean insertar() {
        if (super.openConexcionBD()) {
            try {
                java.sql.Date fechaActual = new java.sql.Date(System.currentTimeMillis());

                PreparedStatement ps = super.getConexion()
                        .prepareStatement("CALL bd_sistema_login.sp_insertar_venta(?)");

                ps.setDate(1, fechaActual);

// Ejecutar y obtener el ResultSet del SELECT dentro del SP
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    this.idVenta = rs.getInt("idVenta"); // Recuperar el ID devuelto por el SP
                }

                rs.close();
                ps.close();

                super.setMensajes("Se guardaron correctamente los datos de la venta con ID " + this.idVenta);

                return true;

            } catch (SQLException e) {
                super.setMensajes("Error de SQL: " + e.getMessage());
            }
        } else {
            super.setMensajes("No se pudo conectar al servidor de BD: " + super.getMensajes());
            JOptionPane.showMessageDialog(null, super.getMensajes());
        }
        return false;
    }

    @Override
    public ArrayList buscar() {
        return null;
    }

    @Override
    public boolean buscarPorId(int id) {
        return false;
    }

    @Override
    public boolean modificar(int id) {
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        return false;
    }
}

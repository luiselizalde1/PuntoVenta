package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.CallableStatement;

public class DetalleVenta extends ConexionBD implements CRUDDetallesVenta {

    private int idDetalleVenta;
    private int idVenta;
    private int idProducto;
    private int cantidadDetalleVenta;
    private String nombreProducto;
    private float precioProducto;
    private float subtotal;

    CallableStatement cstmt;
    ResultSet resultado;

    public DetalleVenta() {
    }

    public DetalleVenta(int idDetalleVenta, int idVenta, int idProducto, int cantidadDetalleVenta,
            String nombreProducto, float precioProducto, float subtotal) {
        this.idDetalleVenta = idDetalleVenta;
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidadDetalleVenta = cantidadDetalleVenta;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.subtotal = subtotal;
    }

    // Getters y setters
    public int getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(int idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidadDetalleVenta() {
        return cantidadDetalleVenta;
    }

    public void setCantidadDetalleVenta(int cantidadDetalleVenta) {
        this.cantidadDetalleVenta = cantidadDetalleVenta;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public float getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(float precioProducto) {
        this.precioProducto = precioProducto;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "DetalleVenta{"
                + "idDetalleVenta=" + idDetalleVenta
                + ", idVenta=" + idVenta
                + ", idProducto=" + idProducto
                + ", cantidadDetalleVenta=" + cantidadDetalleVenta
                + ", nombreProducto='" + nombreProducto + '\''
                + ", precioProducto=" + precioProducto
                + ", subtotal=" + subtotal
                + '}';
    }

    // INSERTAR detalle usando idVenta generado
    public boolean insertar(int idVenta) {
        if (super.openConexcionBD()) {
            try {
                if (!existeProducto(this.idProducto)) {
                    super.setMensajes("El producto con ID " + this.idProducto + " no existe.");
                    return false;
                }

                this.cstmt = super.getConexion().prepareCall(
                        "call bd_sistema_login.sp_insertar_detalleventa(?, ?, ?);"
                );
                this.cstmt.setInt(1, idVenta);
                this.cstmt.setInt(2, this.idProducto);
                this.cstmt.setInt(3, this.cantidadDetalleVenta);
                this.cstmt.execute();
                this.cstmt.close();

                super.setMensajes("Se guardaron correctamente los datos del detalle de la venta");
                return true;

            } catch (SQLException e) {
                super.setMensajes("Error de SQL: " + e.getMessage());
            }
        } else {
            super.setMensajes("No se pudo conectar al servidor de BD.");
        }
        return false;
    }

    // MODIFICAR detalle
    @Override
    public boolean modificar(int id) {
        if (super.openConexcionBD()) {
            try {
                this.cstmt = super.getConexion().prepareCall(
                        "call bd_sistema_login.sp_actualizar_detalleventa(?, ?, ?);"
                );
                this.cstmt.setInt(1, this.idDetalleVenta);
                this.cstmt.setInt(2, this.idProducto);
                this.cstmt.setInt(3, this.cantidadDetalleVenta);
                this.cstmt.execute();
                this.cstmt.close();

                super.setMensajes("Se actualizaron correctamente los datos del detalle de la venta");
                return true;

            } catch (SQLException e) {
                super.setMensajes("Error de SQL: " + e.getMessage());
            }
        } else {
            super.setMensajes("No se pudo conectar al servidor de BD.");
        }
        return false;
    }

    // ELIMINAR detalle
    @Override
    public boolean eliminar(int id) {
        if (super.openConexcionBD()) {
            try {
                this.cstmt = super.getConexion().prepareCall(
                        "call bd_sistema_login.sp_eliminar_detalleventa(?);"
                );
                this.cstmt.setInt(1, this.idDetalleVenta);
                this.cstmt.execute();
                this.cstmt.close();

                super.setMensajes("Se eliminaron correctamente los datos del detalle de la venta");
                return true;

            } catch (SQLException e) {
                super.setMensajes("Error de SQL: " + e.getMessage());
            }
        } else {
            super.setMensajes("No se pudo conectar al servidor de BD.");
        }
        return false;
    }

// BUSCAR detalles por ID venta
    public ArrayList<DetalleVenta> buscarPorIdVenta(int idVenta) {
        ArrayList<DetalleVenta> listaDetalles = new ArrayList<>();
        if (super.openConexcionBD()) {
            try {
                this.cstmt = super.getConexion().prepareCall(
                        "call bd_sistema_login.sp_consultar_detallesventa_idventa(?);"
                );
                this.cstmt.setInt(1, idVenta);
                this.resultado = this.cstmt.executeQuery();

                while (this.resultado.next()) {
                    DetalleVenta detalle = new DetalleVenta();
                    detalle.idDetalleVenta = this.resultado.getInt(1);   // iddetalleventa
                    detalle.idVenta = this.resultado.getInt(2);          // idventa
                    detalle.idProducto = this.resultado.getInt(3);       // idproducto
                    detalle.cantidadDetalleVenta = this.resultado.getInt(4); // cantidad
                    detalle.nombreProducto = this.resultado.getString(5);    // nombreproducto
                    detalle.precioProducto = this.resultado.getFloat(6);     // precio
                    detalle.subtotal = this.resultado.getFloat(7);           // subtotal
                    listaDetalles.add(detalle);
                }
                this.resultado.close();
                this.cstmt.close();
                super.setMensajes("Se consultaron correctamente los detalles de la venta");
            } catch (SQLException e) {
                super.setMensajes("Error de SQL: " + e.getMessage());
            }
        }
        return listaDetalles;
    }

    // Validar existencia de producto
    public boolean existeProducto(int idProducto) {
        if (super.openConexcionBD()) {
            try {
                this.cstmt = super.getConexion().prepareCall(
                        "SELECT COUNT(*) FROM productos WHERE idProducto = ?;"
                );
                this.cstmt.setInt(1, idProducto);
                this.resultado = this.cstmt.executeQuery();
                if (this.resultado.next()) {
                    int count = this.resultado.getInt(1);
                    this.cstmt.close();
                    return count > 0;
                }
            } catch (SQLException e) {
                super.setMensajes("Error de SQL al validar producto: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean insertar() {
        return true;
    }

    @Override
    public ArrayList buscar() {
        return null;
    }

    @Override
    public boolean buscarPorId(int id) {
        return false;
    }
}

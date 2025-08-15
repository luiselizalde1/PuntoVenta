package Modelo;

import com.mysql.cj.jdbc.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Producto extends ConexionBD implements CRUDProducto {

    // Atributos
    private int idProducto;
    private String nombreProducto;
    private String descripcionProducto;
    private float precioProducto;

    CallableStatement cstmt;
    ResultSet resultado;

    // Constructor
    public Producto() {
    }

    public Producto(int idProducto, String nombreProducto, String descripcionProducto, float precioProducto) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.precioProducto = precioProducto;
    }

    // Metodos getters y setters
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public float getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(float precioProducto) {
        this.precioProducto = precioProducto;
    }

    // Metodo toString
    @Override
    public String toString() {
        return "Producto{" + "idProducto=" + idProducto + ", nombreProducto=" + nombreProducto + ", descripcionProducto=" + descripcionProducto + ", precioProducto=" + precioProducto + '}';
    }

    // Metodos
    @Override
    public boolean insertar() {
        if (super.openConexcionBD()) {
            System.out.println("se Conecto correctamente a la base de datos");
            return true;
            /* try {
                // Llamar al procedimiento almacenado
                this.cstmt = (CallableStatement) super.getConexion().prepareCall("call bd_sistema_login");
                this.cstmt.setString(1, this.idProducto);
            } catch (SQLException e) {
                super.setMensajes("Error de SQL: " + e.getMessage());
            } */
        }

        return false;
    }

    @Override
    public ArrayList<Producto> buscar() {
        ArrayList<Producto> listaProductos = new ArrayList<Producto>();

        if (super.openConexcionBD()) {
            try {
                // Llamar el procedimiento almacenado
                this.cstmt = (CallableStatement) super.getConexion().prepareCall("call bd_sistema_login.sp_consultar_productos();");
                // Ejecute el procedimiento almacenado
                this.resultado = (ResultSet) this.cstmt.executeQuery();
                // Recorrer la consulta
                while (this.resultado.next()) {
                    // Crear objeto de producto
                    Producto producto = new Producto();
                    // Agregar los datos de la consulta a los atributos del producto
                    producto.idProducto = Integer.parseInt(this.resultado.getString(1));
                    producto.nombreProducto = this.resultado.getString(2);
                    producto.descripcionProducto = this.resultado.getString(3);
                    producto.precioProducto = Float.parseFloat(this.resultado.getString(4));
                    // Agregar el objeto producto a la lista
                    listaProductos.add(producto);
                }
                // Cerrar conexion
                this.cstmt.close();
                super.getConexion().close();
                super.setMensajes("Se consultaron correctamente los datos de todos los productos");
            } catch (SQLException e) {
                super.setMensajes("Error de SQL: " + e.getMessage());
            }
        } else {
            JOptionPane.showConfirmDialog(null, "No se pudo conectar al servidor de BD: " + super.getMensajes());
        }

        return listaProductos;
    }

    @Override
    public boolean buscarPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean modificar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

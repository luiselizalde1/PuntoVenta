package Modelo;

import com.mysql.cj.jdbc.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Venta extends ConexionBD implements CRUDVenta {

    // Atributos
    private int idVenta;
    private int fechaVenta;

    CallableStatement cstmt;
    ResultSet resultado;

    // Constructor
    public Venta() {
    }

    public Venta(int idVenta, int fechaVenta) {
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
    }

    // Metodos getters y setters
    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(int fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public CallableStatement getCstmt() {
        return cstmt;
    }

    public void setCstmt(CallableStatement cstmt) {
        this.cstmt = cstmt;
    }

    public ResultSet getResultado() {
        return resultado;
    }

    public void setResultado(ResultSet resultado) {
        this.resultado = resultado;
    }

    // Metodo toString
    @Override
    public String toString() {
        return "Venta{" + "idVenta=" + idVenta + ", fechaVenta=" + fechaVenta + ", cstmt=" + cstmt + ", resultado=" + resultado + '}';
    }

    // Metodos
    @Override
    public boolean insertar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList buscar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author bran1
 */
public class DetalleVenta {
    
    //Atributos
    
    private int iddetalleventa;
    private Date fecha;
    private int cantidad;
    private float precio;
    
    //contructor 

    public DetalleVenta() {
    }

    public DetalleVenta(int iddetalleventa, Date fecha, int cantidad, float precio) {
        this.iddetalleventa = iddetalleventa;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.precio = precio;
    }
    
    //getter setter

    public int getIddetalleventa() {
        return iddetalleventa;
    }

    public void setIddetalleventa(int iddetalleventa) {
        this.iddetalleventa = iddetalleventa;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    // to string

    @Override
    public String toString() {
        return "DetalleVenta{" + "iddetalleventa=" + iddetalleventa + ", fecha=" + fecha + ", cantidad=" + cantidad + ", precio=" + precio + '}';
    }
    
    //modelo
    
}

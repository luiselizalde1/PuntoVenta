/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author bran1
 */
public class Clientes {
    
    // Atributos
    
    private int idcliente;
    private String nomcliente;
    private String direcliente;
    private String telcliente;
    
    //Constructor

    public Clientes() {
    }

    public Clientes(int idcliente, String nomcliente, String direcliente, String telcliente) {
        this.idcliente = idcliente;
        this.nomcliente = nomcliente;
        this.direcliente = direcliente;
        this.telcliente = telcliente;
    }
    
    
    // Metodos getter and setter

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public String getNomcliente() {
        return nomcliente;
    }

    public void setNomcliente(String nomcliente) {
        this.nomcliente = nomcliente;
    }

    public String getDirecliente() {
        return direcliente;
    }

    public void setDirecliente(String direcliente) {
        this.direcliente = direcliente;
    }

    public String getTelcliente() {
        return telcliente;
    }

    public void setTelcliente(String telcliente) {
        this.telcliente = telcliente;
    }
    
    // Metodo toString

    @Override
    public String toString() {
        return "Clientes{" + "idcliente=" + idcliente + ", nomcliente=" + nomcliente + ", direcliente=" + direcliente + ", telcliente=" + telcliente + '}';
    }
    
    //Metodos
    
}

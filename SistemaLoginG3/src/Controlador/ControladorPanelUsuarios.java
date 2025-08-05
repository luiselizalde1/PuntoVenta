/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Usuario;
import Vista.PanelUsuario;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LuisE
 */
public class ControladorPanelUsuarios {
    //Atributos
    private PanelUsuario vista;
    private Usuario modelo;
    
    //Constructor

    public ControladorPanelUsuarios() {
        //Crear objetos vista y modelo
        this.vista=new PanelUsuario();
        this.modelo=new Usuario();
        //Llamar al metodo manejador eventos
        manejadorEventos();
    }
    
    //Metodos set y get

    public PanelUsuario getVista() {
        return vista;
    }

    public void setVista(PanelUsuario vista) {
        this.vista = vista;
    }

    public Usuario getModelo() {
        return modelo;
    }

    public void setModelo(Usuario modelo) {
        this.modelo = modelo;
    }
    
    //Metodo par amanejador de eventos 
    //...

public void manejadorEventos(){
    //Agregar evento al boton de Registrar
    this.vista.btnRegistrar.addActionListener(e -> registrar());

    //Agregar evento al boton Buscar
    this.vista.btnBuscar.addActionListener(e -> buscarId());

    //Agregar evento al boton Editar  <----- Aquí se agregó el evento para Editar
    this.vista.btnEditar.addActionListener(e -> editar());

    //Agregar evento al boton Eliminar
    this.vista.btnEliminar.addActionListener(e -> eliminar());
}

//...

    
    //Metodo para registrar usuarios
    public void registrar(){
        //JOptionPane.showMessageDialog(this.vista, "Registrar Usuario");
        
        //Obtener los datos del usuario de la vista yh agregarselos al modelo 
        this.modelo.setNombreUsuario(this.vista.TxtNombre.getText());
        this.modelo.setApellidoParternoUsuario(this.vista.TxtApellidoPaterno.getText());
        this.modelo.setApellidoMaternoUsuario(this.vista.TxtApellidoMaterno.getText());
        this.modelo.setEmailUsuario(this.vista.TxtEmail.getText());
        this.modelo.setTelefonoCleluarUSuario(this.vista.TxtTelefono.getText());
        
        //Validar si se inserta el usuario
        if (this.modelo.insertar()) {
            //Llamar al metodo llenarTablaUsuarios para que se muestre todolos los usuarios en la tabla
            llenarTablaUsuario();
            JOptionPane.showMessageDialog(this.vista, this.modelo.getMensajes());
        }else{}
            JOptionPane.showMessageDialog(this.vista, this.modelo.getMensajes());
            
        }//Fin del metodo buscar
    
        //Metodo para buscar el ID
        public void buscarId(){
            //Obtener el id de l avista y agregarlo al modelo
            this.modelo.setIdUsuario(Integer.parseInt(this.vista.TxtUsuario.getText()));
            
            //Validar la consulta por ID
            
            if (this.modelo.buscarPorId(this.modelo.getIdUsuario())) {
                //Mostrar los datos en la caja de texto de la vista
                this.vista.TxtNombre.setText(this.modelo.getNombreUsuario());
                this.vista.TxtApellidoPaterno.setText(this.modelo.getApellidoParternoUsuario());
                this.vista.TxtApellidoMaterno.setText(this.modelo.getApellidoMaternoUsuario());
                this.vista.TxtEmail.setText(this.modelo.getEmailUsuario());
                this.vista.TxtTelefono.setText(this.modelo.getTelefonoCleluarUSuario())
                        ;
                
                
            } else {
                JOptionPane.showMessageDialog(this.vista, this.modelo.getMensajes());
            }
            
            //Llamar al metodo llenarTablaUsuarios para que se muestre todolos los usuarios en la tabla
            llenarTablaUsuario();
            
        }
        
        //Metodo para agregar el arraylist a la tabla usuario
        public void llenarTablaUsuario(){
            this.vista.tablaUsuario.setModel(obtenerModeloTabla());
        }//Fin del metodo llenar tabla usuario
        
        //Metodo para obrener el modelo tabla (llenar el arraylist al defaultTableModel)
        public DefaultTableModel obtenerModeloTabla(){
            
            String encabezadoTabla[]={"ID","Nombre","Apellido Paterno","Apellido Materno","Email","Telefono"};
            DefaultTableModel modeloTabla=new DefaultTableModel(encabezadoTabla,0);
            
            Object fila[]=new Object[modeloTabla.getColumnCount()];
            
            for (Usuario usuario : this.modelo.buscar()) {
                fila[0]=usuario.getIdUsuario();
                fila[1]=usuario.getNombreUsuario();
                fila[2]=usuario.getApellidoParternoUsuario();
                fila[3]=usuario.getApellidoMaternoUsuario();
                fila[4]=usuario.getEmailUsuario();
                fila[5]=usuario.getTelefonoCleluarUSuario();
                
                modeloTabla.addRow(fila);
                
            }
            
            
            return modeloTabla;
        }
        
        
        //Metodo para editar
        public void editar(){
            //Obtener los datos de la vista y sgregarselos al madelo
            this.modelo.setIdUsuario(Integer.parseInt(this.vista.TxtUsuario.getText()));
            this.modelo.setNombreUsuario(this.vista.TxtNombre.getText());
            this.modelo.setApellidoParternoUsuario(this.vista.TxtApellidoPaterno.getText());
            this.modelo.setApellidoMaternoUsuario(this.vista.TxtApellidoMaterno.getText());
            this.modelo.setEmailUsuario(this.vista.TxtEmail.getText());
            this.modelo.setTelefonoCleluarUSuario(this.vista.TxtTelefono.getText());
            
            //Mandar llamar el metodo modificar del modelo
            if (this.modelo.modificar()) {
                JOptionPane.showMessageDialog(this.vista ,this.modelo.getMensajes());
                //Lamar al metodo llenarTabla
                llenarTablaUsuario();
            } else {
                JOptionPane.showMessageDialog(this.vista, this.modelo.getMensajes());
            }
        }
        
        //Metodo para eliminar
        public void eliminar(){
            this.modelo.setIdUsuario(Integer.parseInt(this.vista.TxtUsuario.getText()));
            
            //Mandar llamar el metodo eliminar del modelo
            if (this.modelo.eliminar(this.modelo.getIdUsuario())) {
                JOptionPane.showMessageDialog(this.vista ,this.modelo.getMensajes());
                //Lamar al metodo llenarTabla
                llenarTablaUsuario();
            } else {
                JOptionPane.showMessageDialog(this.vista, this.modelo.getMensajes());
            }
        }
        
        
    }//Fin de la clase
 
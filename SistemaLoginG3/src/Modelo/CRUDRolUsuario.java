/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author LuisE
 */
public interface CRUDRolUsuario {
    public boolean insertar();
    public ArrayList buscar();
    public boolean buscarPorId(int id);
    public boolean modificar(String nombreRolUsuario1);
    public boolean eliminar(int id);
    
}

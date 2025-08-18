package Modelo;

import java.util.ArrayList;

public interface CRUDDetallesVenta {

    public boolean insertar();

    public ArrayList buscar();

    public boolean buscarPorId(int id);

    public boolean modificar(int id);

    public boolean eliminar(int id);
}

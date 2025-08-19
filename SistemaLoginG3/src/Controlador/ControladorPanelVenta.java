package Controlador;

import Modelo.DetalleVenta;
import Modelo.Venta;
import Vista.PanelVenta;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorPanelVenta {

    private PanelVenta vista;
    private Venta modeloVenta;
    private DetalleVenta modeloDetalleVenta;

    public ControladorPanelVenta() {
        this.vista = new PanelVenta();
        this.modeloVenta = new Venta();
        this.modeloDetalleVenta = new DetalleVenta();

        llenarTablaProductos();
        manejadorEventos();
    }

    public PanelVenta getVista() {
        return vista;
    }

    public void setVista(PanelVenta vista) {
        this.vista = vista;
    }

    public Venta getModeloVenta() {
        return modeloVenta;
    }

    public void setModeloVenta(Venta modeloVenta) {
        this.modeloVenta = modeloVenta;
    }

    public DetalleVenta getModeloDetalleVenta() {
        return modeloDetalleVenta;
    }

    public void setModeloDetalleVenta(DetalleVenta modeloDetalleVenta) {
        this.modeloDetalleVenta = modeloDetalleVenta;
    }

    private void manejadorEventos() {
        this.vista.btnAdd.addActionListener(e -> registrarNuevaVenta());
        this.vista.btnEliminar.addActionListener(e -> eliminarDetalleVenta());
        this.vista.btnModificar.addActionListener(e -> modificarDetalleVenta());
    }

    private void registrarNuevaVenta() {
        try {
            int idProducto = Integer.parseInt(this.vista.txtIdProducto.getText());
            int cantidad = Integer.parseInt(this.vista.txtCantidadDetalleVenta.getText());

            if (!modeloDetalleVenta.existeProducto(idProducto)) {
                JOptionPane.showMessageDialog(vista, "El producto con ID " + idProducto + " no existe.");
                return;
            }

            if (modeloVenta.getIdVenta() == 0) {
                // Crear nueva venta
                if (modeloVenta.insertar()) {
                    JOptionPane.showMessageDialog(vista, "Venta creada con ID: " + modeloVenta.getIdVenta());
                } else {
                    JOptionPane.showMessageDialog(vista, "Error al crear la venta: " + modeloVenta.getMensajes());
                    return;
                }
            }

            modeloDetalleVenta.setIdProducto(idProducto);
            modeloDetalleVenta.setCantidadDetalleVenta(cantidad);

            if (modeloDetalleVenta.insertar(modeloVenta.getIdVenta())) {
                JOptionPane.showMessageDialog(vista, modeloDetalleVenta.getMensajes());
            } else {
                JOptionPane.showMessageDialog(vista, modeloDetalleVenta.getMensajes());
            }

            llenarTablaProductos();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "Ingresa valores numéricos válidos.");
        }
    }

    private void modificarDetalleVenta() {
        try {
            int idDetalle = Integer.parseInt(this.vista.txtIdDetalleVenta.getText());
            int idProducto = Integer.parseInt(this.vista.txtIdProducto.getText());
            int cantidad = Integer.parseInt(this.vista.txtCantidadDetalleVenta.getText());

            modeloDetalleVenta.setIdDetalleVenta(idDetalle);
            modeloDetalleVenta.setIdProducto(idProducto);
            modeloDetalleVenta.setCantidadDetalleVenta(cantidad);

            if (modeloDetalleVenta.modificar(idDetalle)) {
                JOptionPane.showMessageDialog(vista, modeloDetalleVenta.getMensajes());
                llenarTablaProductos();
            } else {
                JOptionPane.showMessageDialog(vista, modeloDetalleVenta.getMensajes());
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "Ingresa valores numéricos válidos.");
        }
    }

    private void eliminarDetalleVenta() {
        try {
            int idDetalle = Integer.parseInt(this.vista.txtIdDetalleVenta.getText());
            modeloDetalleVenta.setIdDetalleVenta(idDetalle);

            if (modeloDetalleVenta.eliminar(idDetalle)) {
                JOptionPane.showMessageDialog(vista, modeloDetalleVenta.getMensajes());
                llenarTablaProductos();
            } else {
                JOptionPane.showMessageDialog(vista, modeloDetalleVenta.getMensajes());
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "Ingresa un ID válido para eliminar.");
        }
    }

    private void llenarTablaProductos() {
        this.vista.tblDetallesVenta.setModel(obtenerModeloTabla());
    }

    private DefaultTableModel obtenerModeloTabla() {
        String[] encabezado = {"ID", "ID Producto", "Nombre Producto", "Costo Unitario", "Cantidad", "Subtotal"};
        DefaultTableModel model = new DefaultTableModel(encabezado, 0);

        if (modeloVenta.getIdVenta() != 0) {
            for (DetalleVenta detalle : modeloDetalleVenta.buscarPorIdVenta(modeloVenta.getIdVenta())) {
                Object[] fila = {
                    detalle.getIdDetalleVenta(),
                    detalle.getIdProducto(),
                    detalle.getNombreProducto(),
                    detalle.getPrecioProducto(),
                    detalle.getCantidadDetalleVenta(),
                    detalle.getSubtotal()
                };
                model.addRow(fila);
            }
        }

        return model;
    }
}

package Controlador;

import Modelo.DetalleVenta;
import Modelo.Venta;
import Vista.PanelVenta;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
        this.vista.btnEnd.addActionListener(e -> imprimirTicket());
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

    public void imprimirTicket() {
        try {
            // Ruta de salida (carpeta del proyecto)
            String ruta = "ticket_venta_" + modeloVenta.getIdVenta() + ".pdf";

            // Configurar el trabajo de impresión
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setJobName("Ticket de Venta");

            job.setPrintable(new Printable() {
                @Override
                public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
                    if (pageIndex > 0) {
                        return NO_SUCH_PAGE;
                    }

                    Graphics2D g2d = (Graphics2D) g;
                    g2d.translate(pf.getImageableX(), pf.getImageableY());

                    int y = 20;

                    // Encabezado
                    g2d.setFont(new Font("Arial", Font.BOLD, 14));
                    g2d.drawString("TICKET DE VENTA", 100, y);
                    y += 20;
                    g2d.setFont(new Font("Arial", Font.PLAIN, 12));
                    g2d.drawString("Venta ID: " + modeloVenta.getIdVenta(), 20, y);
                    y += 20;

                    // Tabla
                    TableModel tableModel = vista.tblDetallesVenta.getModel();

                    // Dibujar encabezados
                    int x = 20;
                    for (int i = 0; i < tableModel.getColumnCount(); i++) {
                        g2d.drawString(tableModel.getColumnName(i), x, y);
                        x += 100;
                    }
                    y += 15;

                    double totalSubtotales = 0.0;

                    // Dibujar filas
                    for (int row = 0; row < tableModel.getRowCount(); row++) {
                        x = 20;
                        for (int col = 0; col < tableModel.getColumnCount(); col++) {
                            Object value = tableModel.getValueAt(row, col);
                            g2d.drawString(value != null ? value.toString() : "", x, y);
                            x += 100;
                        }

                        Object subtotalObj = tableModel.getValueAt(row, tableModel.getColumnCount() - 1);
                        if (subtotalObj != null) {
                            totalSubtotales += Double.parseDouble(subtotalObj.toString());
                        }

                        y += 15;
                    }

                    // Calcular IVA y Total
                    double iva = totalSubtotales * 0.16;
                    double totalVenta = totalSubtotales + iva;

                    y += 20;
                    g2d.drawString("Subtotal: $" + String.format("%.2f", totalSubtotales), 20, y);
                    y += 15;
                    g2d.drawString("IVA (16%): $" + String.format("%.2f", iva), 20, y);
                    y += 15;
                    g2d.setFont(new Font("Arial", Font.BOLD, 12));
                    g2d.drawString("TOTAL: $" + String.format("%.2f", totalVenta), 20, y);

                    return PAGE_EXISTS;
                }
            });

            // Guardar en archivo PDF (usando impresora PDF de Java)
            File file = new File(ruta);
            javax.print.attribute.HashPrintRequestAttributeSet attr = new javax.print.attribute.HashPrintRequestAttributeSet();
            job.print(attr);

            JOptionPane.showMessageDialog(vista, "Ticket generado en: " + ruta);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Error al generar PDF: " + e.getMessage());
        }
    }
}

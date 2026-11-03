package com.andreseg.tattoomvc.gui;

import com.andreseg.tattoomvc.base.Piercing;
import com.andreseg.tattoomvc.base.Servicio;
import com.andreseg.tattoomvc.base.Tatuaje;
import com.andreseg.tattoomvc.util.Util;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.Properties;

public class TattooControlador implements ActionListener, ListSelectionListener, WindowListener {
    private Ventana vista;
    private TattooModelo modelo;
    private File ultimaRutaExportada;

    public TattooControlador(Ventana vista, TattooModelo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        try {
            cargarDatosConfiguracion();
        } catch (IOException e) {
            System.out.println("No existe fichero de configuracion " + e.getMessage());
        }

        asignarActionCommands();
        addActionListener(this);
        addListSelectionListener(this);

        vista.getTatuajeRadioButton().setSelected(true);

    }

    // Vincula el listener a la ventana desde el Principal
    public void setFrame(JFrame frame) {
        frame.addWindowListener(this);
    }

    private void asignarActionCommands() {
        // Aseguramos que los botones manden el texto correcto al Switch
        vista.getNuevoButton().setActionCommand("Nuevo");
        vista.getExportarButton().setActionCommand("Exportar");
        vista.getImportarButton().setActionCommand("Importar");
        vista.getCerrarButton().setActionCommand("Cerrar");
        vista.getTatuajeRadioButton().setActionCommand("Tatuaje");
        vista.getPiercingRadioButton().setActionCommand("Piercing");
        if (vista.getBajaButton() != null) vista.getBajaButton().setActionCommand("Baja");
    }

    private void addActionListener(ActionListener listener) {
        vista.getNuevoButton().addActionListener(listener);
        vista.getExportarButton().addActionListener(listener);
        vista.getImportarButton().addActionListener(listener);
        vista.getCerrarButton().addActionListener(listener);
        vista.getTatuajeRadioButton().addActionListener(listener);
        vista.getPiercingRadioButton().addActionListener(listener);
        if (vista.getBajaButton() != null) {
            vista.getBajaButton().addActionListener(listener);
        }
    }

    private void addListSelectionListener(ListSelectionListener listener) {
        vista.getList1().addListSelectionListener(listener);
    }

    private boolean hayCamposVacios() {
        return vista.getTxtCliente().getText().isEmpty() ||
                vista.getTxtTelefono().getText().isEmpty() ||
                vista.getTxtPrecio().getText().isEmpty() ||
                vista.getfCitaDP().getText().isEmpty() ||
                vista.getTxtEstilo().getText().isEmpty() ||
                vista.getTxtTamano().getText().isEmpty();
    }

    private void limpiarCampos() {
        vista.getTxtCliente().setText(null);
        vista.getTxtTelefono().setText(null);
        vista.getTxtPrecio().setText(null);
        vista.getTxtEstilo().setText(null);
        vista.getTxtTamano().setText(null);
        vista.getfCitaDP().setText(null);
        vista.getTxtCliente().requestFocus();
        vista.getList1().clearSelection();
    }

    public void refrescar() {
        DefaultListModel<Servicio> dlm = new DefaultListModel<>();
        for (Servicio unServicio : modelo.obtenerServicios()) {
            dlm.addElement(unServicio);
        }
        vista.getList1().setModel(dlm);
    }

    private void cargarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.load(new FileReader("tatuajes.conf"));
        ultimaRutaExportada = new File("tatuajes.conf");
    }

    private void guardarConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        if (ultimaRutaExportada != null) {
            configuracion.setProperty("ultimaRutaExportada", ultimaRutaExportada.getAbsolutePath());
        }
        configuracion.store(new PrintWriter("tatuajes.conf"), "Datos configuracion tatuajes");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "Nuevo":
                if (hayCamposVacios()) {
                    Util.mensajeError("Los campos no pueden estar vacíos.");
                    break;
                }

                if (!vista.getTatuajeRadioButton().isSelected() && !vista.getPiercingRadioButton().isSelected()) {
                    Util.mensajeError("Debes seleccionar el tipo de servicio (Tatuaje o Piercing).");
                    break;
                }

                if (modelo.existeCliente(vista.getTxtCliente().getText())) {
                    Util.mensajeError("Ya existe un servicio para este cliente.");
                    break;
                }
                try {
                    if (vista.getTatuajeRadioButton().isSelected()) {
                        modelo.altaTatuaje(vista.getTxtCliente().getText(), vista.getTxtTelefono().getText(),
                                Double.parseDouble(vista.getTxtPrecio().getText()), vista.getfCitaDP().getDate(),
                                vista.getTxtEstilo().getText(), Integer.parseInt(vista.getTxtTamano().getText()));
                    } else if (vista.getPiercingRadioButton().isSelected()) {
                        modelo.altaPiercing(vista.getTxtCliente().getText(), vista.getTxtTelefono().getText(),
                                Double.parseDouble(vista.getTxtPrecio().getText()), vista.getfCitaDP().getDate(),
                                vista.getTxtEstilo().getText(), vista.getTxtTamano().getText());
                    }
                    limpiarCampos();
                    refrescar();
                } catch (NumberFormatException ex) {
                    Util.mensajeError("Comprueba que el precio y el tamaño sean números.");
                }
                break;

            case "Baja":
                Servicio seleccionado = (Servicio) vista.getList1().getSelectedValue();
                if (seleccionado != null) {
                    modelo.bajaServicio(seleccionado);
                    limpiarCampos();
                    refrescar();
                } else {
                    Util.mensajeError("Debes seleccionar un elemento de la lista para darlo de baja.");
                }
                break;

            case "Importar":
                JFileChooser selectorImportar = Util.crearSelectorFichero(ultimaRutaExportada, "Archivos XML", "xml");
                if (selectorImportar.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.importarXML(selectorImportar.getSelectedFile());
                        ultimaRutaExportada = selectorImportar.getSelectedFile();
                        refrescar();
                    } catch (Exception ex) {
                        Util.mensajeError("Error al importar: " + ex.getMessage());
                    }
                }
                break;

            case "Exportar":
                JFileChooser selectorExportar = Util.crearSelectorFichero(ultimaRutaExportada, "Archivos XML", "xml");
                if (selectorExportar.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.exportar(selectorExportar.getSelectedFile());
                        ultimaRutaExportada = selectorExportar.getSelectedFile();
                    } catch (Exception ex) {
                        Util.mensajeError("Error al exportar: " + ex.getMessage());
                    }
                }
                break;

            case "Tatuaje":
                vista.getEstiloLabel().setText("Estilo");
                vista.getTamanoLabel().setText("Tamaño (cm)");
                break;

            case "Piercing":
                vista.getEstiloLabel().setText("Zona del cuerpo");
                vista.getTamanoLabel().setText("Material");
                break;

            case "Cerrar":
                cerrarConConfirmacion();
                break;
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting() && vista.getList1().getSelectedValue() != null) {
            Servicio seleccionado = (Servicio) vista.getList1().getSelectedValue();
            vista.getTxtCliente().setText(seleccionado.getCliente());
            vista.getTxtTelefono().setText(seleccionado.getTelefono());
            vista.getTxtPrecio().setText(String.valueOf(seleccionado.getPrecio()));
            vista.getfCitaDP().setDate(seleccionado.getFechaCita());

            if (seleccionado instanceof Tatuaje) {
                vista.getTxtEstilo().setText(((Tatuaje) seleccionado).getEstilo());
                vista.getTxtTamano().setText(String.valueOf(((Tatuaje) seleccionado).getTamanoCm()));
                vista.getTatuajeRadioButton().doClick(); // Simula el click
            } else {
                vista.getTxtEstilo().setText(((Piercing) seleccionado).getZonaCuerpo());
                vista.getTxtTamano().setText(((Piercing) seleccionado).getMaterial());
                vista.getPiercingRadioButton().doClick(); // Simula el click
            }
        }
    }

    private void cerrarConConfirmacion() {
        int resp = Util.mensajeConfirmacion("¿Desea cerrar la ventana?", "Salir");
        if (resp == JOptionPane.OK_OPTION) {
            try {
                guardarConfiguracion();
            } catch (IOException ex) {
                System.out.println("No se pudo guardar la configuración.");
            }
            System.exit(0);
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        cerrarConConfirmacion();
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
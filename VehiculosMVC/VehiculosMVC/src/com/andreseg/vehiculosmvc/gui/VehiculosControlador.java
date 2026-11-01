package com.andreseg.vehiculosmvc.gui;

import com.andreseg.vehiculosmvc.base.Coche;
import com.andreseg.vehiculosmvc.base.Moto;
import com.andreseg.vehiculosmvc.base.Vehiculo;
import com.andreseg.vehiculosmvc.util.Util;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.Properties;

public class VehiculosControlador implements ActionListener, ListSelectionListener, WindowListener {
    private Ventana vista;
    private VehiculosModelo modelo;
    private File ultimaRutaExportada;
    private Object Coche;
    private Object Moto;

    public VehiculosControlador(Ventana vista, VehiculosModelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        try {
            cargarDatosConfiguracion();
        } catch (IOException e) {
            System.out.println("No existe fichero de configuracion "+e.getMessage());
        }
        addActionListener(this);
        addListSelectionListener(this);
        addWindowListener(this);
    }

    private boolean hayCamposVacios() {
        if (vista.kmsPlazasTxt.getText().isEmpty() ||
                vista.marcaTxt.getText().isEmpty() ||
                vista.modeloTxt.getText().isEmpty() ||
                vista.matriculaTxt.getText().isEmpty() ||
                vista.fechaMatriculacionDPicker.getText().isEmpty()) {
            return true;
        }
        return false;
    }

    private void limpiarCampos() {
        vista.kmsPlazasTxt.setText(null);
        vista.marcaTxt.setText(null);
        vista.matriculaTxt.setText(null);
        vista.modeloTxt.setText(null);
        vista.fechaMatriculacionDPicker.setText(null);
        vista.matriculaTxt.requestFocus();
    }

    public void refrescar() {
        vista.dlmvehiculo.clear();
        for (Vehiculo unVehiculo: modelo.obtenerVehiculos()) {
            vista.dlmvehiculo.addElement(unVehiculo);
        }
    }

    private void addActionListener(ActionListener listener) {
        vista.nuevoButton.addActionListener(listener);
        vista.exportarButton.addActionListener(listener);
        vista.importarButton.addActionListener(listener);
        vista.cocheRadioButton.addActionListener(listener);
        vista.motoRadioButton.addActionListener(listener);
    }
    private void addWindowListener(WindowListener listener) {
        vista.frame.addWindowListener(listener);
    }
    private void addListSelectionListener(ListSelectionListener listener) {
        vista.list1.addListSelectionListener(listener);
    }

    private void cargarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.load(new FileReader("vehiculos.conf"));
        ultimaRutaExportada= new File (configuracion.getProperty("ultimaRutaExportada"));
    }
    private void actualizarDatosConfiguracion(File ultimaRutaExportada) {
        this.ultimaRutaExportada=ultimaRutaExportada;
    }
    private void guardarConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.setProperty("ultimaRutaExportada", ultimaRutaExportada.getAbsolutePath());
        configuracion.store(new PrintWriter("vehiculos.conf"), "Datos configuracion vehiculos");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand){
            case "Nuevo":
                    if(hayCamposVacios()){
                        Util.MensajeError("Los siguientes campos no pueden estar vacios: \n Matricula \n Marca \n Modelo\n Fecha matriculacion"+
                                vista.kmsPlazasLbl.getText());
                    }

                    if(modelo.existeMatricula((vista.matriculaTxt.getText()))){
                        Util.MensajeError("Ya existe un vehiculo con esa matrícula \n" + vista.matriculaTxt.getText());
                    }
                    if(vista.cocheRadioButton.isSelected()) {
                        modelo.altaCoche(vista.matriculaTxt.getText(),vista.marcaTxt.getText(),
                                vista.modeloTxt.getText(),vista.fechaMatriculacionDPicker.getDate(),
                                Integer.parseInt(vista.kmsPlazasTxt.getText()));
                    }
                if(vista.motoRadioButton.isSelected()) {
                    modelo.altaMoto(vista.matriculaTxt.getText(),vista.marcaTxt.getText(),
                            vista.modeloTxt.getText(),vista.fechaMatriculacionDPicker.getDate(),
                            Double.parseDouble(vista.kmsPlazasTxt.getText()));
                }
                limpiarCampos();
                refrescar();
                break;

            case "Importar":
                JFileChooser selectorFichero = Util.crearSelectorFichero(ultimaRutaExportada,"Archivos XML","xml");
                int opcion = selectorFichero.showOpenDialog(null);
                if (opcion== JFileChooser.APPROVE_OPTION){
                    try{
                        modelo.importarXML(selectorFichero.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (SAXException ex) {
                        ex.printStackTrace();
                    }
                }
                break;
            case "Exportar":
                JFileChooser selectorFichero2 = Util.crearSelectorFichero(ultimaRutaExportada,"Archivos XML","xml");
                int opcion2 = selectorFichero2.showSaveDialog(null);
                if (opcion2== JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.importarXML(selectorFichero2.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (SAXException ex) {
                        ex.printStackTrace();
                    }
                }

                break;
            case "Moto":
                vista.kmsPlazasLbl.setText("kms");
                break;
            case "Coche":
                vista.kmsPlazasLbl.setText("N Plazas");
                break;
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getValueIsAdjusting()){
            Vehiculo vehiculoSeleccionado = (Vehiculo) vista.list1.getSelectedValue();
            vista.matriculaTxt.setText(vehiculoSeleccionado.getMatricula());
            vista.marcaTxt.setText(vehiculoSeleccionado.getMarca());
            vista.modeloTxt.setText(vehiculoSeleccionado.getModelo());
            vista.fechaMatriculacionDPicker.setDate(vehiculoSeleccionado.getFechaMatriculacion());
            if (vehiculoSeleccionado instanceof Coche) {
                vista.kmsPlazasTxt.setText(String.valueOf(((Coche)vehiculoSeleccionado).getNumPlazas()));
                vista.cocheRadioButton.doClick();
            } else {
                vista.kmsPlazasTxt.setText(String.valueOf(((Moto) vehiculoSeleccionado).getKms()));
                vista.motoRadioButton.doClick();
            }
        }
    }


    @Override
    public void windowClosing(WindowEvent e) {
        int resp = Util.mensajeConfirmacion("¿Desea cerrar la ventana?", "Salir");
        if (resp == JOptionPane.OK_OPTION) {
            try {
                guardarConfiguracion();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.exit(0);
        }
    }

    //no los uso
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

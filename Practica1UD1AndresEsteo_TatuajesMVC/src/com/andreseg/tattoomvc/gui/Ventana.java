package com.andreseg.tattoomvc.gui;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;

public class Ventana {
    private JPanel panel1;
    private JRadioButton tatuajeRadioButton;
    private JRadioButton piercingRadioButton;
    private JTextField txtCliente;
    private JTextField txtTelefono;
    private JCheckBox autorizacionTutorLegalCheckBox;
    private JTextField txtPrecio;
    private DatePicker fNacDP;
    private DatePicker fCitaDP;
    private JTextField txtEstilo;
    private JTextField txtTamano;
    private JButton nuevoButton;
    private JButton cerrarButton;
    private JButton importarButton;
    private JButton exportarButton;
    private JButton bajaButton;
    private JList list1;
    private JLabel estiloLabel;
    private JLabel tamanoLabel;



    public JPanel getPanel1() {
        return panel1;
    }

    public JRadioButton getTatuajeRadioButton() {
        return tatuajeRadioButton;
    }

    public JRadioButton getPiercingRadioButton() {
        return piercingRadioButton;
    }

    public JTextField getTxtCliente() {
        return txtCliente;
    }

    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    public JCheckBox getAutorizacionTutorLegalCheckBox() {
        return autorizacionTutorLegalCheckBox;
    }

    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    public DatePicker getfNacDP() {
        return fNacDP;
    }

    public DatePicker getfCitaDP() {
        return fCitaDP;
    }

    public JTextField getTxtEstilo() {
        return txtEstilo;
    }

    public JTextField getTxtTamano() {
        return txtTamano;
    }

    public JButton getNuevoButton() {
        return nuevoButton;
    }

    public JButton getCerrarButton() {
        return cerrarButton;
    }

    public JButton getImportarButton() {
        return importarButton;
    }

    public JButton getExportarButton() {
        return exportarButton;
    }

    public JButton getBajaButton() {
        return bajaButton;
    } // Getter del botón Baja

    public JList getList1() {
        return list1;
    }

    public JLabel getEstiloLabel() {
        return estiloLabel;
    }

    public JLabel getTamanoLabel() {
        return tamanoLabel;
    }
}
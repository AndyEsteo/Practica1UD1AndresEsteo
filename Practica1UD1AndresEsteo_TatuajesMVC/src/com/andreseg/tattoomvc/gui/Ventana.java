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
    private JTextArea list1;
    private JLabel estiloLabel;
    private JLabel tamanoLabel;

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    public JRadioButton getTatuajeRadioButton() {
        return tatuajeRadioButton;
    }

    public void setTatuajeRadioButton(JRadioButton tatuajeRadioButton) {
        this.tatuajeRadioButton = tatuajeRadioButton;
    }

    public JRadioButton getPiercingRadioButton() {
        return piercingRadioButton;
    }

    public void setPiercingRadioButton(JRadioButton piercingRadioButton) {
        this.piercingRadioButton = piercingRadioButton;
    }

    public JTextField getTxtCliente() {
        return txtCliente;
    }

    public void setTxtCliente(JTextField txtCliente) {
        this.txtCliente = txtCliente;
    }

    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    public void setTxtTelefono(JTextField txtTelefono) {
        this.txtTelefono = txtTelefono;
    }

    public JCheckBox getAutorizacionTutorLegalCheckBox() {
        return autorizacionTutorLegalCheckBox;
    }

    public void setAutorizacionTutorLegalCheckBox(JCheckBox autorizacionTutorLegalCheckBox) {
        this.autorizacionTutorLegalCheckBox = autorizacionTutorLegalCheckBox;
    }

    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    public void setTxtPrecio(JTextField txtPrecio) {
        this.txtPrecio = txtPrecio;
    }

    public DatePicker getfNacDP() {
        return fNacDP;
    }

    public void setfNacDP(DatePicker fNacDP) {
        this.fNacDP = fNacDP;
    }

    public DatePicker getfCitaDP() {
        return fCitaDP;
    }

    public void setfCitaDP(DatePicker fCitaDP) {
        this.fCitaDP = fCitaDP;
    }

    public JTextField getTxtEstilo() {
        return txtEstilo;
    }

    public void setTxtEstilo(JTextField txtEstilo) {
        this.txtEstilo = txtEstilo;
    }

    public JTextField getTxtTamano() {
        return txtTamano;
    }

    public void setTxtTamano(JTextField txtTamano) {
        this.txtTamano = txtTamano;
    }

    public JButton getNuevoButton() {
        return nuevoButton;
    }

    public void setNuevoButton(JButton nuevoButton) {
        this.nuevoButton = nuevoButton;
    }

    public JButton getCerrarButton() {
        return cerrarButton;
    }

    public void setCerrarButton(JButton cerrarButton) {
        this.cerrarButton = cerrarButton;
    }

    public JButton getImportarButton() {
        return importarButton;
    }

    public void setImportarButton(JButton importarButton) {
        this.importarButton = importarButton;
    }

    public JButton getExportarButton() {
        return exportarButton;
    }

    public void setExportarButton(JButton exportarButton) {
        this.exportarButton = exportarButton;
    }

    public JTextArea getList1() {
        return list1;
    }

    public void setList1(JTextArea list1) {
        this.list1 = list1;
    }

    public JLabel getEstiloLabel() {
        return estiloLabel;
    }

    public void setEstiloLabel(JLabel estiloLabel) {
        this.estiloLabel = estiloLabel;
    }

    public JLabel getTamanoLabel() {
        return tamanoLabel;
    }

    public void setTamanoLabel(JLabel tamanoLabel) {
        this.tamanoLabel = tamanoLabel;
    }
}

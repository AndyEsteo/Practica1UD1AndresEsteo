package com.andreseg.vehiculosmvc.gui;

import com.andreseg.vehiculosmvc.base.Vehiculo;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;

public class Ventana {
    private JPanel panel1;
    public JRadioButton cocheRadioButton;
    public JRadioButton motoRadioButton;
    public JTextField matriculaTxt;
    public JTextField marcaTxt;
    public JTextArea modeloTxt;
    public JTextField kmsPlazasTxt;
    public JButton nuevoButton;
    public JButton exportarButton;
    public JButton importarButton;
    public JList list1;
    public DatePicker fechaMatriculacionDPicker;
    public JLabel kmsPlazasLbl;

    //creador por mi
    public JFrame frame;
    public DefaultListModel<Vehiculo> dlmvehiculo;


    public Ventana() {
        frame = new JFrame("Vehiculos MVC");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        //centrar

        frame.setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents(){
        dlmvehiculo = new DefaultListModel<Vehiculo>();
        list1.setModel(dlmvehiculo);
    }




}

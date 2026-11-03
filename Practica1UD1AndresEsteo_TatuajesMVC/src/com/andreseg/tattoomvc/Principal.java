package com.andreseg.tattoomvc;

import com.andreseg.tattoomvc.gui.TattooControlador;
import com.andreseg.tattoomvc.gui.Ventana;
import javax.swing.JFrame;

public class Principal {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Estudio de Tatuajes");
        Ventana vista = new Ventana();
        frame.setContentPane(vista.getPanel1());


        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);

        TattooControlador controlador = new TattooControlador(vista);

        frame.setVisible(true);
    }
}
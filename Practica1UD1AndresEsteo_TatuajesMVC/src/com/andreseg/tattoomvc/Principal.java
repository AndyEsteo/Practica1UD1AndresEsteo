package com.andreseg.tattoomvc;

import com.andreseg.tattoomvc.gui.TattooControlador;
import com.andreseg.tattoomvc.gui.TattooModelo;
import com.andreseg.tattoomvc.gui.Ventana;

import javax.swing.JFrame;

public class Principal {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestión Estudio de Tatuajes MVC");
        Ventana vista = new Ventana();
        frame.setContentPane(vista.getPanel1());


        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);

        TattooModelo modelo = new TattooModelo();
        TattooControlador controlador = new TattooControlador(vista, modelo);


        controlador.setFrame(frame);

        frame.setVisible(true);
    }
}
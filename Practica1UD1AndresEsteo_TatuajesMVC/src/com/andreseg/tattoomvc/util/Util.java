package com.andreseg.tattoomvc.util;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class Util {
    public static void mensajeError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static int mensajeConfirmacion(String mensaje, String titulo) {
        return JOptionPane.showConfirmDialog(null, mensaje, titulo, JOptionPane.OK_CANCEL_OPTION);
    }

    public static JFileChooser crearSelectorFichero(File rutaPorDefecto, String descripcionFiltro, String extension) {
        JFileChooser selector = new JFileChooser(rutaPorDefecto);
        FileNameExtensionFilter filtro = new FileNameExtensionFilter(descripcionFiltro, extension);
        selector.setFileFilter(filtro);
        return selector;
    }
}
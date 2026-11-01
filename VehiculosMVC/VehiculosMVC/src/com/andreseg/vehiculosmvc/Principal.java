package com.andreseg.vehiculosmvc;

import com.andreseg.vehiculosmvc.base.Vehiculo;
import com.andreseg.vehiculosmvc.gui.VehiculosControlador;
import com.andreseg.vehiculosmvc.gui.VehiculosModelo;
import com.andreseg.vehiculosmvc.gui.Ventana;

public class Principal {

    public static void main(String[] args) {
        Ventana vista = new Ventana();
        VehiculosModelo modelo = new VehiculosModelo();
        VehiculosControlador controlador = new VehiculosControlador(vista,modelo);
    }
}

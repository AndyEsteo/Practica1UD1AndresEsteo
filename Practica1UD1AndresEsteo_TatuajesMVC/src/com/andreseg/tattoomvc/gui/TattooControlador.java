package com.andreseg.tattoomvc.gui;

import com.andreseg.tattoomvc.base.Piercing;
import com.andreseg.tattoomvc.base.Servicio;
import com.andreseg.tattoomvc.base.Tatuaje;
import com.andreseg.tattoomvc.util.Util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class TattooControlador {

    private Ventana vista;

    private ArrayList<Servicio> listaServicios = new ArrayList<>();

    public TattooControlador(Ventana vista) {
        this.vista = vista;
        inicializarEventos();
    }

    private void inicializarEventos() {
        vista.getTatuajeRadioButton().addActionListener(e -> cambiarCamposTatuaje());
        vista.getPiercingRadioButton().addActionListener(e -> cambiarCamposPiercing());

        vista.getfNacDP().addDateChangeListener(event -> comprobarEdad());

        vista.getTatuajeRadioButton().setSelected(true);
        cambiarCamposTatuaje();
        vista.getAutorizacionTutorLegalCheckBox().setVisible(false);

        vista.getCerrarButton().addActionListener(e -> cerrarAplicacion());

        vista.getNuevoButton().addActionListener(e -> guardarNuevoServicio());
    }

    private void cambiarCamposTatuaje() {
        vista.getEstiloLabel().setText("Estilo:");
        vista.getTamanoLabel().setText("Tamaño (cm):");
    }

    private void cambiarCamposPiercing() {
        vista.getEstiloLabel().setText("Zona del cuerpo:");
        vista.getTamanoLabel().setText("Material:");
    }

    private void comprobarEdad() {
        LocalDate fechaNacimiento = vista.getfNacDP().getDate();
        if (fechaNacimiento != null) {
            int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
            vista.getAutorizacionTutorLegalCheckBox().setVisible(edad < 18);
        }
    }

    public void cerrarAplicacion() {
        if (com.andreseg.tattoomvc.util.Util.mostrarConfirmacion("¿Estás seguro de que quieres salir?", "Confirmar Salida")) {
            System.exit(0);
        }
    }

    private void guardarNuevoServicio() {
        try {
            String cliente = vista.getTxtCliente().getText();
            String telefono = vista.getTxtTelefono().getText();
            double precio = Double.parseDouble(vista.getTxtPrecio().getText()); // Convierte texto a decimal
            LocalDate fechaCita = vista.getfCitaDP().getDate();

            // Validar que la fecha no esté vacía
            if (fechaCita == null) {
                Util.mostrarError("Debes seleccionar una fecha de cita.");
                return;
            }

            //Comprobar que radio buton este marcado
            if (vista.getTatuajeRadioButton().isSelected()) {
                String estilo = vista.getTxtEstilo().getText();
                int tamano = Integer.parseInt(vista.getTxtTamano().getText());

                Tatuaje nuevoTattoo = new Tatuaje(cliente, precio, fechaCita, estilo, tamano);
                listaServicios.add(nuevoTattoo);

            } else {
                String zona = vista.getTxtEstilo().getText();
                String material = vista.getTxtTamano().getText();

                Piercing nuevoPiercing = new Piercing(cliente, precio, fechaCita, zona, material);
                listaServicios.add(nuevoPiercing);
            }

            //ActualizarTextArea
            actualizarConsola();

            //LimpiarCampos
            vista.getTxtCliente().setText("");
            vista.getTxtTelefono().setText("");
            vista.getTxtPrecio().setText("");
            vista.getTxtEstilo().setText("");
            vista.getTxtTamano().setText("");
            vista.getfCitaDP().clear(); // Limpia la fecha

        } catch (NumberFormatException ex) {
            Util.mostrarError("Revisa los campos numéricos (Precio y Tamaño deben ser números).");
        } catch (Exception ex) {
            Util.mostrarError("Error al guardar el servicio: " + ex.getMessage());
        }
    }

    private void actualizarConsola() {
        vista.getList1().setText("");
        for (Servicio s : listaServicios) {
            vista.getList1().append(s.toString() + "\n");
        }
    }
}
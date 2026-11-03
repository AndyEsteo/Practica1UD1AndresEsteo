package com.andreseg.tattoomvc.base;

import java.time.LocalDate;

public abstract class Servicio {
    private String cliente;
    private String telefono;
    private double precio;
    private LocalDate fechaCita;

    public Servicio() {
    }

    public Servicio(String cliente, String telefono, double precio, LocalDate fechaCita) {
        this.cliente = cliente;
        this.telefono = telefono;
        this.precio = precio;
        this.fechaCita = fechaCita;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDate getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDate fechaCita) {
        this.fechaCita = fechaCita;
    }

    @Override
    public String toString() {
        return cliente + " | Tlf: " + telefono + " | Fecha: " + fechaCita;
    }
}
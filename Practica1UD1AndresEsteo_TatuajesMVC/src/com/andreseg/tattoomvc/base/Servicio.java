package com.andreseg.tattoomvc.base;

import java.time.LocalDate;

public class Servicio {
    private String cliente;
    private double precio;
    private LocalDate fechaCita;

    public Servicio(String cliente, double precio, LocalDate fechaCita) {
        this.cliente = cliente;
        this.precio = precio;
        this.fechaCita = fechaCita;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
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
        return "Cliente: " + cliente + " | Precio: " + precio + "€ | Fecha: " + fechaCita;
    }
}
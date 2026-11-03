package com.andreseg.tattoomvc.base;

import java.time.LocalDate;

public class Tatuaje extends Servicio {
    private String estilo;
    private int tamanoCm;

    public Tatuaje() {
    }

    public Tatuaje(String cliente, String telefono, double precio, LocalDate fechaCita, String estilo, int tamanoCm) {
        super(cliente, telefono, precio, fechaCita);
        this.estilo = estilo;
        this.tamanoCm = tamanoCm;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public int getTamanoCm() {
        return tamanoCm;
    }

    public void setTamanoCm(int tamanoCm) {
        this.tamanoCm = tamanoCm;
    }

    @Override
    public String toString() {
        return "TATUAJE -> " + super.toString();
    }
}
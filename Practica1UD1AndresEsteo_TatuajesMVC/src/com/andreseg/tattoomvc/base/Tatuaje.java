package com.andreseg.tattoomvc.base;

import java.time.LocalDate;

public class Tatuaje extends Servicio {
    private String estilo;
    private int tamanoCm;

    public Tatuaje(String cliente, double precio, LocalDate fechaCita, String estilo, int tamanoCm) {
        super(cliente, precio, fechaCita);
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
        return "Tatuaje -> " + super.toString() + " | Estilo: " + estilo + " | Tamaño: " + tamanoCm + "cm";
    }
}
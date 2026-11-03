package com.andreseg.tattoomvc.base;

import java.time.LocalDate;

public class Piercing extends Servicio {
    private String zonaCuerpo;
    private String material;

    public Piercing(String cliente, double precio, LocalDate fechaCita, String zonaCuerpo, String material) {
        super(cliente, precio, fechaCita);
        this.zonaCuerpo = zonaCuerpo;
        this.material = material;
    }

    public String getZonaCuerpo() {
        return zonaCuerpo;
    }

    public void setZonaCuerpo(String zonaCuerpo) {
        this.zonaCuerpo = zonaCuerpo;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "Piercing -> " + super.toString() + " | Zona: " + zonaCuerpo + " | Material: " + material;
    }
}
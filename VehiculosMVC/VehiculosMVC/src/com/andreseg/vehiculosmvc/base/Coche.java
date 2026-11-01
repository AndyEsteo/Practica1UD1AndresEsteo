package com.andreseg.vehiculosmvc.base;

import java.time.LocalDate;

public class Coche extends Vehiculo {

    private int numPlazas;
    public Coche(){
        super();
    }

    public Coche(String matricula, String marca, String modelo, LocalDate fechaMatriculacion, int numPlazas) {
        super(matricula, marca, modelo, fechaMatriculacion);
        this.numPlazas = numPlazas;
    }

    public int getNumPlazas() {
        return numPlazas;
    }

    public void setNumPlazas(int numPlazas) {
        this.numPlazas = numPlazas;
    }

    @Override
    public String toString(){
        return "Coche: " + getMatricula() + " " + getMarca()+ " " + getModelo();
    }
}

package com.medisalud.entity;

public class Paciente {

    private final String nombreCompleto;
    private final String codigo;
    private final int edad;

    public Paciente(String nombreCompleto, String codigo, int edad) {
        this.nombreCompleto = nombreCompleto;
        this.codigo = codigo;
        this.edad = edad;
    }

    public String getNombreCompleto() { return nombreCompleto; }
    public String getCodigo() { return codigo; }
    public int getEdad() { return edad; }

    @Override
    public String toString() {
        return codigo + " | " + nombreCompleto + " | " + edad + " años";
    }
}

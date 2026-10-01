package com.medisalud.patron.estructural;

import com.medisalud.entity.Factura;

public class FacturaConRecargoNocturno extends Factura {

    private static final double RECARGO_NOCTURNO = 15.0;
    private final Factura facturaOriginal;

    public FacturaConRecargoNocturno(Factura facturaOriginal) {
        super(facturaOriginal.getCita(), facturaOriginal.getMontoBase());
        this.facturaOriginal = facturaOriginal;
    }

    @Override
    public double calcularMonto() {
        return facturaOriginal.calcularMonto() + RECARGO_NOCTURNO;
    }

    @Override
    public String toString() {
        return facturaOriginal.toString() + " + recargo nocturno = $" + calcularMonto();
    }
}

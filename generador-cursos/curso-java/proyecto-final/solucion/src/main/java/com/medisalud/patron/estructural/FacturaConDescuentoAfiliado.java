package com.medisalud.patron.estructural;

import com.medisalud.entity.Factura;

public class FacturaConDescuentoAfiliado extends Factura {

    private static final double DESCUENTO_AFILIADO = 10.0;
    private final Factura facturaOriginal;

    public FacturaConDescuentoAfiliado(Factura facturaOriginal) {
        super(facturaOriginal.getCita(), facturaOriginal.getMontoBase());
        this.facturaOriginal = facturaOriginal;
    }

    @Override
    public double calcularMonto() {
        double montoConDescuento = facturaOriginal.calcularMonto() - DESCUENTO_AFILIADO;
        return Math.max(0.0, montoConDescuento);
    }

    @Override
    public String toString() {
        return facturaOriginal.toString() + " - descuento afiliado = $" + calcularMonto();
    }
}

package com.medisalud.concurrencia;

import java.util.concurrent.atomic.AtomicLong;

public class ContadorCodigos {

    private final AtomicLong contador = new AtomicLong(0);
    private final String prefijo;

    public ContadorCodigos(String prefijo) {
        this.prefijo = prefijo;
    }

    public String siguienteCodigo() {
        long numero = contador.incrementAndGet();
        String numeroConRelleno = String.format("%03d", numero);
        return prefijo + numeroConRelleno;
    }
}

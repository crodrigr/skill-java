package com.medisalud.entity;

public enum EstadoCita {
    PENDIENTE,
    CONFIRMADA,
    ATENDIDA,
    CANCELADA;

    public boolean puedeTransicionarA(EstadoCita nuevoEstado) {
        switch (this) {
            case PENDIENTE:
                return nuevoEstado == CONFIRMADA || nuevoEstado == CANCELADA;
            case CONFIRMADA:
                return nuevoEstado == ATENDIDA || nuevoEstado == CANCELADA;
            default:
                return false;
        }
    }
}

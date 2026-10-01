package com.medisalud.patron.comportamiento;

import com.medisalud.entity.Cita;

public interface EstrategiaCosto {

    double calcularCosto(Cita cita);
}

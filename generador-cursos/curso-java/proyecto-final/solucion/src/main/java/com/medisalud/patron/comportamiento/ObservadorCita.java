package com.medisalud.patron.comportamiento;

import com.medisalud.entity.Cita;

public interface ObservadorCita {

    void notificarCambioEstado(Cita cita);
}

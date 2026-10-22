package com.tareas.controlador;

import com.tareas.service.ServicioTareas;
import com.tareas.vista.VistaConsola;

/** Capa de controlador: traduce lo que hace el usuario en llamadas al servicio. */
public class ControladorTareas {

    private final ServicioTareas servicio;
    private final VistaConsola vista;

    public ControladorTareas(ServicioTareas servicio, VistaConsola vista) {
        this.servicio = servicio;
        this.vista = vista;
    }

    public void iniciar() {
        boolean seguir = true;
        while (seguir) {
            try {
                switch (vista.pedirOpcion()) {
                    case 1 -> vista.mostrarMensaje("Creada: " + servicio.crear(vista.pedirTexto("Título")));
                    case 2 -> vista.mostrarTareas(servicio.listar());
                    case 3 -> vista.mostrarMensaje("Completada: " + servicio.completar(vista.pedirNumero("Id")));
                    case 0 -> seguir = false;
                    default -> vista.mostrarMensaje("Opción no válida");
                }
            } catch (IllegalArgumentException e) {
                vista.mostrarMensaje("Error: " + e.getMessage());
            }
        }
        vista.mostrarMensaje("Hasta luego");
    }
}

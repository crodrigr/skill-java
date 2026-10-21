package com.medisalud.controlador;

import com.medisalud.entity.Paciente;
import com.medisalud.service.ServicioPacientes;
import com.medisalud.vista.VistaConsola;

/** Traduce la acción del usuario en llamadas al servicio. */
public class ControladorMediSalud {

    private final ServicioPacientes servicioPacientes;
    private final VistaConsola vista;

    public ControladorMediSalud(ServicioPacientes servicioPacientes, VistaConsola vista) {
        this.servicioPacientes = servicioPacientes;
        this.vista = vista;
    }

    public void iniciar() {
        boolean seguir = true;
        while (seguir) {
            switch (vista.pedirOpcion()) {
                case 1 -> registrar();
                case 2 -> vista.mostrarPacientes(servicioPacientes.listarTodos());
                case 3 -> buscar();
                case 0 -> seguir = false;
                default -> vista.mostrarMensaje("Opción no válida");
            }
        }
        vista.mostrarMensaje("Hasta luego");
    }

    private void registrar() {
        try {
            Paciente p = vista.pedirDatosPaciente();
            servicioPacientes.registrarPaciente(p);
            vista.mostrarMensaje("Paciente registrado");
        } catch (IllegalArgumentException e) {
            vista.mostrarMensaje("Error: " + e.getMessage());
        }
    }

    private void buscar() {
        Paciente p = servicioPacientes.buscarPorCodigo(vista.pedirCodigo());
        vista.mostrarMensaje(p == null ? "No encontrado" : p.toString());
    }
}

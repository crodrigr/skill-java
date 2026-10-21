package com.medisalud;

import com.medisalud.controlador.ControladorMediSalud;
import com.medisalud.repository.AlmacenPacientesLog;
import com.medisalud.repository.PacienteDAO;
import com.medisalud.repository.RepositorioPacientes;
import com.medisalud.repository.RepositorioPacientesMemoria;
import com.medisalud.service.ServicioPacientes;
import com.medisalud.vista.VistaConsola;

/**
 * Paso 4 — El ÚNICO lugar que sabe qué implementación se usa (la inyección).
 *
 * Uso: java com.medisalud.Principal [dao | memoria | log]      (por defecto: dao)
 */
public class Principal {

    public static void main(String[] args) {
        String modo = args.length > 0 ? args[0] : "dao";

        RepositorioPacientes repositorio = switch (modo) {
            case "memoria" -> new RepositorioPacientesMemoria();
            case "log"     -> new AlmacenPacientesLog();
            default        -> new PacienteDAO();
        };
        System.out.println("Repositorio en uso: " + repositorio.getClass().getSimpleName());

        ServicioPacientes servicioPacientes = new ServicioPacientes(repositorio);   // inyección
        ControladorMediSalud controlador = new ControladorMediSalud(servicioPacientes, new VistaConsola());
        controlador.iniciar();
    }
}

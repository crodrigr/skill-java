package com.medisalud;

import com.medisalud.concurrencia.HiloNotificaciones;
import com.medisalud.controlador.ControladorMediSalud;
import com.medisalud.patron.comportamiento.ObservadorCitaNotificacion;
import com.medisalud.patron.creacional.GestorClinica;
import com.medisalud.patron.estructural.FachadaAgendamiento;
import com.medisalud.persistencia.jdbc.CitaDAO;
import com.medisalud.persistencia.jdbc.MedicoDAO;
import com.medisalud.persistencia.jdbc.PacienteDAO;
import com.medisalud.service.ServicioCitas;
import com.medisalud.service.ServicioFacturacion;
import com.medisalud.service.ServicioMedicos;
import com.medisalud.service.ServicioPacientes;
import com.medisalud.vista.VistaConsola;

public class Principal {

    public static void main(String[] args) throws InterruptedException {
        PacienteDAO pacienteDAO = new PacienteDAO();
        MedicoDAO medicoDAO = new MedicoDAO();
        CitaDAO citaDAO = new CitaDAO(pacienteDAO, medicoDAO);
        GestorClinica.inicializar(pacienteDAO, medicoDAO, citaDAO);

        ServicioPacientes servicioPacientes = new ServicioPacientes(pacienteDAO);
        ServicioMedicos servicioMedicos = new ServicioMedicos(medicoDAO);
        ServicioCitas servicioCitas = new ServicioCitas(citaDAO);
        ServicioFacturacion servicioFacturacion = new ServicioFacturacion();

        HiloNotificaciones hiloNotificaciones = new HiloNotificaciones();
        hiloNotificaciones.start();
        ObservadorCitaNotificacion observador = new ObservadorCitaNotificacion(hiloNotificaciones);
        FachadaAgendamiento fachadaAgendamiento = new FachadaAgendamiento(
                servicioPacientes, servicioMedicos, servicioCitas, observador);

        ControladorMediSalud controlador = new ControladorMediSalud(
                servicioPacientes, servicioMedicos, servicioCitas, servicioFacturacion, fachadaAgendamiento);

        VistaConsola vista = new VistaConsola(controlador);
        vista.mostrarMenu();

        hiloNotificaciones.detener();
        hiloNotificaciones.join();
    }
}

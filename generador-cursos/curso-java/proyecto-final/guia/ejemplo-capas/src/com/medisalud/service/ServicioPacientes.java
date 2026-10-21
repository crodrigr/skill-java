package com.medisalud.service;

import com.medisalud.entity.Paciente;
import com.medisalud.repository.RepositorioPacientes;
import java.util.List;

/** Paso 3 — El servicio depende de la INTERFAZ y la recibe por constructor. */
public class ServicioPacientes {

    private final RepositorioPacientes repositorioPacientes;   // tipo = interfaz

    public ServicioPacientes(RepositorioPacientes repositorioPacientes) {
        this.repositorioPacientes = repositorioPacientes;      // lo RECIBE
    }

    public void registrarPaciente(Paciente paciente) {
        if (paciente.getEdad() < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa");
        }
        if (repositorioPacientes.buscarPorCodigo(paciente.getCodigo()) != null) {
            throw new IllegalArgumentException("Ya existe el paciente " + paciente.getCodigo());
        }
        repositorioPacientes.guardar(paciente);
    }

    public Paciente buscarPorCodigo(String codigo) {
        return repositorioPacientes.buscarPorCodigo(codigo);
    }

    public List<Paciente> listarTodos() {
        return repositorioPacientes.listarTodos();
    }
}

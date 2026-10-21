package com.medisalud.repository;

import com.medisalud.entity.Paciente;
import java.util.ArrayList;
import java.util.List;

/** Ejercicio corto resuelto — una tercera implementación; el servicio no cambia. */
public class AlmacenPacientesLog implements RepositorioPacientes {

    private final List<Paciente> pacientes = new ArrayList<>();

    @Override
    public void guardar(Paciente paciente) {
        pacientes.add(paciente);
        System.out.println("   Guardado: " + paciente.getNombreCompleto());
    }

    @Override
    public Paciente buscarPorCodigo(String codigo) {
        for (Paciente p : pacientes) {
            if (p.getCodigo().equals(codigo)) return p;
        }
        return null;
    }

    @Override
    public List<Paciente> listarTodos() { return new ArrayList<>(pacientes); }
}

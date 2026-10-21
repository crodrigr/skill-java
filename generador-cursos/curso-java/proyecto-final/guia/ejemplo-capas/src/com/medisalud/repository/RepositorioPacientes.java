package com.medisalud.repository;

import com.medisalud.entity.Paciente;
import java.util.List;

/** Paso 1 — El contrato: solo firmas, sin código, sin ArrayList, sin SQL. */
public interface RepositorioPacientes {
    void guardar(Paciente paciente);
    Paciente buscarPorCodigo(String codigo);
    List<Paciente> listarTodos();
}

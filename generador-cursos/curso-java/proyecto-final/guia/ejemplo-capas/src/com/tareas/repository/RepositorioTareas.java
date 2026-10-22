package com.tareas.repository;

import com.tareas.entity.Tarea;
import java.util.List;

/** Paso 1 — El contrato: solo firmas. Dice QUÉ se puede hacer, no CÓMO. */
public interface RepositorioTareas {
    void guardar(Tarea tarea);          // crea la tarea o reemplaza la que tenga el mismo id
    Tarea buscarPorId(int id);          // null si no existe
    List<Tarea> listarTodas();
}

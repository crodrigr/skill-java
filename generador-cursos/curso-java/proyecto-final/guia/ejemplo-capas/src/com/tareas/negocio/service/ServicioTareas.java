package com.tareas.negocio.service;

import com.tareas.model.Tarea;
import com.tareas.persistence.repository.RepositorioTareas;
import java.util.List;

/** Paso 3 — Reglas de negocio. Depende de la INTERFAZ y la recibe por constructor. */
public class ServicioTareas {

    private final RepositorioTareas repositorio;   // tipo = interfaz, nunca una clase concreta

    public ServicioTareas(RepositorioTareas repositorio) {
        this.repositorio = repositorio;            // lo RECIBE, no hace new
    }

    public Tarea crear(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }
        Tarea tarea = new Tarea(siguienteId(), titulo.trim(), false);
        repositorio.guardar(tarea);
        return tarea;
    }

    public Tarea completar(int id) {
        Tarea tarea = repositorio.buscarPorId(id);
        if (tarea == null) {
            throw new IllegalArgumentException("No existe la tarea " + id);
        }
        tarea.marcarCompletada();
        repositorio.guardar(tarea);
        return tarea;
    }

    public List<Tarea> listar() {
        return repositorio.listarTodas();
    }

    private int siguienteId() {
        int mayor = 0;
        for (Tarea t : repositorio.listarTodas()) {
            mayor = Math.max(mayor, t.getId());
        }
        return mayor + 1;
    }
}

package com.tareas.repository;

import com.tareas.entity.Tarea;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Paso 2a — Guarda en memoria: al cerrar el programa se pierde todo. */
public class RepositorioTareasMemoria implements RepositorioTareas {

    private final Map<Integer, Tarea> tareas = new LinkedHashMap<>();

    @Override
    public void guardar(Tarea tarea) { tareas.put(tarea.getId(), tarea); }

    @Override
    public Tarea buscarPorId(int id) { return tareas.get(id); }

    @Override
    public List<Tarea> listarTodas() { return new ArrayList<>(tareas.values()); }
}

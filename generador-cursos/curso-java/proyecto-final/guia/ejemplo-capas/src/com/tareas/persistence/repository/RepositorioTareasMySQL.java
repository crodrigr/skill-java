package com.tareas.persistence.repository;

import com.tareas.model.Tarea;
import com.tareas.persistence.dao.TareaDAO;
import java.util.List;

/** Paso 2c — Guarda en MySQL. Cumple el contrato y delega el SQL en el DAO. */
public class RepositorioTareasMySQL implements RepositorioTareas {

    private final TareaDAO dao;   // el repositorio NO tiene SQL: lo delega

    public RepositorioTareasMySQL(TareaDAO dao) {
        this.dao = dao;
    }

    @Override
    public void guardar(Tarea tarea) { dao.guardar(tarea); }

    @Override
    public Tarea buscarPorId(int id) { return dao.buscarPorId(id); }

    @Override
    public List<Tarea> listarTodas() { return dao.listarTodas(); }
}

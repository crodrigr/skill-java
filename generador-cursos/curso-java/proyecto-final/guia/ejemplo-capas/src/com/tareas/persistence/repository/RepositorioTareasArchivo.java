package com.tareas.persistence.repository;

import com.tareas.model.Tarea;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Paso 2b — Guarda en un archivo de texto: sobrevive entre ejecuciones. Formato: id;completada;titulo */
public class RepositorioTareasArchivo implements RepositorioTareas {

    private final Path archivo;

    public RepositorioTareasArchivo(String rutaArchivo) {
        this.archivo = Path.of(rutaArchivo);
    }

    @Override
    public void guardar(Tarea tarea) {
        Map<Integer, Tarea> todas = leer();
        todas.put(tarea.getId(), tarea);
        escribir(todas);
    }

    @Override
    public Tarea buscarPorId(int id) { return leer().get(id); }

    @Override
    public List<Tarea> listarTodas() { return new ArrayList<>(leer().values()); }

    private Map<Integer, Tarea> leer() {
        Map<Integer, Tarea> tareas = new LinkedHashMap<>();
        if (!Files.exists(archivo)) return tareas;
        try {
            for (String linea : Files.readAllLines(archivo, StandardCharsets.UTF_8)) {
                String[] partes = linea.split(";", 3);
                int id = Integer.parseInt(partes[0]);
                tareas.put(id, new Tarea(id, partes[2], Boolean.parseBoolean(partes[1])));
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return tareas;
    }

    private void escribir(Map<Integer, Tarea> tareas) {
        List<String> lineas = new ArrayList<>();
        for (Tarea t : tareas.values()) {
            lineas.add(t.getId() + ";" + t.isCompletada() + ";" + t.getTitulo());
        }
        try {
            Files.write(archivo, lineas, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}

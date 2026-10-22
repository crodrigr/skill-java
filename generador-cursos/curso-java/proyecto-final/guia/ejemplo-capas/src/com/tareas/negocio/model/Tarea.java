package com.tareas.negocio.model;

public class Tarea {

    private final int id;
    private final String titulo;
    private boolean completada;

    public Tarea(int id, String titulo, boolean completada) {
        this.id = id;
        this.titulo = titulo;
        this.completada = completada;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public boolean isCompletada() { return completada; }

    public void marcarCompletada() { this.completada = true; }

    @Override
    public String toString() {
        return (completada ? "[x] " : "[ ] ") + id + " - " + titulo;
    }
}

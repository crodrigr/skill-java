package com.tareas;

import com.tareas.controlador.ControladorTareas;
import com.tareas.repository.RepositorioTareas;
import com.tareas.repository.RepositorioTareasArchivo;
import com.tareas.repository.RepositorioTareasMemoria;
import com.tareas.service.ServicioTareas;
import com.tareas.vista.VistaConsola;

/**
 * Paso 4 — El ÚNICO lugar que sabe qué implementación se usa (la inyección).
 *
 * Uso: java com.tareas.Principal [archivo | memoria]      (por defecto: archivo)
 */
public class Principal {

    public static void main(String[] args) {
        String modo = args.length > 0 ? args[0] : "archivo";

        // 1) Se elige y se CREA la implementación (lo más profundo, primero)
        RepositorioTareas repositorio = modo.equals("memoria")
                ? new RepositorioTareasMemoria()
                : new RepositorioTareasArchivo("tareas.txt");
        System.out.println("Guardando con: " + repositorio.getClass().getSimpleName());

        // 2) Se INYECTA en el servicio (el servicio solo ve la interfaz)
        ServicioTareas servicio = new ServicioTareas(repositorio);

        // 3) Se inyecta el servicio en el controlador, junto con la vista
        ControladorTareas controlador = new ControladorTareas(servicio, new VistaConsola());

        // 4) Arranca la aplicación
        controlador.iniciar();
    }
}

package com.tareas;

import com.tareas.presentacion.controlador.ControladorTareas;
import com.tareas.persistence.dao.TareaDAO;
import com.tareas.persistence.repository.RepositorioTareas;
import com.tareas.persistence.repository.RepositorioTareasArchivo;
import com.tareas.persistence.repository.RepositorioTareasMemoria;
import com.tareas.persistence.repository.RepositorioTareasMySQL;
import com.tareas.negocio.service.ServicioTareas;
import com.tareas.presentacion.vista.VistaConsola;

/**
 * Paso 4 — El ÚNICO lugar que sabe qué implementación se usa (la inyección).
 *
 * Uso: java com.tareas.Principal [archivo | memoria | mysql]      (por defecto: archivo)
 * Para mysql: variables de entorno TAREAS_DB_URL, TAREAS_DB_USER, TAREAS_DB_PASS
 * y el conector de MySQL en el classpath.
 */
public class Principal {

    public static void main(String[] args) {
        String modo = args.length > 0 ? args[0] : "archivo";

        // 1) Se elige y se CREA la implementación (lo más profundo, primero)
        RepositorioTareas repositorio = switch (modo) {
            case "memoria" -> new RepositorioTareasMemoria();
            case "mysql" -> new RepositorioTareasMySQL(new TareaDAO(
                    System.getenv().getOrDefault("TAREAS_DB_URL", "jdbc:mysql://localhost:3306/tareas"),
                    System.getenv().getOrDefault("TAREAS_DB_USER", "root"),
                    System.getenv().getOrDefault("TAREAS_DB_PASS", "")));
            default -> new RepositorioTareasArchivo("tareas.txt");
        };
        System.out.println("Guardando con: " + repositorio.getClass().getSimpleName());

        // 2) Se INYECTA en el servicio (el servicio solo ve la interfaz)
        ServicioTareas servicio = new ServicioTareas(repositorio);

        // 3) Se inyecta el servicio en el controlador, junto con la vista
        ControladorTareas controlador = new ControladorTareas(servicio, new VistaConsola());

        // 4) Arranca la aplicación
        controlador.iniciar();
    }
}

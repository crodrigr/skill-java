package com.tareas.presentacion.vista;

import com.tareas.model.Tarea;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/** Capa de vista: solo pide y muestra datos. No conoce el servicio ni el repositorio. */
public class VistaConsola {

    private final Scanner entrada = new Scanner(System.in);

    public int pedirOpcion() {
        System.out.println("\n1) Crear tarea  2) Listar  3) Completar  0) Salir");
        System.out.print("> ");
        try {
            return Integer.parseInt(entrada.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        } catch (NoSuchElementException e) {
            return 0;   // se acabó la entrada
        }
    }

    public String pedirTexto(String etiqueta) {
        System.out.print(etiqueta + ": ");
        return entrada.nextLine();
    }

    public int pedirNumero(String etiqueta) {
        try {
            return Integer.parseInt(pedirTexto(etiqueta).trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Debe ser un número");
        }
    }

    public void mostrarTareas(List<Tarea> tareas) {
        if (tareas.isEmpty()) System.out.println("(no hay tareas)");
        tareas.forEach(System.out::println);
    }

    public void mostrarMensaje(String mensaje) { System.out.println(mensaje); }
}

package com.medisalud.vista;

import com.medisalud.entity.Paciente;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/** Pide y muestra datos. No conoce el servicio ni el repositorio. */
public class VistaConsola {

    private final Scanner entrada = new Scanner(System.in);

    public int pedirOpcion() {
        System.out.println("\n1) Registrar paciente  2) Listar  3) Buscar por código  0) Salir");
        System.out.print("> ");
        try {
            return Integer.parseInt(entrada.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        } catch (NoSuchElementException e) {
            return 0;   // fin de la entrada
        }
    }

    public Paciente pedirDatosPaciente() {
        System.out.print("Nombre completo: ");
        String nombre = entrada.nextLine();
        System.out.print("Código: ");
        String codigo = entrada.nextLine();
        System.out.print("Edad: ");
        int edad;
        try {
            edad = Integer.parseInt(entrada.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("La edad debe ser un número");
        }
        return new Paciente(nombre, codigo, edad);
    }

    public String pedirCodigo() {
        System.out.print("Código: ");
        return entrada.nextLine();
    }

    public void mostrarPacientes(List<Paciente> pacientes) {
        if (pacientes.isEmpty()) System.out.println("(sin pacientes)");
        pacientes.forEach(System.out::println);
    }

    public void mostrarMensaje(String mensaje) { System.out.println(mensaje); }
}

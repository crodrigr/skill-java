# 🟢 Básico 06 — Identificar deserialización manual frágil

## 🧩 Problema

La Biblioteca Universitaria recupera el registro de un usuario con este código:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class SocioBiblioteca {

    private String nombre;
    private String tipo;
    private int prestamosActivos;

    public SocioBiblioteca(String nombre, String tipo, int prestamosActivos) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.prestamosActivos = prestamosActivos;
    }

    @Override
    public String toString() {
        return nombre + " (" + tipo + ") - " + prestamosActivos + " prestamos activos";
    }
}
```

```java
package com.biblioteca;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Demo {

    public static void main(String[] args) {
        try (BufferedReader lector = new BufferedReader(new FileReader("usuario_manual.txt"))) {
            String nombre = lector.readLine();
            String tipo = lector.readLine();
            int prestamosActivos = Integer.parseInt(lector.readLine());

            SocioBiblioteca socio = new SocioBiblioteca(nombre, tipo, prestamosActivos);
            System.out.println("SocioBiblioteca reconstruido a mano: " + socio);
        } catch (IOException e) {
            System.out.println("No se pudo leer usuario_manual.txt: " + e.getMessage());
        }
    }
}
```

Sin escribir código, responde: ¿qué pasaría si el archivo `usuario_manual.txt` tuviera sus líneas en un
orden distinto al esperado?

## 📏 Criterios de evaluación de la solución

- Identifica que la reconstrucción depende de conocer y respetar el orden exacto de los campos.
- Explica que, si el orden cambia (o el archivo se generó con otro formato), la reconstrucción falla o
  produce un objeto con datos incorrectos, sin ningún aviso claro.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-10**: identificar una deserialización manual frágil.

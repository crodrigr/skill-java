# 🟢 Básico 05 — Identificar serialización manual frágil

## 🧩 Problema

La Biblioteca Universitaria persiste el registro de un usuario con este código:

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

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public int getPrestamosActivos() {
        return prestamosActivos;
    }
}
```

```java
package com.biblioteca;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Demo {

    public static void main(String[] args) {
        SocioBiblioteca socio = new SocioBiblioteca("Carla Nunez", "ESTUDIANTE", 2);

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter("usuario_manual.txt"))) {
            escritor.write(socio.getNombre());
            escritor.newLine();
            escritor.write(socio.getTipo());
            escritor.newLine();
            escritor.write(String.valueOf(socio.getPrestamosActivos()));
            escritor.newLine();
        } catch (IOException e) {
            System.out.println("No se pudo escribir usuario_manual.txt: " + e.getMessage());
            return;
        }

        System.out.println("SocioBiblioteca escrito a mano, campo por campo, en usuario_manual.txt");
    }
}
```

Sin escribir código, responde: si se agrega un campo nuevo a `SocioBiblioteca` (por ejemplo, un correo
electrónico), ¿qué partes del código hay que actualizar para no romper la persistencia?

## 📏 Criterios de evaluación de la solución

- Identifica que `SocioBiblioteca` no implementa `Serializable`, y que cada campo se escribe a mano, en un orden
  fijo.
- Explica que agregar un campo nuevo exigiría actualizar tanto la escritura como cualquier código que
  reconstruya el objeto, respetando el mismo orden — frágil y propenso a errores.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-8**: explicar la interfaz `Serializable`.

# 🟢 Básico 04 — Identificar archivo temporal sin borrar

## 🧩 Problema

La Biblioteca Universitaria procesa solicitudes con este código:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Demo {

    public static void main(String[] args) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter("solicitudes_procesadas.txt"))) {
            escritor.write("Solicitudes ya procesadas");
            escritor.newLine();
        } catch (IOException e) {
            System.out.println("No se pudo escribir el archivo: " + e.getMessage());
            return;
        }

        File archivo = new File("solicitudes_procesadas.txt");
        System.out.println("El archivo ya fue procesado, pero sigue existiendo: " + archivo.exists());
        System.out.println("Nada en este programa lo borra: queda acumulado en el disco para siempre.");
    }
}
```

Sin escribir código, responde: si el programa se ejecuta todos los días, ¿qué pasa con
`solicitudes_procesadas.txt` con el paso del tiempo?

## 📏 Criterios de evaluación de la solución

- Identifica que el archivo nunca se borra, aunque ya fue procesado.
- Explica que, ejecutado repetidamente, el archivo queda acumulado (o sobrescrito sin control) en el
  disco, sin ningún beneficio real de conservarlo.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-7**: reconocer cuándo un archivo temporal debería borrarse.

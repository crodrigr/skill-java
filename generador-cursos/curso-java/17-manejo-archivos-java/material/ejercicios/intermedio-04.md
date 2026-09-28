# 🟡 Intermedio 04 — Aplicar borrado de archivos

## 🧩 Problema

Tienes el mismo procesamiento de la Biblioteca Universitaria del ejercicio Básico 04:

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

Rediséñalo para que el archivo temporal se borre una vez procesado.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Escribir y procesar `solicitudes_procesadas.txt` | `File.exists()` antes de borrar | `true` |
| Mismo caso | `File.exists()` después de borrar | `false` |

## 📏 Criterios de evaluación de la solución

- Invoca `File.delete()` sobre el archivo temporal una vez procesado.
- Confirma `exists()` antes (`true`) y después (`false`) del borrado.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni clases atómicas como parte del diseño.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-7**: implementar el borrado de un archivo.

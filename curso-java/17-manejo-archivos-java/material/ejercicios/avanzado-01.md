# 🔴 Avanzado 01 — Corregir un diseño con dos sub-temas técnicos ausentes

## 🧩 Problema

La Biblioteca Universitaria procesa renovaciones de préstamos con este código, que tiene dos problemas
de diseño distintos:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Demo {

    public static void main(String[] args) {
        String[] renovaciones = {
            "Renovacion: El Quijote para Carla Nunez",
            "Renovacion: Rayuela para Diego Perez"
        };

        // Escribe un archivo temporal mientras procesa, pero nunca lo borra.
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter("renovaciones_temp.txt"))) {
            for (String renovacion : renovaciones) {
                escritor.write(renovacion);
                escritor.newLine();
            }
        } catch (IOException e) {
            System.out.println("No se pudo escribir el archivo temporal: " + e.getMessage());
            return;
        }

        // El resultado final solo se imprime, nunca se persiste en un archivo real.
        System.out.println("Resultado de las renovaciones:");
        for (String renovacion : renovaciones) {
            System.out.println(renovacion);
        }

        File temporal = new File("renovaciones_temp.txt");
        System.out.println("El archivo temporal sigue existiendo: " + temporal.exists());
    }
}
```

Encuentra y corrige las dos violaciones por separado:

1. El resultado final de las renovaciones solo se imprime por consola, nunca se persiste en un archivo
   real — viola escritura.
2. El archivo temporal `renovaciones_temp.txt`, usado solo durante el procesamiento, nunca se borra —
   viola borrado.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Procesar 2 renovaciones | `File.exists()` sobre un archivo de resultado final | `true` |
| Mismo caso | `File.exists()` sobre el archivo temporal, después de procesar | `false` |

## 📏 Criterios de evaluación de la solución

- El resultado final se escribe en un archivo real, no solo se imprime.
- El archivo temporal se borra una vez que ya no hace falta.
- Las dos correcciones son independientes entre sí: una no depende de la otra.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni clases atómicas como parte del diseño.

## 📊 Dificultad

Avanzado

## 🎓 Resultados de aprendizaje

- **RA-6**: implementar escritura en un archivo de texto.
- **RA-7**: implementar el borrado de un archivo.
- **RA-11**: combinar varias herramientas de persistencia sin dejar ninguna ausente.

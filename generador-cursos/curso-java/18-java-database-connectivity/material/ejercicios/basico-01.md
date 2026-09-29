# 🟢 Básico 01 — Identificar violación de configuración/conexión

## 🧩 Problema

La Biblioteca Universitaria consulta los préstamos activos con este código:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class Demo {

    public static void main(String[] args) {
        String[] prestamosActivos = {"El Quijote", "Cien anios de soledad"};

        System.out.println("Prestamos activos (segun lista fija en el codigo):");
        for (String titulo : prestamosActivos) {
            System.out.println("- " + titulo);
        }
    }
}
```

La base de datos real de la biblioteca (tabla `prestamos`) tiene, en este momento, **tres** préstamos
activos: `El Quijote`, `Rayuela` y `Cien años de soledad`.

Sin escribir código, responde: ¿por qué el programa solo menciona dos préstamos, si la base de datos real
tiene tres?

## 📏 Criterios de evaluación de la solución

- Identifica que el programa nunca se conecta a la base de datos: usa una lista fija de títulos escrita
  en el código.
- Explica que, sin una conexión JDBC real, el programa no tiene forma de reflejar cambios que ocurren en
  la base de datos después de escribir el código.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-2**: configurar el driver de MySQL y conectar con `DriverManager`.

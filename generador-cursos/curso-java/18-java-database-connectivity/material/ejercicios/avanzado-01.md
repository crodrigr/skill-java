# 🔴 Avanzado 01 — Corregir un diseño con dos sub-temas técnicos ausentes

## 🧩 Problema

La Biblioteca Universitaria renueva un préstamo (actualiza su fecha) y muestra el resultado con este
código:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo {
    private static final String URL = "jdbc:mysql://localhost:3306/medisalud";
    private static final String USUARIO = "root";
    private static final String CLAVE = "curso_java_root";

    public static void main(String[] args) {
        String tituloBuscado = "Rayuela";
        String nuevaFecha = "2026-10-05";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
             Statement sentencia = conexion.createStatement()) {

            sentencia.executeUpdate(
                "UPDATE prestamos SET fecha_prestamo = '" + nuevaFecha
                    + "' WHERE titulo = '" + tituloBuscado + "'");

            try (ResultSet resultado = sentencia.executeQuery(
                    "SELECT titulo, socio, fecha_prestamo FROM prestamos WHERE titulo = '"
                        + tituloBuscado + "'")) {
                System.out.println("=== Renovacion de prestamo ===");
                while (resultado.next()) {
                    System.out.println(resultado.getString("titulo") + " - "
                            + resultado.getString("socio") + " (" + resultado.getDate("fecha_prestamo") + ")");
                }
            }

            sentencia.executeUpdate(
                "UPDATE prestamos SET fecha_prestamo = '2026-09-25' WHERE titulo = '"
                    + tituloBuscado + "'");
        } catch (SQLException e) {
            System.out.println("Error real de base de datos: " + e.getMessage());
        }
    }
}
```

Este diseño tiene **dos** problemas técnicos ausentes a la vez: identifícalos y corrígelos por separado.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Renovar "Rayuela" a una fecha nueva y consultarlo | Datos mostrados | Título, socio y fecha nueva, sin errores de sintaxis ni riesgo de inyección |

## 📏 Criterios de evaluación de la solución

- **Corrección 1 (consultas inseguras)**: usa `PreparedStatement` en el `UPDATE` y en el `SELECT`, en vez
  de concatenar el título y la fecha en el texto SQL.
- **Corrección 2 (capas mezcladas)**: separa el acceso a datos (Modelo), la presentación (Vista) y la
  coordinación (Controlador) en clases distintas.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni excepciones propias como parte del diseño.

## 📊 Dificultad

Avanzado

## 🎓 Resultados de aprendizaje

- **RA-8**: combinar `PreparedStatement` con una organización MVC para un caso dado.

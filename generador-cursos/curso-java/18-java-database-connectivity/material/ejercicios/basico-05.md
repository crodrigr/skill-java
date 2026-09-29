# 🟢 Básico 05 — Identificar capas MVC mezcladas

## 🧩 Problema

La Biblioteca Universitaria muestra sus préstamos activos con este código:

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
        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
             Statement sentencia = conexion.createStatement();
             ResultSet resultado = sentencia.executeQuery(
                     "SELECT titulo, socio, fecha_prestamo FROM prestamos ORDER BY id")) {
            System.out.println("=== Prestamos activos ===");
            while (resultado.next()) {
                System.out.println(resultado.getString("titulo") + " - " + resultado.getString("socio")
                        + " (" + resultado.getDate("fecha_prestamo") + ")");
            }
        } catch (SQLException e) {
            System.out.println("Error real de base de datos: " + e.getMessage());
        }
    }
}
```

Sin escribir código, responde: si mañana la biblioteca pidiera mostrar los préstamos ordenados por fecha
en vez de por id, ¿qué parte de este código habría que tocar, y por qué eso es un síntoma de un problema
de diseño?

## 📏 Criterios de evaluación de la solución

- Identifica que la conexión JDBC, la consulta y el formato de impresión están todos mezclados en el
  mismo método.
- Explica que cualquier cambio en la presentación (como el orden mostrado) obliga a tocar el mismo código
  que accede a la base de datos, en vez de un componente separado dedicado solo a la presentación.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-6**: explicar la arquitectura MVC y la responsabilidad de cada capa.

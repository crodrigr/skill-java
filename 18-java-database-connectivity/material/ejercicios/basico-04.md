# 🟢 Básico 04 — Identificar operaciones sin parametrizar

## 🧩 Problema

La Biblioteca Universitaria registra, actualiza y borra un préstamo de prueba con este código:

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
        String titulo = "El Principito";
        String socio = "Marco Leiva";
        String fecha = "2026-09-28";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
             Statement sentencia = conexion.createStatement()) {

            sentencia.executeUpdate(
                "INSERT INTO prestamos (titulo, socio, fecha_prestamo) VALUES ('"
                    + titulo + "', '" + socio + "', '" + fecha + "')",
                Statement.RETURN_GENERATED_KEYS);
            int id;
            try (ResultSet claves = sentencia.getGeneratedKeys()) {
                claves.next();
                id = claves.getInt(1);
            }

            sentencia.executeUpdate(
                "UPDATE prestamos SET socio = 'Marco Leiva Rojas' WHERE id = " + id);

            try (ResultSet resultado = sentencia.executeQuery(
                    "SELECT titulo, socio FROM prestamos WHERE id = " + id)) {
                resultado.next();
                System.out.println("Prestamo registrado: " + resultado.getString("titulo")
                        + ", " + resultado.getString("socio"));
            }

            sentencia.executeUpdate("DELETE FROM prestamos WHERE id = " + id);
            System.out.println("Prestamo de prueba borrado. La tabla vuelve a su estado original.");
        } catch (SQLException e) {
            System.out.println("Error real de base de datos: " + e.getMessage());
        }
    }
}
```

Sin escribir código, responde: ¿qué pasaría si `titulo` o `socio` vinieran de un formulario, y alguien
escribiera un valor con una comilla simple (`'`) dentro?

## 📏 Criterios de evaluación de la solución

- Identifica que las tres operaciones (`INSERT`, `UPDATE`, `SELECT`) concatenan los valores directamente
  en el texto SQL.
- Explica que un valor con una comilla simple rompería la sintaxis de la consulta, y que un valor
  pensado a propósito podría alterar su significado (inyección SQL), igual que en una consulta de
  lectura.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-5**: implementar operaciones CRUD con `PreparedStatement`.

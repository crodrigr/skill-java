# 🟡 Intermedio 03 — Aplicar consultas parametrizadas

## 🧩 Problema

Tienes el mismo programa de la Biblioteca Universitaria del ejercicio Básico 03:

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
        String tituloBuscado = "Nada' OR '1'='1";

        String sql = "SELECT titulo FROM libros WHERE titulo = '" + tituloBuscado + "'";
        System.out.println("Consulta ejecutada: " + sql);

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
             Statement sentencia = conexion.createStatement();
             ResultSet resultado = sentencia.executeQuery(sql)) {
            int filas = 0;
            while (resultado.next()) {
                filas++;
                System.out.println("- " + resultado.getString("titulo"));
            }
            System.out.println("Filas devueltas: " + filas);
        } catch (SQLException e) {
            System.out.println("Error real de base de datos: " + e.getMessage());
        }
    }
}
```

Rediséñalo para que la búsqueda use `PreparedStatement` con un parámetro, en vez de concatenar el valor
en el texto SQL.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Buscar `"Nada' OR '1'='1"` con `PreparedStatement` | Filas devueltas | 0 (el valor se trata como texto literal, no altera la consulta) |

## 📏 Criterios de evaluación de la solución

- Usa `PreparedStatement` con un marcador `?` para el valor buscado, en vez de concatenarlo.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni excepciones propias como parte del diseño.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-4**: aplicar consultas parametrizadas para un caso dado.

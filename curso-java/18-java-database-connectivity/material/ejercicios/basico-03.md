# 🟢 Básico 03 — Identificar una consulta insegura

## 🧩 Problema

La Biblioteca Universitaria busca un libro por título con este código:

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

Sin escribir código, responde: ¿por qué el programa devuelve los tres libros de la tabla, si el título
buscado (`"Nada' OR '1'='1"`) no coincide con ninguno de verdad?

## 📏 Criterios de evaluación de la solución

- Identifica que el programa concatena el valor buscado directamente en el texto SQL.
- Explica que el valor `"Nada' OR '1'='1"` altera la condición real de la consulta, convirtiéndola en
  "trae cualquier fila" — una inyección SQL real.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-4**: explicar consultas parametrizadas y el riesgo de inyección SQL.

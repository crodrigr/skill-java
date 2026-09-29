# 🟡 Intermedio 05 — Aplicar arquitectura MVC

## 🧩 Problema

Tienes el mismo programa de la Biblioteca Universitaria del ejercicio Básico 05:

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

Rediséñalo separando el acceso a datos, la presentación y la coordinación en capas Modelo, Vista y
Controlador.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Tabla `prestamos` con 3 filas reales | Salida del programa reorganizado | Exactamente igual a la salida de la versión original (Básico 05) |

## 📏 Criterios de evaluación de la solución

- Declara un Modelo (`Prestamo` + una clase de acceso a datos) que consulta la base de datos real.
- Declara una Vista dedicada exclusivamente a formatear e imprimir, sin ninguna llamada JDBC propia.
- Declara un Controlador que coordina ambas, sin ejecutar SQL ni imprimir directamente.
- El programa compila, se ejecuta y produce exactamente la misma salida que la versión original.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni excepciones propias como parte del diseño.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-7**: aplicar la arquitectura MVC a un proyecto Java para un caso dado.

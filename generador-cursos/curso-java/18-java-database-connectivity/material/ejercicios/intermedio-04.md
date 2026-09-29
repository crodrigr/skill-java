# 🟡 Intermedio 04 — Aplicar operaciones con PreparedStatement

## 🧩 Problema

Tienes el mismo programa de la Biblioteca Universitaria del ejercicio Básico 04:

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

Rediséñalo para que las tres operaciones (`INSERT`, `UPDATE`, `SELECT`) usen `PreparedStatement`, en vez
de concatenar los valores en el texto SQL.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Registrar, actualizar y consultar un préstamo con `PreparedStatement` | Datos del préstamo tras actualizar | `El Principito, Marco Leiva Rojas`, sin errores de sintaxis ni riesgo de inyección |

## 📏 Criterios de evaluación de la solución

- Las tres operaciones usan `PreparedStatement` con marcadores `?`, en vez de concatenar valores.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni excepciones propias como parte del diseño.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-5**: aplicar operaciones CRUD con `PreparedStatement` para un caso dado.

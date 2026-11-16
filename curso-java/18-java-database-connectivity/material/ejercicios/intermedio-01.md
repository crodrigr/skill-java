# 🟡 Intermedio 01 — Aplicar configuración del controlador y conexión

## 🧩 Problema

Tienes el mismo programa de la Biblioteca Universitaria del ejercicio Básico 01:

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

La base de datos real de la biblioteca (tabla `prestamos`) tiene tres préstamos activos.

Rediséñalo para que se conecte con `DriverManager` y consulte el contenido real de la tabla, en vez de
asumirlo.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Tabla `prestamos` con 3 filas reales | Cantidad de préstamos listados | 3, coincidiendo exactamente con el contenido real de la base de datos |

## 📏 Criterios de evaluación de la solución

- Se conecta con `DriverManager.getConnection()`, usando try-with-resources.
- Consulta la tabla real con `Statement`/`ResultSet`, en vez de una lista fija en el código.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni excepciones propias como parte del diseño.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-2**: implementar la configuración del controlador y la conexión para un caso dado.

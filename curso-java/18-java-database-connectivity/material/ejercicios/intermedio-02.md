# 🟡 Intermedio 02 — Aplicar uso de JDBC

## 🧩 Problema

Tienes el mismo programa de la Biblioteca Universitaria del ejercicio Básico 02:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class Demo {

    public static void main(String[] args) {
        System.out.println("Catalogo de libros (segun lista fija en el codigo):");
        System.out.println("- El Quijote, Cervantes");
        System.out.println("- Cien anios de soledad, Garcia Marquez");
    }
}
```

La tabla real `libros` tiene tres libros.

Rediséñalo para que consulte el catálogo real con `Statement`/`ResultSet`, en vez de asumirlo.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Tabla `libros` con 3 filas reales | Cantidad de libros listados | 3, coincidiendo exactamente con el contenido real de la base de datos |

## 📏 Criterios de evaluación de la solución

- Ejecuta `executeQuery()` sobre la tabla real, en vez de una lista fija en el código.
- Recorre el `ResultSet` con `next()` hasta agotar las filas.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni excepciones propias como parte del diseño.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-3**: implementar el uso de `Statement`/`ResultSet` para un caso dado.

# 🟡 Intermedio 03 — Declarar un enum y usarlo en un switch

## 🧩 Problema

Tienes este programa de Biblioteca Universitaria, que representa el estado de un préstamo con un
`String` suelto:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

// TODO: reemplazar el String suelto por un enum EstadoPrestamo con ACTIVO, DEVUELTO y VENCIDO,
// y usarlo como condicion de un switch.
public class Demo {
    public static void main(String[] args) {
        String estado = "ACTIVO";
        if (estado.equals("ACTIVO")) {
            System.out.println("El prestamo esta activo");
        }
    }
}
```

Reemplaza el `String` por un `enum EstadoPrestamo` con `ACTIVO`, `DEVUELTO` y `VENCIDO`, y úsalo como
condición de un `switch` que imprima un mensaje distinto para cada valor.

## 🧪 Casos de prueba

| Entrada | Operación | Salida esperada |
|---|---|---|
| `estado = EstadoPrestamo.ACTIVO` | `switch` | `"El prestamo esta activo"` |

## 📏 Criterios de evaluación de la solución

- Declara `EstadoPrestamo` como `enum`, con los tres valores pedidos.
- Usa un `switch` sobre el `enum`, con un `case` por valor.

## 🚧 Restricciones

- No se usa `Set` ni `Map` (temas de puntos anteriores del módulo, ya cubiertos por separado).
- No se usa `try`/`catch`.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-9**: declarar un `enum`.
- **RA-10**: usar un valor de `enum` como condición de un `switch`.

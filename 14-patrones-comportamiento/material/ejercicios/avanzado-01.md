# 🔴 Avanzado 01 — Corregir un diseño con dos patrones ausentes

## 🧩 Problema

La Biblioteca Universitaria aprueba solicitudes de préstamo especial con este código, que tiene dos
problemas de diseño distintos:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class GestorDeSolicitudes {
    public String aprobar(String tituloEjemplar, String excepcionalidad) {
        String resultado;
        if (excepcionalidad.equals("BAJA")) {
            resultado = "Aprobado nivel 1: " + tituloEjemplar;
        } else if (excepcionalidad.equals("ALTA")) {
            resultado = "Aprobado nivel 2: " + tituloEjemplar;
        } else {
            throw new IllegalArgumentException("Excepcionalidad desconocida: " + excepcionalidad);
        }
        System.out.println("Inventario: reservando " + tituloEjemplar);
        System.out.println("Avisos: notificando aprobacion de " + tituloEjemplar);
        return resultado;
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        GestorDeSolicitudes gestor = new GestorDeSolicitudes();
        System.out.println(gestor.aprobar("Manuscrito comun", "BAJA"));
        System.out.println(gestor.aprobar("Manuscrito unico", "ALTA"));
    }
}
```

Encuentra y corrige las dos violaciones por separado:

1. `aprobar()` resuelve el nivel de aprobación con un condicional único — viola Chain of
   Responsibility.
2. `aprobar()` notifica directamente a inventario y a avisos — viola Mediator.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Aprobar un ejemplar de excepcionalidad `"BAJA"` y otro de `"ALTA"` | Salida completa del programa antes y después de corregir | Idéntica, carácter por carácter |
| `Demo.java` después de corregir | Cantidad de niveles de aprobación resueltos por condicional | Ninguno: se resuelven con una cadena de manejadores |
| Se agrega un módulo nuevo a notificar al aprobar | Clases de nivel de aprobación (`AprobadorNivel1`, `AprobadorNivel2`) | Sin ninguna modificación (solo cambia el mediador) |

## 📏 Criterios de evaluación de la solución

- Declara una interfaz (por ejemplo, `ManejadorDeSolicitud`) y una cadena de manejadores que resuelve
  el nivel de aprobación sin condicional único.
- Declara una clase mediadora (por ejemplo, `MediadorDeSolicitudes`) que coordina la notificación a
  inventario y avisos.
- La salida por consola no cambia respecto de la versión original.
- Las dos correcciones son independientes entre sí: una no depende de la otra.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni excepciones como parte del diseño (más allá de señalar, sin capturarla,
  que ningún nivel pudo aprobar).

## 📊 Dificultad

Avanzado

## 🎓 Resultados de aprendizaje

- **RA-4**: implementar Chain of Responsibility para un caso dado.
- **RA-13**: implementar Mediator para un caso dado.
- **RA-29**: dado un problema de diseño nuevo, elegir el patrón de comportamiento adecuado y
  justificarlo.

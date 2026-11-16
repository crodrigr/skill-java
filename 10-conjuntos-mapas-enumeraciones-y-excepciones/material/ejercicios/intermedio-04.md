# 🟡 Intermedio 04 — Agregar try/catch/finally a un programa que falla

## 🧩 Problema

Este programa de MediSalud termina con una excepción real sin capturar:

## 💻 Código o contexto de partida

```java falla-en-ejecucion
package com.medisalud;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        List<String> pacientesEnEspera = new ArrayList<>();
        pacientesEnEspera.add("Marta Diaz");
        pacientesEnEspera.add("Jorge Paz");

        System.out.println("Siguiente paciente: " + pacientesEnEspera.get(5));
    }
}
```

## 🧪 Casos de prueba

Salida real del programa sin manejar (`pacientesEnEspera` con 2 elementos, `get(5)`):

```text
Exception in thread "main" java.lang.IndexOutOfBoundsException: Index 5 out of bounds for length 2
```

Agrégale `try`/`catch`/`finally`: el `catch` debe imprimir un mensaje en vez de dejar que el programa
termine, y el `finally` debe imprimir que la consulta terminó, se haya encontrado el paciente o no.

## 📏 Criterios de evaluación de la solución

- El `catch` captura `IndexOutOfBoundsException` (o una superclase apropiada) e imprime un mensaje, sin
  dejar que el programa termine.
- El `finally` se ejecuta siempre.
- El programa termina con código `0`.

## 🚧 Restricciones

- No se usa `throw` ni una excepción personalizada (temas del Ejemplo 08).

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-12**: usar `try`/`catch` para capturar una excepción real.
- **RA-13**: usar `finally`.

# 🔴 Avanzado 01 — Corregir un programa con errores de esta etapa

## 🧩 Problema

Este programa de Biblioteca Universitaria tiene dos errores distintos, cada uno en su propia ronda.

## 💻 Código o contexto de partida

**Ronda 1** — no compila:

```java no-compila
package com.biblioteca;

import java.util.HashMap;
import java.util.Map;

public class RevisionDeCatalogo {
    public static void main(String[] args) {
        Map<String, String> catalogoLibros = new HashMap<>();
        catalogoLibros.put("LIB-012", "Cien anios de soledad");

        try {
            Integer.parseInt("no-es-un-numero");
        } catch (RuntimeException e) {
            System.out.println("Error general: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Numero invalido: " + e.getMessage());
        }
    }
}
```

**Ronda 2** — compila, pero termina con una excepción real sin capturar:

```java falla-en-ejecucion
package com.biblioteca;

import java.util.HashMap;
import java.util.Map;

public class RevisionDeCatalogo {
    public static void main(String[] args) {
        Map<String, String> catalogoLibros = new HashMap<>();
        catalogoLibros.put("LIB-012", "Cien anios de soledad");

        String titulo = catalogoLibros.get("LIB-999");
        System.out.println("longitud=" + titulo.length());
    }
}
```

## 🧪 Casos de prueba

Mensaje real de compilación de la Ronda 1:

```text
./RevisionDeCatalogo.java:15: error: exception NumberFormatException has already been caught
        } catch (NumberFormatException e) {
          ^
1 error
```

Salida real de la Ronda 2:

```text
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "String.length()" because "<local2>" is null
```

Corrige ambas rondas:

1. En la Ronda 1, invertí el orden de los `catch`: el tipo más específico (`NumberFormatException`) va
   primero, y el más general (`RuntimeException`) va después.
2. En la Ronda 2, agrega un `try`/`catch` que maneje el acceso a una clave inexistente.

## 📏 Criterios de evaluación de la solución

- Ronda 1: el programa corregido compila.
- Ronda 2: el programa corregido termina con código `0`, sin dejar pasar la excepción sin capturar.

## 🚧 Restricciones

- No se agregan clases nuevas: solo se corrige el orden de los `catch` y se agrega el manejo que falta.
- No se usa `throw` ni una excepción personalizada.

## 📊 Dificultad

Avanzado

## 🎓 Resultados de aprendizaje

- **RA-11**: explicar qué es una excepción.
- **RA-12**: usar `try`/`catch` para capturar una excepción real.
- **RA-15**: identificar y corregir los errores frecuentes de esta etapa.

# 💡 Ejemplo 05 — Excepción sin capturar

## 🌍 Contexto

Desde el Módulo 4 viste varios errores que terminan un programa en tiempo de ejecución: un índice fuera
de rango, una conversión numérica inválida. Todos compartían algo: el curso todavía no te había
enseñado cómo evitarlos, así que el programa simplemente terminaba. Este ejemplo muestra el mismo tipo
de error, ahora con `Map`, como punto de partida antes de aprender a manejarlo.

**Qué busca demostrar este ejemplo**: con una ejecución real, qué pasa cuando el resultado `null` de un
`Map.get(...)` se usa sin comprobar antes si la clave existía.

## 📚 Caso de estudio

Biblioteca Universitaria: buscar el título de un libro por un código que no está en el catálogo.

## 🌳 Árbol de archivos (como se vería en VS Code)

```text
Modulo10ConjuntosMapasEnumeracionesYExcepciones/
└── src/
    └── com/
        └── biblioteca/
            └── Demo.java
```

## 💻 Archivo: Demo.java

```java falla-en-ejecucion
package com.biblioteca;

import java.util.HashMap;
import java.util.Map;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        Map<String, String> catalogoLibros = new HashMap<>();
        catalogoLibros.put("LIB-012", "Cien anios de soledad");
        catalogoLibros.put("LIB-030", "El principito");

        String titulo = catalogoLibros.get("LIB-999");
        System.out.println("longitud del titulo=" + titulo.length());
    }
}
```

## 🧭 Explicación paso a paso

1. `catalogoLibros.get("LIB-999")` no lanza nada: como esa clave no existe, `get` simplemente devuelve
   `null`.
2. `titulo` queda con el valor `null`.
3. `titulo.length()` intenta invocar un método sobre `null`: ahí es donde ocurre el error real, no un
   instante antes.
4. Como nadie maneja el error, el programa **termina** en ese punto: ninguna línea después de esa se
   ejecuta.

## ✅ Resultado esperado

```text
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "String.length()" because "<local2>" is null
```

## 🧪 Casos de prueba

| Entrada | Operación | Resultado |
|---|---|---|
| `catalogoLibros` sin la clave `"LIB-999"` | `catalogoLibros.get("LIB-999")` | `null` (sin excepción todavía) |
| Mismo caso | `titulo.length()` sobre ese `null` | Termina con `NullPointerException` real |

## 🔍 Análisis: errores frecuentes

- Confundir "un `Map` sin esa clave" con "un error": `get` sobre una clave inexistente **no** es, por sí
  solo, un problema — devuelve `null` sin quejarse. El error aparece recién cuando ese `null` se usa de
  una forma que lo requiere.
- El panel Problems **no** detecta este caso antes de ejecutar: no hay forma de que el análisis estático
  sepa, en general, si una clave va a estar presente en tiempo de ejecución.
- El mensaje real de `NullPointerException` usa un rótulo genérico (`"<local2>"`) en vez del nombre real
  de la variable (`titulo`) porque el generador compila sin información de depuración completa (mismo
  criterio que en los Módulos 4 y 5).

## ❓ Preguntas de repaso

**1. [Selección]** ¿Qué devuelve `catalogoLibros.get("LIB-999")` si esa clave no está en el `Map`?

- **A.** Lanza una excepción inmediatamente.
- **B.** Devuelve `null`.
- **C.** Devuelve una cadena vacía.
- **D.** El programa no compila.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** `get` sobre una clave inexistente devuelve `null`, sin lanzar nada por sí
solo.

</details>

**2. [Selección múltiple]** ¿Cuáles afirmaciones son verdaderas sobre este ejemplo?

- **A.** El programa termina abruptamente.
- **B.** El error ocurre en la línea del `get`.
- **C.** El error ocurre en la línea donde se usa `titulo.length()`.
- **D.** El panel Problems marca el error antes de ejecutar.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A y C.** B es falsa: `get` no falla; el error ocurre al invocar `.length()`
sobre el `null`. D es falsa: el panel no lo detecta.

</details>

**3. [Abierta]** ¿Qué tendrías que hacer para que el programa no termine abruptamente en este caso?

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta esperada:** envolver el código riesgoso en un `try`, y agregar un `catch` que capture
`NullPointerException` (o comprobar antes con `containsKey` o comparando con `null`) — tema del Ejemplo
06.

</details>

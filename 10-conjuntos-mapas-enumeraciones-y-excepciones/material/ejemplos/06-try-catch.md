# 💡 Ejemplo 06 — try/catch

## 🌍 Contexto

El Ejemplo 05 terminó abruptamente. `try`/`catch` te deja **capturar** esa misma excepción: el código
riesgoso va dentro del `try`, y si falla, el `catch` correspondiente se ejecuta en su lugar — sin que el
programa se caiga entero.

**Qué busca demostrar este ejemplo**: cómo envolver el mismo caso del Ejemplo 05 en `try`/`catch`, y que
el programa sigue corriendo después.

## 📚 Caso de estudio

Biblioteca Universitaria: el mismo catálogo y el mismo código inexistente del Ejemplo 05, ahora
manejado.

## 🌳 Árbol de archivos (como se vería en VS Code)

```text
Modulo10ConjuntosMapasEnumeracionesYExcepciones/
└── src/
    └── com/
        └── biblioteca/
            └── Demo.java
```

## 💻 Archivo: Demo.java

```java
package com.biblioteca;

import java.util.HashMap;
import java.util.Map;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        Map<String, String> catalogoLibros = new HashMap<>();
        catalogoLibros.put("LIB-012", "Cien anios de soledad");
        catalogoLibros.put("LIB-030", "El principito");

        try {
            String titulo = catalogoLibros.get("LIB-999");
            System.out.println("longitud del titulo=" + titulo.length());
        } catch (NullPointerException e) {
            System.out.println("No se encontro el libro pedido");
        }
        System.out.println("El programa sigue despues del try/catch");
    }
}
```

## 🧭 Explicación paso a paso

1. El código que antes terminaba el programa (Ejemplo 05) ahora va dentro de un `try`.
2. `catch (NullPointerException e)` declara qué tipo de excepción capturar; cuando ocurre, ese bloque se
   ejecuta en vez de que el programa termine.
3. La línea `System.out.println("El programa sigue despues del try/catch");`, **fuera** del
   `try`/`catch`, se ejecuta igual: la única diferencia con el Ejemplo 05 es que ahora el programa llega
   hasta ahí.

## ✅ Resultado esperado

```text
No se encontro el libro pedido
El programa sigue despues del try/catch
```

## 🧪 Casos de prueba

| Entrada | Operación | Salida esperada |
|---|---|---|
| `catalogoLibros.get("LIB-999")` dentro de un `try` | Ejecución | Imprime `"No se encontro el libro pedido"` y sigue corriendo |

## 🔍 Análisis: errores frecuentes

- Capturar un tipo de excepción distinto al que realmente puede ocurrir (por ejemplo,
  `catch (ArithmeticException e)` en vez de `catch (NullPointerException e)`): el `catch` nunca se
  ejecutaría, y la excepción real seguiría terminando el programa sin que nadie la capture.

## ❓ Preguntas de repaso

**1. [Selección]** ¿Qué pasa con el programa después de que el `catch` se ejecuta?

- **A.** El programa termina igual.
- **B.** El programa sigue corriendo, en la línea después del bloque `try`/`catch`.
- **C.** El `try` se vuelve a intentar.
- **D.** Se lanza una segunda excepción automáticamente.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Una vez que el `catch` maneja la excepción, el programa continúa
normalmente después de todo el bloque `try`/`catch`.

</details>

**2. [Selección múltiple]** ¿Cuáles afirmaciones son verdaderas sobre este ejemplo?

- **A.** El `catch` declara `NullPointerException`.
- **B.** El programa termina con código distinto de `0`.
- **C.** `"El programa sigue despues del try/catch"` se imprime.
- **D.** El `catch` se ejecuta.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, C y D.** B es falsa: al capturar la excepción, el programa termina con
código `0`.

</details>

**3. [Abierta]** ¿Qué pasaría si el `catch` declarara `IllegalArgumentException` en vez de
`NullPointerException`?

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta esperada:** el `catch` no capturaría la excepción real (`NullPointerException` no es un
`IllegalArgumentException`), así que el programa terminaría igual que en el Ejemplo 05, sin manejarla.

</details>

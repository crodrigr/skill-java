# 💡 Ejemplo 03 — Longitud de cadena

## 🌍 Contexto

`length()` devuelve cuántos caracteres tiene una cadena. Es el primer método que usarás para
**recorrer** un texto carácter por carácter, combinando lo aprendido en el Módulo 3: un `for` que va de
`0` a `length() - 1`.

- `cadena.length()`: la cantidad de caracteres.
- `cadena.isEmpty()`: `true` si `length()` es `0`.
- Cada tilde y la `ñ` cuentan como **un solo carácter** (ya lo viste con `toUpperCase` en el Módulo 1).

**Qué busca demostrar este ejemplo**: cómo obtener la longitud de una cadena y usarla para recorrerla
con un bucle, y qué diferencia hay entre un texto vacío y uno que simplemente no tiene lo que buscas.

## 📚 Caso de estudio

La **Biblioteca Universitaria** quiere saber cuántas vocales tiene el título de un libro, recorriendo
cada carácter con un `for`.

## 🌳 Árbol de archivos (como se vería en VS Code)

Agrega la clase `LongitudDelTitulo.java` al paquete `com.biblioteca` del proyecto
`Modulo04CadenasConversiones`:

```text
Modulo04CadenasConversiones
└── src
    └── com
        ├── biblioteca
        │   └── LongitudDelTitulo.java   ← nuevo en este ejemplo
        └── medisalud
            ├── CodigoDeCita.java
            └── CreacionDeCadenas.java
```

## 💻 Archivo: LongitudDelTitulo.java

```java
package com.biblioteca;

public class LongitudDelTitulo {

    public static void main(String[] args) {
        // Datos de entrada
        String tituloLibro = "Biblioteca Universitaria";

        System.out.println("Longitud: " + tituloLibro.length());
        System.out.println("¿Está vacío? " + tituloLibro.isEmpty());

        int vocales = 0;
        for (int i = 0; i < tituloLibro.length(); i++) {
            char letra = Character.toLowerCase(tituloLibro.charAt(i));
            if (letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o' || letra == 'u') {
                vocales++;
            }
        }
        System.out.println("Vocales: " + vocales);
    }
}
```

## 🧭 Explicación paso a paso

1. `tituloLibro.length()` da `24`: cuenta cada letra y cada espacio.
2. `tituloLibro.isEmpty()` da `false`, porque la longitud no es `0`.
3. El `for` recorre desde `0` hasta `tituloLibro.length() - 1` (como aprendiste en el Módulo 3),
   obteniendo cada carácter con `charAt(i)`.
4. `Character.toLowerCase(letra)` convierte cada carácter a minúscula antes de comparar, para que
   contar las vocales no dependa de si están en mayúscula o minúscula.
5. El resultado es `12` vocales en "Biblioteca Universitaria".

## ✅ Resultado esperado

```text
Longitud: 24
¿Está vacío? false
Vocales: 12
```

## 🧪 Casos de prueba

| Título | Longitud | Vocales |
|---|---|---|
| [] | `0` | `0` |
| [A] | `1` | `1` |
| [Biblioteca Universitaria] | `24` | `12` |

## 🔍 Análisis: errores frecuentes

**Error — Confundir "sin vocales" con "vacío" (error lógico).**

```java error-logico
package com.biblioteca;

public class LongitudDelTitulo {

    public static void main(String[] args) {
        String tituloLibro = "";
        int vocales = 0;
        for (int i = 0; i < tituloLibro.length(); i++) {
            char letra = Character.toLowerCase(tituloLibro.charAt(i));
            if (letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o' || letra == 'u') {
                vocales++;
            }
        }
        System.out.println("Vocales: " + vocales);
    }
}
```

```text
Vocales: 0
```

El programa muestra `Vocales: 0` tanto para un título vacío como para uno que de verdad no tiene
vocales (por ejemplo, un código sin letras). El panel Problems no marca ningún problema: el `for` no
se ejecuta ninguna vez cuando la cadena está vacía, y el resultado (`0`) es matemáticamente correcto,
pero puede confundirse con "no encontré vocales" si no se revisa también `isEmpty()` antes.

## ❓ Preguntas de repaso

**1. [Selección]** **Pregunta:** ¿cuánto vale `"MediSalud".length()`?

- **A.** `8`
- **B.** `9`
- **C.** `10`
- **D.** Da un error de compilación.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** "MediSalud" tiene 9 letras.

</details>

**2. [Selección múltiple]** **Pregunta:** ¿cuáles afirmaciones son verdaderas?

- **A.** `"".length()` da `0`.
- **B.** `"".isEmpty()` da `true`.
- **C.** Un `for` que recorre una cadena vacía no ejecuta su bloque ninguna vez.
- **D.** Las tildes no cuentan como caracteres para `length()`.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y C.** D es falsa: cada tilde y la `ñ` cuentan como un carácter.

</details>

**3. [Abierta]** ¿Por qué un `for` que recorre caracteres de una cadena usa `length()` en su condición
en vez de un número fijo?

<details>
<summary>🔑 Ver respuesta modelo</summary>

Porque cada cadena puede tener una longitud distinta; usar `length()` hace que el bucle se adapte al
texto real en vez de asumir un tamaño fijo, y evita salirse del rango válido de índices.

</details>

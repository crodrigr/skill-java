# 💡 Ejemplo 04 — Extracción de caracteres

## 🌍 Contexto

`charAt(indice)` devuelve el carácter que está en una posición de la cadena. Los índices empiezan en
`0`; el último índice válido es siempre `length() - 1`.

```text
"Ana"
 A  n  a
 0  1  2   ← índices
```

Pedir un índice igual o mayor que `length()` (o negativo) **detiene el programa**: Java no lo redondea
ni lo ignora.

**Qué busca demostrar este ejemplo**: cómo obtener un carácter con `charAt`, cómo recorrer una cadena
contando ocurrencias de una letra, y qué pasa exactamente cuando el índice se pasa de rango.

## 📚 Caso de estudio

La **Biblioteca Universitaria** quiere saber cuántas veces aparece una letra en el nombre de un autor.

## 🌳 Árbol de archivos (como se vería en VS Code)

Agrega la clase `ConteoDeLetras.java` al paquete `com.biblioteca` del proyecto
`Modulo04CadenasConversiones`:

```text
Modulo04CadenasConversiones
└── src
    └── com
        ├── biblioteca
        │   ├── ConteoDeLetras.java   ← nuevo en este ejemplo
        │   └── LongitudDelTitulo.java
        └── medisalud
            ├── CodigoDeCita.java
            └── CreacionDeCadenas.java
```

## 💻 Archivo: ConteoDeLetras.java

```java
package com.biblioteca;

public class ConteoDeLetras {

    public static void main(String[] args) {
        // Datos de entrada
        String autorLibro = "Gabriel Garcia Marquez";

        System.out.println("Primera letra: " + autorLibro.charAt(0));
        System.out.println("Última letra: " + autorLibro.charAt(autorLibro.length() - 1));

        int apariciones = 0;
        for (int i = 0; i < autorLibro.length(); i++) {
            if (Character.toLowerCase(autorLibro.charAt(i)) == 'a') {
                apariciones++;
            }
        }
        System.out.println("Apariciones de 'a': " + apariciones);
    }
}
```

## 🧭 Explicación paso a paso

1. `autorLibro.charAt(0)` da la primera letra: `G`.
2. `autorLibro.charAt(autorLibro.length() - 1)` da la última: `z`. Usar `length() - 1` (y no un número
   fijo) funciona sin importar cuán largo sea el nombre.
3. El `for` recorre cada posición y compara, en minúscula, si el carácter es `'a'`; el resultado es 4
   apariciones.

## ✅ Resultado esperado

```text
Primera letra: G
Última letra: z
Apariciones de 'a': 4
```

## 🧪 Casos de prueba

| Cadena | `charAt(0)` | `charAt(length()-1)` | Apariciones de 'a' |
|---|---|---|---|
| `""` | (no se puede: no hay carácter en la posición 0) | (no se puede) | `0` |
| `"a"` | `a` | `a` | `1` |
| `"Gabriel Garcia Marquez"` | `G` | `z` | `4` |

## 🔍 Análisis: errores frecuentes

**Error — Índice igual a la longitud (falla en ejecución).**

> ⚠️ **Este programa se detiene con un error.** Es intencional: sirve para mostrar el mensaje real.

```java falla-en-ejecucion
package com.biblioteca;

public class ConteoDeLetras {

    public static void main(String[] args) {
        String autorLibro = "Gabriel Garcia Marquez";
        System.out.println(autorLibro.charAt(autorLibro.length()));
    }
}
```

```text
Exception in thread "main" java.lang.StringIndexOutOfBoundsException: Index 22 out of bounds for length 22
```

`autorLibro.length()` es `22`, así que sus índices válidos van de `0` a `21`. Pedir el índice `22`
(la longitud misma) se sale por uno. El panel Problems no marca ningún problema: el índice es una
variable que Java no puede predecir en tiempo de compilación. La defensa es comprobar
`indice < cadena.length()` antes de llamar a `charAt`.

## ❓ Preguntas de repaso

**1. [Selección]** Con `String s = "Java";`. **Pregunta:** ¿cuál es el índice del último carácter?

- **A.** `4`
- **B.** `3`
- **C.** `5`
- **D.** No tiene último índice.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** `"Java"` tiene longitud 4; el último índice válido es `length() - 1 = 3`.

</details>

**2. [Selección múltiple]** **Pregunta:** ¿cuáles afirmaciones sobre `charAt` son verdaderas?

- **A.** Los índices empiezan en 0.
- **B.** `cadena.charAt(cadena.length())` es válido y da el último carácter.
- **C.** Un índice negativo también detiene el programa.
- **D.** El error de índice fuera de rango es un error de compilación.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A y C.** B es falsa: ese índice ya se pasa por uno. D es falsa: es una falla en
tiempo de **ejecución**, no de compilación; el panel Problems no la detecta.

</details>

**3. [Abierta]** ¿Por qué `cadena.charAt(cadena.length() - 1)` funciona para cualquier cadena no vacía,
sin importar su longitud?

<details>
<summary>🔑 Ver respuesta modelo</summary>

Porque el índice se calcula a partir de la longitud real de la cadena en ese momento, en vez de un
número fijo. Así siempre apunta al último carácter válido, sea cual sea el tamaño del texto.

</details>

# 💡 Ejemplo 11 — Mutabilidad

## 🌍 Contexto

Ya lo viste en varios ejemplos: `toUpperCase()`, `trim()`, `replace()`... ninguno cambia la cadena
original. Esto no es casualidad: `String` es **inmutable**. Una vez creado, su contenido no puede
cambiar. Cada método que "transforma" una cadena en realidad **crea una cadena nueva** y la devuelve.

Cuando necesitas construir un texto **por partes**, sobre todo dentro de un bucle, `String` se vuelve
incómodo: cada `+=` crea una cadena nueva y descarta la anterior. Para eso existe `StringBuilder`: un
objeto **mutable** que sí puede cambiar su contenido sin crear uno nuevo cada vez.

| | `String` | `StringBuilder` |
|---|---|---|
| ¿Se puede modificar? | No (inmutable) | Sí (mutable) |
| Cada "cambio" | Crea un objeto nuevo | Modifica el mismo objeto |
| Se usa con | Texto que no cambia mucho | Texto que se arma por partes (bucles) |

Los métodos principales de `StringBuilder`: `.append(texto)` agrega al final, `.insert(indice, texto)`
inserta en una posición, `.reverse()` invierte el contenido, `.length()` da la longitud, y
`.toString()` lo convierte a un `String` normal.

**Qué busca demostrar este ejemplo**: por qué `String` es inmutable, cómo usar `StringBuilder` para
construir texto por partes, y por qué comparar dos `StringBuilder` con `.equals()` no compara su
contenido.

## 📚 Caso de estudio

La **Biblioteca Universitaria** arma un listado de usuarios registrados, agregando cada nombre al
listado.

## 🌳 Árbol de archivos (como se vería en VS Code)

Agrega la clase `ListadoDeUsuarios.java` al paquete `com.biblioteca`:

```text
Modulo04CadenasConversiones
└── src
    └── com
        ├── biblioteca
        │   ├── ConteoDeLetras.java
        │   ├── CuposDeSala.java
        │   ├── ListadoDeUsuarios.java   ← nuevo en este ejemplo
        │   ├── LongitudDelTitulo.java
        │   └── PromedioDePrestamos.java
        └── medisalud
            └── (...)
```

## 💻 Archivo: ListadoDeUsuarios.java

```java
package com.biblioteca;

public class ListadoDeUsuarios {

    public static void main(String[] args) {
        // Datos de entrada
        String nombreUsuario1 = "Ana Torres";
        String nombreUsuario2 = "Carlos Ramírez";
        String nombreUsuario3 = "Lucía Gómez";

        // String es inmutable: cada método devuelve un texto nuevo
        String original = "biblioteca";
        String mayusculas = original.toUpperCase();
        System.out.println("Original: " + original + " | Mayúsculas: " + mayusculas);

        // StringBuilder es mutable: el mismo objeto va cambiando
        StringBuilder listado = new StringBuilder();
        listado.append("- ").append(nombreUsuario1).append("\n");
        listado.append("- ").append(nombreUsuario2).append("\n");
        listado.append("- ").append(nombreUsuario3).append("\n");
        System.out.print(listado);

        listado.insert(0, "Usuarios registrados:\n");
        System.out.println("Longitud del listado: " + listado.length());

        StringBuilder copia = new StringBuilder(nombreUsuario1);
        copia.reverse();
        System.out.println("Nombre al revés: " + copia);
        System.out.println("Como texto: " + copia.toString());
    }
}
```

## 🗺️ Diagrama

`String` crea un objeto nuevo en cada transformación; `StringBuilder` modifica el mismo objeto:

```mermaid
flowchart TD
    subgraph Inmutable["String (inmutable)"]
        A["\"biblioteca\""] -- toUpperCase() --> B["\"BIBLIOTECA\" (objeto nuevo)"]
    end
    subgraph Mutable["StringBuilder (mutable)"]
        C["listado (vacío)"] -- append --> C
        C -- insert --> C
    end
```

## 🧭 Explicación paso a paso

1. `original.toUpperCase()` devuelve una cadena nueva; `original` sigue igual.
2. `new StringBuilder()` crea un `StringBuilder` vacío. Cada `.append(...)` **modifica el mismo
   objeto**, agregando texto al final; por eso se pueden encadenar (`.append(a).append(b)`).
3. `.insert(0, texto)` agrega texto **al principio**, desplazando lo que ya había.
4. `.length()` funciona igual que en `String`, pero refleja el contenido actual (que puede haber
   cambiado).
5. `.reverse()` invierte el contenido del mismo objeto.
6. `.toString()` convierte el `StringBuilder` a un `String` normal, por ejemplo para compararlo o
   guardarlo donde se espere texto.

## ✅ Resultado esperado

```text
Original: biblioteca | Mayúsculas: BIBLIOTECA
- Ana Torres
- Carlos Ramírez
- Lucía Gómez
Longitud del listado: 66
Nombre al revés: serroT anA
Como texto: serroT anA
```

## 🧪 Casos de prueba

| Usuarios en el listado | ¿Qué construye `append`? |
|---|---|
| 1 | Una sola línea |
| 3 | Tres líneas, en el orden en que se agregaron |

## 🔍 Análisis: errores frecuentes

**Error — Comparar dos `StringBuilder` con `.equals()` (error lógico).**

```java error-logico
package com.biblioteca;

public class ListadoDeUsuarios {

    public static void main(String[] args) {
        StringBuilder a = new StringBuilder("Biblioteca");
        StringBuilder b = new StringBuilder("Biblioteca");
        System.out.println("¿Son iguales con equals? " + a.equals(b));
        System.out.println("¿Son iguales comparando el contenido? " + a.toString().equals(b.toString()));
    }
}
```

```text
¿Son iguales con equals? false
¿Son iguales comparando el contenido? true
```

A diferencia de `String`, `StringBuilder` **no** redefine `.equals()` para comparar contenido: compara
si son el mismo objeto (igual que `==`). El panel Problems no marca ningún problema. Para comparar el
contenido de dos `StringBuilder`, conviértelos primero a `String` con `.toString()` y compara esos
textos con `.equals()`.

## ❓ Preguntas de repaso

**1. [Selección]** **Pregunta:** ¿qué le pasa a una cadena `String` cuando se le llama un método como
`.trim()` o `.toUpperCase()`?

- **A.** Se modifica directamente.
- **B.** No cambia; el método devuelve una cadena nueva.
- **C.** Se convierte en un `StringBuilder`.
- **D.** Se borra.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** `String` es inmutable: ningún método cambia su contenido. Siempre devuelven
una cadena nueva.

</details>

**2. [Selección múltiple]** **Pregunta:** ¿cuáles afirmaciones sobre `StringBuilder` son verdaderas?

- **A.** Es mutable: `.append(...)` modifica el mismo objeto.
- **B.** `.equals()` entre dos `StringBuilder` compara su contenido, igual que en `String`.
- **C.** `.toString()` lo convierte en un `String` normal.
- **D.** Conviene usarlo quen se construye texto por partes dentro de un bucle.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, C y D.** B es falsa: `StringBuilder` no redefine `.equals()` para comparar
contenido.

</details>

**3. [Abierta]** ¿Por qué conviene `StringBuilder` en vez de concatenar con `+=` dentro de un bucle
que se repite muchas veces?

<details>
<summary>🔑 Ver respuesta modelo</summary>

Porque cada `+=` sobre un `String` crea una cadena nueva y descarta la anterior: con muchas
repeticiones, eso significa crear muchos objetos intermedios sin necesidad. `StringBuilder` modifica
el mismo objeto en cada `.append(...)`, sin crear cadenas de más.

</details>

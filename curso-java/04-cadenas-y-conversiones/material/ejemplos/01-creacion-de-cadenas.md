# 💡 Ejemplo 01 — Creación de cadenas

## 🌍 Contexto

Ya usabas cadenas en los módulos anteriores para mostrar texto en consola. Ahora vas a mirarlas más de
cerca. Un `String` es un **objeto**: un valor que vive en la memoria. Una variable de tipo `String` no
guarda el texto directamente, guarda una **referencia** a ese objeto (piensa en la referencia como la
dirección donde vive el texto). Esta idea explica por qué `==` y `.equals()` no siempre dan lo mismo:

- `==` compara si dos variables apuntan **al mismo objeto**.
- `.equals()` compara si **dicen lo mismo**, sin importar si son el mismo objeto.

Hay cuatro formas de tener una cadena:

- Un **literal**: `String saludo = "Bienvenido";`. Java guarda los literales en un espacio interno (el
  "pool de cadenas") y **reutiliza** el mismo objeto cuando dos literales dicen lo mismo.
- `new String("...")`: crea **siempre** un objeto nuevo, aunque el contenido sea igual a uno que ya
  existe.
- La **cadena vacía**: `String vacia = "";`. Es un objeto válido, con longitud 0.
- La **cadena nula**: `String nula = null;`. No es un objeto: es la ausencia de uno. Llamar a un método
  sobre una cadena nula detiene el programa.

**Qué busca demostrar este ejemplo**: la diferencia entre crear una cadena con un literal y con `new
String(...)`, por qué `==` y `.equals()` pueden dar resultados distintos, y qué ocurre si se usa una
cadena nula sin darse cuenta.

## 🏥 Caso de estudio

**MediSalud** arma mensajes de bienvenida para sus pacientes. Vas a comparar dos formas de crear el
mismo texto y a ver qué pasa cuando un dato que "debería" tener un nombre en realidad no lo tiene.

## 🌳 Árbol de archivos (como se vería en VS Code)

Crea un proyecto llamado `Modulo04CadenasConversiones` como aprendiste en el Módulo 1 (**Java: Create
Java Project... → No build tools**) y ve agregando en él una clase por cada ejemplo. En este ejemplo
agregas `CreacionDeCadenas.java` dentro de `src/com/medisalud`:

```text
Modulo04CadenasConversiones
└── src
    └── com
        └── medisalud
            └── CreacionDeCadenas.java   ← nuevo en este ejemplo
```

## 💻 Archivo: CreacionDeCadenas.java

```java
package com.medisalud;

public class CreacionDeCadenas {

    public static void main(String[] args) {
        // Datos de entrada
        String saludo1 = "Bienvenido";
        String saludo2 = new String("Bienvenido");
        String cadenaVacia = "";
        String unCaracter = "A";

        System.out.println("saludo1 == saludo2: " + (saludo1 == saludo2));
        System.out.println("saludo1.equals(saludo2): " + saludo1.equals(saludo2));
        System.out.println("cadenaVacia.isEmpty(): " + cadenaVacia.isEmpty());
        System.out.println("cadenaVacia.length(): " + cadenaVacia.length());
        System.out.println("unCaracter: " + unCaracter + " (length: " + unCaracter.length() + ")");
    }
}
```

## 🗺️ Diagrama

Dos literales iguales apuntan al mismo objeto del "pool" de cadenas; `new String(...)` crea uno aparte:

```mermaid
flowchart LR
    subgraph Pool["Pool de cadenas"]
        obj1["\"Bienvenido\""]
    end
    saludo1["saludo1"] --> obj1
    saludo2Literal["(otro literal \"Bienvenido\")"] --> obj1
    saludo2["saludo2 = new String(...)"] --> obj2["\"Bienvenido\" (objeto aparte)"]
```

## 🧭 Explicación paso a paso

1. `saludo1` es un literal: Java lo guarda en el pool de cadenas.
2. `saludo2` se crea con `new String("Bienvenido")`: aunque el texto es igual, es **otro objeto**.
3. `saludo1 == saludo2` compara las referencias (¿el mismo objeto?): da `false`.
4. `saludo1.equals(saludo2)` compara el contenido: da `true`, porque los dos dicen "Bienvenido".
5. `cadenaVacia.isEmpty()` y `.length()` muestran que la cadena vacía es un objeto real, con longitud 0.
6. El programa aparte (`falla-en-ejecucion`) simula un dato que a veces no llega: `obtenerNombreOpcional()`
   puede devolver `null`. Cuando eso ocurre y el programa llama a `.length()` sobre el resultado sin
   comprobarlo antes, el programa se detiene.

## ✅ Resultado esperado

```text
saludo1 == saludo2: false
saludo1.equals(saludo2): true
cadenaVacia.isEmpty(): true
cadenaVacia.length(): 0
unCaracter: A (length: 1)
```

## 🧪 Casos de prueba

| Cadena | `.isEmpty()` | `.length()` |
|---|---|---|
| `""` | `true` | `0` |
| `"A"` | `false` | `1` |
| `"Bienvenido"` | `false` | `10` |

## 🔍 Análisis: errores frecuentes

**Error — Usar una cadena nula sin comprobarlo antes (falla en ejecución).**

> ⚠️ **Este programa se detiene con un error.** Es intencional: sirve para mostrar el mensaje real que
> verías en tu consola.

```java falla-en-ejecucion
package com.medisalud;

public class CreacionDeCadenas {

    public static void main(String[] args) {
        String nombre = obtenerNombreOpcional();
        System.out.println("Longitud: " + nombre.length());
    }

    static String obtenerNombreOpcional() {
        // Simula un dato que a veces no llega (por ejemplo, un paciente sin nombre registrado todavía)
        return null;
    }
}
```

```text
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "String.length()" because "<local1>" is null
```

El panel Problems **no** marca ningún error aquí: el dato nulo viene de otra parte del programa (un
método), no de una asignación obvia en la misma línea, así que el editor no tiene forma de saberlo de
antemano. La única defensa es comprobar el dato antes de usarlo (por ejemplo, con una condición que
pregunte si es distinto de `null`, que verás con más detalle en un módulo posterior).

## ❓ Preguntas de repaso

**1. [Selección]** Se ejecuta `String a = "Java"; String b = "Java"; String c = new String("Java");`.
**Pregunta:** ¿cuál comparación da `true`?

- **A.** `a == c`
- **B.** `b == c`
- **C.** `a == b`
- **D.** Ninguna de las anteriores.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: C.** `a` y `b` son dos literales iguales: Java reutiliza el mismo objeto del pool
de cadenas. `c` se creó con `new String(...)`, así que es un objeto aparte.

</details>

**2. [Selección múltiple]** **Pregunta:** ¿cuáles afirmaciones sobre crear cadenas son verdaderas?

- **A.** `new String("Ana")` siempre crea un objeto distinto de un literal `"Ana"`.
- **B.** La cadena vacía `""` y la cadena nula `null` son lo mismo.
- **C.** `""` .isEmpty()` da `true`.
- **D.** Llamar a un método sobre una cadena nula detiene el programa.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, C y D.** B es falsa: la cadena vacía es un objeto real de longitud 0; la
nula no es ningún objeto.

</details>

**3. [Abierta]** Explica con tus palabras por qué `==` puede dar `false` entre dos cadenas que
"dicen lo mismo".

<details>
<summary>🔑 Ver respuesta modelo</summary>

Porque `==` compara si las dos variables apuntan al mismo objeto en memoria, no si el contenido es
igual. Dos cadenas creadas por separado con `new String(...)` (o una que llega de otra parte del
programa) pueden tener el mismo contenido y aun así ser objetos distintos. Para comparar el contenido
se usa `.equals()`.

</details>

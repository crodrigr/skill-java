# 💡 Ejemplo 02 — HashMap

## 🌍 Contexto

Un `Set` agrupa valores sin duplicados, pero no te deja asociarle nada a cada valor. Cuando necesitás
identificar cada dato con una **clave única** y guardarle un valor (por ejemplo, el código de un libro y
su título), un `Set` no alcanza: necesitás un `Map`. `HashMap` es la implementación más común.

**Qué busca demostrar este ejemplo**: cómo declarar un `HashMap`, usar sus operaciones más comunes, y
qué pasa al hacer `put` con una clave que ya existe.

## 📚 Caso de estudio

Biblioteca Universitaria: un catálogo que asocia el código de cada libro con su título.

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
        catalogoLibros.put("LIB-045", "Rayuela");
        catalogoLibros.put("LIB-012", "Cien anios de soledad");
        catalogoLibros.put("LIB-030", "El principito");
        catalogoLibros.put("LIB-012", "Cien anios de soledad (2da edicion)");

        System.out.println("size=" + catalogoLibros.size());
        System.out.println("LIB-012=" + catalogoLibros.get("LIB-012"));
        System.out.println("contiene LIB-099=" + catalogoLibros.containsKey("LIB-099"));

        catalogoLibros.remove("LIB-030");
        System.out.println("size tras remove=" + catalogoLibros.size());

        for (Map.Entry<String, String> entrada : catalogoLibros.entrySet()) {
            System.out.println(entrada.getKey() + " -> " + entrada.getValue());
        }
    }
}
```

## 🧭 Explicación paso a paso

1. `catalogoLibros.put("LIB-012", "Cien anios de soledad")` agrega el par; llamar a `put` de nuevo con
   la misma clave (`"LIB-012"`) **actualiza** el valor asociado, no agrega un par nuevo.
2. `get("LIB-012")` devuelve el valor actual asociado a esa clave; `containsKey` comprueba si una clave
   existe, sin necesidad de comparar el valor.
3. `remove("LIB-030")` saca ese par del `Map`; `size()` refleja la cantidad de pares actual.
4. `entrySet()` recorre todos los pares clave-valor; `HashMap` no garantiza en qué orden.

## ✅ Resultado esperado

```text
size=3
LIB-012=Cien anios de soledad (2da edicion)
contiene LIB-099=false
size tras remove=2
LIB-045 -> Rayuela
LIB-012 -> Cien anios de soledad (2da edicion)
```

## 🧪 Casos de prueba

| Entrada | Operación | Salida esperada |
|---|---|---|
| `put("LIB-012", ...)` dos veces con valores distintos | `catalogoLibros.get("LIB-012")` | El **segundo** valor puesto |
| Mismo catálogo | `catalogoLibros.size()` | `3` (la clave repetida no agrega un par) |
| `containsKey("LIB-099")`, código no agregado | resultado | `false` |

## 🔍 Análisis: errores frecuentes

- Esperar que `put` con una clave repetida lance una excepción o agregue un segundo par: en realidad
  **reemplaza** el valor, en silencio.
- Suponer que `entrySet()` recorre un `HashMap` en el orden en que se agregaron los pares: no hay
  ninguna garantía de orden (a diferencia de `TreeMap`, Ejemplo 03).

## ❓ Preguntas de repaso

**1. [Selección]** ¿Qué pasa al hacer `put` con una clave que ya existe en un `HashMap`?

- **A.** Se lanza una excepción.
- **B.** Se agrega un segundo par con la misma clave.
- **C.** Se actualiza el valor asociado a esa clave.
- **D.** No pasa nada: el `put` se ignora.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: C.** El valor nuevo reemplaza al anterior; la clave sigue siendo única.

</details>

**2. [Selección múltiple]** ¿Cuáles afirmaciones son verdaderas sobre `HashMap`?

- **A.** Cada clave es única.
- **B.** `get` de una clave inexistente devuelve `null`, sin lanzar ninguna excepción por sí solo.
- **C.** El recorrido con `entrySet()` respeta siempre el orden de inserción.
- **D.** `containsKey` comprueba si una clave existe.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y D.** C es falsa: `HashMap` no garantiza ningún orden de recorrido.

</details>

**3. [Abierta]** ¿Por qué `catalogoLibros.get("LIB-099")` no lanza ninguna excepción, aunque
`"LIB-099"` nunca se haya agregado?

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta esperada:** `get` sobre una clave inexistente simplemente devuelve `null`; no es, por sí
solo, un error. El problema real aparece recién si ese `null` se usa después de una forma que lo
requiera (por ejemplo, invocando un método sobre el resultado) — tema de los Ejemplos 05 y 06.

</details>

# 💡 Ejemplo 03 — TreeMap

## 🌍 Contexto

`TreeMap` es otra implementación de `Map`, con las mismas operaciones básicas que `HashMap`. La
diferencia real aparece al recorrerlo: `TreeMap` mantiene sus claves siempre ordenadas.

**Qué busca demostrar este ejemplo**: con una ejecución real, que un `TreeMap` recorre sus claves
ordenadas, a diferencia de un `HashMap` con exactamente los mismos pares.

## 📚 Caso de estudio

Biblioteca Universitaria: el mismo catálogo código → título del Ejemplo 02, esta vez como `TreeMap`.

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
import java.util.TreeMap;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada (mismo catalogo del Ejemplo 02, sin el remove)
        Map<String, String> catalogoHash = new HashMap<>();
        catalogoHash.put("LIB-045", "Rayuela");
        catalogoHash.put("LIB-012", "Cien anios de soledad");
        catalogoHash.put("LIB-030", "El principito");

        Map<String, String> catalogoOrdenado = new TreeMap<>(catalogoHash);

        System.out.println("HashMap keySet=" + catalogoHash.keySet());
        System.out.println("TreeMap keySet=" + catalogoOrdenado.keySet());

        for (Map.Entry<String, String> entrada : catalogoOrdenado.entrySet()) {
            System.out.println(entrada.getKey() + " -> " + entrada.getValue());
        }
    }
}
```

## 🧭 Explicación paso a paso

1. `catalogoHash` es un `HashMap` con los mismos tres pares del Ejemplo 02 (sin la actualización ni el
   `remove`, para comparar sobre el mismo punto de partida).
2. `new TreeMap<>(catalogoHash)` crea un `TreeMap` a partir de esos mismos pares.
3. Imprimir `keySet()` de ambos, uno junto al otro, muestra la diferencia real: el `HashMap` no sigue
   ningún orden reconocible; el `TreeMap` sí, alfabético por clave.
4. Recorrer el `TreeMap` con `entrySet()` entrega los pares siempre en ese mismo orden, ejecución tras
   ejecución.

## ✅ Resultado esperado

```text
HashMap keySet=[LIB-030, LIB-045, LIB-012]
TreeMap keySet=[LIB-012, LIB-030, LIB-045]
LIB-012 -> Cien anios de soledad
LIB-030 -> El principito
LIB-045 -> Rayuela
```

## 🧪 Casos de prueba

| Entrada | Operación | Salida esperada |
|---|---|---|
| `{"LIB-045", "LIB-012", "LIB-030"}` como `HashMap` | `keySet()` | Orden no alfabético (`[LIB-030, LIB-045, LIB-012]`, sin garantía de mantenerse igual entre ejecuciones distintas del JDK) |
| Mismos pares como `TreeMap` | `keySet()` | `[LIB-012, LIB-030, LIB-045]`, siempre alfabético |

## 🔍 Análisis: errores frecuentes

- Confundir "no garantiza orden" con "siempre desordenado": un `HashMap` **podría** coincidir con el
  orden alfabético por casualidad con otro conjunto de claves — lo que hay que recordar es que no lo
  **garantiza**, no que siempre se vea distinto.
- Elegir `TreeMap` sin necesitarlo: si el programa no depende de que las claves queden ordenadas,
  `HashMap` alcanza y evita el costo extra de mantener el orden.

## ❓ Preguntas de repaso

**1. [Selección]** ¿Qué garantiza un `TreeMap` que un `HashMap` no garantiza?

- **A.** Que las claves no se repitan.
- **B.** Que las claves queden ordenadas al recorrerlas.
- **C.** Que `get` sea más rápido.
- **D.** Que los valores no se repitan.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Ambos garantizan claves únicas (A es de cualquier `Map`); la diferencia real
es el orden de recorrido.

</details>

**2. [Selección múltiple]** ¿Cuándo conviene elegir `TreeMap` en vez de `HashMap`?

- **A.** Cuando el programa necesita mostrar los datos siempre en el mismo orden de clave.
- **B.** Cuando no importa en qué orden se recorran los pares.
- **C.** Cuando se necesita ordenar por clave sin tener que llamar a un método de ordenamiento aparte.
- **D.** Siempre: `TreeMap` es una mejora estricta sobre `HashMap`.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A y C.** B describe el caso donde `HashMap` alcanza; D es falsa: mantener el
orden tiene su propio costo, así que no es una mejora "gratis".

</details>

**3. [Abierta]** El Ejemplo 02 y este ejemplo usan exactamente los mismos tres pares. ¿Por qué el orden
de `keySet()` es distinto entre ambos?

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta esperada:** porque `HashMap` y `TreeMap` organizan internamente sus pares de forma distinta:
`HashMap` los ubica según el código hash de la clave (sin relación con el orden alfabético), mientras
que `TreeMap` los mantiene siempre ordenados por clave. Los datos son los mismos; la estructura elegida
es lo que cambia el orden de recorrido.

</details>

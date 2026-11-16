# 💡 Ejemplo 09 — LinkedList

## 🌍 Contexto

`LinkedList` es otra implementación de `List`, respaldada por nodos enlazados entre sí (cada elemento
"conoce" al siguiente y al anterior). Es especialmente eficiente para insertar o eliminar elementos al
principio o al final de la lista.

**Qué busca demostrar este ejemplo**: las mismas operaciones básicas de `List` que ya se vieron con
`ArrayList`, más `addFirst()`/`removeFirst()`, propias de `LinkedList`.

## 📚 Caso de estudio

**Biblioteca Universitaria** agrega un título al **principio** de la lista de disponibles (una
reimpresión reciente que llegó primero al catálogo), algo que con un arreglo requeriría desplazar todos
los demás elementos manualmente.

## 🌳 Árbol de archivos (como se vería en VS Code)

```text
Modulo08ArreglosListasGenericas
└── src
    └── com
        └── biblioteca
            └── Demo.java   ← nuevo en este ejemplo
```

## 💻 Archivo: Demo.java

```java
package com.biblioteca;

import java.util.LinkedList;

public class Demo {
    public static void main(String[] args) {
        LinkedList<String> titulos = new LinkedList<>();
        titulos.add("Cien años de soledad");
        titulos.add("El principito");
        titulos.addFirst("Rayuela");
        System.out.println(titulos.get(0));
        System.out.println(titulos.get(1));
        System.out.println(titulos.get(2));
        System.out.println(titulos.size());

        titulos.removeFirst();
        System.out.println(titulos.get(0));
        System.out.println(titulos.size());
    }
}
```

## 🧭 Explicación paso a paso

1. `LinkedList<String> titulos = new LinkedList<>();` declara e instancia una `LinkedList`, con las
   mismas operaciones básicas (`add`, `get`, `size`) que cualquier `List`.
2. `titulos.addFirst("Rayuela")` inserta un elemento **al principio**, desplazando a los demás una
   posición hacia adelante, sin necesidad de calcular índices manualmente.
3. Tras `addFirst`, `titulos.get(0)` es `"Rayuela"` (el que se acaba de insertar), y los demás títulos
   quedan en las posiciones siguientes.
4. `titulos.removeFirst()` elimina el primer elemento (`"Rayuela"`); el segundo pasa a ocupar la
   posición `0`.
5. `LinkedList` también implementa `List`, así que `get`, `size` y `remove` funcionan igual que en
   `ArrayList` (Ejemplo 08); lo que cambia es qué tan eficiente es cada operación según su estructura
   interna (tabla comparativa a continuación).

## ✅ Resultado esperado

```text
Rayuela
Cien años de soledad
El principito
3
Cien años de soledad
2
```

## 🧪 Casos de prueba

| Operación | Resultado |
|---|---|
| Tras `addFirst("Rayuela")`, `titulos.get(0)` | `"Rayuela"` |
| `titulos.size()` (con 3 elementos) | `3` |
| Tras `removeFirst()`, `titulos.get(0)` | `"Cien años de soledad"` |
| Tras `removeFirst()`, `titulos.size()` | `2` |

## 🗺️ Diagrama

| | Arreglo | `ArrayList` | `LinkedList` |
|---|---|---|---|
| Tamaño | Fijo | Dinámico | Dinámico |
| Acceso por índice | Eficiente | Eficiente | Menos eficiente |
| Insertar/eliminar en los extremos | No aplica (tamaño fijo) | Menos eficiente | Eficiente |
| Tipos permitidos | Primitivos y de referencia | Solo de referencia | Solo de referencia |

## 🔍 Análisis: errores frecuentes

**Error conceptual — Pensar que `LinkedList` y `ArrayList` tienen operaciones distintas.** Ambas
implementan `List`, así que comparten `add`, `get`, `remove`, `size`. La diferencia real no está en qué
se puede hacer, sino en qué tan **eficiente** es cada operación según cómo está organizada internamente
cada una.

## ❓ Preguntas de repaso

**1. [Selección]** **Pregunta:** ¿qué operación es especialmente eficiente en `LinkedList`?

- **A.** Acceder a un elemento por índice en el medio de la lista.
- **B.** Insertar o eliminar un elemento al principio o al final.
- **C.** Calcular el tamaño con `.length`.
- **D.** Ordenar la lista.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** `LinkedList` es eficiente para insertar o eliminar en los extremos, gracias a
sus nodos enlazados.

</details>

**2. [Selección múltiple]** **Pregunta:** ¿cuáles afirmaciones sobre `ArrayList` y `LinkedList` son
verdaderas?

- **A.** Ambas implementan la interfaz `List`.
- **B.** Ambas ofrecen `add`, `get`, `remove` y `size`.
- **C.** `ArrayList` es más eficiente para acceder por índice.
- **D.** `LinkedList` no permite agregar elementos al final.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y C.** D es falsa: `LinkedList` sí permite `addLast` (o `add`, que agrega
al final por defecto).

</details>

**3. [Abierta]** ¿Por qué `LinkedList` es más eficiente que `ArrayList` para insertar un elemento al
principio de la lista?

<details>
<summary>🔑 Ver respuesta modelo</summary>

Porque `LinkedList` solo necesita crear un nuevo nodo y enlazarlo al que antes era el primero, sin mover
ningún otro elemento. `ArrayList`, al estar respaldada por un arreglo interno, tendría que desplazar
todos los elementos existentes una posición para hacer espacio al nuevo primer elemento.

</details>

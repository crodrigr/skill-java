# 💡 Ejemplo 08 — ArrayList

## 🌍 Contexto

`ArrayList` es la implementación más común de `List`: respaldada internamente por un arreglo que crece
automáticamente, con acceso eficiente por índice, igual que un arreglo, pero con tamaño dinámico.

**Qué busca demostrar este ejemplo**: las operaciones básicas de una `List` (`add`, `get`, `remove`,
`size`), y los cuatro errores reales que puede producir usarla mal.

## 📚 Caso de estudio

**Biblioteca Universitaria** registra los títulos disponibles en una `ArrayList`, agregando y quitando
títulos sin declarar de antemano cuántos habrá.

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

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        List<String> titulos = new ArrayList<>();
        titulos.add("Cien años de soledad");
        titulos.add("El principito");
        System.out.println(titulos.get(0));
        System.out.println(titulos.size());

        titulos.remove(0);
        System.out.println(titulos.get(0));
        System.out.println(titulos.size());
    }
}
```

## 🧭 Explicación paso a paso

1. `List<String> titulos = new ArrayList<>();` declara la variable con el tipo de la **interfaz**
   (`List`) y la instancia con la implementación concreta (`ArrayList`).
2. `titulos.add("...")` agrega un elemento al final; a diferencia de un arreglo, no hace falta declarar
   el tamaño de antemano.
3. `titulos.get(0)` lee el elemento en la posición `0`; `titulos.size()` devuelve la cantidad actual de
   elementos (con paréntesis, a diferencia de `.length` de un arreglo).
4. `titulos.remove(0)` elimina el elemento en la posición `0`; los elementos siguientes se recorren una
   posición hacia atrás, y `size()` disminuye en 1.
5. Después de eliminar, `titulos.get(0)` ya devuelve lo que antes estaba en la posición `1`
   (`"El principito"`).

## ✅ Resultado esperado

```text
Cien años de soledad
2
El principito
1
```

## 🧪 Casos de prueba

| Operación | Resultado |
|---|---|
| `titulos.get(0)` (inicial) | `"Cien años de soledad"` |
| `titulos.size()` (inicial) | `2` |
| Tras `titulos.remove(0)`, `titulos.get(0)` | `"El principito"` |
| Tras `titulos.remove(0)`, `titulos.size()` | `1` |

## 🔍 Análisis: errores frecuentes

**Error — Acceder a un índice fuera de rango en una List (ejecución, sin capturar).**

```java falla-en-ejecucion
package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        List<String> titulos = new ArrayList<>();
        titulos.add("Cien años de soledad");
        titulos.add("El principito");
        titulos.remove(0);
        System.out.println("Antes del error");
        System.out.println(titulos.get(5));
    }
}
```

```text
Exception in thread "main" java.lang.IndexOutOfBoundsException: Index 5 out of bounds for length 1
```

Tampoco lo detecta el panel Problems: es un error de ejecución, igual que en un arreglo.

**Error — Usar un tipo primitivo como argumento de tipo genérico (compilación).**

```java no-compila
package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        List<int> numeros = new ArrayList<>();
    }
}
```

```text
✖ Syntax error, insert "Dimensions" to complete ReferenceType Java(1610612976) [Ln 8, Col 14]
```

El mensaje real del panel (`"insert Dimensions to complete ReferenceType"`) es muy distinto, en su
redacción, del de `javac` (`"required: reference"`): ambos señalan el mismo error real, verificado con
el JDK y el servidor de lenguaje reales.

**Error — Invocar `.length()` sobre una `List` en vez de `.size()` (compilación).**

```java no-compila
package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        List<String> titulos = new ArrayList<>();
        titulos.add("Cien años de soledad");
        System.out.println(titulos.length());
    }
}
```

```text
✖ The method length() is undefined for the type List<String> Java(67108964) [Ln 10, Col 36]
```

## ❓ Preguntas de repaso

**1. [Selección]** **Pregunta:** ¿qué método de `List` obtiene la cantidad de elementos?

- **A.** `.length`.
- **B.** `.length()`.
- **C.** `.size()`.
- **D.** `.size`.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: C.** `.size()` es un método (con paréntesis) de `List`; `.length` (sin
paréntesis) es de los arreglos.

</details>

**2. [Selección múltiple]** **Pregunta:** ¿cuáles afirmaciones sobre `ArrayList` son verdaderas?

- **A.** Su tamaño cambia dinámicamente al agregar o quitar elementos.
- **B.** `List<int>` es una declaración válida.
- **C.** Acceder a un índice fuera de rango con `get(...)` lanza una excepción real en tiempo de
  ejecución.
- **D.** `add(...)` agrega un elemento al final de la lista.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, C y D.** B es falsa: los argumentos de tipo genérico deben ser tipos de
referencia (`List<Integer>`, no `List<int>`).

</details>

**3. [Abierta]** ¿Por qué `List<int>` no compila, pero `List<Integer>` sí?

<details>
<summary>🔑 Ver respuesta modelo</summary>

Porque los argumentos de tipo genérico en Java deben ser tipos de **referencia**, nunca tipos
primitivos. `int` es un tipo primitivo; `Integer` es su clase envolvente (un tipo de referencia), así
que `List<Integer>` sí es válido.

</details>

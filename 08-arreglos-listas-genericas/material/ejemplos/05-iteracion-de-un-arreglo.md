# 💡 Ejemplo 05 — Iteración de un arreglo

## 🌍 Contexto

Recorrer un arreglo (visitar cada uno de sus elementos) se puede hacer con un bucle `for` **indexado**,
usando `.length` como límite, o con un `for-each`, que recorre los elementos directamente sin manejar
un índice.

**Qué busca demostrar este ejemplo**: las dos formas de recorrer el mismo arreglo, produciendo el mismo
resultado.

## 🏥 Caso de estudio

**MediSalud** imprime la edad de todos sus pacientes registrados, primero con un `for` indexado y
después con un `for-each`.

## 🌳 Árbol de archivos (como se vería en VS Code)

```text
Modulo08ArreglosListasGenericas
└── src
    └── Demo.java   ← igual que en el Ejemplo 04
```

## 💻 Archivo: Demo.java

```java
public class Demo {
    public static void main(String[] args) {
        int[] edades = {34, 41, 29};

        System.out.println("Con for indexado:");
        for (int i = 0; i < edades.length; i++) {
            System.out.println(edades[i]);
        }

        System.out.println("Con for-each:");
        for (int edad : edades) {
            System.out.println(edad);
        }
    }
}
```

## 🧭 Explicación paso a paso

1. `for (int i = 0; i < edades.length; i++)` recorre el arreglo con un índice `i`, desde `0` hasta
   `edades.length - 1` (usar `<` con `.length`, no `<=`, evita salirse del rango).
2. Dentro del `for` indexado, `edades[i]` accede al elemento en la posición actual.
3. `for (int edad : edades)` (`for-each`) recorre los elementos directamente: en cada vuelta, `edad`
   toma el valor del siguiente elemento del arreglo, sin necesidad de manejar un índice.
4. Ambas formas visitan los mismos elementos, en el mismo orden, y producen la misma salida.
5. El `for-each` es más simple cuando solo se necesita **leer** cada elemento; el `for` indexado sigue
   siendo necesario cuando se necesita el índice (por ejemplo, para modificar el arreglo o comparar
   posiciones entre sí).

## ✅ Resultado esperado

```text
Con for indexado:
34
41
29
Con for-each:
34
41
29
```

## 🧪 Casos de prueba

| Forma de recorrido | Salida |
|---|---|
| `for` indexado | `34`, `41`, `29` |
| `for-each` | `34`, `41`, `29` |

## 🔍 Análisis: errores frecuentes

**Error conceptual — Usar `<=` en vez de `<` en la condición de un `for` indexado.** Escribir
`i <= edades.length` (en vez de `i < edades.length`) haría que el bucle intente acceder a
`edades[edades.length]`, un índice fuera de rango, lanzando `ArrayIndexOutOfBoundsException` en la
última vuelta (el mismo error del Ejemplo 03).

## ❓ Preguntas de repaso

**1. [Selección]** **Pregunta:** ¿qué condición de un `for` indexado recorre correctamente todo el
arreglo `edades` sin salirse de rango?

- **A.** `i <= edades.length`.
- **B.** `i < edades.length`.
- **C.** `i < edades.length - 1`.
- **D.** `i <= edades.length - 2`.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** `i < edades.length` recorre todos los índices válidos, de `0` a
`edades.length - 1`, sin salirse de rango.

</details>

**2. [Selección múltiple]** **Pregunta:** ¿cuáles afirmaciones sobre `for` indexado y `for-each` son
verdaderas?

- **A.** Ambos pueden recorrer el mismo arreglo y producir el mismo resultado.
- **B.** El `for-each` necesita manejar un índice explícito.
- **C.** El `for` indexado permite acceder al índice actual dentro del bucle.
- **D.** El `for-each` es más simple cuando solo se necesita leer cada elemento.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, C y D.** B es falsa: el `for-each` no expone ni necesita un índice.

</details>

**3. [Abierta]** ¿En qué situación seguiría siendo necesario un `for` indexado en vez de un `for-each`?

<details>
<summary>🔑 Ver respuesta modelo</summary>

Cuando se necesita el índice mismo, por ejemplo para modificar el arreglo durante el recorrido
(`edades[i] = edades[i] + 1;`) o para comparar la posición actual con otra (como la anterior o la
siguiente). El `for-each` solo entrega el valor de cada elemento, no su posición.

</details>

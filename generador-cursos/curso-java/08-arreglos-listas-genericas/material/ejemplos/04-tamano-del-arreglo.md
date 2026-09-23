# 💡 Ejemplo 04 — Obtener el tamaño del arreglo

## 🌍 Contexto

`.length` es un **atributo** (no un método: no lleva paréntesis) que devuelve la cantidad de elementos
de un arreglo. Es fijo desde el momento en que el arreglo se crea: no puede cambiar durante la
ejecución del programa.

**Qué busca demostrar este ejemplo**: usar `.length` para conocer el tamaño de un arreglo, y para
calcular el índice de su último elemento sin necesidad de un número literal.

## 🏥 Caso de estudio

**MediSalud** necesita saber cuántos pacientes tiene registrados en el arreglo, sin contar manualmente.

## 🌳 Árbol de archivos (como se vería en VS Code)

```text
Modulo08ArreglosListasGenericas
└── src
    └── Demo.java   ← igual que en el Ejemplo 03
```

## 💻 Archivo: Demo.java

```java
public class Demo {
    public static void main(String[] args) {
        int[] edades = {34, 41, 29};
        System.out.println(edades.length);
        System.out.println(edades[edades.length - 1]);
    }
}
```

## 🧭 Explicación paso a paso

1. `edades.length` devuelve `3`: la cantidad de elementos del arreglo, sin paréntesis (es un atributo,
   no un método).
2. `edades[edades.length - 1]` accede al **último** elemento del arreglo, sea cual sea su tamaño: no
   hace falta escribir el índice literal (`edades[2]`), que dejaría de ser correcto si el arreglo
   cambiara de tamaño.
3. El tamaño de un arreglo es **fijo** desde su creación: no existe una forma de "agrandar" `edades`
   después de declararlo con 3 elementos; para eso sirve una `List` (Ejemplo 08).

## ✅ Resultado esperado

```text
3
29
```

## 🧪 Casos de prueba

| Expresión | Resultado |
|---|---|
| `edades.length` | `3` |
| `edades[edades.length - 1]` | `29` |

## 🔍 Análisis: errores frecuentes

**Error conceptual — Confundir `.length` (atributo, sin paréntesis) con `.length()` (método, con
paréntesis, de `String`).** Un arreglo usa `.length` sin paréntesis; un `String` usa `.length()` con
paréntesis. Usar la forma incorrecta produce un error de compilación (se profundiza al comparar con
`.size()` de `List` en el Básico 06).

## ❓ Preguntas de repaso

**1. [Selección]** **Pregunta:** ¿cómo se obtiene la cantidad de elementos de un arreglo llamado
`edades`?

- **A.** `edades.length()`.
- **B.** `edades.length`.
- **C.** `edades.size()`.
- **D.** `edades.size`.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** `.length` es un atributo de los arreglos, sin paréntesis.

</details>

**2. [Selección múltiple]** **Pregunta:** ¿cuáles afirmaciones sobre `.length` son verdaderas?

- **A.** Es un atributo, no un método.
- **B.** Su valor puede cambiar después de crear el arreglo.
- **C.** `edades[edades.length - 1]` accede siempre al último elemento.
- **D.** Es fijo desde que el arreglo se crea.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, C y D.** B es falsa: el tamaño de un arreglo es fijo desde su creación.

</details>

**3. [Abierta]** ¿Por qué conviene escribir `edades[edades.length - 1]` en vez de `edades[2]` para
acceder al último elemento?

<details>
<summary>🔑 Ver respuesta modelo</summary>

Porque `edades[edades.length - 1]` sigue siendo correcto sin importar el tamaño real del arreglo: si
`edades` tuviera 10 elementos en vez de 3, la misma expresión seguiría accediendo al último. Con un
índice literal como `edades[2]`, habría que actualizarlo manualmente cada vez que cambiara el tamaño
del arreglo.

</details>

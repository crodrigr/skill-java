# 💡 Ejemplo 02 — Asignación de valores a un arreglo

## 🌍 Contexto

Un arreglo se puede llenar de dos formas: con un **literal** (`{ ... }`), que asigna todos los valores
de una vez al declararlo, o **posición por posición**, asignando cada índice por separado después de
crearlo con `new`.

**Qué busca demostrar este ejemplo**: las dos formas de asignar valores a un arreglo, produciendo el
mismo resultado.

## 🏥 Caso de estudio

**MediSalud** registra las edades de tres pacientes, primero con un literal y después asignando cada
edad por separado.

## 🌳 Árbol de archivos (como se vería en VS Code)

```text
Modulo08ArreglosListasGenericas
└── src
    └── Demo.java   ← nuevo en este ejemplo
```

## 💻 Archivo: Demo.java

```java
public class Demo {
    public static void main(String[] args) {
        // Asignación por literal
        int[] edades = {34, 41, 29};
        System.out.println(edades[0]);
        System.out.println(edades[1]);
        System.out.println(edades[2]);

        // Asignación por índice, uno a la vez
        int[] otrasEdades = new int[3];
        otrasEdades[0] = 34;
        otrasEdades[1] = 41;
        otrasEdades[2] = 29;
        System.out.println(otrasEdades[0]);
        System.out.println(otrasEdades[1]);
        System.out.println(otrasEdades[2]);
    }
}
```

## 🧭 Explicación paso a paso

1. `int[] edades = {34, 41, 29};` declara el arreglo **y** le asigna los tres valores en la misma
   línea, con un literal entre llaves.
2. `int[] otrasEdades = new int[3];` declara un arreglo de tamaño 3, con `new`, pero **sin** asignarle
   valores todavía: cada posición queda en `0` (el valor por defecto de `int`).
3. `otrasEdades[0] = 34;` asigna el primer valor por índice; lo mismo para las posiciones 1 y 2.
4. Ambas formas producen arreglos equivalentes: la elección depende de si ya se conocen todos los
   valores al declarar el arreglo (literal) o si se van a asignar después, por ejemplo dentro de un
   bucle (por índice).

## ✅ Resultado esperado

```text
34
41
29
34
41
29
```

## 🧪 Casos de prueba

| Forma de asignación | `[0]` | `[1]` | `[2]` |
|---|---|---|---|
| Literal (`edades`) | `34` | `41` | `29` |
| Por índice (`otrasEdades`) | `34` | `41` | `29` |

## 🔍 Análisis: errores frecuentes

**Error conceptual — Creer que `new int[3]` ya asigna valores útiles.** `new int[3]` reserva espacio
para 3 elementos, pero los inicializa con el valor por defecto de `int` (`0`), no con valores "vacíos"
o indefinidos. Hasta que no se asigna cada posición explícitamente, el arreglo contiene ceros.

## ❓ Preguntas de repaso

**1. [Selección]** **Pregunta:** ¿qué valor tiene `otrasEdades[0]` justo después de
`int[] otrasEdades = new int[3];`, antes de asignarle nada?

- **A.** Un valor indefinido, distinto cada vez que se ejecuta.
- **B.** `0`, el valor por defecto de `int`.
- **C.** El programa no compila hasta asignar todas las posiciones.
- **D.** `null`.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Un arreglo de `int` creado con `new` inicializa cada posición en `0`, el
valor por defecto de ese tipo primitivo.

</details>

**2. [Selección múltiple]** **Pregunta:** ¿cuáles afirmaciones sobre `{34, 41, 29}` y
`new int[3]` con asignación por índice son verdaderas?

- **A.** Ambas formas terminan con el mismo contenido si se asignan los mismos valores.
- **B.** El literal asigna todos los valores en la misma línea de la declaración.
- **C.** La asignación por índice es útil cuando los valores se conocen después, por ejemplo en un
  bucle.
- **D.** Solo el literal es válido en Java.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y C.** D es falsa: ambas formas son válidas y útiles según el caso.

</details>

**3. [Abierta]** ¿Cuándo conviene usar un literal para asignar un arreglo, y cuándo conviene asignar
cada posición por índice?

<details>
<summary>🔑 Ver respuesta modelo</summary>

Un literal conviene cuando todos los valores ya se conocen al momento de declarar el arreglo (por
ejemplo, una tabla de constantes). Asignar por índice conviene cuando los valores se calculan o se leen
después de crear el arreglo, por ejemplo dentro de un bucle que los va llenando uno por uno.

</details>

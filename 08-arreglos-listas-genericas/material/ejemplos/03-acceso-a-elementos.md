# 💡 Ejemplo 03 — Acceso a elementos del arreglo

## 🌍 Contexto

Cada elemento de un arreglo se lee o se escribe con su **índice**, un número entero entre corchetes que
indica su posición. En Java, el primer elemento está en el índice `0`, no en el `1`; el último está en
`.length - 1`.

**Qué busca demostrar este ejemplo**: el acceso válido a un elemento por índice, y el error real que
produce acceder a un índice fuera del rango válido del arreglo.

## 🏥 Caso de estudio

**MediSalud** lee la edad del primer y del último paciente registrado, y después comete el error de
intentar leer un paciente que no existe.

## 🌳 Árbol de archivos (como se vería en VS Code)

```text
Modulo08ArreglosListasGenericas
└── src
    └── Demo.java   ← igual que en el Ejemplo 02
```

## 💻 Archivo: Demo.java

```java
public class Demo {
    public static void main(String[] args) {
        int[] edades = {34, 41, 29};
        System.out.println(edades[0]);
        System.out.println(edades[2]);
    }
}
```

## 🧭 Explicación paso a paso

1. `edades[0]` accede al **primer** elemento del arreglo (`34`).
2. `edades[2]` accede al **último** elemento de un arreglo de 3 posiciones (`29`): el último índice
   válido es siempre `.length - 1`, no `.length`.
3. Si se intenta acceder a `edades[5]`, un índice que no existe en un arreglo de 3 elementos, Java no lo
   detecta al compilar: el programa compila sin problema.
4. Al **ejecutar**, el acceso a `edades[5]` lanza una excepción real,
   `ArrayIndexOutOfBoundsException`, que termina el programa inmediatamente (no se llega a la línea
   siguiente).
5. Este módulo todavía no enseña a **capturar** una excepción (`try`/`catch` queda para un módulo
   posterior): por ahora, el programa simplemente se deja fallar, mostrando el error real.

## ✅ Resultado esperado

```text
34
29
```

## 🧪 Casos de prueba

| Acceso | Resultado |
|---|---|
| `edades[0]` | `34` |
| `edades[2]` | `29` |
| `edades[5]` | El programa termina con `ArrayIndexOutOfBoundsException` |

## 🔍 Análisis: errores frecuentes

**Error — Acceder a un índice fuera del rango del arreglo (ejecución, sin capturar).**

```java falla-en-ejecucion
public class Demo {
    public static void main(String[] args) {
        int[] edades = {34, 41, 29};
        System.out.println("Antes del error");
        System.out.println(edades[5]);
    }
}
```

```text
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 3
```

Este error **no** lo detecta el compilador ni el panel Problems, ni siquiera cuando el índice y el
tamaño del arreglo son literales conocidos de antemano: solo se manifiesta al **ejecutar** el programa,
como una excepción real.

## ❓ Preguntas de repaso

**1. [Selección]** **Pregunta:** en un arreglo de 3 elementos, ¿cuál es el índice del último elemento
válido?

- **A.** `3`.
- **B.** `2`.
- **C.** `1`.
- **D.** Depende del tipo de dato.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** El último índice válido siempre es `.length - 1`; en un arreglo de 3
elementos, `.length` es `3`, así que el último índice válido es `2`.

</details>

**2. [Selección múltiple]** **Pregunta:** ¿cuáles afirmaciones sobre acceder a `edades[5]` en un
arreglo de 3 elementos son verdaderas?

- **A.** El compilador lo rechaza antes de ejecutar.
- **B.** El programa compila sin problema.
- **C.** El programa termina con una excepción real al ejecutarse.
- **D.** El panel Problems marca ese acceso como un error antes de ejecutar.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: B y C.** A y D son falsas: ni el compilador ni el panel detectan un índice
fuera de rango; solo se manifiesta en tiempo de ejecución.

</details>

**3. [Abierta]** ¿Por qué Java no rechaza `edades[5]` al compilar, si ya sabe que `edades` tiene solo 3
elementos?

<details>
<summary>🔑 Ver respuesta modelo</summary>

Porque, en general, el compilador no puede saber en todos los casos qué valor tendrá un índice en
tiempo de ejecución (podría venir de una variable calculada, no de un literal). Java opta por verificar
el rango de un arreglo **en tiempo de ejecución**, en cada acceso, en vez de intentar predecirlo en
tiempo de compilación; por eso el error aparece como una excepción real al ejecutar, no como un error
del compilador.

</details>

# 💡 Ejemplo 02 — Concatenación de cadenas

## 🌍 Contexto

**Concatenar** es unir texto. Ya usaste `+` en los módulos anteriores para mostrar un texto junto a un
valor; ahora vas a mirarlo con más detalle, porque hay tres formas y un orden que hay que respetar:

- `+` une dos valores en una expresión nueva: `"Hola " + "mundo"`.
- `+=` une y **actualiza** la variable: `texto += " más"` equivale a `texto = texto + " más"`.
- `.concat(...)` es un método de `String` que hace lo mismo que `+`, pero solo entre dos cadenas (no
  admite números directamente).

El `+` se evalúa **de izquierda a derecha**: en `"Cita " + 1 + 2`, primero se une el texto con el `1`
(da `"Cita 1"`) y después con el `2` (da `"Cita 12"`). En cambio `1 + 2 + " citas"` sí sigue el orden
matemático porque ninguno de los dos primeros es texto: primero se **suma** `1 + 2` (da `3`) y recién
ahí se concatena con el texto (`"3 citas"`).

**Qué busca demostrar este ejemplo**: las tres formas de concatenar y cómo el orden de evaluación de
`+` cambia el resultado según dónde aparezca el primer texto.

## 🏥 Caso de estudio

**MediSalud** arma el código de cada cita combinando un prefijo, el año y un número de cita.

## 🌳 Árbol de archivos (como se vería en VS Code)

Agrega la clase `CodigoDeCita.java` al paquete `com.medisalud` del proyecto
`Modulo04CadenasConversiones`:

```text
Modulo04CadenasConversiones
└── src
    └── com
        └── medisalud
            ├── CodigoDeCita.java   ← nuevo en este ejemplo
            └── CreacionDeCadenas.java
```

## 💻 Archivo: CodigoDeCita.java

```java
package com.medisalud;

public class CodigoDeCita {

    public static void main(String[] args) {
        // Datos de entrada
        int anioCita = 2026;
        int numeroCita = 42;

        String codigoCita = "CIT-" + anioCita + "-" + numeroCita;
        System.out.println("Código de la cita: " + codigoCita);

        String resumen = "Cita ";
        resumen += anioCita;
        resumen = resumen.concat("-").concat(String.valueOf(numeroCita));
        System.out.println("Con += y concat: " + resumen);

        // El orden de evaluación importa: + se evalúa de izquierda a derecha
        System.out.println("Cita " + 1 + 2);
        System.out.println(1 + 2 + " citas");
    }
}
```

## 🧭 Explicación paso a paso

1. `"CIT-" + anioCita + "-" + numeroCita` concatena de izquierda a derecha: primero el prefijo con el
   año, después el guion, después el número. El resultado es texto en cada paso, así que no hay
   ambigüedad.
2. `resumen += anioCita;` actualiza `resumen` uniéndole el año.
3. `.concat("-").concat(String.valueOf(numeroCita))` encadena dos llamadas: cada `.concat(...)`
   devuelve una cadena nueva sobre la que se llama al siguiente `.concat(...)`. Como `concat` solo
   admite `String`, el número se convierte antes con `String.valueOf(...)`.
4. `"Cita " + 1 + 2` empieza con texto: se evalúa de izquierda a derecha y da `"Cita 12"`.
5. `1 + 2 + " citas"` empieza con dos números: esos se suman primero (`3`) y el resultado se concatena
   al final (`"3 citas"`).

## ✅ Resultado esperado

```text
Código de la cita: CIT-2026-42
Con += y concat: Cita 2026-42
Cita 12
3 citas
```

## 🧪 Casos de prueba

Cambia `numeroCita` y ejecuta de nuevo. Prueba los valores límite:

| numeroCita | codigoCita |
|---|---|
| `0` | CIT-2026-0 |
| `1` | CIT-2026-1 |
| `9999` | CIT-2026-9999 |

## 🔍 Análisis: errores frecuentes

No hay errores nuevos que demostrar en este ejemplo: la trampa de este tema (el orden de evaluación
de `+`) ya se muestra en la explicación paso a paso, con los dos casos contrastados. La comparación con
`==`, que también aparece al crear texto, se retoma en el Ejemplo 05.

## ❓ Preguntas de repaso

**1. [Selección]** **Pregunta:** ¿qué muestra `System.out.println("Total: " + 2 + 3);`?

- **A.** `Total: 23`
- **B.** `Total: 5`
- **C.** Da un error de compilación.
- **D.** `Total: 2 3`

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: A.** Empieza con texto, así que `+` se evalúa de izquierda a derecha: primero
`"Total: " + 2` da `"Total: 2"`, y después `+ 3` da `"Total: 23"`.

</details>

**2. [Selección múltiple]** **Pregunta:** ¿cuáles afirmaciones sobre concatenar son verdaderas?

- **A.** `texto += "algo"` actualiza la variable `texto`.
- **B.** `.concat(...)` admite un número como argumento directamente.
- **C.** `2 + 3 + " citas"` da `"5 citas"`.
- **D.** El orden en que aparece el primer texto puede cambiar el resultado de una suma con `+`.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, C y D.** B es falsa: `concat` solo une `String` con `String`; para unir un
número hay que convertirlo antes con `String.valueOf(...)`.

</details>

**3. [Abierta]** Explica por qué `"Cita " + 1 + 2` y `1 + 2 + " citas"` dan resultados distintos, aunque
las dos expresiones usan los mismos números.

<details>
<summary>🔑 Ver respuesta modelo</summary>

Porque `+` se evalúa de izquierda a derecha, y en el primer caso el primer operando ya es texto: la
suma se convierte en concatenación desde el principio (`Cita 1`, después `Cita 12`). En el segundo
caso los dos primeros operandos son números, así que primero se suman de verdad (`3`) y solo al final
ese resultado se convierte en texto para unirse con `" citas"`.

</details>

# 💡 Ejemplo 03 — Operadores condicionales

## 🌍 Contexto

Una regla de negocio casi nunca depende de una sola comparación: "la cita se autoriza si el
paciente es afiliado **y** no tiene deuda". Los **operadores condicionales** combinan varias
condiciones (valores `boolean`) en una sola:

| Operador | Nombre | Se lee | Da `true` cuando |
|---|---|---|---|
| `&&` | Y | "A y B" | las **dos** condiciones son verdaderas |
| `\|\|` | O | "A o B" | **al menos una** condición es verdadera |
| `!` | NO | "no A" | la condición es falsa (invierte el valor) |

**Qué busca demostrar este ejemplo**: cómo combinar condiciones con Y, O y NO, cómo leer su
tabla de verdad y por qué la segunda condición a veces no llega a evaluarse (cortocircuito),
lo que permite evitar errores como la división entre cero.

## 🏥 Caso de estudio

**MediSalud** autoriza citas y prioriza la atención. Dos reglas:

- **Cita autorizada**: el paciente es afiliado **y** no tiene deuda.
- **Atención prioritaria**: el paciente tiene 65 años o más **o** menos de 5.

Además, la clínica quiere saber si el promedio facturado por consulta supera los $100.000, sin
que el programa falle cuando el paciente aún no tiene consultas.

## 🌳 Árbol de archivos (como se vería en VS Code)

Agrega la clase `AutorizacionCita.java` al paquete `com.medisalud` del proyecto
`Modulo02Decisiones`:

```text
Modulo02Decisiones
└── src
    └── com
        ├── biblioteca
        │   └── ControlPrestamo.java
        └── medisalud
            ├── AutorizacionCita.java   ← nuevo en este ejemplo
            └── FacturaConsulta.java
```

## 💻 Archivo: AutorizacionCita.java

```java
package com.medisalud;

public class AutorizacionCita {

    public static void main(String[] args) {
        // Datos de entrada
        boolean esAfiliado = true;
        boolean tieneDeuda = false;
        int edadPaciente = 65;
        int cantidadConsultas = 0;
        int totalFacturado = 255000;

        // Y (&&): deben cumplirse las dos condiciones
        boolean citaAutorizada = esAfiliado && !tieneDeuda;
        System.out.println("Cita autorizada: " + citaAutorizada);

        // O (||): basta con que se cumpla una
        boolean atencionPrioritaria = edadPaciente >= 65 || edadPaciente < 5;
        System.out.println("Atención prioritaria: " + atencionPrioritaria);

        // NO (!): invierte el valor
        System.out.println("No es afiliado: " + !esAfiliado);

        // Cortocircuito: si la primera condición ya decide, la segunda no se evalúa
        boolean promedioAlto = cantidadConsultas > 0 && totalFacturado / cantidadConsultas > 100000;
        System.out.println("Promedio por consulta alto: " + promedioAlto);
    }
}
```

## 🧮 Tabla de verdad

Una **tabla de verdad** muestra el resultado de un operador para cada combinación de valores.
Estas tablas salen de ejecutar los operadores de Java:

| A | B | `A && B` (Y) | `A \|\| B` (O) |
|---|---|---|---|
| `true` | `true` | `true` | `true` |
| `true` | `false` | `false` | `true` |
| `false` | `true` | `false` | `true` |
| `false` | `false` | `false` | `false` |

| A | `!A` (NO) |
|---|---|
| `true` | `false` |
| `false` | `true` |

Cuando una condición mezcla operadores, Java los evalúa en este orden: primero `!`, luego las
comparaciones (`>=`, `<`, `==`...), después `&&` y al final `||`. Usa paréntesis para que la
intención sea clara.

## 🧭 Explicación paso a paso

1. `esAfiliado && !tieneDeuda` primero calcula `!tieneDeuda` (con `tieneDeuda = false` da
   `true`) y luego combina con Y: `true && true` da `true`. La cita está autorizada.
2. `edadPaciente >= 65 || edadPaciente < 5` evalúa las dos comparaciones y las combina con O.
   Con `65`, la primera ya es verdadera: el resultado es `true`.
3. `!esAfiliado` invierte el valor de `esAfiliado`: de `true` pasa a `false`.
4. `cantidadConsultas > 0 && totalFacturado / cantidadConsultas > 100000` usa el
   **cortocircuito**: con `&&`, si la primera condición es falsa el resultado ya es falso, así
   que Java **no evalúa** la segunda. Como `cantidadConsultas` vale `0`, la división entre cero
   nunca se ejecuta y el resultado es `false`.
5. Con `||` ocurre lo contrario: si la primera condición ya es verdadera, la segunda no se
   evalúa.

## ✅ Resultado esperado

```text
Cita autorizada: true
Atención prioritaria: true
No es afiliado: false
Promedio por consulta alto: false
```

## 🧪 Casos de prueba

Cambia los datos de entrada y ejecuta de nuevo. Cada tabla recorre todas las combinaciones de
su regla, incluidos los valores límite.

**Cita autorizada** (`esAfiliado && !tieneDeuda`):

| esAfiliado | tieneDeuda | Cita autorizada |
|---|---|---|
| `true` | `false` | `true` |
| `true` | `true` | `false` |
| `false` | `false` | `false` |
| `false` | `true` | `false` |

**Atención prioritaria** (`edadPaciente >= 65 || edadPaciente < 5`), con los valores límite
`4`, `5`, `64` y `65`:

| edadPaciente | Atención prioritaria |
|---|---|
| `4` | `true` |
| `5` | `false` |
| `64` | `false` |
| `65` | `true` |

**Promedio por consulta alto** con `totalFacturado = 255000`; el caso `0` es el que evita la
división entre cero:

| cantidadConsultas | Promedio alto |
|---|---|
| `0` | `false` |
| `1` | `true` |
| `3` | `false` |

## 🔍 Análisis: errores frecuentes

**Error 1 — Olvidar la guarda del cortocircuito (error lógico).** Sin la comprobación previa, con
`cantidadConsultas = 0` el programa se detiene:

```java error-logico
package com.medisalud;

public class AutorizacionCita {

    public static void main(String[] args) {
        int cantidadConsultas = 0;
        int totalFacturado = 255000;
        boolean promedioAlto = totalFacturado / cantidadConsultas > 100000;
        System.out.println("Promedio por consulta alto: " + promedioAlto);
    }
}
```

```text
Exception in thread "main" java.lang.ArithmeticException: / by zero
	at com.medisalud.AutorizacionCita.main(AutorizacionCita.java:8)
```

El código compila, así que el panel Problems no marca ningún problema, pero falla al
ejecutarse. La solución es la condición del ejemplo principal: `cantidadConsultas > 0 &&
...`. **El orden importa**: la comprobación que protege debe ir a la izquierda.

**Error 2 — Cambiar Y por O.** `esAfiliado || !tieneDeuda` autoriza a un afiliado con deuda
(basta con que se cumpla una). Es un error lógico que ningún mensaje avisa: se descubre
probando las cuatro combinaciones de la tabla de casos de arriba.

**Error 3 — El rango con `&&`.** Para "entre 18 y 64 años" la forma correcta es `edad >= 18 &&
edad < 65`, no `18 <= edad < 65` (que no compila, ver el Ejemplo 02).

## ❓ Preguntas de repaso

**1. [Selección]** Un paciente es afiliado (`esAfiliado = true`) pero tiene deuda
(`tieneDeuda = true`). **Pregunta:** ¿cuánto vale `esAfiliado && !tieneDeuda`?

- **A.** `true`
- **B.** `false`
- **C.** Da un error de compilación.
- **D.** No se puede saber.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** `!tieneDeuda` es `false`, y `true && false` da `false`: la cita no
se autoriza.

</details>

**2. [Selección múltiple]** **Pregunta:** ¿en qué casos Java **no evalúa** la segunda condición?

- **A.** En `A && B`, cuando `A` es `false`.
- **B.** En `A && B`, cuando `A` es `true`.
- **C.** En `A || B`, cuando `A` es `true`.
- **D.** En `A || B`, cuando `A` es `false`.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A y C.** Si con la primera condición ya se conoce el resultado (Y
falso, O verdadero), la segunda se omite. En B y D todavía hay que mirar la segunda.

</details>

**3. [Abierta]** Explica cómo el cortocircuito evita el error en
`cantidadConsultas > 0 && totalFacturado / cantidadConsultas > 100000`.

<details>
<summary>🔑 Ver respuesta modelo</summary>

Cuando `cantidadConsultas` vale `0`, la primera condición es `false`. Como con `&&` el resultado
ya es falso, Java no evalúa la segunda condición y la división entre cero nunca se ejecuta. La
primera condición actúa como guarda de la segunda.

</details>

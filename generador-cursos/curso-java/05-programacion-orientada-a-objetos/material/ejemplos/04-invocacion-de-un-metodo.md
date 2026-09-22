# 💡 Ejemplo 04 — Invocación de un método

## 🌍 Contexto

Un método se invoca con la **notación de punto**: `objeto.metodo(argumentos)`. Los argumentos deben
coincidir en cantidad y tipo con los parámetros que el método declara. Si el método devuelve un valor,
ese valor puede guardarse en una variable, usarse directamente, o **ignorarse** — pero ignorarlo no
siempre es inofensivo.

**Qué busca demostrar este ejemplo**: invocar un método sin retorno (`void`) y uno con retorno,
guardando su resultado; y qué pasa si el resultado de un método con retorno se descarta sin darse
cuenta.

## 🏥 Caso de estudio

**MediSalud** saluda a un paciente y calcula el copago de su consulta a partir del monto base y el
porcentaje que le corresponde.

## 🌳 Árbol de archivos (como se vería en VS Code)

```text
Modulo05ObjetosYClases
└── src
    └── com
        └── medisalud
            ├── SaludoAlPaciente.java       ← nuevo en este ejemplo
            └── DemoSaludoAlPaciente.java   ← nuevo en este ejemplo
```

## 💻 Archivo: SaludoAlPaciente.java

```java
package com.medisalud;

public class SaludoAlPaciente {
    public String nombreCompleto;

    public void saludar() {
        System.out.println("Hola, " + nombreCompleto);
    }

    public double calcularCopago(double montoBase, double porcentajeCopago) {
        return montoBase * porcentajeCopago;
    }
}
```

## 💻 Archivo: DemoSaludoAlPaciente.java

```java
package com.medisalud;

public class DemoSaludoAlPaciente {

    public static void main(String[] args) {
        SaludoAlPaciente paciente = new SaludoAlPaciente();
        paciente.nombreCompleto = "Ana Torres";

        paciente.saludar();

        double copago = paciente.calcularCopago(80000.0, 0.20);
        System.out.println("Copago: " + copago);

        double copagoSinPorcentaje = paciente.calcularCopago(80000.0, 0.0);
        System.out.println("Copago sin porcentaje: " + copagoSinPorcentaje);
    }
}
```

## 🧭 Explicación paso a paso

1. `paciente.saludar();` invoca un método `void`: no devuelve nada, solo imprime.
2. `paciente.calcularCopago(80000.0, 0.20)` invoca un método con dos parámetros y devuelve un `double`;
   el resultado se guarda en `copago` antes de imprimirlo.
3. Los argumentos (`80000.0`, `0.20`) deben coincidir en cantidad y tipo con los parámetros
   (`montoBase`, `porcentajeCopago`).
4. `calcularCopago(80000.0, 0.0)` (porcentaje cero) es el caso límite: el resultado es `0.0`, un copago
   válido de cero, no un error.

## ✅ Resultado esperado

```text
Hola, Ana Torres
Copago: 16000.0
Copago sin porcentaje: 0.0
```

## 🧪 Casos de prueba

| `montoBase` | `porcentajeCopago` | `calcularCopago(...)` |
|---|---|---|
| `80000.0` | `0.20` | `16000.0` |
| `80000.0` | `0.0` | `0.0` (límite) |

## 🔍 Análisis: errores frecuentes

**Error — Ignorar el valor que devuelve un método (error lógico).**

```java error-logico
package com.medisalud;

public class SaludoAlPaciente {
    public String nombreCompleto;

    public double calcularCopago(double montoBase, double porcentajeCopago) {
        return montoBase * porcentajeCopago;
    }

    public static void main(String[] args) {
        SaludoAlPaciente paciente = new SaludoAlPaciente();
        paciente.nombreCompleto = "Ana Torres";

        paciente.calcularCopago(80000.0, 0.20);
        System.out.println("Copago registrado.");
    }
}
```

```text
Copago registrado.
```

El programa compila y se ejecuta sin ningún error: `calcularCopago(...)` sí se ejecuta, pero su resultado
no se guarda en ninguna variable, así que se pierde. El mensaje "Copago registrado." es engañoso: no se
registró ningún valor. El panel Problems no marca ningún problema, porque el código es válido: solo hace
algo distinto de lo que se esperaba.

## ❓ Preguntas de repaso

**1. [Selección]** **Pregunta:** ¿qué necesita `paciente.calcularCopago(80000.0, 0.20)` para invocarse
correctamente?

- **A.** Que `paciente` sea la clase, no un objeto.
- **B.** Que los argumentos coincidan en cantidad y tipo con los parámetros del método.
- **C.** Que el método no devuelva ningún valor.
- **D.** Que se invoque antes de crear el objeto.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Los argumentos deben coincidir en cantidad y tipo con los parámetros
declarados por el método.

</details>

**2. [Selección múltiple]** **Pregunta:** ¿cuáles afirmaciones sobre invocar un método son verdaderas?

- **A.** El valor que devuelve un método puede guardarse en una variable.
- **B.** El valor que devuelve un método siempre debe usarse.
- **C.** Un método `void` no devuelve ningún valor.
- **D.** Ignorar el valor devuelto por un método puede producir un error lógico.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, C y D.** B es falsa: se puede ignorar, aunque hacerlo sin darse cuenta puede
ser un error.

</details>

**3. [Abierta]** ¿Qué le pasa al resultado de `calcularCopago(...)` si se invoca sin guardarlo en
ninguna variable, y por qué el panel Problems no avisa de nada?

<details>
<summary>🔑 Ver respuesta modelo</summary>

El resultado se calcula pero se pierde: no queda guardado en ningún lado. El panel Problems no avisa
porque el código es válido en Java (invocar un método sin usar su resultado no es un error); solo
produce un comportamiento distinto del esperado, que hay que detectar comparando la salida con lo que se
quería lograr.

</details>

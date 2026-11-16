# 💡 Ejemplo 08 — Variables y constantes

## 🌍 Contexto

Un programa trabaja con **datos**: la edad de un paciente, el nombre de una clínica, el
precio de una consulta. Para guardar un dato mientras el programa corre, Java usa
espacios de memoria con nombre. Hay dos clases:

- Una **variable** es un espacio con **nombre** y **tipo** cuyo valor **puede cambiar**
  mientras el programa se ejecuta.
- Una **constante** es un espacio con nombre y tipo cuyo valor **no cambia** una vez
  asignado. Se declara con la palabra `final`.

Se declaran con esta forma:

```text
tipo nombre = valor;
```

**Qué busca demostrar este ejemplo**: cómo se declara y se inicializa una variable o una
constante, qué reglas debe cumplir su nombre, y qué errores del compilador aparecen
cuando se usan mal.

## 🏥 Caso de estudio

La ficha de un paciente de **MediSalud** mezcla datos que cambian (la edad, el peso) y
datos que no (el nombre de la clínica). Vas a guardar cada uno en el lugar correcto: los
que cambian, en variables; el que no cambia, en una constante.

## 🔍 Reglas para los nombres

Un nombre (o **identificador**) de variable o constante debe cumplir estas reglas:

- Empieza con una **letra** (no con un número).
- No lleva **espacios** ni símbolos como `-`.
- No puede ser una **palabra reservada** de Java (`class`, `int`, `public`, `static`,
  `void`, `final`, `if`, `for`, `while`, `new`, `return`, entre otras).
- Distingue **mayúsculas de minúsculas**: `edad` y `Edad` son nombres distintos.

Además, el curso sigue estas **convenciones** (no las exige el compilador, pero las usa
todo el mundo):

| Elemento | Convención | Ejemplo |
|---|---|---|
| Variable | `camelCase`: primera palabra en minúscula y las siguientes con mayúscula inicial | `edadPaciente`, `pesoKg` |
| Constante | `MAYUSCULAS_CON_GUION_BAJO` | `NOMBRE_CLINICA` |
| Sin tildes ni `ñ` en los nombres | Se evitan por compatibilidad | `anioPublicacion`, no `añoPublicación` |

## 🌳 Árbol de archivos (como se vería en VS Code)

```text
FichaPaciente
└── src
    └── com
        └── medisalud
            └── FichaPaciente.java
```

## 💻 Archivo: FichaPaciente.java

```java
package com.medisalud;

public class FichaPaciente {

    public static void main(String[] args) {
        final String NOMBRE_CLINICA = "MediSalud";
        int edadPaciente = 34;
        double pesoKg = 62.5;
        boolean esAfiliado = true;

        System.out.println(NOMBRE_CLINICA);
        System.out.println(edadPaciente);
        System.out.println(pesoKg);
        System.out.println(esAfiliado);

        edadPaciente = 35;
        System.out.println(edadPaciente);
    }
}
```

`System.out.println(x)` muestra el **valor** que guarda `x`. En el Ejemplo 10 vas a
aprender a acompañar cada valor con un texto; por ahora lo importante es entender qué
guarda cada nombre.

## ✅ Resultado esperado

```text
MediSalud
34
62.5
true
35
```

## 🧭 Explicación paso a paso

1. `final String NOMBRE_CLINICA = "MediSalud";` declara una **constante**: `final` indica
   que no se podrá cambiar. `String` es el tipo para **texto** (una "cadena de
   texto"): no es un tipo primitivo y se estudia con más detalle en otro módulo.
2. `int edadPaciente = 34;` declara una **variable** de tipo `int` (número entero) y le
   da el valor inicial `34`. Declarar y dar el primer valor a la vez se llama
   **inicializar**.
3. `double pesoKg = 62.5;` y `boolean esAfiliado = true;` guardan un decimal y un
   valor de verdadero/falso.
4. Las cuatro instrucciones `println` muestran los valores en el orden en que se
   escribieron (el programa se ejecuta de arriba hacia abajo).
5. `edadPaciente = 35;` **cambia** el valor de una variable ya declarada. No lleva tipo
   delante, porque el tipo se indica solo al declarar.
6. Por eso la última línea muestra `35`: la variable cambió, pero `NOMBRE_CLINICA` no
   podría hacerlo.

## 🧩 Errores frecuentes

Los mensajes de abajo son los que muestra el panel **Problems** de VS Code (Ejemplo 06).
La posición `[Ln 7, Col 9]` se refiere al archivo completo, con su línea `package`.

**1. Cambiar el valor de una constante.**

```java
final String NOMBRE_CLINICA = "MediSalud";
NOMBRE_CLINICA = "Otra Clinica";
```

```text
✖ The final local variable NOMBRE_CLINICA cannot be assigned. It must be blank and not using a compound assignment Java(536870970) [Ln 7, Col 9]
```

Una constante no se puede reasignar (el mensaje dice que la variable `final` "cannot be
assigned"). Si el valor debe cambiar, es una variable: quitá `final` y cambiale el nombre
a `camelCase`.

**2. Usar una variable sin darle valor.**

```java
int edadPaciente;
System.out.println(edadPaciente);
```

```text
✖ The local variable edadPaciente may not have been initialized Java(536870963) [Ln 7, Col 28]
```

Una variable se puede declarar sin valor (`int edadPaciente;`), pero **hay que asignarle
uno antes de leerla**.

**3. Un nombre con espacio.**

```java
int edad paciente = 34;
```

```text
✖ Syntax error, insert ";" to complete BlockStatements Java(1610612976) [Ln 6, Col 13]
✖ paciente cannot be resolved to a variable Java(33554515) [Ln 6, Col 18]
```

El editor cree que `int edad` ya terminó (por eso espera un `;`) y después no encuentra una
variable llamada `paciente`. Un solo nombre con espacio produjo **dos** mensajes. Usá
`edadPaciente`.

**4. Una palabra reservada como nombre.**

```java
int class = 34;
System.out.println(class);
```

```text
✖ Syntax error on token "int", . expected after this token Java(1610612967) [Ln 6, Col 9]
✖ The left-hand side of an assignment must be a variable Java(1610612959) [Ln 6, Col 9]
✖ Syntax error on token "class", delete this token Java(1610612968) [Ln 7, Col 28]
```

Un solo problema (usar `class` como nombre) produjo **tres** mensajes. Cuando esto
pasa, **empezá por el primero**: los demás suelen ser consecuencia de él.

## ❓ Preguntas de repaso

**1. [Selección]** ¿Cuál de estas líneas declara correctamente una **constante**?

- **A.** `int TASA_IVA = 0.19;`
- **B.** `final double TASA_IVA = 0.19;`
- **C.** `constante double TASA_IVA = 0.19;`
- **D.** `double tasa iva = 0.19;`

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B**. `final` es la palabra que hace que el valor no pueda
cambiar. La A además tiene un tipo incorrecto (`int` para un decimal), la C usa una
palabra que no existe en Java y la D tiene espacios en el nombre.

</details>

**2. [Selección múltiple]** Seleccioná **todos** los nombres válidos para una variable.

- **A.** `edadPaciente`
- **B.** `2edad`
- **C.** `pesoKg`
- **D.** `class`

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: A y C**. La B empieza con un número y la D es una palabra
reservada.

</details>

**3. [Abierta]** Un compañero guarda el valor de la consulta en `double valorConsulta`
y quiere que nunca cambie por accidente. Explicá qué debe modificar y cómo debería
llamarse.

<details>
<summary>🔑 Ver respuesta modelo</summary>

**Respuesta modelo**: Debe declararla como constante agregando `final` y, por
convención, escribir su nombre en mayúsculas con guiones bajos:
`final double VALOR_CONSULTA = 85000.0;`. Si alguien intenta reasignarla, el
compilador mostrará el error `cannot assign a value to final variable`.

</details>

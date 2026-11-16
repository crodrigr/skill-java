# 💡 Ejemplo 01 — ¿Qué es encapsulamiento?

## 🌍 Contexto

En el Módulo 5 declaraste atributos **públicos**: cualquier código podía asignarles cualquier valor,
incluso uno que no tuviera sentido de negocio (una edad negativa, por ejemplo). El **encapsulamiento**
consiste en ocultar el estado interno de un objeto (sus atributos) y controlar cómo se accede a él y se
modifica, en vez de exponerlo directamente.

**Qué busca demostrar este ejemplo**: la misma idea de negocio (un paciente con nombre y edad),
implementada primero sin encapsular (como en el Módulo 5) y después encapsulada, para comparar qué pasa
cuando se intenta asignar una edad inválida en cada caso.

## 🏥 Caso de estudio

**MediSalud** registra la edad de un paciente. Sin encapsular, nada impide guardar una edad negativa.
Encapsulando el atributo con un `set` validado, ese valor se rechaza.

## 🌳 Árbol de archivos (como se vería en VS Code)

Crea un proyecto llamado `Modulo06Encapsulamiento` (**Java: Create Java Project... → No build tools**)
y ve agregando en él una clase por cada ejemplo. En este ejemplo agregas dos archivos dentro de
`src/com/medisalud`:

```text
Modulo06Encapsulamiento
└── src
    └── com
        └── medisalud
            ├── SinEncapsular.java        ← nuevo en este ejemplo
            └── ConEncapsulamiento.java   ← nuevo en este ejemplo
```

## 💻 Archivo: SinEncapsular.java

```java
package com.medisalud;

public class SinEncapsular {
    public String nombreCompleto;
    public int edad;

    public static void main(String[] args) {
        SinEncapsular paciente = new SinEncapsular();
        paciente.nombreCompleto = "Ana Torres";
        paciente.edad = -5;

        System.out.println(paciente.nombreCompleto + " - edad: " + paciente.edad);
    }
}
```

## 💻 Archivo: ConEncapsulamiento.java

```java
package com.medisalud;

public class ConEncapsulamiento {
    private String nombreCompleto;
    private int edad;

    public ConEncapsulamiento(String nombreCompleto, int edad) {
        this.nombreCompleto = nombreCompleto;
        setEdad(edad);
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if (edad < 0) {
            System.out.println("Edad inválida (" + edad + "): se conserva la edad actual (" + this.edad + ")");
            return;
        }
        this.edad = edad;
    }

    public static void main(String[] args) {
        ConEncapsulamiento paciente = new ConEncapsulamiento("Ana Torres", -5);

        System.out.println(paciente.getNombreCompleto() + " - edad: " + paciente.getEdad());
    }
}
```

## 🗺️ Diagrama

```mermaid
flowchart TB
    subgraph Sin["Sin encapsular"]
        a["paciente.edad = -5;"] --> r1["edad queda en -5 (sin control)"]
    end
    subgraph Con["Encapsulado"]
        b["new ConEncapsulamiento(\"Ana Torres\", -5)"] --> c["setEdad(-5)"]
        c -->|"edad < 0"| r2["se rechaza; edad queda en 0"]
    end
```

## 🧭 Explicación paso a paso

1. `SinEncapsular` permite asignar `paciente.edad = -5;` directamente: el atributo público no tiene
   forma de rechazar ese valor.
2. `ConEncapsulamiento` declara `nombreCompleto` y `edad` como `private`: ya no se pueden asignar con la
   notación de punto desde fuera de la clase.
3. El constructor de `ConEncapsulamiento` llama a `setEdad(edad)` en vez de asignar `this.edad = edad;`
   directamente, para que la validación se aplique también al crear el objeto.
4. `setEdad(-5)` detecta que `-5` es inválido, muestra un aviso y **no** asigna: `edad` queda en `0`
   (el valor por defecto de `int`), no en `-5`.
5. El acceso a los datos, en ambos casos, se hace a través de `getNombreCompleto()`/`getEdad()` en la
   versión encapsulada, en vez de la notación de punto directa.

## ✅ Resultado esperado

`SinEncapsular`:

```text
Ana Torres - edad: -5
```

`ConEncapsulamiento`:

```text
Edad inválida (-5): se conserva la edad actual (0)
Ana Torres - edad: 0
```

## 🧪 Casos de prueba

| Versión | `edad` asignada | `edad` final |
|---|---|---|
| `SinEncapsular` | `-5` | `-5` (sin control) |
| `ConEncapsulamiento` | `-5` | `0` (rechazada, valor por defecto) |
| `ConEncapsulamiento` | `34` (válida) | `34` |

## 🔍 Análisis: errores frecuentes

El error conceptual más frecuente de este punto es pensar que encapsular "esconde" los datos sin más:
en realidad, el objetivo es **controlar** el acceso, no ocultarlo del todo. Los datos siguen siendo
accesibles a través de `get`/`set`; lo que cambia es que un `set` puede validar antes de asignar, algo
que un atributo público nunca puede hacer.

## ❓ Preguntas de repaso

**1. [Selección]** **Pregunta:** ¿qué es el encapsulamiento?

- **A.** Hacer que una clase no tenga ningún atributo.
- **B.** Ocultar el estado interno de un objeto y controlar cómo se accede a él y se modifica.
- **C.** Usar solo métodos estáticos.
- **D.** Evitar declarar constructores.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** El encapsulamiento oculta el estado interno de un objeto y controla su
acceso, en vez de exponerlo directamente con atributos públicos.

</details>

**2. [Selección múltiple]** **Pregunta:** ¿cuáles afirmaciones sobre `SinEncapsular` y
`ConEncapsulamiento` son verdaderas?

- **A.** `SinEncapsular` permite asignar una edad negativa sin ningún control.
- **B.** `ConEncapsulamiento` rechaza la edad negativa y avisa por consola.
- **C.** Los dos programas dan exactamente la misma edad final.
- **D.** `ConEncapsulamiento` usa el `set` también desde el constructor.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y D.** C es falsa: `SinEncapsular` termina con `edad = -5` y
`ConEncapsulamiento` con `edad = 0`.

</details>

**3. [Abierta]** Explica con tus palabras por qué el constructor de `ConEncapsulamiento` llama a
`setEdad(edad)` en vez de escribir `this.edad = edad;` directamente.

<details>
<summary>🔑 Ver respuesta modelo</summary>

Porque si el constructor asignara `this.edad = edad;` directamente, la validación de `setEdad` nunca se
aplicaría al crear el objeto: se podría crear un paciente con una edad inválida desde el principio.
Llamar a `setEdad(edad)` desde el constructor garantiza que la misma regla de validación se cumpla
siempre, se cree el objeto o se modifique después.

</details>

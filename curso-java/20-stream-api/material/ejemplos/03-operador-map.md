# 💡 Ejemplo 03 — Operador map

## 🌍 Contexto

MediSalud necesita transformar el nombre de cada paciente de una lista a mayúsculas, para un reporte.
Hoy esa transformación está implementada con un bucle `for` que arma una nueva lista a mano.

**Qué busca demostrar este ejemplo**: cómo usar el operador `map` con una `Function` (Módulo 19) para
transformar cada elemento de un stream.

## 🏥 Caso de estudio

MediSalud transforma los nombres de una lista de pacientes a mayúsculas.

## 🗺️ Diagrama

```mermaid
sequenceDiagram
    participant Demo
    participant Stream
    participant Function

    Demo->>Stream: pacientes.stream()
    Demo->>Stream: map(aMayusculas)
    Stream->>Function: apply(paciente) por cada elemento
    Function-->>Stream: nombre en mayusculas
    Stream-->>Demo: Stream&lt;String&gt;
```

## 🌳 Árbol de archivos — antes

```text
map-antes/
└── com/medisalud/
    ├── PacienteMuestra.java
    └── Demo.java
```

## 💻 Archivo: PacienteMuestra.java

```java
package com.medisalud;

public class PacienteMuestra {
    private final String nombre;

    public PacienteMuestra(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
```

## 💻 Archivo: Demo.java

```java
package com.medisalud;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        List<PacienteMuestra> pacientes = new ArrayList<>();
        pacientes.add(new PacienteMuestra("Ana Torres"));
        pacientes.add(new PacienteMuestra("Luis Rios"));

        List<String> nombresEnMayusculas = new ArrayList<>();
        for (PacienteMuestra paciente : pacientes) {
            nombresEnMayusculas.add(paciente.getNombre().toUpperCase());
        }

        System.out.println("Nombres en mayusculas:");
        for (String nombre : nombresEnMayusculas) {
            System.out.println("- " + nombre);
        }
    }
}
```

## ✅ Resultado esperado — antes

```text
Nombres en mayusculas:
- ANA TORRES
- LUIS RIOS
```

## 🌳 Árbol de archivos — después

```text
map-despues/
├── PacienteMuestra.java        (sin cambios)
└── Demo.java                   (cambió)
```

## 💻 Archivo: PacienteMuestra.java

```java
package com.medisalud;

public class PacienteMuestra {
    private final String nombre;

    public PacienteMuestra(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
```

## 💻 Archivo: Demo.java — cambió

```java
package com.medisalud;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class Demo {
    public static void main(String[] args) {
        List<PacienteMuestra> pacientes = new ArrayList<>();
        pacientes.add(new PacienteMuestra("Ana Torres"));
        pacientes.add(new PacienteMuestra("Luis Rios"));

        Function<PacienteMuestra, String> aMayusculas = paciente -> paciente.getNombre().toUpperCase();

        System.out.println("Nombres en mayusculas:");
        Stream<String> nombresEnMayusculas = pacientes.stream().map(aMayusculas);
        nombresEnMayusculas.forEach(nombre -> System.out.println("- " + nombre));
    }
}
```

## ✅ Resultado esperado — después

```text
Nombres en mayusculas:
- ANA TORRES
- LUIS RIOS
```

## 🔍 Comparación: la prueba concreta

Ambas versiones producen exactamente los mismos dos nombres en mayúsculas, verificable ejecutando las
dos. La versión "antes" arma una nueva `List` a mano dentro del bucle. La versión "después" usa `map`
con una `Function<PacienteMuestra, String>`, que transforma cada elemento del stream sin declarar una
lista intermedia.

## 🔍 Análisis: errores frecuentes

El error más frecuente es esperar que `map` filtre elementos: `map` siempre produce **la misma cantidad**
de elementos que el stream original, uno transformado por cada uno de entrada — seleccionar elementos es
responsabilidad de `filter` (Ejemplo 04), no de `map`.

## ❓ Preguntas de repaso

**1. [Selección]** ¿Qué hace el operador `map` sobre un stream?

- A. Selecciona los elementos que cumplen una condición.
- B. Transforma cada elemento en otro valor, manteniendo la misma cantidad de elementos.
- C. Verifica si al menos un elemento cumple una condición.
- D. Aplana una estructura de streams anidados.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** `map` transforma cada elemento del stream con la `Function` recibida,
produciendo un nuevo stream con la misma cantidad de elementos que el original.

</details>

**2. [Selección múltiple]** Sobre este ejemplo, ¿cuáles afirmaciones son verdaderas?

- A. `map` recibe una `Function<T, R>` como argumento.
- B. El stream resultante de `map` tiene la misma cantidad de elementos que el original.
- C. `map` puede reducir la cantidad de elementos del stream, igual que `filter`.
- D. Ambas versiones (antes y después) producen exactamente el mismo resultado.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y D.** C es falsa: `map` nunca cambia la cantidad de elementos, solo los
transforma uno a uno.

</details>

**3. [Abierta]** Un compañero dice: "puedo usar `map` para quedarme solo con los pacientes mayores de
cierta edad". ¿Estás de acuerdo? Justifica tu respuesta.

<details>
<summary>🔑 Ver respuesta</summary>

No. `map` transforma cada elemento, pero nunca cambia cuántos elementos hay en el stream resultante.
Para seleccionar solo algunos elementos según una condición, la herramienta correcta es `filter`
(Ejemplo 04), no `map`.

</details>

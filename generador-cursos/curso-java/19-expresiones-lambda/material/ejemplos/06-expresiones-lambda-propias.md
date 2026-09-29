# 💡 Ejemplo 06 — Expresiones lambda propias

## 🌍 Contexto

MediSalud necesita generar una nota clínica combinando el nombre de un paciente y su diagnóstico en un
solo texto. Ninguna de las cuatro interfaces estándar (`Consumer`, `Supplier`, `Function`, `Predicate`)
recibe **dos** parámetros de tipos distintos — este caso necesita una interfaz funcional propia.

**Qué busca demostrar este ejemplo**: cómo diseñar e implementar una interfaz funcional propia con
`@FunctionalInterface` cuando ninguna interfaz estándar se ajusta al problema, y el error real de
compilación si esa interfaz declara más de un método abstracto.

## 🏥 Caso de estudio

MediSalud combina el nombre y el diagnóstico de un paciente en una nota clínica de texto.

## 🗺️ Diagrama

```mermaid
classDiagram
    class GeneradorNota {
        <<interface>>
        +generar(String, String) String
    }
    class GeneradorNotaSimple {
        +generar(String, String) String
    }
    GeneradorNotaSimple ..|> GeneradorNota

    class GeneradorNotaLambda {
        <<@FunctionalInterface>>
        (nombrePaciente, diagnostico) -> texto
    }
```

## 🌳 Árbol de archivos — antes

```text
propias-antes/
└── com/medisalud/
    ├── GeneradorNota.java
    ├── GeneradorNotaSimple.java
    └── Demo.java
```

## 💻 Archivo: GeneradorNota.java

```java
package com.medisalud;

public interface GeneradorNota {
    String generar(String nombrePaciente, String diagnostico);
}
```

## 💻 Archivo: GeneradorNotaSimple.java

```java
package com.medisalud;

public class GeneradorNotaSimple implements GeneradorNota {
    @Override
    public String generar(String nombrePaciente, String diagnostico) {
        return "Nota: " + nombrePaciente + " presenta " + diagnostico + ".";
    }
}
```

## 💻 Archivo: Demo.java

```java
package com.medisalud;

public class Demo {
    public static void main(String[] args) {
        GeneradorNota generador = new GeneradorNotaSimple();
        System.out.println(generador.generar("Ana Torres", "Gripe"));
        System.out.println(generador.generar("Luis Rios", "Hipertension"));
    }
}
```

## ✅ Resultado esperado — antes

```text
Nota: Ana Torres presenta Gripe.
Nota: Luis Rios presenta Hipertension.
```

## 🌳 Árbol de archivos — después

```text
propias-despues/
├── GeneradorNota.java            (cambió)
└── Demo.java                     (cambió)
```

## 💻 Archivo: GeneradorNota.java — cambió

```java
package com.medisalud;

@FunctionalInterface
public interface GeneradorNota {
    String generar(String nombrePaciente, String diagnostico);
}
```

## 💻 Archivo: Demo.java — cambió

```java
package com.medisalud;

public class Demo {
    public static void main(String[] args) {
        GeneradorNota generador = (nombrePaciente, diagnostico) ->
                "Nota: " + nombrePaciente + " presenta " + diagnostico + ".";
        System.out.println(generador.generar("Ana Torres", "Gripe"));
        System.out.println(generador.generar("Luis Rios", "Hipertension"));
    }
}
```

## ✅ Resultado esperado — después

```text
Nota: Ana Torres presenta Gripe.
Nota: Luis Rios presenta Hipertension.
```

## 🔍 Comparación: la prueba concreta

Ambas versiones producen exactamente el mismo texto de nota clínica, verificable ejecutando las dos. La
versión "antes" necesita una clase completa (`GeneradorNotaSimple`) para implementar `GeneradorNota`. La
versión "después" anota la misma interfaz con `@FunctionalInterface` y la implementa con una expresión
lambda de dos parámetros — sin declarar ninguna clase.

## ⚠️ ¿Qué pasa si la interfaz declara un segundo método abstracto?

`@FunctionalInterface` exige que la interfaz declare **exactamente un** método abstracto. Si se le agrega
un segundo método abstracto, el compilador rechaza la interfaz con un error real:

```java no-compila
package com.medisalud;

@FunctionalInterface
public interface GeneradorNotaInvalida {
    String generar(String nombrePaciente, String diagnostico);
    String generarResumen(String nombrePaciente);
}
```

Mensaje real de `javac`:

```text
./GeneradorNotaInvalida.java:3: error: Unexpected @FunctionalInterface annotation
@FunctionalInterface
^
  GeneradorNotaInvalida is not a functional interface
    multiple non-overriding abstract methods found in interface GeneradorNotaInvalida
1 error
```

## 🔍 Análisis: errores frecuentes

El error más frecuente es agregar un segundo método a una interfaz que ya se está usando con expresiones
lambda en otras partes del código, sin notar que eso rompe todas esas expresiones lambda —
`@FunctionalInterface` convierte ese error en un error de compilación inmediato, en vez de un error
silencioso.

## ❓ Preguntas de repaso

**1. [Selección]** ¿Qué exige la anotación `@FunctionalInterface`?

- A. Que la interfaz declare al menos un método, sin límite máximo.
- B. Que la interfaz declare exactamente un método abstracto.
- C. Que la interfaz se implemente únicamente con una expresión lambda, nunca con una clase.
- D. Que la interfaz extienda alguna de las cuatro interfaces estándar de `java.util.function`.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** `@FunctionalInterface` exige exactamente un método abstracto; si la interfaz
declara un segundo, el compilador rechaza la anotación con un error real.

</details>

**2. [Selección múltiple]** Sobre este ejemplo, ¿cuáles afirmaciones son verdaderas?

- A. `GeneradorNota` no encaja en `Consumer`, `Supplier`, `Function` ni `Predicate` porque recibe dos
  parámetros de tipos distintos.
- B. `@FunctionalInterface` es opcional: el compilador igual rechaza una expresión lambda sobre una
  interfaz con más de un método abstracto, con o sin la anotación.
- C. Una interfaz anotada `@FunctionalInterface` puede declarar cualquier cantidad de métodos abstractos.
- D. Ambas versiones (antes y después) producen el mismo resultado.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y D.** C es falsa: es exactamente lo opuesto — `@FunctionalInterface`
exige un único método abstracto, y lo hace cumplir con un error real de compilación.

</details>

**3. [Abierta]** Un compañero dice: "si mi interfaz ya tiene dos métodos abstractos, le agrego
`@FunctionalInterface` igual, por si en el futuro la simplifico a uno". ¿Estás de acuerdo? Justifica tu
respuesta.

<details>
<summary>🔑 Ver respuesta</summary>

No. `@FunctionalInterface` no es solo documentación: el compilador la verifica en el momento, y una
interfaz con dos métodos abstractos anotada así produce un error de compilación real de inmediato, no
una advertencia para el futuro. La anotación debe agregarse solo cuando la interfaz ya tiene exactamente
un método abstracto.

</details>

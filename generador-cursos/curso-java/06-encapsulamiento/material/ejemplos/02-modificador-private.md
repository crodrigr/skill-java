# 💡 Ejemplo 02 — Modificador de acceso private

## 🌍 Contexto

`private` es el modificador de acceso más restrictivo: un miembro `private` solo es accesible dentro de
la **misma clase** donde se declara. Ni siquiera otra clase del mismo paquete puede acceder a él
directamente: debe hacerlo a través de sus métodos `get`/`set`.

**Qué busca demostrar este ejemplo**: un atributo `private` accedido correctamente a través de su `get`,
y el error real que produce intentar acceder a él directamente, incluso desde una clase del mismo
paquete.

## 🏥 Caso de estudio

**MediSalud** protege el diagnóstico de un paciente en su ficha clínica: solo se puede leer o escribir a
través de métodos, nunca directamente.

## 🌳 Árbol de archivos (como se vería en VS Code)

```text
Modulo06Encapsulamiento
└── src
    └── com
        └── medisalud
            ├── FichaClinica.java       ← nuevo en este ejemplo
            └── DemoFichaClinica.java   ← nuevo en este ejemplo
```

## 💻 Archivo: FichaClinica.java

```java
package com.medisalud;

public class FichaClinica {
    private String diagnostico;

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
}
```

## 💻 Archivo: DemoFichaClinica.java

```java
package com.medisalud;

public class DemoFichaClinica {

    public static void main(String[] args) {
        FichaClinica ficha = new FichaClinica();
        ficha.setDiagnostico("Control anual");

        System.out.println(ficha.getDiagnostico());
    }
}
```

## 🗺️ Diagrama

```mermaid
flowchart LR
    subgraph FichaClinica
        d["private String diagnostico"]
    end
    Demo["DemoFichaClinica (mismo paquete)"] -->|"getDiagnostico() / setDiagnostico(...)"| d
    Demo2["Cualquier otra clase"] -.->|"ficha.diagnostico (directo)"| d
    d -. rechazado .-> Demo2
```

## 🧭 Explicación paso a paso

1. `diagnostico` es `private`: solo el código de `FichaClinica` puede acceder a él directamente.
2. `DemoFichaClinica`, aunque está en el **mismo paquete**, no puede escribir `ficha.diagnostico`
   directamente: debe usar `setDiagnostico(...)` y `getDiagnostico()`.
3. Esto distingue `private` de los demás modificadores (Ejemplos 03 a 05): ni siquiera el mismo paquete
   alcanza para acceder a un `private`.

## ✅ Resultado esperado

```text
Control anual
```

## 🧪 Casos de prueba

| Acceso | ¿Compila? |
|---|---|
| `ficha.setDiagnostico("Control anual")` (mismo paquete, por método) | Sí |
| `ficha.diagnostico = "Control anual"` (mismo paquete, directo) | No |

## 🔍 Análisis: errores frecuentes

**Error — Acceder a un atributo private directamente, incluso desde el mismo paquete (compilación).**

```java no-compila
package com.medisalud;

class FichaClinica {
    private String diagnostico;
}

public class AccesoDirectoFichaClinica {
    public static void main(String[] args) {
        FichaClinica ficha = new FichaClinica();
        ficha.diagnostico = "Control anual";
    }
}
```

```text
⚠ The value of the field FichaClinica.diagnostico is not used Java(570425421) [Ln 4, Col 20]
✖ The field FichaClinica.diagnostico is not visible Java(33554503) [Ln 10, Col 15]
```

El servidor de lenguaje marca el acceso como un **Error** real: `The field FichaClinica.diagnostico is
not visible`. El mensaje de `javac` en la terminal dice lo mismo con otras palabras:
`diagnostico has private access in FichaClinica`.

## ❓ Preguntas de repaso

**1. [Selección]** **Pregunta:** ¿desde dónde se puede acceder directamente a un atributo `private`?

- **A.** Desde cualquier clase del mismo paquete.
- **B.** Solo desde la misma clase donde se declara.
- **C.** Desde cualquier clase del proyecto.
- **D.** Solo desde `main`.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** `private` es el modificador más restrictivo: solo la misma clase puede
acceder a él directamente.

</details>

**2. [Selección múltiple]** **Pregunta:** ¿cuáles afirmaciones sobre `FichaClinica` son verdaderas?

- **A.** `DemoFichaClinica`, del mismo paquete, puede acceder a `diagnostico` con `ficha.diagnostico`.
- **B.** `DemoFichaClinica` puede acceder a `diagnostico` con `ficha.getDiagnostico()`.
- **C.** El compilador rechaza el acceso directo, aunque sea del mismo paquete.
- **D.** El panel Problems marca el acceso directo como un `Error`.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: B, C y D.** A es falsa: ni el mismo paquete permite el acceso directo a un
`private`.

</details>

**3. [Abierta]** ¿En qué se diferencia `private` del modificador por defecto (sin modificador), que
verás en el Ejemplo 03?

<details>
<summary>🔑 Ver respuesta modelo</summary>

`private` solo permite el acceso desde la misma clase donde se declara el miembro, ni siquiera otra
clase del mismo paquete puede acceder directamente. El modificador por defecto sí permite el acceso
desde cualquier clase del mismo paquete, pero no desde otro paquete.

</details>

# 💡 Ejemplo 04 — Modificador de acceso protected

## 🌍 Contexto

`protected` es un modificador pensado para la herencia: permite el acceso desde subclases, incluso de
otros paquetes. Como este curso todavía no cubre herencia, este ejemplo muestra únicamente lo que
`protected` hace **sin** ella: exactamente lo mismo que el acceso por defecto (mismo paquete sí, otro
paquete no). Su alcance ampliado a subclases de otros paquetes se estudia en el módulo de herencia.

**Qué busca demostrar este ejemplo**: que, sin herencia, `protected` tiene el mismo alcance que el
acceso por defecto del Ejemplo 03, aunque el mensaje de `javac` en la terminal sea distinto.

## 🏥📚 Caso de estudio

**MediSalud** lleva el mismo registro de consultas del Ejemplo 03, ahora con el atributo `protected` en
vez de por defecto.

## 🌳 Árbol de archivos (como se vería en VS Code)

```text
Modulo06Encapsulamiento
└── src
    └── com
        ├── medisalud
        │   ├── RegistroMedico.java       ← nuevo en este ejemplo
        │   └── DemoRegistroMedico.java   ← nuevo en este ejemplo
        └── biblioteca
            └── AccesoRegistroMedico.java ← nuevo en este ejemplo (no compila)
```

## 💻 Archivo: RegistroMedico.java

```java
package com.medisalud;

public class RegistroMedico {
    protected int totalConsultas;
}
```

## 💻 Archivo: DemoRegistroMedico.java

```java
package com.medisalud;

public class DemoRegistroMedico {

    public static void main(String[] args) {
        RegistroMedico registro = new RegistroMedico();
        registro.totalConsultas = 12;

        System.out.println("Total de consultas: " + registro.totalConsultas);
    }
}
```

## 🧭 Explicación paso a paso

1. `totalConsultas` ahora es `protected`, en vez de por defecto.
2. `DemoRegistroMedico`, del mismo paquete, accede a `registro.totalConsultas` directamente: compila
   igual que en el Ejemplo 03.
3. Una clase de `com.biblioteca` sigue sin poder acceder directamente: sin herencia, `protected` no
   amplía nada respecto del acceso por defecto.

## ✅ Resultado esperado

```text
Total de consultas: 12
```

## 🧪 Casos de prueba

| Acceso | ¿Compila? | Igual que en el Ejemplo 03 |
|---|---|---|
| Mismo paquete | Sí | Sí |
| Otro paquete, sin herencia | No | Sí |

## 🔍 Análisis: errores frecuentes

**Error — Acceder a un miembro protected desde otro paquete, sin herencia (compilación).**

```java no-compila
package com.biblioteca;
import com.medisalud.RegistroMedico;

public class AccesoRegistroMedico {
    public static void main(String[] args) {
        RegistroMedico registro = new RegistroMedico();
        registro.totalConsultas = 12;
    }
}
```

```text
✖ The field RegistroMedico.totalConsultas is not visible Java(33554503) [Ln 7, Col 18]
```

El mensaje de `javac` en la terminal es `totalConsultas has protected access in RegistroMedico`,
distinto en la forma al del Ejemplo 03 (`is not public in ...; cannot be accessed from outside
package`). Pero el mensaje del **panel** de VS Code es exactamente el mismo en los dos casos: `The field
RegistroMedico.totalConsultas is not visible`. La forma del mensaje cambia según dónde se mire; el
alcance real, sin herencia, es idéntico.

> 📌 La ampliación real de `protected` (acceso desde una subclase, incluso de otro paquete) se estudia
> en el módulo de herencia. Aquí no se usa ni se menciona código de herencia.

## ❓ Preguntas de repaso

**1. [Selección]** **Pregunta:** sin herencia, ¿cuál es el alcance de un miembro `protected`?

- **A.** Solo la misma clase.
- **B.** El mismo paquete, igual que el acceso por defecto.
- **C.** Cualquier paquete.
- **D.** Ningún acceso, ni siquiera desde la misma clase.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Sin herencia, `protected` se comporta igual que el acceso por defecto.

</details>

**2. [Selección múltiple]** **Pregunta:** ¿cuáles afirmaciones sobre el Ejemplo 04 son verdaderas?

- **A.** El mensaje de `javac` es distinto del que se vio en el Ejemplo 03.
- **B.** El mensaje del panel de VS Code es distinto del que se vio en el Ejemplo 03.
- **C.** El alcance efectivo, sin herencia, es el mismo que el del Ejemplo 03.
- **D.** `protected` amplía su alcance a subclases de otros paquetes, un tema de un módulo posterior.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, C y D.** B es falsa: el panel da el mismo mensaje (`is not visible`) para
`protected` y para el acceso por defecto.

</details>

**3. [Abierta]** ¿Por qué este módulo no demuestra la verdadera diferencia entre `protected` y el
acceso por defecto?

<details>
<summary>🔑 Ver respuesta modelo</summary>

Porque esa diferencia solo existe cuando hay herencia de por medio (una subclase de otro paquete puede
acceder a un miembro `protected` de su superclase, pero no a uno con acceso por defecto), y este módulo
no cubre herencia todavía. Sin herencia, ambos modificadores tienen exactamente el mismo alcance.

</details>

# 💡 Ejemplo 03 — Modificador de acceso por defecto (default)

## 🌍 Contexto

Cuando un atributo o método **no lleva ningún modificador de acceso**, Java le da el acceso "por
defecto": accesible desde cualquier clase del **mismo paquete**, pero no desde otro paquete. Es más
permisivo que `private` (que ni el mismo paquete alcanza) y más restrictivo que `public` (que se
accede desde cualquier parte).

**Qué busca demostrar este ejemplo**: un atributo por defecto accedido directamente desde el mismo
paquete (compila) y el error real de intentar acceder a él desde otro paquete.

## 🏥📚 Caso de estudio

**MediSalud** lleva un registro con el total de consultas. Otra clase del **mismo paquete**
(`com.medisalud`) puede leerlo y modificarlo directamente. Una clase de **Biblioteca Universitaria**
(`com.biblioteca`), en otro paquete, no puede.

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
    int totalConsultas;
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

1. `totalConsultas` no lleva ningún modificador: tiene acceso "por defecto".
2. `DemoRegistroMedico`, del **mismo paquete** `com.medisalud`, accede a `registro.totalConsultas`
   directamente: compila sin problema.
3. Una clase de `com.biblioteca` que intente lo mismo no compila (ver "Análisis: errores frecuentes").

## ✅ Resultado esperado

```text
Total de consultas: 12
```

## 🧪 Casos de prueba

| Acceso | ¿Compila? |
|---|---|
| `registro.totalConsultas` desde `com.medisalud` (mismo paquete) | Sí |
| `registro.totalConsultas` desde `com.biblioteca` (otro paquete) | No |

## 🔍 Análisis: errores frecuentes

**Error — Acceder a un miembro por defecto desde otro paquete (compilación).**

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

El servidor de lenguaje marca el acceso como un **Error** real: `The field
RegistroMedico.totalConsultas is not visible`. El mensaje de `javac` en la terminal dice:
`totalConsultas is not public in RegistroMedico; cannot be accessed from outside package`.

## ❓ Preguntas de repaso

**1. [Selección]** **Pregunta:** ¿desde dónde se puede acceder a un miembro con acceso por defecto (sin
modificador)?

- **A.** Solo desde la misma clase.
- **B.** Desde cualquier clase del mismo paquete.
- **C.** Desde cualquier clase de cualquier paquete.
- **D.** Desde ninguna otra clase.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** El acceso por defecto permite el acceso desde cualquier clase del mismo
paquete, pero no desde otro paquete.

</details>

**2. [Selección múltiple]** **Pregunta:** ¿cuáles afirmaciones sobre `RegistroMedico` son verdaderas?

- **A.** `DemoRegistroMedico`, del mismo paquete, accede a `totalConsultas` directamente.
- **B.** Una clase de `com.biblioteca` puede acceder a `totalConsultas` directamente.
- **C.** El compilador rechaza el acceso desde `com.biblioteca`.
- **D.** El acceso por defecto es más permisivo que `private`.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, C y D.** B es falsa: el acceso por defecto no llega a otro paquete.

</details>

**3. [Abierta]** ¿En qué se diferencia el acceso por defecto de `private`?

<details>
<summary>🔑 Ver respuesta modelo</summary>

`private` solo permite el acceso desde la misma clase donde se declara; ni siquiera otra clase del
mismo paquete puede acceder. El acceso por defecto sí permite el acceso desde cualquier clase del mismo
paquete, aunque no desde otro paquete.

</details>

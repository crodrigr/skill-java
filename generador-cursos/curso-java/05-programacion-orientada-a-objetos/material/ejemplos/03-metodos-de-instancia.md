# 💡 Ejemplo 03 — Métodos de instancia

## 🌍 Contexto

Un **método** es un bloque de código declarado dentro de una clase que define un comportamiento. Un
método de instancia tiene nombre, un tipo de retorno (o `void` si no devuelve nada), una lista de
parámetros entre paréntesis y un cuerpo entre llaves. A diferencia de una variable local, un método de
instancia puede leer los **atributos del objeto** que lo invoca, sin que se los pasen como parámetro.

**Qué busca demostrar este ejemplo**: una clase con un método de instancia que usa un atributo del
objeto para calcular un resultado.

## 🏥 Caso de estudio

**MediSalud** quiere saber si un paciente es mayor de edad, a partir de su atributo `edad`.

## 🌳 Árbol de archivos (como se vería en VS Code)

```text
Modulo05ObjetosYClases
└── src
    └── com
        └── medisalud
            ├── EdadDelPaciente.java       ← nuevo en este ejemplo
            └── DemoEdadDelPaciente.java   ← nuevo en este ejemplo
```

## 💻 Archivo: EdadDelPaciente.java

```java
package com.medisalud;

public class EdadDelPaciente {
    public String nombreCompleto;
    public int edad;

    public boolean esMayorDeEdad() {
        return edad >= 18;
    }
}
```

## 💻 Archivo: DemoEdadDelPaciente.java

```java
package com.medisalud;

public class DemoEdadDelPaciente {

    public static void main(String[] args) {
        EdadDelPaciente paciente1 = new EdadDelPaciente();
        paciente1.nombreCompleto = "Ana Torres";
        paciente1.edad = 17;

        EdadDelPaciente paciente2 = new EdadDelPaciente();
        paciente2.nombreCompleto = "Luis Gomez";
        paciente2.edad = 18;

        System.out.println(paciente1.nombreCompleto + " es mayor de edad: " + paciente1.esMayorDeEdad());
        System.out.println(paciente2.nombreCompleto + " es mayor de edad: " + paciente2.esMayorDeEdad());
    }
}
```

## 🧭 Explicación paso a paso

1. `esMayorDeEdad()` es un método de instancia: no recibe ningún parámetro, pero lee el atributo `edad`
   del objeto que lo invoca.
2. Su tipo de retorno es `boolean`: el método termina con un `return` que devuelve `true` o `false`.
3. `paciente1.esMayorDeEdad()` evalúa `edad >= 18` usando el `edad` de **ese** objeto (`17`): da `false`.
4. `paciente2.esMayorDeEdad()` usa el `edad` de otro objeto (`18`): da `true`, aunque el método sea
   exactamente el mismo código.
5. Cada objeto "trae consigo" sus propios atributos cuando invoca un método de instancia.

## ✅ Resultado esperado

```text
Ana Torres es mayor de edad: false
Luis Gomez es mayor de edad: true
```

## 🧪 Casos de prueba

| `edad` | `esMayorDeEdad()` |
|---|---|
| `17` | `false` |
| `18` | `true` (límite) |

## 🔍 Análisis: errores frecuentes

Declarar un método sin tipo de retorno cuando sí devuelve un valor (o al revés, con `return` cuando es
`void`) es un error de compilación frecuente al empezar; se muestra con código real en el Ejemplo 04,
donde aparecen los dos casos (con y sin retorno) en la misma clase.

## ❓ Preguntas de repaso

**1. [Selección]** **Pregunta:** ¿qué necesita un método de instancia para leer el atributo `edad` de un
objeto?

- **A.** Que se lo pasen como parámetro.
- **B.** Nada más que ser invocado sobre ese objeto.
- **C.** Que el atributo sea `static`.
- **D.** Que el método se llame igual que el atributo.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Un método de instancia puede leer los atributos del objeto que lo invoca, sin
que se los pasen como parámetro.

</details>

**2. [Selección múltiple]** **Pregunta:** ¿cuáles afirmaciones sobre `esMayorDeEdad()` son verdaderas?

- **A.** Su tipo de retorno es `boolean`.
- **B.** No recibe ningún parámetro.
- **C.** Da un resultado distinto según el objeto que lo invoca.
- **D.** Modifica el atributo `edad` del objeto.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y C.** D es falsa: el método solo lee `edad`, no lo cambia.

</details>

**3. [Abierta]** Explica por qué `paciente1.esMayorDeEdad()` y `paciente2.esMayorDeEdad()` pueden dar
resultados distintos, aunque invocan exactamente el mismo método.

<details>
<summary>🔑 Ver respuesta modelo</summary>

Porque el método lee el atributo `edad` del objeto sobre el que se invoca. `paciente1` y `paciente2` son
objetos distintos, cada uno con su propio valor de `edad`, así que el mismo código de `esMayorDeEdad()`
da un resultado distinto para cada uno.

</details>

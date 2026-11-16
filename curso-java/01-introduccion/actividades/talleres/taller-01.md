# 🛠️ Taller 01 — Ficha de registro de un paciente

## 🎯 Objetivo

Crear desde cero, en Visual Studio Code, un proyecto de consola que muestre la ficha de registro
de un paciente de MediSalud, usando constantes y variables de distintos tipos primitivos
y mostrando los datos con `println` y `printf` (RA-6, RA-7, RA-9, RA-10, RA-11).

## 🌍 Contexto

La recepción de **MediSalud** imprime una ficha con los datos de cada paciente que llega
a una consulta. Hoy la escribe a mano. Vas a automatizarla con un programa de consola
que guarde los datos en variables y constantes y muestre la ficha con un formato fijo,
para que todas las fichas se vean igual.

## 🪜 Pasos

1. **Crear el proyecto.** En VS Code, abrí la paleta de comandos (`Ctrl+Shift+P`) y
   ejecutá **Java: Create Java Project... → No build tools**. Llamalo
   `FichaRegistroPaciente`. Dentro de `src` creá la carpeta `com/medisalud` y la clase
   `FichaRegistroPaciente.java`, y borrá `App.java` (Ejemplo 05).
2. **Declarar las constantes.** Dentro de `main`, declará estas dos constantes con
   `final`:

   | Identificador | Tipo | Valor |
   |---|---|---|
   | `NOMBRE_CLINICA` | `String` (texto) | `"MediSalud"` |
   | `VALOR_CONSULTA` | `double` | `85000.0` |

3. **Declarar las variables** del paciente, cada una con el tipo adecuado:

   | Identificador | Dato | Valor del caso 1 |
   |---|---|---|
   | `nombrePaciente` | Nombre (texto) | `"Ana Torres"` |
   | `edadPaciente` | Edad | `34` |
   | `numeroHistoria` | Número de historia clínica | `9876543210` |
   | `pesoKg` | Peso en kilogramos | `62.5` |
   | `grupoSanguineo` | Grupo sanguíneo (una letra) | `O` |
   | `esAfiliado` | ¿Es afiliado? | `true` |

   Elegí el tipo de cada una entre `int`, `long`, `double`, `char` y `boolean` (más
   `String` para el nombre). Recordá el sufijo del `long` y las comillas del `char`.
4. **Imprimir la ficha** con el formato exacto de los casos de prueba: usá `println` con
   `+` para las líneas de texto y `printf` (con `%.2f` y `%n`) para el peso y el valor
   de la consulta.
5. **Ejecutar** el proyecto con **Run** (el enlace que aparece sobre `main`) y comparar la
   salida de la terminal con la del caso de prueba 1.
6. **Provocar un error a propósito.** Agregá una línea que le asigne otro valor a
   `VALOR_CONSULTA`, mirá el panel **Problems** (`Ctrl+Shift+M`), anotá qué dice y quitá la línea.

## 💡 Ejemplo resuelto

Este es el comienzo del programa. **No es la solución completa**: completá los `TODO`.

```java
package com.medisalud;

public class FichaRegistroPaciente {

    public static void main(String[] args) {
        final String NOMBRE_CLINICA = "MediSalud";
        // TODO: declarar la segunda constante, VALOR_CONSULTA

        String nombrePaciente = "Ana Torres";
        int edadPaciente = 34;
        // TODO: declarar las otras cuatro variables del paciente

        System.out.println("==============================");
        System.out.println(NOMBRE_CLINICA + " - Ficha de registro");
        System.out.println("==============================");
        System.out.println("Paciente: " + nombrePaciente);
        System.out.println("Edad: " + edadPaciente + " años");
        // TODO: imprimir el resto de la ficha, hasta la línea final
    }
}
```

## 📦 Entregable

Un proyecto de VS Code con esta estructura, que compila y produce la salida de los casos
de prueba:

```text
FichaRegistroPaciente
├── .vscode
│   └── settings.json
├── lib
├── src
│   └── com
│       └── medisalud
│           └── FichaRegistroPaciente.java
└── README.md
```

Junto con el proyecto, entregá una nota breve con el mensaje que mostró el panel
**Problems** en el paso 6 y lo que hiciste para corregirlo.

## 🧪 Casos de prueba

**Caso 1 — datos del paso 3.** La salida debe ser:

```text
==============================
MediSalud - Ficha de registro
==============================
Paciente: Ana Torres
Edad: 34 años
Historia clínica: 9876543210
Peso: 62.50 kg
Grupo sanguíneo: O
Afiliado: true
Valor de la consulta: 85000.00
==============================
Gracias por confiar en MediSalud
```

**Caso 2 — otro paciente.** Cambiá **solo los valores** de las variables por estos y
ejecutá de nuevo: nombre `"Luis Mora"`, edad `52`, número de historia `1234567890123`,
peso `80.25`, grupo sanguíneo `A`, afiliado `false`. La salida debe ser:

```text
==============================
MediSalud - Ficha de registro
==============================
Paciente: Luis Mora
Edad: 52 años
Historia clínica: 1234567890123
Peso: 80.25 kg
Grupo sanguíneo: A
Afiliado: false
Valor de la consulta: 85000.00
==============================
Gracias por confiar en MediSalud
```

**Caso 3 — error de compilación (paso 6).** Al reasignar `VALOR_CONSULTA`, el panel
**Problems** debe mostrar un error como este (la línea y la columna dependen de dónde
escribiste la reasignación):

```text
✖ The final local variable VALOR_CONSULTA cannot be assigned. It must be blank and not using a compound assignment Java(536870970) [Ln ..., Col ...]
```

> ⚠️ En un computador configurado en español, `printf` puede mostrar los decimales con
> coma (`62,50`, `85000,00`). Es normal (Ejemplo 10).

## 📏 Criterios de evaluación

- El proyecto se llama `FichaRegistroPaciente`, con paquete `com.medisalud`, sin
  espacios ni tildes.
- `NOMBRE_CLINICA` y `VALOR_CONSULTA` están declaradas como constantes (`final`) y
  nunca se reasignan.
- Cada variable tiene un tipo adecuado (en particular, `long` con sufijo `L` para el
  número de historia y `char` con comillas simples para el grupo sanguíneo).
- Los nombres siguen las convenciones (`camelCase` y `MAYUSCULAS_CON_GUION_BAJO`).
- La salida coincide exactamente con los casos de prueba 1 y 2.
- Se identifica y se explica el error del paso 6.

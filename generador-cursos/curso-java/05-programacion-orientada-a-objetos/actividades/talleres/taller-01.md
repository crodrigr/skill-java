# 🛠️ Taller 01 — Ficha ampliada de un paciente

## 🎯 Objetivo

Crear desde cero, en Visual Studio Code, un proyecto de consola que declare una clase `Paciente` con un
constructor y varios métodos de instancia, cree al menos tres objetos con datos distintos e invoque sus
métodos, comprobando que cada uno mantiene su propio estado (RA-3, RA-4, RA-5, RA-8, RA-9, RA-10,
RA-11).

## 🌍 Contexto

**MediSalud** quiere una ficha de paciente más completa que la de los Ejemplos 06 y 07: además del
nombre, la edad y la historia clínica, necesita el plan de cobertura, y saber si el paciente es mayor de
edad y si tiene un plan premium.

## 🪜 Pasos

1. **Crear el proyecto.** En VS Code abre la paleta de comandos (`Ctrl+Shift+P`) y ejecuta **Java:
   Create Java Project... → No build tools**. Llámalo `FichaAmpliadaDePaciente`. Dentro de `src` crea la
   carpeta `com/medisalud` y la clase `Paciente.java`, y borra `App.java`.
2. **Declarar los atributos**: `nombreCompleto`, `edad`, `historiaClinica` y `planCobertura`, todos
   públicos.
3. **Declarar el constructor** con los cuatro parámetros, usando `this` para inicializar cada atributo.
4. **Declarar los métodos de instancia**: `mostrarFicha()` (imprime los cuatro atributos),
   `esMayorDeEdad()` (ya vista en los Ejemplos 06 y 07) y `tienePlanPremium()` (nuevo: compara
   `planCobertura` con `"premium"`, sin importar mayúsculas).
5. **Crear una clase `DemoPaciente`** con `main`, en el mismo paquete.
6. **Declarar los datos de entrada** de al menos tres pacientes al inicio de `main`.
7. **Crear los tres objetos** con `new Paciente(...)`, invocar sus tres métodos y mostrar la salida de
   cada uno.
8. **Comprobar la independencia**: verifica que los valores de un paciente no afectan a los demás.
9. **Provocar un error a propósito.** Invoca `mostrarFicha()` como si fuera un método estático
   (`Paciente.mostrarFicha();`, sin ningún objeto), mira el panel **Problems** (`Ctrl+Shift+M`), anota
   qué dice y corrígelo.

## 💡 Ejemplo resuelto

Este es el comienzo del programa. **No es la solución completa**: completa los `TODO`.

```java
package com.medisalud;

public class Paciente {
    public String nombreCompleto;
    public int edad;
    public String historiaClinica;
    public String planCobertura;

    // TODO: 1. declara el constructor con los cuatro parámetros, usando this

    // TODO: 2. declara mostrarFicha(), esMayorDeEdad() y tienePlanPremium()
}
```

## 📦 Entregable

El proyecto de Visual Studio Code `FichaAmpliadaDePaciente` con esta estructura:

```text
FichaAmpliadaDePaciente
└── src
    └── com
        └── medisalud
            ├── Paciente.java
            └── DemoPaciente.java
```

`Paciente.java` y `DemoPaciente.java` deben compilar sin errores en el panel Problems y producir la
salida de los tres pacientes.

## 🧪 Casos de prueba

Ejecuta el programa con los tres pacientes y compara la salida.

```text
Paciente: Ana Torres
Edad: 34
Historia clínica: HC-2026-000123
Plan: Premium
Es mayor de edad: true
Tiene plan premium: true
---
Paciente: Luis Gómez
Edad: 15
Historia clínica: HC-2026-000456
Plan: Básico
Es mayor de edad: false
Tiene plan premium: false
---
Paciente: Lucía Fernández
Edad: 70
Historia clínica: HC-2026-000789
Plan: premium
Es mayor de edad: true
Tiene plan premium: true
```

Para el paso 9, este es el mensaje que muestra el panel Problems al invocar `mostrarFicha()` como si
fuera estático:

```text
✖ Cannot make a static reference to the non-static method mostrarFicha() from the type Paciente Java(603979977) [Ln 21, Col 9]
```

## 📏 Criterios de evaluación

- Los tres pacientes producen exactamente la salida indicada.
- El paciente menor de edad (`edad = 15`) muestra `false` en "Es mayor de edad".
- `tienePlanPremium()` no distingue mayúsculas de minúsculas (`"Premium"` y `"premium"` dan `true`;
  `"Básico"` da `false`).
- Los tres objetos son independientes: los datos de uno no afectan a los demás.
- Los datos de entrada están al inicio de `main` y los identificadores siguen las convenciones del
  curso.
- Se identifica el mensaje del panel Problems del paso 9 y se corrige el error.

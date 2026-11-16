# 🛠️ Taller 01 — Ficha encapsulada de un paciente

## 🎯 Objetivo

Crear desde cero, en Visual Studio Code, un proyecto de consola que declare una clase `Paciente` con
atributos `private`, sus métodos `get`/`set` (con un `set` validado) y un constructor que use ese `set`
para inicializar el atributo validado, creando al menos dos pacientes distintos, uno con un valor
inválido (RA-3, RA-7, RA-8, RA-9, RA-10).

## 🌍 Contexto

**MediSalud** necesita una ficha de paciente que proteja sus datos: nadie debe poder asignar una edad
inválida directamente, y el teléfono y el nombre solo se leen a través de métodos.

## 🪜 Pasos

1. **Crear el proyecto.** En VS Code abre la paleta de comandos (`Ctrl+Shift+P`) y ejecuta **Java:
   Create Java Project... → No build tools**. Llámalo `FichaEncapsuladaDePaciente`. Dentro de `src`
   crea la clase `Paciente.java`, y borra `App.java`.
2. **Declarar los atributos**, todos `private`: `nombreCompleto`, `edad`, `telefono`.
3. **Declarar el constructor** con los tres parámetros, usando `setEdad(edad)` en vez de asignar el
   atributo directamente.
4. **Declarar los métodos**: `getNombreCompleto()`, `getEdad()`, `setEdad(edad)` (validado, rechaza
   negativos), `getTelefono()` y `mostrarFicha()`.
5. **Crear una clase `DemoPaciente`** con `main`.
6. **Declarar los datos de entrada** de al menos dos pacientes al inicio de `main`, uno con una edad
   inválida.
7. **Crear los objetos** con `new Paciente(...)` e invocar `mostrarFicha()` sobre cada uno.
8. **Comprobar** que el paciente con la edad inválida la conserva en su valor por defecto, sin detener
   el programa.
9. **Provocar un error a propósito.** Intenta asignar `paciente.edad = 34;` directamente desde `main`,
   mira el panel **Problems** (`Ctrl+Shift+M`), anota qué dice y corrígelo usando `setEdad(34)`.

## 💡 Ejemplo resuelto

Este es el comienzo del programa. **No es la solución completa**: completa los `TODO`.

```java
public class Paciente {
    private String nombreCompleto;
    private int edad;
    private String telefono;

    // TODO: 1. declara el constructor, usando setEdad(edad) para inicializar la edad

    // TODO: 2. declara getNombreCompleto(), getEdad(), setEdad(edad) validado, getTelefono() y mostrarFicha()
}
```

## 📦 Entregable

El proyecto de Visual Studio Code `FichaEncapsuladaDePaciente` con esta estructura:

```text
FichaEncapsuladaDePaciente
└── src
    ├── Paciente.java
    └── DemoPaciente.java
```

`Paciente.java` y `DemoPaciente.java` deben compilar sin errores en el panel Problems y producir la
salida de los dos pacientes.

## 🧪 Casos de prueba

```text
Paciente: Ana Torres
Edad: 34
Teléfono: 555-0101
---
Edad inválida (-5): se conserva la edad actual (0)
Paciente: Luis Gómez
Edad: 0
Teléfono: 555-0102
```

Para el paso 9, este es el mensaje que muestra el panel Problems al asignar `edad` directamente:

```text
⚠ The value of the field Paciente.edad is not used Java(570425421) [Ln 2, Col 17]
✖ The field Paciente.edad is not visible Java(33554503) [Ln 8, Col 18]
```

## 📏 Criterios de evaluación

- Los dos pacientes producen exactamente la salida indicada.
- El paciente con edad inválida conserva `edad = 0`, sin detener el programa.
- Los tres atributos son `private`; solo se accede a ellos a través de sus métodos.
- Los datos de entrada están al inicio de `main` y los identificadores siguen las convenciones del
  curso.
- Se identifica el mensaje del panel Problems del paso 9 y se corrige el error.

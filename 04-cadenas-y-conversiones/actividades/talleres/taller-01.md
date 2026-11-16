# 🛠️ Taller 01 — Ficha de registro completa

## 🎯 Objetivo

Crear desde cero, en Visual Studio Code, un proyecto de consola que normalice el nombre de un paciente,
obtenga sus iniciales, arme el código de su cita y convierta el importe de la consulta validado,
comprobándolo con tres casos de prueba (RA-2, RA-6, RA-7, RA-11, RA-13, RA-14).

## 🌍 Contexto

Al registrar un paciente, **MediSalud** recibe el nombre con espacios y mayúsculas inconsistentes, y el
importe de la consulta como texto (puede venir con errores). Vas a automatizar la ficha completa:
nombre normalizado, iniciales, código de la cita y el importe con el descuento aplicado, o un aviso si
el importe no es válido.

## 🪜 Pasos

1. **Crear el proyecto.** En VS Code abre la paleta de comandos (`Ctrl+Shift+P`) y ejecuta **Java:
   Create Java Project... → No build tools**. Llámalo `FichaRegistroCompleta`. Dentro de `src` crea la
   carpeta `com/medisalud` y la clase `FichaRegistroCompleta.java`, y borra `App.java`.
2. **Declarar los datos de entrada** al inicio de `main`: la constante `NOMBRE_CLINICA`, el nombre sin
   normalizar, el año y número de la cita, el importe como texto y el porcentaje de descuento.
3. **Normalizar el nombre** con `trim` y las transformaciones necesarias (primera letra en mayúscula,
   resto en minúscula).
4. **Obtener las iniciales** con `indexOf` y `substring` (o `charAt`), contemplando que el nombre
   normalizado pueda tener una sola palabra.
5. **Armar el código de la cita** con concatenación.
6. **Validar el importe** con `Character.isDigit` en un bucle; conviértelo con `Integer.parseInt`
   **solo si es válido**.
7. **Armar la ficha final** con un `StringBuilder`, incluido el importe con descuento o un mensaje si
   no era válido.
8. **Ejecutar** con los tres casos de prueba, cambiando solo los datos de entrada.
9. **Provocar un error a propósito.** Asigna el importe de texto directamente a una variable `double`
   (sin convertir), mira el panel **Problems** (`Ctrl+Shift+M`), anota qué dice y corrígelo.

## 💡 Ejemplo resuelto

Este es el comienzo del programa. **No es la solución completa**: completa los `TODO`.

```java
package com.medisalud;

public class FichaRegistroCompleta {

    public static void main(String[] args) {
        // Datos de entrada
        final String NOMBRE_CLINICA = "MediSalud";
        String nombreSucio = "  ana maría torres  ";
        int anioCita = 2026;
        int numeroCita = 42;
        String importeTexto = "85000";
        double porcentajeDescuento = 0.10;

        // TODO: 1. normaliza el nombre
        // TODO: 2. obtén las iniciales (con o sin apellido)
        // TODO: 3. arma el código de la cita
        // TODO: 4. valida el importe y conviértelo si es válido
        // TODO: 5. arma la ficha final con StringBuilder y muéstrala
    }
}
```

## 📦 Entregable

El proyecto de Visual Studio Code `FichaRegistroCompleta` con esta estructura:

```text
FichaRegistroCompleta
└── src
    └── com
        └── medisalud
            └── FichaRegistroCompleta.java
```

`FichaRegistroCompleta.java` debe compilar sin errores en el panel Problems y producir la salida de
cada caso de prueba.

## 🧪 Casos de prueba

Ejecuta el programa tres veces, cambiando solo los datos de entrada, y compara la salida.

**Caso 1** — con los datos del código (nombre de dos palabras, con espacios y mayúsculas mezcladas):

```text
=== MediSalud: ficha de registro ===
Paciente: Ana maría torres (Am)
Código de cita: CIT-2026-42
Importe con descuento: 76500
```

**Caso 2** — `nombreSucio = "  cristina  "` (nombre de una sola palabra):

```text
=== MediSalud: ficha de registro ===
Paciente: Cristina (C)
Código de cita: CIT-2026-42
Importe con descuento: 76500
```

**Caso 3** — `importeTexto = "85000 pesos"` (importe inválido):

```text
=== MediSalud: ficha de registro ===
Paciente: Ana maría torres (Am)
Código de cita: CIT-2026-42
Importe: no válido (no se aplicó ningún cálculo)
```

Para el paso 9, este es el mensaje que muestra el panel Problems al asignar el importe de texto
directamente a un `double`, en un programa mínimo (la posición depende de tu archivo):

```text
✖ Type mismatch: cannot convert from String to double Java(16777233) [Ln 7, Col 26]
```

## 📏 Criterios de evaluación

- Los tres casos producen exactamente la salida indicada.
- El caso 2 obtiene una sola inicial cuando el nombre no tiene apellido.
- El caso 3 muestra el aviso de importe no válido, sin ningún cálculo.
- Los datos de entrada están al inicio de `main` y los identificadores siguen las convenciones del
  curso.
- Se identifica el mensaje del panel Problems del paso 9 y se corrige el error.

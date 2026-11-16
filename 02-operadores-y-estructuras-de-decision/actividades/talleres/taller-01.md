# 🛠️ Taller 01 — Factura de una consulta médica

## 🎯 Objetivo

Crear desde cero, en Visual Studio Code, un proyecto de consola que calcule y muestre la factura
de una consulta de MediSalud, aplicando operadores aritméticos, de comparación y condicionales,
un `if - else if - else`, un `switch` y un operador ternario, y comprobarlo con tres casos de
prueba (RA-1, RA-2, RA-4, RA-5, RA-6, RA-7, RA-8, RA-9, RA-10).

## 🌍 Contexto

**MediSalud** factura cada consulta según estas reglas:

- **Total**: `VALOR_CONSULTA * cantidadConsultas`, con `VALOR_CONSULTA = 85000.0`.
- **Descuento**: los pacientes **afiliados con 3 o más consultas** reciben un descuento del
  10% (`DESCUENTO_AFILIADO = 0.10`) sobre el total.
- **Categoría** por edad: menos de 12, `Pediátrico`; de 12 a 17, `Adolescente`; de 18 a 64,
  `Adulto`; 65 o más, `Adulto mayor`.
- **Copago** según el plan de cobertura: `'B'` 30%, `'E'` 20%, `'P'` 10% y cualquier otra letra
  100% (sin plan). El paciente paga ese porcentaje del total menos el descuento.
- **Atención**: `Prioritaria` si tiene 65 años o más, o menos de 5; en otro caso, `General`.
- **Tipo de paciente**: `Afiliado` o `Particular`.

Hoy el cajero hace estos cálculos a mano. Vas a automatizarlos y a probarlos con tres pacientes.

## 🪜 Pasos

1. **Crear el proyecto.** En VS Code abre la paleta de comandos (`Ctrl+Shift+P`) y ejecuta
   **Java: Create Java Project... → No build tools**. Llámalo `FacturacionConsulta`. Dentro de
   `src` crea la carpeta `com/medisalud` y la clase `FacturacionConsulta.java`, y borra `App.java`
   (como en el Módulo 1).
2. **Declarar los datos de entrada** al inicio de `main`, en este orden: las tres constantes
   (`NOMBRE_CLINICA`, `VALOR_CONSULTA`, `DESCUENTO_AFILIADO`) y las variables `edadPaciente`,
   `esAfiliado`, `cantidadConsultas` y `planCobertura` (un `char`).
3. **Calcular el total y el descuento.** Calcula `total`; el descuento vale `0.0` salvo que el
   paciente sea afiliado **y** tenga 3 o más consultas (una condición con `&&` y una
   comparación). Calcula `neto = total - descuento`.
4. **Clasificar al paciente por edad** con un `if - else if - else` (variable `categoria`).
5. **Obtener el porcentaje de copago** con un `switch` sobre `planCobertura` y calcula
   `valorAPagar = neto * porcentajeCopago`.
6. **Determinar el tipo de paciente y la atención** con dos operadores ternarios: uno para
   `Afiliado` o `Particular` y otro para `Prioritaria` o `General`.
7. **Imprimir la factura** con el formato exacto de los casos de prueba y ejecutarla con los tres
   casos, cambiando solo los datos de entrada.
8. **Provocar un error a propósito.** Cambia una comparación por `if (edadPaciente = 65)`, mira
   el panel **Problems** (`Ctrl+Shift+M`), anota qué dice y corrígelo.

## 💡 Ejemplo resuelto

Este es el comienzo del programa. **No es la solución completa**: completa los `TODO`.

```java
package com.medisalud;

public class FacturacionConsulta {

    public static void main(String[] args) {
        // Datos de entrada
        final String NOMBRE_CLINICA = "MediSalud";
        final double VALOR_CONSULTA = 85000.0;
        final double DESCUENTO_AFILIADO = 0.10;
        int edadPaciente = 34;
        int cantidadConsultas = 3;
        // TODO: declara esAfiliado y planCobertura

        // 1. Total y descuento
        double total = VALOR_CONSULTA * cantidadConsultas;
        // TODO: calcula el descuento con una condición y luego el neto

        // TODO: 2. categoría, 3. copago, 4. tipo de paciente y atención, 5. factura
    }
}
```

## 📦 Entregable

El proyecto de Visual Studio Code `FacturacionConsulta` con esta estructura:

```text
FacturacionConsulta
└── src
    └── com
        └── medisalud
            └── FacturacionConsulta.java
```

`FacturacionConsulta.java` debe compilar sin errores en el panel Problems y producir la salida
de cada caso de prueba.

## 🧪 Casos de prueba

Ejecuta el programa tres veces, cambiando solo los datos de entrada, y compara la salida.

**Caso 1** — `edadPaciente = 34`, `esAfiliado = true`, `cantidadConsultas = 3`,
`planCobertura = 'P'`:

```text
=== MediSalud ===
Paciente: Afiliado, Adulto (34 años)
Consultas: 3
Total: 255000
Descuento: 25500
A pagar (plan P): 22950
Atención: General
```

**Caso 2** — `edadPaciente = 8`, `esAfiliado = false`, `cantidadConsultas = 1`,
`planCobertura = 'X'` (una letra sin plan):

```text
=== MediSalud ===
Paciente: Particular, Pediátrico (8 años)
Consultas: 1
Total: 85000
Descuento: 0
A pagar (plan X): 85000
Atención: General
```

**Caso 3** — `edadPaciente = 65` (valor límite), `esAfiliado = true`, `cantidadConsultas = 2`
(no llega a 3), `planCobertura = 'B'`:

```text
=== MediSalud ===
Paciente: Afiliado, Adulto mayor (65 años)
Consultas: 2
Total: 170000
Descuento: 0
A pagar (plan B): 51000
Atención: Prioritaria
```

Para el paso 8, este es el mensaje que muestra el panel Problems con `if (edadPaciente = 65)`
en un programa mínimo (la posición depende de tu archivo):

```text
✖ Type mismatch: cannot convert from int to boolean Java(16777233) [Ln 7, Col 13]
```

## 📏 Criterios de evaluación

- Los tres casos producen exactamente la salida indicada.
- El descuento solo se aplica al afiliado con 3 o más consultas (el caso 3 lo comprueba: es
  afiliado con 2 consultas y no recibe descuento).
- La categoría es correcta en el valor límite (`65` es `Adulto mayor`).
- El `switch` incluye el caso por defecto (`'X'` cobra el 100%).
- Se usa un ternario para el tipo de paciente y otro para la atención.
- Los datos de entrada están al inicio de `main` y los identificadores siguen las convenciones
  del curso.
- Se identifica el mensaje del panel Problems del paso 8 y se corrige el error.

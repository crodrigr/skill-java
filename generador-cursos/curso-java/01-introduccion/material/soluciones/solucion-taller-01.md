# 🔑 Solución — Taller 01: Ficha de registro de un paciente

Material docente. No enlazar desde archivos de audiencia estudiante (salvo la
subsección "Soluciones" de `specs/01-introduccion.md`).

## 🌳 Árbol de archivos

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

## 💻 Archivo: FichaRegistroPaciente.java

Código verificado con Temurin 25:

```java
package com.medisalud;

public class FichaRegistroPaciente {

    public static void main(String[] args) {
        final String NOMBRE_CLINICA = "MediSalud";
        final double VALOR_CONSULTA = 85000.0;

        String nombrePaciente = "Ana Torres";
        int edadPaciente = 34;
        long numeroHistoria = 9876543210L;
        double pesoKg = 62.5;
        char grupoSanguineo = 'O';
        boolean esAfiliado = true;

        System.out.println("==============================");
        System.out.println(NOMBRE_CLINICA + " - Ficha de registro");
        System.out.println("==============================");
        System.out.println("Paciente: " + nombrePaciente);
        System.out.println("Edad: " + edadPaciente + " años");
        System.out.println("Historia clínica: " + numeroHistoria);
        System.out.printf("Peso: %.2f kg%n", pesoKg);
        System.out.println("Grupo sanguíneo: " + grupoSanguineo);
        System.out.println("Afiliado: " + esAfiliado);
        System.out.printf("Valor de la consulta: %.2f%n", VALOR_CONSULTA);
        System.out.println("==============================");
        System.out.println("Gracias por confiar en " + NOMBRE_CLINICA);
    }
}
```

## ✅ Salida real

Caso 1:

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

Caso 2 (cambiando solo los valores de las variables):

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

## 🧩 Errores comunes y su corrección

Mensajes del panel **Problems** (VS Code), salvo el de `printf`, que aparece al ejecutar.

| Error del estudiante | Mensaje o síntoma | Corrección |
|---|---|---|
| Reasignar `VALOR_CONSULTA` (paso 6) | `The final local variable VALOR_CONSULTA cannot be assigned. It must be blank and not using a compound assignment` | Quitar la reasignación; una constante no cambia. |
| Escribir `9876543210` sin `L` | `The literal 9876543210 of type int is out of range` | `9876543210L` |
| Escribir `char grupoSanguineo = "O";` | `Type mismatch: cannot convert from String to char` | Comillas simples: `'O'` |
| Guardar el peso en un `int` | `Type mismatch: cannot convert from double to int` | Declarar `double pesoKg = 62.5;` |
| Usar `%d` para el peso en `printf` | `IllegalFormatConversionException: d != java.lang.Double` al ejecutar | Usar `%.2f` |
| Olvidar `%n` en `printf` | Las líneas quedan pegadas | Agregar `%n` al final del formato |
| Ver `62,50` en vez de `62.50` | Configuración regional en español | Es correcto; `printf` respeta la configuración regional |

Salida con configuración regional `es-CO` (solo cambian las líneas de `printf`):

```text
Peso: 62,50 kg
Valor de la consulta: 85000,00
```

# 🔑 Solución — Taller 01: Ficha de registro completa

Material docente. No enlazar desde archivos de audiencia estudiante (salvo la subsección
"Soluciones" de `specs/04-cadenas-y-conversiones.md`).

## 🌳 Árbol de archivos del proyecto final

```text
FichaRegistroCompleta
└── src
    └── com
        └── medisalud
            └── FichaRegistroCompleta.java
```

## 💻 Archivo: FichaRegistroCompleta.java

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

        // 1. Normalizar el nombre
        String nombreLimpio = nombreSucio.trim();
        String nombreNormalizado = nombreLimpio.substring(0, 1).toUpperCase() + nombreLimpio.substring(1).toLowerCase();

        // 2. Iniciales (con o sin apellido)
        int espacio = nombreNormalizado.indexOf(" ");
        String iniciales = (espacio == -1)
                ? "" + nombreNormalizado.charAt(0)
                : "" + nombreNormalizado.charAt(0) + nombreNormalizado.charAt(espacio + 1);

        // 3. Código de la cita
        String codigoCita = "CIT-" + anioCita + "-" + numeroCita;

        // 4. Validar y convertir el importe
        boolean esValido = !importeTexto.isEmpty();
        for (int i = 0; i < importeTexto.length() && esValido; i++) {
            if (!Character.isDigit(importeTexto.charAt(i))) {
                esValido = false;
            }
        }

        // 5. Armar la ficha con StringBuilder
        StringBuilder ficha = new StringBuilder();
        ficha.append("=== ").append(NOMBRE_CLINICA).append(": ficha de registro ===\n");
        ficha.append("Paciente: ").append(nombreNormalizado).append(" (").append(iniciales).append(")\n");
        ficha.append("Código de cita: ").append(codigoCita).append("\n");
        if (esValido) {
            int importe = Integer.parseInt(importeTexto);
            double totalConDescuento = importe - (importe * porcentajeDescuento);
            ficha.append("Importe con descuento: ").append((int) totalConDescuento);
        } else {
            ficha.append("Importe: no válido (no se aplicó ningún cálculo)");
        }

        System.out.println(ficha);
    }
}
```

## ✅ Resultado esperado

Caso 1 (los datos del código):

```text
=== MediSalud: ficha de registro ===
Paciente: Ana maría torres (Am)
Código de cita: CIT-2026-42
Importe con descuento: 76500
```

Caso 2 (`nombreSucio = "  cristina  "`):

```text
=== MediSalud: ficha de registro ===
Paciente: Cristina (C)
Código de cita: CIT-2026-42
Importe con descuento: 76500
```

Caso 3 (`importeTexto = "85000 pesos"`):

```text
=== MediSalud: ficha de registro ===
Paciente: Ana maría torres (Am)
Código de cita: CIT-2026-42
Importe: no válido (no se aplicó ningún cálculo)
```

## 🧭 Explicación paso a paso

1. `trim()` quita los espacios de los extremos; `substring(0, 1).toUpperCase()` y
   `substring(1).toLowerCase()` arman la primera letra en mayúscula y el resto en minúscula.
2. `indexOf(" ")` puede dar `-1` si el nombre no tiene apellido; el operador ternario elige entre una
   inicial o dos según ese resultado, sin necesitar un `if - else` completo.
3. El código de la cita se arma con concatenación simple, como en el Ejemplo 02.
4. La validación recorre **todos** los caracteres del importe con `Character.isDigit` antes de llamar
   a `Integer.parseInt`; si algún carácter no es un dígito, `esValido` queda en `false` y el importe
   nunca se convierte.
5. La ficha se arma con `StringBuilder`, con un mensaje distinto según si el importe era válido.

## 🐞 Errores comunes observados

| Error | Tipo | Cómo se ve | Corrección |
|---|---|---|---|
| Comparar nombres con `==` | lógico | dos ejecuciones con el "mismo" nombre pueden dar resultados distintos; el panel no marca ningún problema | usar `equals` |
| Convertir el importe sin validar | falla en ejecución | `NumberFormatException` con un importe que trae texto | validar con `Character.isDigit` antes de `Integer.parseInt` |
| Olvidar `trim()` antes de comparar u obtener iniciales | lógico | espacios de más rompen la comparación o desplazan los índices | aplicar `trim()` como primer paso |
| Ignorar el valor que devuelve `toUpperCase()` | lógico | el nombre sigue en minúsculas; el panel no marca ningún problema | asignar siempre el resultado a una variable |
| Asignar el importe de texto directamente a `double` | de compilación | `Type mismatch: cannot convert from String to double` | usar `Integer.parseInt` o `Double.parseDouble` |

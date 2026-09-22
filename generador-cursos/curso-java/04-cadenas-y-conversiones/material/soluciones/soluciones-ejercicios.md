# 🔑 Soluciones — Ejercicios Módulo 4

Material docente. No enlazar desde archivos de audiencia estudiante (salvo la subsección
"Soluciones" de `specs/04-cadenas-y-conversiones.md`).

## 🟢 Básico 01 — Predecir concatenaciones

| Línea | Resultado | Por qué |
|---|---|---|
| a) | `false` | `libroB` se creó con `new String(...)`: es un objeto distinto de `libroA`, aunque el contenido sea igual. |
| b) | `Préstamo 31` | Empieza con texto: `+` concatena de izquierda a derecha (`3`, luego `1`). |
| c) | `4 préstamos` | Empieza con dos números: se suman primero (`3 + 1 = 4`) y después se concatenan. |
| d) | `Usuarios activos: 5` | `+=` une el texto con el número convertido automáticamente. |
| e) | `PR-N-000045` | Cada `.concat(...)` devuelve una cadena nueva sobre la que se encadena el siguiente. |
| f) | `Multa: 1500.0` | Concatenar un `double` conserva su forma de mostrarse (con el `.0`). |

Salida real del programa:

```text
a) false
b) Préstamo 31
c) 4 préstamos
d) Usuarios activos: 5
e) PR-N-000045
f) Multa: 1500.0
```

## 🟢 Básico 02 — Índices y longitud

| Línea | Resultado |
|---|---|
| a) | `10` |
| b) | `A` |
| c) | `s` |
| d) | `false` |
| e) | `true 0` |

Salida real:

```text
a) 10
b) A
c) s
d) false
e) true 0
```

## 🟢 Básico 04 — Predecir subcadenas

| Línea | Resultado |
|---|---|
| a) | `PR` |
| b) | `N` |
| c) | `000045` |
| d) | `2` |
| e) | `4` |
| f) | `5` |

Salida real:

```text
a) PR
b) N
c) 000045
d) 2
e) 4
f) 5
```

## 🟡 Intermedio 02 — Descomponer un código

```java
public class DescomponerCodigo {

    public static void main(String[] args) {
        // Datos de entrada
        String codigoPrestamo = "PR-N-000045";

        int primerGuion = codigoPrestamo.indexOf("-");
        int segundoGuion = codigoPrestamo.indexOf("-", primerGuion + 1);
        String categoria = codigoPrestamo.substring(primerGuion + 1, segundoGuion);
        String numero = codigoPrestamo.substring(segundoGuion + 1);

        System.out.println("Categoría: " + categoria + " | Número: " + numero);
    }
}
```

Salida con los datos de partida:

```text
Categoría: N | Número: 000045
```

Resultado de los dos casos de prueba:

| Código | Categoría | Número |
|---|---|---|
| PR-N-000045 | N | `000045` |
| PR-C-5 | C | `5` |

## 🟢 Básico 03 — ¿Cómo comparo?

| Línea | Resultado | Método adecuado |
|---|---|---|
| a) | `true` | `equals` (dos literales iguales) |
| b) | `true` | `equalsIgnoreCase` |
| c) | `11` (positivo: "Marquez" va después de "Borges") | `compareTo` (solo el signo importa) |
| d) | `true` | `contains` |
| e) | `true` | `startsWith` |
| f) | `false` | Hace falta `trim()` antes de `equals` |

Salida real:

```text
a) true
b) true
c) 11
d) true
e) true
f) false
```

## 🟡 Intermedio 01 — Normalizar y formatear un nombre

```java
public class NormalizarUsuario {

    public static void main(String[] args) {
        // Datos de entrada
        String nombreSucio = "  carlos ramírez  ";

        String sinEspacios = nombreSucio.trim();
        String nombreNormalizado = sinEspacios.substring(0, 1).toUpperCase() + sinEspacios.substring(1).toLowerCase();

        System.out.println("[" + nombreNormalizado + "]");
    }
}
```

Salida con los datos de partida:

```text
[Carlos ramírez]
```

Resultado de los tres casos de prueba:

| Nombre de entrada | Nombre normalizado |
|---|---|
| [  carlos ramírez  ] | Carlos ramírez |
| [  ANA  ] | Ana |
| [lucía] | Lucía |

## 🟢 Básico 05 — Tipo y valor de conversiones

| Línea | Tipo | Valor | ¿Se pierden datos? |
|---|---|---|---|
| a) | `double` | `96.5` | No |
| b) | `int` (3) y `double` (3.5) | ver anterior | Sí, la primera (división entera) |
| c) | `int` | `85999` | Sí, se cortan los decimales |
| d) | `int` | `77` | No (es la conversión esperada) |
| e) | `byte` | `-56` | Sí, desbordamiento |
| f) | `double` | `1.0E16` | Sí, pérdida de precisión |

Salida real:

```text
a) 96.5
b) 3 | 3.5
c) 85999
d) 77
e) -56
f) 1.0E16
```

## 🟢 Básico 06 — Empaquetado y parseInt

| Línea | Resultado |
|---|---|
| a) | `true` (100 está en el rango cacheado) |
| b) | `false` (300 no está en el rango cacheado) |
| c) | `true` (`equals` siempre compara el valor) |
| d) | `120` |
| e) | `19.5` |
| f) | `true false` |

Salida real:

```text
a) true
b) false
c) true
d) 120
e) 19.5
f) true false
```

## 🟡 Intermedio 03 — Validar y convertir un importe

```java
public class ValidarImporte {

    public static void main(String[] args) {
        // Datos de entrada
        String diasPrestamoTexto = "15 dias";

        boolean esValido = !diasPrestamoTexto.isEmpty();
        for (int i = 0; i < diasPrestamoTexto.length() && esValido; i++) {
            if (!Character.isDigit(diasPrestamoTexto.charAt(i))) {
                esValido = false;
            }
        }

        if (esValido) {
            int dias = Integer.parseInt(diasPrestamoTexto);
            System.out.println("Días de préstamo: " + dias);
        } else {
            System.out.println("El valor '" + diasPrestamoTexto + "' no es válido");
        }
    }
}
```

Salida con los datos de partida (texto inválido):

```text
El valor '15 dias' no es válido
```

Resultado de los tres casos de prueba:

| Texto | Resultado |
|---|---|
| [] | El valor '' no es válido |
| [15] | Días de préstamo: 15 |
| [15 dias] | El valor '15 dias' no es válido |

## 🟡 Intermedio 04 — Listado con StringBuilder

```java
public class ListadoConBuilder {

    public static void main(String[] args) {
        // Datos de entrada
        String pacienteA = "Ana Torres";
        String pacienteB = "Bruno Peña";
        String pacienteC = "Carla Ruiz";

        StringBuilder listado = new StringBuilder();
        listado.append("- ").append(pacienteA).append("\n");
        listado.append("- ").append(pacienteB).append("\n");
        listado.append("- ").append(pacienteC).append("\n");

        System.out.print(listado);
    }
}
```

Salida real:

```text
- Ana Torres
- Bruno Peña
- Carla Ruiz
```

Con un solo paciente:

```text
- Ana Torres
```

## 🔴 Avanzado 01 — Corregir un programa con errores de texto

**Ronda 1 — error de compilación.** Mensaje del panel Problems para el código de partida:

```text
✖ Type mismatch: cannot convert from String to double Java(16777233) [Ln 9, Col 24]
```

| Mensaje | Causa | Corrección |
|---|---|---|
| `Type mismatch: cannot convert from String to double` | se asignó el texto `"29.8"` directamente a una variable `double` | escribir el literal `29.8` sin comillas |

**Ronda 2 — el error que el panel no marca.** Con la variable `cupos` corregida, el programa compila
(el panel Problems no marca ningún problema):

```java error-logico
package com.biblioteca;

public class RevisionCatalogo {

    public static void main(String[] args) {
        // Datos de entrada
        String tituloGuardado = "Cien años de soledad";
        String tituloConsultado = new String("Cien años de soledad");
        double cupos = 29.8;

        boolean mismoTitulo = tituloGuardado == tituloConsultado;
        int cuposReales = (int) cupos;
        char ultimaLetra = tituloGuardado.charAt(tituloGuardado.length() - 1);

        System.out.println("¿Mismo título? " + mismoTitulo);
        System.out.println("Cupos reales: " + cuposReales);
        System.out.println("Última letra: " + ultimaLetra);
    }
}
```

Pero su salida no coincide con la esperada:

```text
¿Mismo título? false
Cupos reales: 29
Última letra: d
```

`¿Mismo título?` muestra `false`, aunque los dos textos dicen lo mismo: `tituloConsultado` se creó con
`new String(...)`, así que es un objeto distinto, y `==` compara objetos, no contenido. Se corrige con
`.equals(...)`.

**Ronda 3 — el programa se detiene.** Con la comparación corregida, pero con `charAt(length())` (sin
`-1`):

> ⚠️ **Este programa se detiene con un error.** Es intencional: sirve para mostrar el mensaje real.

```java falla-en-ejecucion
package com.biblioteca;

public class RevisionCatalogo {

    public static void main(String[] args) {
        // Datos de entrada
        String tituloGuardado = "Cien años de soledad";
        String tituloConsultado = new String("Cien años de soledad");
        double cupos = 29.8;

        boolean mismoTitulo = tituloGuardado.equals(tituloConsultado);
        int cuposReales = (int) cupos;
        char ultimaLetra = tituloGuardado.charAt(tituloGuardado.length());

        System.out.println("¿Mismo título? " + mismoTitulo);
        System.out.println("Cupos reales: " + cuposReales);
        System.out.println("Última letra: " + ultimaLetra);
    }
}
```

```text
Exception in thread "main" java.lang.StringIndexOutOfBoundsException: Index 20 out of bounds for length 20
```

El índice `length()` ya se pasa de rango; el último índice válido es `length() - 1`.

**Programa corregido:**

```java
package com.biblioteca;

public class RevisionCatalogo {

    public static void main(String[] args) {
        // Datos de entrada
        String tituloGuardado = "Cien años de soledad";
        String tituloConsultado = new String("Cien años de soledad");
        double cupos = 29.8;

        boolean mismoTitulo = tituloGuardado.equals(tituloConsultado);
        int cuposReales = (int) cupos;
        char ultimaLetra = tituloGuardado.charAt(tituloGuardado.length() - 1);

        System.out.println("¿Mismo título? " + mismoTitulo);
        System.out.println("Cupos reales: " + cuposReales);
        System.out.println("Última letra: " + ultimaLetra);
    }
}
```

```text
¿Mismo título? true
Cupos reales: 29
Última letra: d
```

## 🏆 Desafío 01 — Etiqueta de historia clínica con salida exacta

Solución (la del caso 1; los otros casos solo cambian los datos de entrada):

```java
package com.medisalud;

public class EtiquetaHistoriaClinica {

    public static void main(String[] args) {
        // Datos de entrada
        String historiaClinica = "HC-2026-000123";
        String nombreCompletoPaciente = "Ana Torres";
        String importeTexto = "85000";
        double porcentajeDescuento = 0.10;

        // 1. El importe se valida ANTES de construir cualquier otra cosa
        boolean esValido = !importeTexto.isEmpty();
        for (int i = 0; i < importeTexto.length() && esValido; i++) {
            if (!Character.isDigit(importeTexto.charAt(i))) {
                esValido = false;
            }
        }
        if (!esValido) {
            System.out.println("Importe no válido: no se puede generar la etiqueta.");
            return;
        }

        // 2. Conversión con la clase envolvente
        int importe = Integer.parseInt(importeTexto);
        double totalConDescuento = importe - (importe * porcentajeDescuento);

        // 3. Descomponer la historia clínica con subcadenas
        String prefijo = historiaClinica.substring(0, 2);
        String anio = historiaClinica.substring(3, 7);
        String numero = historiaClinica.substring(8);

        // 4. Iniciales (con o sin apellido)
        int espacio = nombreCompletoPaciente.indexOf(" ");
        String iniciales = (espacio == -1)
                ? "" + nombreCompletoPaciente.charAt(0)
                : "" + nombreCompletoPaciente.charAt(0) + nombreCompletoPaciente.charAt(espacio + 1);

        // 5. StringBuilder: la etiqueta se arma por partes
        StringBuilder etiqueta = new StringBuilder();
        etiqueta.append("=== Etiqueta de historia clínica ===\n");
        etiqueta.append("Historia: ").append(prefijo).append(" ").append(anio).append(" ").append(numero).append("\n");
        etiqueta.append("Paciente: ").append(nombreCompletoPaciente).append(" (").append(iniciales).append(")\n");
        etiqueta.append("Importe con descuento: ").append((int) totalConDescuento);

        System.out.println(etiqueta);
    }
}
```

Salida de cada caso (las mismas del enunciado):

**Caso 1:**

```text
=== Etiqueta de historia clínica ===
Historia: HC 2026 000123
Paciente: Ana Torres (AT)
Importe con descuento: 76500
```

**Caso 2:**

```text
=== Etiqueta de historia clínica ===
Historia: HC 2026 000000
Paciente: Ana Torres (AT)
Importe con descuento: 76500
```

**Caso 3:**

```text
=== Etiqueta de historia clínica ===
Historia: HC 2026 000123
Paciente: Cristina (C)
Importe con descuento: 76500
```

**Caso 4:**

```text
Importe no válido: no se puede generar la etiqueta.
```

Justificación de los métodos (la que debe aparecer en los comentarios del estudiante):

- **Validar antes de convertir**: se hace primero y con un `return` temprano, porque ninguna otra parte
  de la etiqueta tiene sentido si el importe no es válido.
- **Descomponer la historia clínica**: `substring`, porque tiene un formato de posiciones fijas.
- **Iniciales**: un operador ternario con `indexOf`, porque son solo dos resultados posibles (con o sin
  apellido).
- **Armar la etiqueta**: `StringBuilder`, porque se construye en varias partes.

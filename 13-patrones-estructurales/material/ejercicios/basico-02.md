# 🟢 Básico 02 — Identificar violación de Bridge

## 🧩 Problema

La Biblioteca Universitaria genera reportes de préstamos con estas clases:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class ReporteDiarioTexto {
    public String generar() {
        return "REPORTE (texto): 12 prestamos hoy";
    }
}
```

```java
package com.biblioteca;

public class ReporteDiarioHtml {
    public String generar() {
        return "<html><body>12 prestamos hoy</body></html>";
    }
}
```

```java
package com.biblioteca;

public class ReporteMensualTexto {
    public String generar() {
        return "REPORTE (texto): 340 prestamos este mes";
    }
}
```

```java
package com.biblioteca;

public class ReporteMensualHtml {
    public String generar() {
        return "<html><body>340 prestamos este mes</body></html>";
    }
}
```

Sin escribir código, responde: si se agrega un tercer formato de salida (por ejemplo, PDF), ¿cuántas
clases nuevas hacen falta, y por qué esa cantidad depende de cuántos períodos de reporte existan?

## 📏 Criterios de evaluación de la solución

- Calcula que hacen falta 2 clases nuevas (`ReporteDiarioPdf`, `ReporteMensualPdf`) — una por cada
  período existente.
- Explica que esa cantidad crece porque el período (diario/mensual) y el formato (texto/html/pdf) están
  mezclados en una sola jerarquía de herencia: cada combinación nueva de las dos dimensiones exige una
  clase nueva.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-5**: reconocer qué resuelve Bridge.
- **RA-6**: identificar una explosión combinatoria de subclases en un fragmento dado.

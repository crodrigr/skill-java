# 🟢 Básico 04 — Identificar violación de Decorator

## 🧩 Problema

La Biblioteca Universitaria calcula el costo de un préstamo con estas clases:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class Prestamo {
    public double costo() {
        return 0.0;
    }

    public String descripcion() {
        return "Prestamo estandar";
    }
}
```

```java
package com.biblioteca;

public class PrestamoConRenovacion extends Prestamo {
    public double costo() {
        return 50.0;
    }

    public String descripcion() {
        return "Prestamo estandar + renovacion automatica";
    }
}
```

```java
package com.biblioteca;

public class PrestamoConSeguro extends Prestamo {
    public double costo() {
        return 80.0;
    }

    public String descripcion() {
        return "Prestamo estandar + seguro contra daños";
    }
}
```

```java
package com.biblioteca;

public class PrestamoConAmbos extends Prestamo {
    public double costo() {
        return 130.0;
    }

    public String descripcion() {
        return "Prestamo estandar + renovacion automatica + seguro contra daños";
    }
}
```

Sin escribir código, responde: si se agrega un tercer extra opcional (por ejemplo, envío a domicilio),
¿cuántas clases nuevas hacen falta para cubrir todas las combinaciones posibles con los dos extras
existentes?

## 📏 Criterios de evaluación de la solución

- Calcula que hacen falta 4 clases nuevas (envío solo, envío + renovación, envío + seguro, envío +
  renovación + seguro) para cubrir todas las combinaciones con los extras existentes.
- Explica que esa cantidad crece porque cada combinación de extras está fija en una subclase, decidida en
  tiempo de compilación.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-11**: reconocer qué resuelve Decorator.
- **RA-12**: identificar una explosión combinatoria de subclases de extras en un fragmento dado.

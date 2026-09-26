# 🟡 Intermedio 04 — Aplicar Decorator

## 🧩 Problema

Tienes las mismas clases de la Biblioteca Universitaria del ejercicio Básico 04:

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

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        System.out.println(new Prestamo().costo());
        System.out.println(new PrestamoConRenovacion().costo());
        System.out.println(new PrestamoConSeguro().costo());
        System.out.println(new PrestamoConAmbos().costo());
    }
}
```

Rediséñalo para que respete Decorator: los extras opcionales deben poder combinarse envolviendo objetos
en tiempo de ejecución, sin ninguna clase que represente una combinación en particular.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Préstamo sin extras, con renovación, con seguro, con ambos (envolviendo un decorador dentro de otro) | Costo final de cada combinación | Idéntico al de la versión original: `0.0`, `50.0`, `80.0`, `130.0` |
| Cantidad de clases que representan la combinación "renovación + seguro" en la versión refactorizada | Búsqueda en el código | Ninguna clase de combinación: se logra envolviendo `ConSeguro(new ConRenovacion(...))` |

## 📏 Criterios de evaluación de la solución

- Declara una clase abstracta (por ejemplo, `PrestamoDecorado`) que extiende `Prestamo` y envuelve otro
  `Prestamo`.
- Declara `ConRenovacion` y `ConSeguro` como decoradores concretos, cada uno sumando su propio costo al
  del préstamo que envuelve.
- El costo final de combinar ambos extras es idéntico al de la versión original (`130.0`).
- Ninguna clase representa la combinación "renovación + seguro" en particular.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni excepciones como parte del diseño.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-13**: implementar Decorator para un caso dado.

# 🟡 Intermedio 03 — Aplicar Mediator

## 🧩 Problema

Tienes las mismas clases de la Biblioteca Universitaria del ejercicio Básico 03:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class AreaPrestamos {
    private AreaReservas areaReservas;
    private AreaMultas areaMultas;

    public AreaPrestamos(AreaReservas areaReservas, AreaMultas areaMultas) {
        this.areaReservas = areaReservas;
        this.areaMultas = areaMultas;
    }

    public void registrarDevolucion(String ejemplar) {
        System.out.println("Prestamos: registrando devolucion de " + ejemplar);
        areaReservas.recibirAvisoDeDevolucion(ejemplar);
        areaMultas.recibirAvisoDeDevolucion(ejemplar);
    }
}
```

```java
package com.biblioteca;

public class AreaReservas {
    public void recibirAvisoDeDevolucion(String ejemplar) {
        System.out.println("Reservas: notificando al proximo interesado en " + ejemplar);
    }
}
```

```java
package com.biblioteca;

public class AreaMultas {
    public void recibirAvisoDeDevolucion(String ejemplar) {
        System.out.println("Multas: verificando atraso de " + ejemplar);
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        AreaReservas areaReservas = new AreaReservas();
        AreaMultas areaMultas = new AreaMultas();
        AreaPrestamos areaPrestamos = new AreaPrestamos(areaReservas, areaMultas);

        areaPrestamos.registrarDevolucion("Cien anios de soledad");
    }
}
```

Rediséñalas para que respeten Mediator: declara un mediador que centralice la comunicación entre las
tres áreas, de forma que `AreaPrestamos` no conozca directamente a las otras dos.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Registrar la devolución de un ejemplar | Salida completa del programa antes y después de refactorizar | Idéntica, carácter por carácter |
| Se agrega un área nueva, registrada en el mediador | Archivos `AreaPrestamos.java`, `AreaReservas.java` y `AreaMultas.java` | Sin ninguna modificación (solo cambia el mediador) |

## 📏 Criterios de evaluación de la solución

- Declara una clase (por ejemplo, `MediadorDeBiblioteca`) que registra las áreas y coordina la
  comunicación entre ellas.
- `AreaPrestamos` conoce solo al mediador, no a `AreaReservas` ni a `AreaMultas` directamente.
- La salida por consola no cambia respecto de la versión original.
- Un área nueva se agrega registrándola en el mediador, sin modificar las áreas existentes.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni excepciones como parte del diseño.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-10**: implementar Mediator para un caso dado.

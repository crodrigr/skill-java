# 🟡 Intermedio 08 — Aplicar Strategy

## 🧩 Problema

Tienes la misma clase de la Biblioteca Universitaria del ejercicio Básico 08:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class SolicitudDeSala {
    private String horario;

    public SolicitudDeSala(String horario) {
        this.horario = horario;
    }

    public double calcularCosto() {
        if (horario.equals("DIURNO")) {
            return 500.0;
        } else if (horario.equals("NOCTURNO")) {
            return 700.0;
        } else if (horario.equals("FIN_DE_SEMANA")) {
            return 900.0;
        }
        throw new IllegalArgumentException("Horario desconocido: " + horario);
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        SolicitudDeSala solicitudDiurna = new SolicitudDeSala("DIURNO");
        SolicitudDeSala solicitudNocturna = new SolicitudDeSala("NOCTURNO");
        System.out.println(solicitudDiurna.calcularCosto());
        System.out.println(solicitudNocturna.calcularCosto());
    }
}
```

Rediséñala para que respete Strategy: declara una interfaz que encapsule el cálculo del costo, con una
implementación por horario, elegida en tiempo de ejecución sin ningún condicional en `SolicitudDeSala`.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Una solicitud diurna y una nocturna | Salida completa del programa antes y después de refactorizar | Idéntica, carácter por carácter |
| Se agrega un horario nuevo (`"FERIADO"`) como una clase que implementa la interfaz de estrategia | Archivo `SolicitudDeSala.java` | Sin ninguna modificación |

## 📏 Criterios de evaluación de la solución

- Declara una interfaz (por ejemplo, `EstrategiaDeCosto`) con un método `calcular()`.
- `SolicitudDeSala` recibe la estrategia por composición (constructor) y delega en ella, sin ningún
  condicional.
- La salida por consola no cambia respecto de la versión original.
- Un horario nuevo se agrega con una clase nueva, sin modificar `SolicitudDeSala.java`.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni excepciones como parte del diseño.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-25**: implementar Strategy para un caso dado.

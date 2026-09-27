# 🔴 Avanzado 02 — Corregir un diseño con dos patrones ausentes

## 🧩 Problema

La Biblioteca Universitaria resuelve retiros de préstamos interbibliotecarios con este código, que
tiene dos problemas de diseño distintos:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class SistemaDePrestamos {
    public String resolverRetiro(String modalidad, String codigo) {
        if (modalidad.equals("MOSTRADOR")) {
            return "Retiro de " + codigo + " en mostrador";
        } else if (modalidad.equals("CASILLERO")) {
            return "Retiro de " + codigo + " en casillero";
        }
        throw new IllegalArgumentException("Modalidad desconocida: " + modalidad);
    }

    public void avisarVencimientoProximo(String codigo) {
        System.out.println("Alerta: el prestamo de " + codigo + " vence pronto");
        System.out.println("Panel: mostrar aviso de vencimiento de " + codigo);
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        SistemaDePrestamos sistema = new SistemaDePrestamos();

        System.out.println(sistema.resolverRetiro("MOSTRADOR", "L001"));
        System.out.println(sistema.resolverRetiro("CASILLERO", "L002"));

        sistema.avisarVencimientoProximo("L001");
    }
}
```

Encuentra y corrige las dos violaciones por separado:

1. `resolverRetiro()` resuelve la modalidad de retiro con un condicional — viola Strategy.
2. `avisarVencimientoProximo()` notifica a cada interesado con una llamada directa dentro de su propio
   cuerpo — viola Observer.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Retirar `"L001"` en mostrador y `"L002"` en casillero, y avisar el vencimiento de `"L001"` | Salida completa del programa antes y después de corregir | Idéntica, carácter por carácter |
| `SistemaDePrestamos.java` después de corregir | Presencia de un condicional en `resolverRetiro()` | Ninguna |
| Se agrega un tercer observador de vencimiento nuevo, registrado dinámicamente | Método que notifica el vencimiento | Sin ninguna modificación |

## 📏 Criterios de evaluación de la solución

- Declara una interfaz (por ejemplo, `EstrategiaDeRetiro`) con una implementación por modalidad, elegida
  por quien llama, sin condicional en `SistemaDePrestamos`.
- Declara una interfaz de observador (por ejemplo, `ObservadorDeVencimiento`) y una lista de
  observadores registrados dinámicamente para el aviso de vencimiento.
- La salida por consola no cambia respecto de la versión original.
- Las dos correcciones son independientes entre sí: una no depende de la otra.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni excepciones como parte del diseño.

## 📊 Dificultad

Avanzado

## 🎓 Resultados de aprendizaje

- **RA-25**: implementar Strategy para un caso dado.
- **RA-19**: implementar Observer para un caso dado.
- **RA-29**: dado un problema de diseño nuevo, elegir el patrón de comportamiento adecuado y
  justificarlo.

# 🟢 Básico 05 — Identificar violación de Singleton

## 🧩 Problema

La Biblioteca Universitaria cuenta los préstamos desde dos lugares distintos:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class ContadorDePrestamos {
    private int total = 0;

    public void registrarPrestamo() {
        total = total + 1;
    }

    public int getTotal() {
        return total;
    }
}
```

```java
package com.biblioteca;

public class SalaLectura {
    private ContadorDePrestamos contador = new ContadorDePrestamos();

    public void prestar() {
        contador.registrarPrestamo();
    }

    public ContadorDePrestamos getContador() {
        return contador;
    }
}
```

```java
package com.biblioteca;

public class Mostrador {
    private ContadorDePrestamos contador = new ContadorDePrestamos();

    public void prestar() {
        contador.registrarPrestamo();
    }

    public ContadorDePrestamos getContador() {
        return contador;
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        SalaLectura sala = new SalaLectura();
        Mostrador mostrador = new Mostrador();

        sala.prestar();
        mostrador.prestar();
        mostrador.prestar();

        boolean mismaInstancia = sala.getContador() == mostrador.getContador();
        System.out.println("mismaInstancia=" + mismaInstancia);
        System.out.println("total en sala=" + sala.getContador().getTotal());
        System.out.println("total en mostrador=" + mostrador.getContador().getTotal());
    }
}
```

## ✅ Salida real del programa

```text
mismaInstancia=false
total en sala=1
total en mostrador=2
```

Sin escribir código, respondé: ¿por qué `mismaInstancia` da `false`? ¿Qué implica eso para un reporte
que solo consulte el contador de `SalaLectura`? Y en general: ¿qué riesgo real corre un diseño que abusa
de Singleton para evitar pasar dependencias por constructor, más allá de este caso puntual?

## 📏 Criterios de evaluación de la solución

- Identifica que `SalaLectura` y `Mostrador` crean cada uno su propia instancia de
  `ContadorDePrestamos`, porque su constructor es público.
- Explica que un reporte que consulte solo uno de los dos contadores vería un total incompleto.
- Menciona al menos un riesgo real de abusar de Singleton (estado global oculto, acoplamiento difícil de
  probar) más allá del caso concreto del contador.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-14**: reconocer qué es Singleton.
- **RA-15**: reconocer los riesgos de abusar de Singleton.

# 🟡 Intermedio 05 — Aplicar Singleton

## 🧩 Problema

Tienes el mismo sistema de la Biblioteca Universitaria del ejercicio Básico 05:

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

Convierte `ContadorDePrestamos` en un Singleton correcto (sin manejo de hilos, el curso no lo cubre), de
forma que `SalaLectura` y `Mostrador` compartan el mismo contador.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| `ContadorDePrestamos.getInstancia()` llamado dos veces | Comparación con `==` | `true`: es la misma referencia |
| Préstamos registrados desde `SalaLectura` y `Mostrador` | `getTotal()` desde cualquiera de los dos | El total combinado de ambos |

## 📏 Criterios de evaluación de la solución

- El constructor de `ContadorDePrestamos` es `private`.
- Existe un único punto de acceso estático (`getInstancia()`) que siempre devuelve la misma instancia.
- `SalaLectura` y `Mostrador` ya no crean su propia instancia con `new`.

## 🚧 Restricciones

- No se usa `synchronized` ni ninguna verificación de hilos.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-16**: implementar Singleton correctamente para un caso dado.

# 🟢 Básico 03 — Identificar violación de Builder

## 🧩 Problema

La Biblioteca Universitaria registra cada préstamo con esta clase:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class RegistroPrestamo {
    private String usuario;
    private String material;
    private String fechaDevolucionExtendida;
    private boolean renovado;
    private String observacionBibliotecario;
    private String contactoAlternativo;

    public RegistroPrestamo(String usuario, String material, String fechaDevolucionExtendida,
                             boolean renovado, String observacionBibliotecario, String contactoAlternativo) {
        this.usuario = usuario;
        this.material = material;
        this.fechaDevolucionExtendida = fechaDevolucionExtendida;
        this.renovado = renovado;
        this.observacionBibliotecario = observacionBibliotecario;
        this.contactoAlternativo = contactoAlternativo;
    }

    public String resumen() {
        return usuario + " - " + material
            + ", fecha extendida: " + (fechaDevolucionExtendida == null ? "-" : fechaDevolucionExtendida)
            + ", renovado: " + renovado
            + ", observacion: " + (observacionBibliotecario == null ? "-" : observacionBibliotecario)
            + ", contacto alternativo: " + (contactoAlternativo == null ? "-" : contactoAlternativo);
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        RegistroPrestamo correcto = new RegistroPrestamo(
            "Elena Ruiz", "Cien anios de soledad", "2026-10-15", true, "Ninguna", "Pablo Ruiz - 555-1111"
        );
        System.out.println("correcto: " + correcto.resumen());

        // Error facil de cometer: observacion y contacto alternativo intercambiados por accidente
        // (el compilador no puede detectarlo: ambos son String)
        RegistroPrestamo confundido = new RegistroPrestamo(
            "Pablo Sosa", "Rayuela", null, false, "Ana Sosa - 555-2222", "Sin observaciones"
        );
        System.out.println("confundido: " + confundido.resumen());
    }
}
```

## ✅ Salida real del programa

```text
correcto: Elena Ruiz - Cien anios de soledad, fecha extendida: 2026-10-15, renovado: true, observacion: Ninguna, contacto alternativo: Pablo Ruiz - 555-1111
confundido: Pablo Sosa - Rayuela, fecha extendida: -, renovado: false, observacion: Ana Sosa - 555-2222, contacto alternativo: Sin observaciones
```

Sin escribir código, respondé: mirando la salida de `confundido`, ¿qué dos argumentos se intercambiaron
al invocar el constructor? ¿Por qué el compilador no lo detectó?

## 📏 Criterios de evaluación de la solución

- Identifica que `observacionBibliotecario` y `contactoAlternativo` se pasaron en el orden equivocado.
- Explica que el compilador no lo detecta porque ambos parámetros son `String` consecutivos.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-8**: reconocer qué resuelve Builder.
- **RA-9**: identificar el problema de un constructor con muchos parámetros.

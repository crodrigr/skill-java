# 🟢 Básico 03 — Identificar violación de LSP

## 🧩 Problema

La Biblioteca Universitaria tiene esta jerarquía de usuarios:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class Usuario {
    protected String nombre;

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    public boolean renovarPrestamo(int renovacionesPrevias) {
        if (renovacionesPrevias >= 3) {
            return false;
        }
        System.out.println(nombre + ": prestamo renovado (van " + (renovacionesPrevias + 1) + ")");
        return true;
    }

    public String getNombre() {
        return nombre;
    }
}
```

```java
package com.biblioteca;

public class UsuarioInvitado extends Usuario {
    public UsuarioInvitado(String nombre) {
        super(nombre);
    }

    @Override
    public boolean renovarPrestamo(int renovacionesPrevias) {
        // Precondicion mas estricta que la de Usuario: exige 0 renovaciones previas, no 3
        if (renovacionesPrevias >= 0) {
            System.out.println(nombre + ": prestamo NO renovado (los invitados no pueden renovar)");
            return false;
        }
        System.out.println(nombre + ": prestamo renovado (van " + (renovacionesPrevias + 1) + ")");
        return true;
    }
}
```

```java
package com.biblioteca;

import java.util.List;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        List<Usuario> usuarios = List.of(new Usuario("Elena Ruiz"), new UsuarioInvitado("Pablo Sosa"));

        for (Usuario usuario : usuarios) {
            boolean exito = usuario.renovarPrestamo(0);
            System.out.println("resultado=" + exito);
        }
    }
}
```

## ✅ Salida real del programa

```text
Elena Ruiz: prestamo renovado (van 1)
resultado=true
Pablo Sosa: prestamo NO renovado (los invitados no pueden renovar)
resultado=false
```

Sin modificar el código, respondé: ¿qué precondición estrecha `UsuarioInvitado.renovarPrestamo` respecto
de la que declara `Usuario`? ¿Por qué el resultado de `renovarPrestamo(0)` sobre un `UsuarioInvitado`
podría sorprender a un código que solo conoce el tipo `Usuario`?

## 📏 Criterios de evaluación de la solución

- Identifica que `Usuario.renovarPrestamo` acepta hasta 2 renovaciones previas (permite renovar con
  `renovacionesPrevias < 3`), mientras que `UsuarioInvitado.renovarPrestamo` no permite ninguna
  renovación (exige `renovacionesPrevias < 0`, imposible).
- Explica que el `Demo` trata a ambos usuarios de manera uniforme, como `Usuario`, y obtiene resultados
  distintos ante la misma llamada sin ninguna excepción que lo avise.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-8**: reconocer qué significa que una subclase sea sustituible por su superclase.
- **RA-9**: identificar una violación de LSP dado un fragmento de código.

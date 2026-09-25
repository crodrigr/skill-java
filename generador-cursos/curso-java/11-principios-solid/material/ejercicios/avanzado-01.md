# 🔴 Avanzado 01 — Corregir un diseño con dos violaciones

## 🧩 Problema

La Biblioteca Universitaria procesa devoluciones de préstamos y avisa al usuario por consola. Este diseño
tiene **dos** violaciones distintas: una de LSP y una de DIP.

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class Usuario {
    protected String nombre;

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    public boolean devolverPrestamo(int diasAtraso) {
        System.out.println(nombre + ": devolucion registrada (" + diasAtraso + " dias de atraso)");
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
    public boolean devolverPrestamo(int diasAtraso) {
        // Precondicion mas estricta que la de Usuario: exige 0 dias de atraso, no cualquier valor
        if (diasAtraso > 0) {
            System.out.println(nombre + ": devolucion NO registrada (los invitados no pueden tener atraso)");
            return false;
        }
        System.out.println(nombre + ": devolucion registrada (" + diasAtraso + " dias de atraso)");
        return true;
    }
}
```

```java
package com.biblioteca;

public class NotificadorConsola {
    public void avisar(String nombre, boolean exito) {
        if (exito) {
            System.out.println("Aviso a " + nombre + ": devolucion procesada correctamente");
        } else {
            System.out.println("Aviso a " + nombre + ": devolucion rechazada");
        }
    }
}
```

```java
package com.biblioteca;

public class GestorDevoluciones {
    private NotificadorConsola notificador = new NotificadorConsola();

    public void procesarDevolucion(Usuario usuario, int diasAtraso) {
        boolean exito = usuario.devolverPrestamo(diasAtraso);
        notificador.avisar(usuario.getNombre(), exito);
    }
}
```

```java
package com.biblioteca;

import java.util.List;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        GestorDevoluciones gestor = new GestorDevoluciones();
        List<Usuario> usuarios = List.of(new Usuario("Elena Ruiz"), new UsuarioInvitado("Pablo Sosa"));

        for (Usuario usuario : usuarios) {
            gestor.procesarDevolucion(usuario, 2);
        }
    }
}
```

## 🧪 Casos de prueba

| Violación | Dónde está | Cómo se corrige |
|---|---|---|
| LSP | `UsuarioInvitado.devolverPrestamo` sobrescribe con una precondición más estricta (exige 0 días de atraso, no cualquier valor), devolviendo `false` en silencio | Mover el límite a un dato consultable de `Usuario` (`getAtrasoMaximoPermitido()`), sin sobrescribir el método |
| DIP | `GestorDevoluciones` crea `new NotificadorConsola()` dentro de la declaración de su atributo | `GestorDevoluciones` recibe una interfaz de notificación por constructor |

## 📏 Criterios de evaluación de la solución

- Corrige la violación de LSP: `Usuario` expone el límite de atraso permitido como un dato consultable;
  `UsuarioInvitado` ya no sobrescribe `devolverPrestamo`, solo fija su propio límite en el constructor.
- Corrige la violación de DIP: se declara una interfaz de notificación (por ejemplo `CanalAviso`) y
  `GestorDevoluciones` la recibe por constructor, en vez de crear `NotificadorConsola` internamente.
- Cada corrección se verifica por separado: la de LSP consultando el límite de cada usuario antes de
  procesar su devolución; la de DIP comprobando que `GestorDevoluciones` funcionaría igual con otra
  implementación de la interfaz de notificación.

## 🚧 Restricciones

- Las dos correcciones son independientes entre sí: no dependen una de la otra.

## 📊 Dificultad

Avanzado

## 🎓 Resultados de aprendizaje

- **RA-9**: identificar una violación de LSP dado un fragmento de código.
- **RA-10**: corregir una violación de LSP.
- **RA-14**: diseñar una solución que respete DIP.
- **RA-15**: dado un fragmento de código, identificar qué principio viola.

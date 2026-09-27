# 🟡 Intermedio 07 — Aplicar State

## 🧩 Problema

Tienes la misma clase de la Biblioteca Universitaria del ejercicio Básico 07:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class ItemDeCatalogo {
    private String estado = "DISPONIBLE";

    public String prestar() {
        if (estado.equals("DISPONIBLE")) {
            estado = "PRESTADO";
            return "Prestamo registrado, pasa a PRESTADO";
        }
        return "No se puede prestar: el item no esta disponible";
    }

    public String devolver() {
        if (estado.equals("PRESTADO")) {
            estado = "DISPONIBLE";
            return "Devolucion registrada, pasa a DISPONIBLE";
        }
        return "No se puede devolver: el item no esta prestado";
    }

    public String enviarAReparacion() {
        if (estado.equals("DISPONIBLE")) {
            estado = "EN_REPARACION";
            return "Enviado a reparacion";
        }
        return "No se puede enviar a reparacion: el item no esta disponible";
    }

    public String getEstado() {
        return estado;
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        ItemDeCatalogo item = new ItemDeCatalogo();
        System.out.println(item.devolver());
        System.out.println(item.prestar());
        System.out.println(item.devolver());
        System.out.println("estado final=" + item.getEstado());
    }
}
```

Rediséñalo para que respete State: declara una clase por estado, que decida sus propias transiciones
válidas, sin ningún condicional en `ItemDeCatalogo`.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Un alta inválida, un préstamo válido, una devolución válida (misma secuencia que la versión original) | Salida completa del programa antes y después de refactorizar | Idéntica, carácter por carácter, incluida la línea de `estado final` |
| El código de `ItemDeCatalogo` después de refactorizar | Presencia de condicionales sobre un campo de estado | Ninguna |

## 📏 Criterios de evaluación de la solución

- Declara una interfaz (por ejemplo, `EstadoDeItem`) con un método por operación, implementada por
  `Disponible`, `Prestado` y `EnReparacion`.
- `ItemDeCatalogo` delega cada operación en su estado actual, sin ningún condicional.
- La salida por consola no cambia respecto de la versión original.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni excepciones como parte del diseño.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-22**: implementar State para un caso dado.

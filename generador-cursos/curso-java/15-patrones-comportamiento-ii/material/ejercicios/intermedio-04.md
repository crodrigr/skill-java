# 🟡 Intermedio 04 — Aplicar Memento

## 🧩 Problema

Tienes la misma clase de la Biblioteca Universitaria del ejercicio Básico 04:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class BorradorDeFicha {
    private String descripcion;

    public void escribir(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        BorradorDeFicha borrador = new BorradorDeFicha();
        borrador.escribir("Novela de realismo magico");
        System.out.println(borrador.getDescripcion());

        borrador.escribir("Novela de ciencia ficcion");
        System.out.println(borrador.getDescripcion());
    }
}
```

Rediséñala para que respete Memento: agrega la posibilidad de guardar y restaurar instantes del
borrador, sin exponer su estructura interna a quien los guarda.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Escribir una descripción, guardar un instante, escribir una segunda descripción, y restaurar el instante guardado | Descripción final del borrador | Exactamente la primera descripción, no la segunda |
| `InstanteDeFicha` | Métodos públicos que expongan la descripción a cualquier clase | Ninguno (constructor y lectura sin modificador de acceso) |

## 📏 Criterios de evaluación de la solución

- Declara una clase (por ejemplo, `InstanteDeFicha`) que captura la descripción en un momento dado, con
  constructor y lectura de visibilidad de paquete (no públicos).
- Declara un historial (por ejemplo, `HistorialDeEdiciones`) que guarda instantes y puede devolver el
  último.
- `BorradorDeFicha.restaurar(instante)` reemplaza su descripción por la del instante recibido.
- El texto se restaura exactamente al de un instante guardado anteriormente.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni excepciones como parte del diseño.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-13**: implementar Memento para un caso dado.

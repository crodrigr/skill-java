# 🟡 Intermedio 02 — Aplicar Iterator

## 🧩 Problema

Tienes la misma clase de la Biblioteca Universitaria del ejercicio Básico 02:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class ColeccionDeFichas {
    private String[] fichas;
    private int cantidad;

    public ColeccionDeFichas(int capacidad) {
        this.fichas = new String[capacidad];
        this.cantidad = 0;
    }

    public void agregar(String ficha) {
        fichas[cantidad] = ficha;
        cantidad = cantidad + 1;
    }

    public String[] getFichas() {
        return fichas;
    }

    public int getCantidad() {
        return cantidad;
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        ColeccionDeFichas coleccion = new ColeccionDeFichas(3);
        coleccion.agregar("Rayuela");
        coleccion.agregar("El Aleph");
        coleccion.agregar("Ficciones");

        for (int i = 0; i < coleccion.getCantidad(); i = i + 1) {
            System.out.println(coleccion.getFichas()[i]);
        }
    }
}
```

Rediséñala para que respete Iterator: declara una interfaz de iterador que oculte la estructura interna
de la colección.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Recorrer una colección con tres fichas | Salida completa del programa antes y después de refactorizar | Idéntica, carácter por carácter |
| Código de `Demo.java` después de refactorizar | Acceso directo al arreglo interno de la colección | Ninguno |

## 📏 Criterios de evaluación de la solución

- Declara una interfaz (por ejemplo, `IteradorDeFichas`) con `haySiguiente()`/`siguiente()`.
- `ColeccionDeFichas` expone un método que crea su iterador (`crearIterador()`), sin exponer su arreglo
  interno.
- La salida por consola no cambia respecto de la versión original.
- `Demo` recorre la colección solo a través del iterador.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni excepciones como parte del diseño.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-7**: implementar Iterator para un caso dado.

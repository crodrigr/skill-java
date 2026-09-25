# 🟢 Básico 05 — Identificar violación de DIP

## 🧩 Problema

La Biblioteca Universitaria tiene este sistema de búsqueda de libros:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class BuscadorLocal {
    public String buscar(String titulo) {
        return "Encontrado en el catalogo local: " + titulo;
    }
}
```

```java
package com.biblioteca;

public class SistemaBusqueda {
    private BuscadorLocal buscador = new BuscadorLocal();

    public void buscarLibro(String titulo) {
        System.out.println(buscador.buscar(titulo));
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        SistemaBusqueda sistema = new SistemaBusqueda();
        sistema.buscarLibro("Rayuela");
    }
}
```

## ✅ Salida real del programa

```text
Encontrado en el catalogo local: Rayuela
```

Sin escribir código, respondé: ¿qué le impide a `SistemaBusqueda` buscar en un catálogo en línea, además
del catálogo local? ¿Qué línea exacta del código es la responsable de esa limitación?

## 📏 Criterios de evaluación de la solución

- Identifica que `SistemaBusqueda` crea `new BuscadorLocal()` directamente dentro de la declaración del
  atributo, sin recibirlo desde afuera.
- Explica que, para buscar en otro catálogo, habría que modificar el código de `SistemaBusqueda`.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-13**: reconocer qué es la Inversión de Dependencias.

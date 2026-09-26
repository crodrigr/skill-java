# 🟡 Intermedio 07 — Aplicar Proxy

## 🧩 Problema

Tienes la misma clase de la Biblioteca Universitaria del ejercicio Básico 07:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class DocumentoDigitalReal {
    private String titulo;
    private String contenido;

    public DocumentoDigitalReal(String titulo) {
        this.titulo = titulo;
        System.out.println("Cargando contenido completo de " + titulo + "...");
        this.contenido = "Contenido completo de " + titulo;
    }

    public String verTitulo() {
        return titulo;
    }

    public String verContenido() {
        return contenido;
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        DocumentoDigitalReal documento = new DocumentoDigitalReal("Tesis de grado");
        System.out.println("Consultando titulo...");
        System.out.println(documento.verTitulo());
    }
}
```

Rediséñalo para que respete Proxy: la carga del contenido completo debe postergarse hasta que
`verContenido()` se llame por primera vez, nunca antes.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| `Demo` original (solo consulta el título, nunca llama a `verContenido()`) | Mensaje `"Cargando contenido completo de..."` | No aparece en absoluto (antes de refactorizar, sí aparece) |
| Consultar el título de un documento | Salida de esa línea | Idéntica a la de la versión original |

## 📏 Criterios de evaluación de la solución

- Declara una interfaz (por ejemplo, `DocumentoDigital`) con `verTitulo()` y `verContenido()`.
- Declara una clase proxy (por ejemplo, `DocumentoDigitalProxy`) que implementa la interfaz, guarda el
  título sin crear la instancia real, y crea `DocumentoDigitalReal` recién dentro de `verContenido()`, la
  primera vez que se llama.
- Si `verContenido()` nunca se llama, `DocumentoDigitalReal` nunca se crea (y su constructor nunca se
  ejecuta).
- `DocumentoDigitalReal.java` no se modifica en su lógica de carga.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni excepciones como parte del diseño.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-22**: implementar Proxy para un caso dado.

# 🟢 Básico 07 — Identificar violación de Proxy

## 🧩 Problema

La Biblioteca Universitaria carga documentos digitales escaneados con esta clase:

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

Esta es la salida real de ejecutar el `Demo` de arriba:

```text
Cargando contenido completo de Tesis de grado...
Consultando titulo...
Tesis de grado
```

Sin escribir código, responde: en el `Demo` de arriba, que solo consulta el título del documento, ¿el
contenido completo se carga o no? Justifica tu respuesta con el constructor de `DocumentoDigitalReal`.

## 📏 Criterios de evaluación de la solución

- Identifica que el contenido completo se carga siempre, en el constructor de `DocumentoDigitalReal`,
  aunque `Demo` nunca llegue a pedirlo con `verContenido()`.
- Explica que eso desperdicia trabajo cada vez que se crea el objeto solo para consultar el título.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-20**: reconocer qué resuelve Proxy.
- **RA-21**: reconocer un objeto costoso o sensible que se accede sin ningún control intermedio.

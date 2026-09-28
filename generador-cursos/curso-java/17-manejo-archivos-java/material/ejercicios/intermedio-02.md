# 🟡 Intermedio 02 — Aplicar lectura de archivos

## 🧩 Problema

Tienes el mismo catálogo de la Biblioteca Universitaria del ejercicio Básico 02:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class Demo {

    public static void main(String[] args) {
        String[] libros = {
            "El Quijote,Cervantes",
            "Cien anios de soledad,Garcia Marquez",
            "Rayuela,Cortazar"
        };

        System.out.println("Catalogo de libros (hardcodeado en el codigo):");
        for (String libro : libros) {
            System.out.println(libro);
        }
    }
}
```

Ya existe un archivo real `libros.txt` con el mismo catálogo, una línea por libro.

Rediséñalo para que lea el catálogo desde `libros.txt`, en vez de tenerlo hardcodeado.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Archivo `libros.txt` con 3 líneas | Salida del programa | Exactamente esas 3 líneas, en el mismo orden |

## 📏 Criterios de evaluación de la solución

- Usa `BufferedReader`/`FileReader` con try-with-resources para leer el archivo línea por línea.
- La salida coincide exactamente con el contenido real de `libros.txt`.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni clases atómicas como parte del diseño.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-4**: implementar lectura de un archivo de texto.

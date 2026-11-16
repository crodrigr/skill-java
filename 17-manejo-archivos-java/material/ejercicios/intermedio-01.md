# 🟡 Intermedio 01 — Aplicar la clase File

## 🧩 Problema

Tienes el mismo programa de la Biblioteca Universitaria del ejercicio Básico 01:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class Demo {

    public static void main(String[] args) {
        String[] prestamosEsperados = {"prestamo1.txt", "prestamo2.txt"};

        System.out.println("Prestamos activos (segun lista fija en el codigo):");
        for (String nombre : prestamosEsperados) {
            System.out.println("- " + nombre);
        }
    }
}
```

La carpeta real `prestamos_activos/` tiene tres archivos.

Rediséñalo para que consulte el contenido real de la carpeta con `File`, en vez de asumirlo.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Carpeta `prestamos_activos/` con 3 archivos | Cantidad de préstamos listados | 3, coincidiendo exactamente con el contenido real |

## 📏 Criterios de evaluación de la solución

- Usa `File.listFiles()` sobre la carpeta real, en vez de una lista fija en el código.
- Ordena el resultado explícitamente antes de mostrarlo (el sistema operativo no garantiza ningún orden).
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni excepciones propias como parte del diseño.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-2**: implementar el uso de la clase `File` para un caso dado.

# 🟡 Intermedio 03 — Aplicar escritura de archivos

## 🧩 Problema

Tienes el mismo registro de la Biblioteca Universitaria del ejercicio Básico 03:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class Demo {

    public static void main(String[] args) {
        String[] solicitudes = {
            "Solicitud: prestamo de El Quijote para Carla Nunez",
            "Solicitud: prestamo de Rayuela para Diego Perez"
        };

        for (String solicitud : solicitudes) {
            System.out.println(solicitud);
        }
        System.out.println("Las solicitudes se imprimieron, pero no quedan guardadas en ningun lado.");
    }
}
```

Rediséñalo para que las solicitudes se escriban en un archivo `solicitudes.txt`, y se confirme el
resultado releyéndolo.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Escribir 2 solicitudes | `File.exists()` sobre `solicitudes.txt` | `true` |
| Mismo caso | Contenido releído del archivo | Exactamente las 2 solicitudes escritas, en el mismo orden |

## 📏 Criterios de evaluación de la solución

- Usa `BufferedWriter`/`FileWriter` con try-with-resources para escribir el archivo.
- Confirma con `File.exists()` que el archivo existe después de escribirlo.
- Relee el archivo y confirma que el contenido coincide exactamente con lo escrito.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni clases atómicas como parte del diseño.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-6**: implementar escritura en un archivo de texto.

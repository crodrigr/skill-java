# 🟢 Básico 03 — Identificar violación de escritura de archivos

## 🧩 Problema

La Biblioteca Universitaria registra solicitudes de préstamo con este código:

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

Sin escribir código, responde: si el programa se cierra y se vuelve a abrir, ¿dónde están las
solicitudes registradas antes?

## 📏 Criterios de evaluación de la solución

- Identifica que las solicitudes solo se imprimen por consola, sin persistirse en ningún archivo.
- Explica que, al cerrar y reabrir el programa, esas solicitudes ya no existen en ningún lado.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-5**: identificar datos que deberían escribirse en un archivo.

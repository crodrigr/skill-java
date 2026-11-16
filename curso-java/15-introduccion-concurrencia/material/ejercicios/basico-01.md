# 🟢 Básico 01 — Identificar violación de creación de hilos

## 🧩 Problema

La Biblioteca Universitaria notifica a los usuarios con préstamos vencidos con este código:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class GestorDeNotificaciones {

    public static void notificarVencimiento(String usuario) throws InterruptedException {
        Thread.sleep(150);
        System.out.println("Notificacion de vencimiento enviada a " + usuario);
    }
}
```

```java
package com.biblioteca;

public class Demo {

    public static void main(String[] args) throws InterruptedException {
        String[] usuarios = {"Carla Nunez", "Diego Perez", "Elena Ruiz"};

        long inicio = System.currentTimeMillis();
        for (String usuario : usuarios) {
            GestorDeNotificaciones.notificarVencimiento(usuario);
        }
        long fin = System.currentTimeMillis();

        System.out.println("Tiempo total secuencial: " + (fin - inicio) + " ms");
    }
}
```

Sin escribir código, responde: si la biblioteca tuviera que notificar a cien usuarios en vez de tres,
¿qué le pasaría al tiempo total, y por qué?

## 📏 Criterios de evaluación de la solución

- Identifica que las tres notificaciones se envían una detrás de otra, desde el mismo hilo, aunque son
  tareas independientes entre sí (notificar a un usuario no depende de haber notificado a otro).
- Explica que el tiempo total crece en proporción directa a la cantidad de usuarios, porque nada se
  ejecuta en paralelo.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-2**: reconocer las dos formas de crear un hilo en Java.
- **RA-3**: identificar tareas independientes ejecutadas de forma secuencial que podrían ser
  concurrentes.

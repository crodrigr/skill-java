# 🟡 Intermedio 01 — Aplicar creación de hilos

## 🧩 Problema

Tienes el mismo gestor de la Biblioteca Universitaria del ejercicio Básico 01:

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

Rediséñalo para que cada notificación se envíe desde su propio hilo, en vez de una detrás de otra.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Notificar a los tres usuarios con un hilo cada uno | Tiempo total medido | Notablemente menor que la suma de los tres tiempos individuales |
| Comparar el tiempo total contra un umbral derivado de esa suma | Valor booleano impreso por el programa | `true` |

## 📏 Criterios de evaluación de la solución

- Crea un hilo por usuario (con `Thread` o `Runnable`, a elección) e invoca `start()` en cada uno —
  nunca `run()` directamente.
- El tiempo total medido queda notablemente por debajo de la suma de los tres tiempos individuales.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni excepciones propias como parte del diseño (`InterruptedException` de
  `Thread.sleep()` se maneja, pero no es el foco del ejercicio).

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-4**: implementar la creación de hilos para un caso dado.

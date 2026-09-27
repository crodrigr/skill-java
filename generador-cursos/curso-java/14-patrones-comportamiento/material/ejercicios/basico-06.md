# 🟢 Básico 06 — Identificar violación de Observer

## 🧩 Problema

La Biblioteca Universitaria avisa cuando un ejemplar vuelve a estar disponible con este código:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class DisponibilidadDeEjemplar {
    public void notificarDisponible(String titulo) {
        System.out.println("Lista de espera: " + titulo + " ya esta disponible");
        System.out.println("Bibliotecario: preparar " + titulo + " para el proximo retiro");
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        DisponibilidadDeEjemplar disponibilidad = new DisponibilidadDeEjemplar();
        disponibilidad.notificarDisponible("Cien anios de soledad");
    }
}
```

Sin escribir código, responde: ¿a cuántos interesados avisa `notificarDisponible()` directamente, y qué
pasaría si mañana se agrega un tercer interesado (por ejemplo, un registro de estadísticas)?

## 📏 Criterios de evaluación de la solución

- Identifica que `notificarDisponible()` llama directamente a dos interesados, dentro de su propio
  cuerpo.
- Explica que agregar un tercer interesado exigiría modificar ese mismo método, en vez de registrar el
  interesado nuevo desde afuera.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-17**: reconocer qué resuelve Observer.
- **RA-18**: identificar la notificación manual a cada interesado en un fragmento dado.

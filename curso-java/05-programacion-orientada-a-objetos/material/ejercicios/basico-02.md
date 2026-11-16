# 🟢 Básico 02 — Predecir la salida de invocar un método

## 🧩 Problema

Lee el siguiente código de la **Biblioteca Universitaria** y predice exactamente qué imprime, antes de
ejecutarlo.

## 💻 Código o contexto de partida

```java
public class Prestamo {
    public String tituloLibro;
    public int diasRestantes;

    public String mostrarEstado() {
        if (diasRestantes < 0) {
            return tituloLibro + ": vencido";
        }
        return tituloLibro + ": " + diasRestantes + " días restantes";
    }
}
```

```java
public class PrediccionInvocacion {
    public static void main(String[] args) {
        Prestamo prestamo = new Prestamo();
        prestamo.tituloLibro = "El principito";
        prestamo.diasRestantes = 3;

        System.out.println(prestamo.mostrarEstado());
    }
}
```

## 📏 Criterios de evaluación de la solución

- Predice la salida exacta antes de ejecutar el programa.
- Explica cómo `mostrarEstado()` usa los atributos `tituloLibro` y `diasRestantes` del objeto que lo
  invoca.

## 🚧 Restricciones

- No modifiques el código: solo predice su salida.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-5**: declarar un método de instancia.
- **RA-6**: invocar un método sobre un objeto con notación de punto.

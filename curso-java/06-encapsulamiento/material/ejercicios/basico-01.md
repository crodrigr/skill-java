# 🟢 Básico 01 — Por qué encapsular

## 🧩 Problema

La **Biblioteca Universitaria** registra un préstamo con atributos públicos. Analiza el siguiente
programa y explica qué problema tiene el atributo `diasPrestamo`, y cómo lo resolvería el
encapsulamiento.

## 💻 Código o contexto de partida

```java
public class Prestamo {
    public String tituloLibro;
    public int diasPrestamo;
}
```

```java
public class PorQueEncapsular {
    public static void main(String[] args) {
        Prestamo prestamo = new Prestamo();
        prestamo.tituloLibro = "El principito";
        prestamo.diasPrestamo = -3;

        System.out.println(prestamo.tituloLibro + ": " + prestamo.diasPrestamo + " días");
    }
}
```

## 📏 Criterios de evaluación de la solución

- Identifica que nada impide asignar `diasPrestamo = -3`, un valor sin sentido de negocio.
- Explica que encapsular `diasPrestamo` como `private`, con un `set` que valide (por ejemplo, que no
  sea negativo), evitaría ese valor.
- No propone eliminar el atributo, sino protegerlo con un `set` validado.

## 🚧 Restricciones

- No modifiques el código: solo analízalo.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-1**: explicar qué es el encapsulamiento frente a los atributos públicos.

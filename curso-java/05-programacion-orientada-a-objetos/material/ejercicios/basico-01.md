# 🟢 Básico 01 — Distinguir clase de objeto

## 🧩 Problema

Un compañero de **MediSalud** escribió el siguiente programa para registrar dos salas de atención.
Identifica cuál es la clase y cuáles son los objetos, y explica en una frase una ventaja de usar una
clase en vez de variables sueltas para este caso.

## 💻 Código o contexto de partida

```java
public class Sala {
    public String nombre;
    public int capacidad;
}
```

```java
public class IdentificarClaseYObjeto {
    public static void main(String[] args) {
        Sala sala1 = new Sala();
        sala1.nombre = "Consultorio 1";
        sala1.capacidad = 2;

        Sala sala2 = new Sala();
        sala2.nombre = "Sala de espera";
        sala2.capacidad = 15;

        System.out.println(sala1.nombre + " - capacidad: " + sala1.capacidad);
        System.out.println(sala2.nombre + " - capacidad: " + sala2.capacidad);
    }
}
```

## 📏 Criterios de evaluación de la solución

- Identifica correctamente que `Sala` es la clase y que `sala1` y `sala2` son objetos (instancias) de
  esa clase.
- Explica una ventaja real de usar una clase en vez de variables sueltas (por ejemplo, evitar declarar
  `nombreSala1`, `capacidadSala1`, `nombreSala2`, `capacidadSala2`).
- No confunde el nombre de la clase con el de una variable, ni al revés.

## 🚧 Restricciones

- No modifiques el código: solo analízalo.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-1**: explicar qué es la POO frente al estilo de variables sueltas.
- **RA-2**: explicar por qué conviene la POO.
- **RA-3**: distinguir una clase de un objeto.

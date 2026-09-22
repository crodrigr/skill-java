# 🟡 Intermedio 01 — Encapsular una clase

## 🧩 Problema

La **Biblioteca Universitaria** tiene la clase `Libro`, con el atributo `tituloLibro` todavía público.
Completa el `TODO` para encapsularlo: hazlo `private` y agrega sus accesores.

## 💻 Código o contexto de partida

```java
public class Libro {
    public String tituloLibro;

    // TODO: 1. cambia el atributo a private

    // TODO: 2. declara getTituloLibro() y setTituloLibro(titulo)

    public static void main(String[] args) {
        Libro libro = new Libro();
        libro.tituloLibro = "Cien años de soledad";
        System.out.println(libro.tituloLibro);
    }
}
```

## 🧪 Casos de prueba

```text
Cien años de soledad
```

## 📏 Criterios de evaluación de la solución

- El atributo `tituloLibro` pasa a ser `private`.
- Declara `getTituloLibro()` y `setTituloLibro(titulo)`.
- El programa sigue mostrando el mismo resultado, ahora accediendo por `get`/`set`, no por la notación
  de punto.

## 🚧 Restricciones

- No cambies el nombre del atributo.
- Los identificadores siguen las convenciones del curso.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-3**: declarar un atributo `private`.
- **RA-8**: declarar un método `get`.

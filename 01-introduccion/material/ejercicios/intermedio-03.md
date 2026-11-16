# 🟡 Intermedio 03 — Ficha de un libro

## 🧩 Problema

La **Biblioteca Universitaria** necesita mostrar la ficha de un libro en la consola. Ya
están declarados los datos de *Don Quijote de la Mancha*; tu tarea es mostrarlos con el
formato exacto que se pide, usando `print`, `println` y `printf`.

## 💻 Código o contexto de partida

Creá un proyecto `FichaBiblioteca` con el paquete `com.biblioteca` y pegá este código. Ya
compila, pero todavía no muestra nada:

```java
package com.biblioteca;

public class FichaBiblioteca {

    public static void main(String[] args) {
        String tituloLibro = "Don Quijote de la Mancha";
        long isbn = 9788420412146L;
        short numeroPaginas = 1376;
        double precioLibro = 89.5;
        boolean disponible = false;

        // TODO 1: con print y println, mostrar en una sola línea:  Título: Don Quijote de la Mancha
        // TODO 2: con println y el signo +, mostrar:  ISBN: 9788420412146
        // TODO 3: con printf, mostrar el precio con dos decimales:  Precio: 89.50
        // TODO 4: con printf, %s y %d, mostrar:  Don Quijote de la Mancha tiene 1376 páginas
        // TODO 5: con una sola instrucción println y una secuencia de escape, mostrar dos líneas:
        //         Ficha completa
        //         Disponible: false
    }
}
```

> 📝 Mientras las variables no se usen, el panel **Problems** muestra advertencias
> amarillas como `The value of the local variable tituloLibro is not used`. No son errores
> y desaparecen cuando completás los `TODO` y usás cada variable.

## 📏 Criterios de evaluación de la solución

- La salida coincide **exactamente** con la salida esperada, línea por línea.
- El título se muestra en una sola línea usando `print` y `println` (TODO 1).
- El ISBN se muestra con `println` y el signo `+` (TODO 2).
- El precio se muestra con `printf`, el marcador `%.2f` y `%n` (TODO 3).
- La línea del título y las páginas usa `%s` y `%d` en el orden correcto (TODO 4).
- Las dos últimas líneas se muestran con **una sola** instrucción `println` y `\n`
  (TODO 5).

## 🚧 Restricciones

- No cambies las variables declaradas.
- No escribas los valores directamente en los textos: usá las variables.

## 📊 Dificultad

Intermedio

## ✅ Salida esperada al ejecutar

```text
Título: Don Quijote de la Mancha
ISBN: 9788420412146
Precio: 89.50
Don Quijote de la Mancha tiene 1376 páginas
Ficha completa
Disponible: false
```

> ⚠️ En un computador configurado en español, `printf` puede mostrar el precio con coma
> (`89,50`) en lugar de punto. Es normal (Ejemplo 10).

## 🎓 Resultados de aprendizaje

RA-11

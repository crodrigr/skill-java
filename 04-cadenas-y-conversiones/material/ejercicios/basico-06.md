# 🟢 Básico 06 — Empaquetado y parseInt

## 🧩 Problema

La **Biblioteca Universitaria** trabaja con envolventes y con texto convertido a número. Sin ejecutar
el programa, predice el resultado de cada línea marcada con una letra.

## 💻 Código o contexto de partida

```java
public class EmpaquetadoYParseInt {

    public static void main(String[] args) {
        // a) Autoboxing con valores pequeños
        Integer x = 100;
        Integer y = 100;
        System.out.println("a) " + (x == y));

        // b) Autoboxing con valores grandes
        Integer m = 300;
        Integer n = 300;
        System.out.println("b) " + (m == n));

        // c) equals siempre funciona
        System.out.println("c) " + m.equals(n));

        // d) parseInt válido
        System.out.println("d) " + Integer.parseInt("120"));

        // e) parseDouble válido
        System.out.println("e) " + Double.parseDouble("19.5"));

        // f) Character.isDigit
        System.out.println("f) " + Character.isDigit('7') + " " + Character.isDigit('x'));
    }
}
```

| Línea | Tu predicción |
|---|---|
| a) | |
| b) | |
| c) | |
| d) | |
| e) | |
| f) | |

## 📏 Criterios de evaluación de la solución

- Las seis respuestas son correctas.
- Se explica por qué a) y b) dan resultados distintos, aunque las dos comparan dos `Integer` con el
  mismo valor entre sí.
- Se explica que `.equals(...)` siempre funciona, sin importar el valor.

## 🚧 Restricciones

- Resuélvelo a mano; después puedes comprobarlo ejecutando el programa.
- No modifiques el código.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-10**: explicar el empaquetado y desempaquetado automáticos.
- **RA-11**: predecir el resultado de `parseInt` y `parseDouble`.

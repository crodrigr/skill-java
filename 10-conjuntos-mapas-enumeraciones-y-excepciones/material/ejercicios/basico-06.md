# 🟢 Básico 06 — Identificar qué catch se ejecuta

## 🧩 Problema

Mira estos dos fragmentos:

## 💻 Código o contexto de partida

**Fragmento 1**:

```java
public class Fragmento1 {
    public static void main(String[] args) {
        try {
            String texto = null;
            System.out.println(texto.length());
        } catch (NullPointerException e) {
            System.out.println("A: capturado NullPointerException");
        } catch (RuntimeException e) {
            System.out.println("B: capturado RuntimeException");
        }
    }
}
```

**Fragmento 2**:

```java no-compila
public class Fragmento2 {
    public static void main(String[] args) {
        try {
            Integer.parseInt("abc");
        } catch (RuntimeException e) {
            System.out.println("A: capturado RuntimeException");
        } catch (NumberFormatException e) {
            System.out.println("B: capturado NumberFormatException");
        }
    }
}
```

**Pregunta**:

1. En el Fragmento 1, ¿qué `catch` se ejecuta?
2. ¿El Fragmento 2 compila? Si no, ¿por qué?

## 📏 Criterios de evaluación de la solución

- Identifica que en el Fragmento 1 se ejecuta el primer `catch` (`NullPointerException`), porque
  coincide exactamente con lo que se lanza.
- Identifica que el Fragmento 2 **no compila**, porque el primer `catch` (`RuntimeException`) ya cubre
  cualquier `NumberFormatException` posible, dejando el segundo `catch` inalcanzable.

## 🚧 Restricciones

- No hace falta escribir código: es un ejercicio de lectura.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-11**: explicar qué es una excepción.
- **RA-15**: identificar los errores frecuentes de esta etapa.

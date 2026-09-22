# 🟢 Básico 03 — ¿Estático o de instancia?

## 🧩 Problema

La **Biblioteca Universitaria** tiene la siguiente clase, con un método estático y uno de instancia.
Decide cómo se invoca correctamente cada uno y justifica en una frase por qué.

## 💻 Código o contexto de partida

```java
public class ReglamentoBiblioteca {
    public String nombreUsuario;

    public static void mostrarHorarioDeAtencion() {
        System.out.println("Horario: 9am a 8pm, de lunes a viernes");
    }

    public void mostrarSaludoPersonalizado() {
        System.out.println("Hola, " + nombreUsuario);
    }
}
```

## 🧪 Casos de prueba

```text
Horario: 9am a 8pm, de lunes a viernes
Hola, Lucía Gómez
```

## 📏 Criterios de evaluación de la solución

- Identifica `mostrarHorarioDeAtencion()` como estático (se invoca sobre la clase) y
  `mostrarSaludoPersonalizado()` como de instancia (se invoca sobre un objeto).
- Justifica la diferencia con el hecho de que el saludo depende de un atributo del objeto
  (`nombreUsuario`) y el horario no depende de ningún objeto en particular.

## 🚧 Restricciones

- No modifiques el código: solo decide cómo invocar cada método.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-7**: distinguir método estático de método de instancia.

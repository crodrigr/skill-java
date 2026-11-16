# 🟢 Básico 04 — Identificar violación de ISP

## 🧩 Problema

La Biblioteca Universitaria tiene esta interfaz para su personal:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public interface PersonalBiblioteca {
    void catalogar(String libro);
    void atenderPublico(String usuario);
    void multar(String usuario, double monto);
}
```

```java
package com.biblioteca;

public class Bibliotecario implements PersonalBiblioteca {
    private String nombre;

    public Bibliotecario(String nombre) {
        this.nombre = nombre;
    }

    public void catalogar(String libro) {
        System.out.println(nombre + " cataloga " + libro);
    }

    public void atenderPublico(String usuario) {
        System.out.println(nombre + " atiende a " + usuario);
    }

    public void multar(String usuario, double monto) {
        System.out.println(nombre + " multa $" + monto + " a " + usuario);
    }
}
```

```java
package com.biblioteca;

public class Voluntario implements PersonalBiblioteca {
    private String nombre;

    public Voluntario(String nombre) {
        this.nombre = nombre;
    }

    public void catalogar(String libro) {
        System.out.println(nombre + " cataloga " + libro);
    }

    public void atenderPublico(String usuario) {
        System.out.println(nombre + " atiende a " + usuario);
    }

    public void multar(String usuario, double monto) {
        // La interfaz obliga a declarar este metodo, pero un voluntario no puede aplicar multas:
        // no hay ninguna implementacion con sentido real para este rol.
        System.out.println(nombre + " no puede aplicar multas (no le corresponde este rol)");
    }
}
```

```java
package com.biblioteca;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        PersonalBiblioteca bibliotecario = new Bibliotecario("Marcos Vega");
        PersonalBiblioteca voluntario = new Voluntario("Nadia Rios");

        bibliotecario.catalogar("Ficciones");
        bibliotecario.multar("Elena Ruiz", 200.0);

        voluntario.atenderPublico("Pablo Sosa");
        voluntario.multar("Pablo Sosa", 200.0);
    }
}
```

## ✅ Salida real del programa

```text
Marcos Vega cataloga Ficciones
Marcos Vega multa $200.0 a Elena Ruiz
Nadia Rios atiende a Pablo Sosa
Nadia Rios no puede aplicar multas (no le corresponde este rol)
```

Sin escribir código, respondé: ¿qué método de `PersonalBiblioteca` no tiene sentido real para
`Voluntario`? ¿Por qué la interfaz lo obliga a implementarlo de todas formas?

## 📏 Criterios de evaluación de la solución

- Identifica que `multar` no tiene sentido real para un voluntario.
- Explica que la interfaz `PersonalBiblioteca` obliga a toda clase que la implemente a declarar los tres
  métodos, sin importar si tienen sentido para ese rol.

## 🚧 Restricciones

- No se pide código: es un ejercicio de lectura e identificación.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-11**: reconocer qué evita la Segregación de Interfaces.

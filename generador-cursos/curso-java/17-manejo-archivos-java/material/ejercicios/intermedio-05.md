# 🟡 Intermedio 05 — Aplicar serialización

## 🧩 Problema

Tienes el mismo registro de la Biblioteca Universitaria del ejercicio Básico 05:

## 💻 Código o contexto de partida

```java
package com.biblioteca;

public class SocioBiblioteca {

    private String nombre;
    private String tipo;
    private int prestamosActivos;

    public SocioBiblioteca(String nombre, String tipo, int prestamosActivos) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.prestamosActivos = prestamosActivos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public int getPrestamosActivos() {
        return prestamosActivos;
    }
}
```

```java
package com.biblioteca;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Demo {

    public static void main(String[] args) {
        SocioBiblioteca socio = new SocioBiblioteca("Carla Nunez", "ESTUDIANTE", 2);

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter("usuario_manual.txt"))) {
            escritor.write(socio.getNombre());
            escritor.newLine();
            escritor.write(socio.getTipo());
            escritor.newLine();
            escritor.write(String.valueOf(socio.getPrestamosActivos()));
            escritor.newLine();
        } catch (IOException e) {
            System.out.println("No se pudo escribir usuario_manual.txt: " + e.getMessage());
            return;
        }

        System.out.println("SocioBiblioteca escrito a mano, campo por campo, en usuario_manual.txt");
    }
}
```

Rediséñalo para que `SocioBiblioteca` se serialice completo con `ObjectOutputStream`, en vez de escribirse a
mano.

## 🧪 Casos de prueba

| Entrada | Verificación | Resultado esperado |
|---|---|---|
| Serializar un `SocioBiblioteca` con `writeObject()` | Resultado de la serialización | Éxito, sin necesitar convertir ningún campo manualmente |

## 📏 Criterios de evaluación de la solución

- Declara `SocioBiblioteca implements Serializable`, con `serialVersionUID` explícito.
- Serializa el objeto completo con una sola llamada a `ObjectOutputStream.writeObject()`.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

## 🚧 Restricciones

- No se usan `Set`, `Map` ni clases atómicas como parte del diseño.

## 📊 Dificultad

Intermedio

## 🎓 Resultados de aprendizaje

- **RA-9**: implementar serialización con `ObjectOutputStream`.

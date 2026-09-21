# 🟢 Básico 04 — Rotular las partes de un proyecto

## 🧩 Problema

Un compañero de **MediSalud** te pasa el proyecto `CitasMedicas` para que lo revises.
Antes de tocar nada, tenés que reconocer sus partes.

## 💻 Código o contexto de partida

**Parte 1.** Este es el árbol del proyecto en el **Explorer** de VS Code. Cada elemento
tiene una letra:

```text
CitasMedicas                          (A)
├── .vscode                           (B)
│   └── settings.json
├── bin                               (C)
├── lib                               (D)
└── src                               (E)
    └── com
        └── medisalud                 (F)
            └── CitasMedicas.java     (G)
```

Indicá qué letra corresponde a cada elemento:

- **a.** El proyecto.
- **b.** La carpeta donde se escribe el código fuente.
- **c.** La carpeta del paquete `com.medisalud`.
- **d.** El archivo que contiene la clase principal.
- **e.** La carpeta donde el compilador deja los archivos `.class`.
- **f.** La carpeta para bibliotecas externas (archivos `.jar`).

**Parte 2.** Este es el contenido de `CitasMedicas.java`, con las líneas numeradas
(los números **no** forman parte del código):

```text
1  package com.medisalud;
2
3  public class CitasMedicas {
4
5      public static void main(String[] args) {
6          System.out.println("Sistema de citas medicas");
7      }
8  }
```

Indicá el número de línea de cada elemento:

- **a.** La declaración del paquete.
- **b.** La declaración de la clase principal.
- **c.** El método `main`, donde empieza el programa.
- **d.** La instrucción que muestra un texto en la consola.

**Parte 3.** Al escribir la clase con otro nombre, el panel **Problems** muestra este
error:

```text
✖ The public type Citas must be defined in its own file Java(16777541) [Ln 3, Col 14]
```

- **a.** ¿Qué diferencia hay entre el nombre de la clase y el del archivo?
- **b.** Proponé dos formas de corregirlo.

## 📏 Criterios de evaluación de la solución

- Parte 1: las seis letras son correctas.
- Parte 2: los cuatro números de línea son correctos (1, 3, 5 y 6).
- Parte 3: se identifica que la clase se llama `Citas` y el archivo `CitasMedicas.java`,
  y se proponen las dos correcciones válidas (cambiar el nombre de la clase o el del
  archivo, para que coincidan).

## 🚧 Restricciones

- No hace falta ejecutar nada: es un ejercicio de lectura.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

RA-8

# 🟢 Básico 03 — Verificar el entorno

## 🧩 Problema

El equipo de sistemas de **MediSalud** preparó cuatro computadores para nuevos
desarrolladores. En cada uno se ejecutaron los dos comandos de verificación del
Ejemplo 04. Tu tarea es interpretar los resultados y decir cuáles computadores están
listos para este curso.

## 💻 Código o contexto de partida

El curso exige **JDK 25** con compilador. Estas son las salidas de cada computador:

**Computador 1**

```text
$ java -version
openjdk version "25" 2025-09-16 LTS
OpenJDK Runtime Environment Temurin-25+36 (build 25+36-LTS)
OpenJDK 64-Bit Server VM Temurin-25+36 (build 25+36-LTS, mixed mode, sharing)

$ javac -version
javac 25
```

**Computador 2**

```text
$ java -version
openjdk version "25" 2025-09-16 LTS
OpenJDK Runtime Environment Temurin-25+36 (build 25+36-LTS)
OpenJDK 64-Bit Server VM Temurin-25+36 (build 25+36-LTS, mixed mode, sharing)

$ javac -version
bash: javac: command not found
```

**Computador 3**

```text
$ java -version
bash: java: command not found

$ javac -version
bash: javac: command not found
```

**Computador 4**

```text
$ java -version
openjdk version "17.0.20" 2026-07-21
OpenJDK Runtime Environment (build 17.0.20+8-1-22.04-Ubuntu)
OpenJDK 64-Bit Server VM (build 17.0.20+8-1-22.04-Ubuntu, mixed mode, sharing)

$ javac -version
javac 17.0.20
```

Para **cada computador**, respondé:

- **a.** ¿Está listo para el curso? (sí o no)
- **b.** Si no lo está, ¿qué le falta o qué hay que corregir?

## 📏 Criterios de evaluación de la solución

- Se identifica que solo el computador 1 cumple los dos requisitos (versión 25 y
  compilador disponible).
- Para el computador 2 se explica que hay una JVM sin compilador, es decir, no se
  instaló el JDK completo.
- Para el computador 3 se explica que no hay ningún JDK instalado, o que la terminal
  no lo encuentra (por ejemplo, no está en el `PATH`).
- Para el computador 4 se explica que el entorno está completo, pero es de otra
  versión (17), y se propone instalar el JDK 25 o ponerlo primero en el `PATH`.

## 🚧 Restricciones

- Basate únicamente en lo que muestran las salidas.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

RA-5

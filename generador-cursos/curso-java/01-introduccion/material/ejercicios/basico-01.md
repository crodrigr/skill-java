# 🟢 Básico 01 — Características de Java y etapas de ejecución

## 🧩 Problema

La red de clínicas **MediSalud** está evaluando construir su nuevo sistema de citas en
Java. La dirección médica te pide que revises qué se sabe sobre el lenguaje y que
expliques cómo llegará el programa a los equipos de las clínicas.

## 💻 Código o contexto de partida

### 🧠 Parte A — Características y usos de Java

**A1.** Marcá cada afirmación como **Verdadera** o **Falsa**:

1. Un programa compilado en Windows debe volver a compilarse para poder ejecutarse en
   Linux.
2. Java revisa los tipos de los datos antes de ejecutar el programa.
3. El programador debe liberar manualmente la memoria que el programa deja de usar.
4. Java se usa solo para aplicaciones de escritorio.
5. Java puede realizar varias tareas al mismo tiempo (multihilo).
6. El archivo `.class` contiene el código fuente tal como lo escribió el programador.

**A2.** Nombrá **dos** aplicaciones (una de MediSalud y una de Biblioteca Universitaria)
que podrían desarrollarse en Java, e indicá **qué característica de Java** justifica
que sea una buena elección para cada una.

### 🪜 Parte B — Etapas de ejecución

Estas cinco acciones ocurren, en algún orden, desde que un programador escribe el
programa `SaludoClinica` hasta que aparece el saludo en pantalla:

- **A.** La JVM lee el *bytecode* y lo ejecuta.
- **B.** Se ejecuta el comando `java SaludoClinica`.
- **C.** El programador escribe el archivo `SaludoClinica.java`.
- **D.** Aparece el saludo en la consola.
- **E.** Se ejecuta el comando `javac SaludoClinica.java` y se genera `SaludoClinica.class`.

**B1.** Escribí las letras en el orden correcto.

**B2.** El programador olvidó un punto y coma en el código. **Pregunta:** ¿en cuál de
las cinco acciones se detectaría el error, y por qué en esa y no en otra?

## 📏 Criterios de evaluación de la solución

- **A1**: las seis respuestas son correctas (dos verdaderas y cuatro falsas).
- **A2**: cada aplicación va acompañada de una característica que realmente la
  justifica (por ejemplo, portabilidad para equipos distintos, multihilo para tareas
  simultáneas, seguridad para datos sensibles).
- **B1**: el orden respeta la secuencia escribir → compilar → ejecutar → mostrar.
- **B2**: se identifica la etapa de compilación y se explica que el compilador revisa
  el código antes de generar el *bytecode*.

## 🚧 Restricciones

- No hace falta escribir ni ejecutar código: es un ejercicio de análisis.
- Respondé con tus palabras; no copies definiciones textuales.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

RA-1, RA-2, RA-3

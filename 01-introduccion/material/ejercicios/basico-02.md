# 🟢 Básico 02 — ¿JDK, JRE o JVM?

## 🧩 Problema

La **Biblioteca Universitaria** va a instalar Java en dos tipos de computadores: los de
las salas de consulta, donde solo se ejecuta el sistema de préstamos ya compilado, y el
del equipo de sistemas, donde se escribe código nuevo. Antes de instalar, necesitan
saber a qué "capa" pertenece cada pieza de Java.

## 💻 Código o contexto de partida

**Parte 1.** Para cada pieza, indicá la **capa más pequeña** que la contiene: **JVM**,
**JRE** o **JDK**.

| N.º | Pieza |
|---|---|
| 1 | El compilador `javac` |
| 2 | Las bibliotecas estándar de Java (por ejemplo, la que permite imprimir en la consola) |
| 3 | El componente que ejecuta el *bytecode* |
| 4 | El recolector de basura |
| 5 | La herramienta `javadoc`, que genera documentación a partir del código |
| 6 | La herramienta `jshell`, que permite probar instrucciones de Java una a una |

**Parte 2.** Respondé:

- **a.** ¿Qué debe instalarse, como mínimo, en un computador de sala de consulta?
- **b.** ¿Qué debe instalarse en el computador del equipo de sistemas?
- **c.** En este curso instalamos el JDK 25 y no las tres piezas por separado.
  **Pregunta:** ¿por qué alcanza con instalar solo el JDK?

## 📏 Criterios de evaluación de la solución

- Las seis piezas están asignadas a la capa correcta y se respeta que cada capa
  contiene a la anterior (JDK ⊃ JRE ⊃ JVM).
- Se distingue entre lo necesario para **ejecutar** (JRE o JVM más bibliotecas) y lo
  necesario para **desarrollar** (JDK).
- La respuesta c) menciona que el JDK incluye al JRE y a la JVM, y que hoy las
  distribuciones suelen entregar el JDK completo.

## 🚧 Restricciones

- No busques las respuestas en internet antes de intentarlo: apoyate en el Ejemplo 03.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

RA-4

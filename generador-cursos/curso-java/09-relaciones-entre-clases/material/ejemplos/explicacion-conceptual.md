# 📚 Explicación conceptual — Módulo 9

## 🧠 Concepto: Relaciones entre clases

- Hasta el Módulo 7 viste la relación **"es un"**: la herencia, donde una subclase extiende a una
  superclase (`Medico extends Empleado`).
- Este módulo introduce la relación **"tiene un"**: una clase guarda una referencia a un objeto de otra
  clase, sin heredar nada de ella.
- Hay tres formas de "tiene un", de menos a más "propiedad" sobre el objeto relacionado:
  - **Asociación**: una clase simplemente conoce a otra.
  - **Agregación**: una clase agrupa a otra, sin controlar su ciclo de vida.
  - **Composición**: una clase posee a otra por completo y controla su ciclo de vida.
- Ninguna de las tres relaciones usa `extends` ni `implements`: son atributos de tipo referencia y
  constructores ordinarios.

📎 Ver en la práctica: [Ejemplo 01 — Relaciones entre clases](01-relaciones-entre-clases.md)

## 🧠 Concepto: Multiplicidad

- La **multiplicidad** indica cuántos objetos participan en cada extremo de una relación.
- Notación UML estándar:
  - `1`: exactamente uno.
  - `0..1`: cero o uno.
  - `0..*` (o `*`): cero o muchos.
  - `1..*`: uno o muchos.
  - Un rango exacto, por ejemplo `2..4`: entre 2 y 4.
- La multiplicidad se lee **en el extremo opuesto** a la clase que se está describiendo: en
  `Medico "1" -- "0..*" Paciente`, el `"0..*"` describe cuántos `Paciente` tiene un `Medico`.
- Es transversal a los tres tipos de relación: toda asociación, agregación o composición tiene
  multiplicidad en ambos extremos.

📎 Ver en la práctica: [Ejemplo 02 — Multiplicidad](02-multiplicidad.md)

## 🧠 Concepto: Asociación unidireccional

- Una clase tiene un atributo del tipo de otra clase, navegable en un solo sentido.
- La clase referenciada no tiene ningún atributo ni método para navegar de vuelta.
- Es la forma más simple de "tiene un": un atributo de tipo referencia, recibido por constructor.

📎 Ver en la práctica: [Ejemplo 03 — Asociación unidireccional](03-asociacion-unidireccional.md)

## 🧠 Concepto: Asociación bidireccional

- Dos clases se referencian mutuamente: cada una tiene un atributo del tipo de la otra.
- Crear o modificar el vínculo debe actualizar **ambos extremos a la vez**, con un único método (por
  ejemplo, `medico.agregarPaciente(paciente)`).
- Si se actualiza un solo extremo (por ejemplo, llamando directamente a `paciente.setMedico(medico)`),
  el programa compila y se ejecuta sin fallar, pero el otro extremo queda desactualizado: un **error
  lógico**, no detectado por el compilador ni por el panel Problems.

📎 Ver en la práctica: [Ejemplo 04 — Asociación bidireccional](04-asociacion-bidireccional.md)

## 🧠 Concepto: Agregación

- Relación todo-parte donde la parte **existe independientemente** del todo.
- El todo **recibe** la parte ya creada (por constructor o por un método), nunca la crea él mismo.
- La parte puede **compartirse** entre varios "todos" al mismo tiempo, y sigue siendo válida aunque uno
  de ellos deje de usarse.

📎 Ver en la práctica: [Ejemplo 05 — Agregación](05-agregacion.md)

## 🧠 Concepto: Composición

- Relación todo-parte donde la parte **no tiene sentido ni existencia** fuera del todo.
- El todo **crea** la parte dentro de su propio constructor; el código externo nunca puede construir la
  parte por su cuenta ni pasarla desde afuera.
- El ciclo de vida de la parte depende por completo del todo.

📎 Ver en la práctica: [Ejemplo 06 — Composición](06-composicion.md)

## 🧠 Tabla de decisión: asociación, agregación o composición

| Pregunta | Asociación | Agregación | Composición |
|---|---|---|---|
| ¿Quién crea al objeto relacionado? | Puede crearlo cualquiera | Se recibe ya creado | El "todo" lo crea en su propio constructor |
| ¿Puede existir independientemente? | Sí | Sí | No |
| ¿Puede compartirse entre varios objetos? | Sí | Sí | No |
| ¿Qué pasa si el "todo" deja de existir? | No afecta al objeto relacionado | El objeto relacionado sigue existiendo | El objeto relacionado deja de tener sentido |

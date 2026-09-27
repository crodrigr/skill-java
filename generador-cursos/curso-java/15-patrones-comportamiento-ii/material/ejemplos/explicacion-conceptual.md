# 📚 Explicación conceptual — Módulo 15

## 🧠 Concepto: Cómo se completa la categoría de comportamiento

- El temario original de "Patrones de comportamiento" tiene nueve puntos: cinco ya cubiertos en el
  Módulo 14 (Strategy, Observer, Command, State, Template Method) y cuatro que cubre este módulo (Chain
  of Responsibility, Iterator, Mediator, Memento).
- Entre los Módulos 14 y 15, los nueve patrones de comportamiento del temario quedan completos, sin
  repetir ninguno.

📎 Ver en la práctica: [Ejemplo 01 — Cómo se completa la categoría de comportamiento](01-como-se-completa-la-categoria-de-comportamiento.md)

## 🧠 Concepto: Chain of Responsibility

- Pasa una solicitud a lo largo de una cadena de manejadores hasta que uno de ellos la resuelve, sin
  que quien envía la solicitud conozca cuál.
- El problema que resuelve: un único método con un condicional gigante que decide, según distintos
  criterios, quién resuelve una solicitud.
- Se implementa con una interfaz común y una cadena de objetos, cada uno con una referencia al
  siguiente.

📎 Ver en la práctica: [Ejemplo 02 — Chain of Responsibility](02-chain-of-responsibility.md)

## 🧠 Concepto: Iterator

- Permite recorrer los elementos de una colección sin exponer su representación interna.
- El problema que resuelve: código cliente acoplado a la estructura interna de una colección (por
  ejemplo, accediendo directamente a un arreglo por índice).
- Se implementa con una interfaz de iterador (`haySiguiente()`/`siguiente()`) que la colección produce,
  ocultando su estructura interna.

📎 Ver en la práctica: [Ejemplo 03 — Iterator](03-iterator.md)

## 🧠 Concepto: Mediator

- Centraliza en un único objeto la comunicación entre varios objetos que, de otro modo, se
  referenciarían directamente entre sí.
- El problema que resuelve: varios objetos que se llaman directamente entre sí (acoplamiento de todos
  con todos), en vez de comunicarse a través de un punto central.
- Se implementa con una clase mediadora que conoce a los colegas y coordina su comunicación; agregar un
  colega nuevo concentra el cambio en el mediador, no en los colegas existentes.

📎 Ver en la práctica: [Ejemplo 04 — Mediator](04-mediator.md)

## 🧠 Concepto: Memento

- Captura y guarda el estado interno de un objeto en un momento dado, para poder restaurarlo más
  tarde, sin romper su encapsulamiento.
- El problema que resuelve: un objeto editable sin ninguna forma de deshacer cambios y volver a un
  estado anterior.
- Se implementa con una clase "instante" opaca (visibilidad de paquete) que el objeto original crea e
  interpreta, y un "historial" que la guarda sin necesitar leer su contenido.
- No debe confundirse con Command (Módulo 14): Command revierte una acción ejecutando su operación
  contraria; Memento restaura un estado completo guardado, sin ejecutar ninguna operación inversa.

📎 Ver en la práctica: [Ejemplo 05 — Memento](05-memento.md)

## 📋 Resumen de los cuatro patrones de este módulo

| Patrón | Qué problema de comunicación resuelve | Ejemplo de MediSalud |
|---|---|---|
| Chain of Responsibility | Un condicional único decide quién resuelve una solicitud | `AprobadorNivelBasico/Intermedio/Avanzado` encadenados |
| Iterator | El cliente accede a la estructura interna de una colección | `ColaDeSalaDeEsperaIterador` oculta el arreglo interno |
| Mediator | Varios objetos se llaman directamente entre sí | `MediadorDeAltaMedica` coordina tres áreas |
| Memento | Un objeto editable no puede deshacer cambios | `HistorialDeBorradores` guarda instantes restaurables |

Junto con los cinco patrones del Módulo 14 (Strategy, Observer, Command, State, Template Method — ver su
[tabla resumen](../../../14-patrones-comportamiento/material/ejemplos/explicacion-conceptual.md)), estos
cuatro completan los nueve patrones de comportamiento del temario.

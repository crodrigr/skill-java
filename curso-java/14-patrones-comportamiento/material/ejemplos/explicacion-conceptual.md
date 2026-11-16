# 📚 Explicación conceptual — Módulo 14

## 🧠 Concepto: Qué es un patrón de comportamiento

- Resuelve cómo se comunican los objetos entre sí y cómo reparten responsabilidades de comportamiento,
  a diferencia de los creacionales (cómo se crean) y los estructurales (cómo se componen).
- Este módulo cubre los nueve patrones de comportamiento del catálogo GoF: Chain of Responsibility,
  Command, Iterator, Mediator, Memento, Observer, State, Strategy y Template Method.
- El diseño que resolvía OCP en el Módulo 11 (una interfaz con una implementación por caso) ya era,
  sin nombrarlo, un patrón de comportamiento — Strategy.

📎 Ver en la práctica: [Ejemplo 01 — Qué son los patrones de comportamiento](01-que-son-los-patrones-de-comportamiento.md)

## 🧠 Concepto: Chain of Responsibility

- Pasa una solicitud a lo largo de una cadena de manejadores hasta que uno de ellos la resuelve, sin
  que quien envía la solicitud conozca cuál.
- El problema que resuelve: un único método con un condicional gigante que decide, según distintos
  criterios, quién resuelve una solicitud.
- Se implementa con una interfaz común y una cadena de objetos, cada uno con una referencia al
  siguiente.

📎 Ver en la práctica: [Ejemplo 02 — Chain of Responsibility](02-chain-of-responsibility.md)

## 🧠 Concepto: Command

- Encapsula una solicitud como un objeto, permitiendo parametrizarla, encolarla, registrarla o
  deshacerla.
- El problema que resuelve: código cliente que invoca directamente al receptor de una acción, sin
  ningún objeto intermedio que la represente, impidiendo deshacerla o registrarla de forma uniforme.
- Se implementa con una interfaz de acción (`ejecutar()`/`deshacer()`) y un historial que la ejecuta y
  la guarda.

📎 Ver en la práctica: [Ejemplo 03 — Command](03-command.md)

## 🧠 Concepto: Iterator

- Permite recorrer los elementos de una colección sin exponer su representación interna.
- El problema que resuelve: código cliente acoplado a la estructura interna de una colección (por
  ejemplo, accediendo directamente a un arreglo por índice).
- Se implementa con una interfaz de iterador (`haySiguiente()`/`siguiente()`) que la colección produce,
  ocultando su estructura interna.

📎 Ver en la práctica: [Ejemplo 04 — Iterator](04-iterator.md)

## 🧠 Concepto: Mediator

- Centraliza en un único objeto la comunicación entre varios objetos que, de otro modo, se
  referenciarían directamente entre sí.
- El problema que resuelve: varios objetos que se llaman directamente entre sí (acoplamiento de todos
  con todos), en vez de comunicarse a través de un punto central.
- Se implementa con una clase mediadora que conoce a los colegas y coordina su comunicación; agregar un
  colega nuevo concentra el cambio en el mediador, no en los colegas existentes.

📎 Ver en la práctica: [Ejemplo 05 — Mediator](05-mediator.md)

## 🧠 Concepto: Memento

- Captura y guarda el estado interno de un objeto en un momento dado, para poder restaurarlo más
  tarde, sin romper su encapsulamiento.
- El problema que resuelve: un objeto editable sin ninguna forma de deshacer cambios y volver a un
  estado anterior.
- Se implementa con una clase "instante" opaca (visibilidad de paquete) que el objeto original crea e
  interpreta, y un "historial" que la guarda sin necesitar leer su contenido.
- No debe confundirse con Command: Command revierte una acción ejecutando su operación contraria;
  Memento restaura un estado completo guardado, sin ejecutar ninguna operación inversa.

📎 Ver en la práctica: [Ejemplo 06 — Memento](06-memento.md)

## 🧠 Concepto: Observer

- Define una dependencia uno-a-muchos entre objetos: cuando uno cambia de estado, todos sus
  dependientes se notifican automáticamente, sin que el sujeto conozca sus clases concretas.
- El problema que resuelve: código que notifica manualmente a cada interesado, uno por uno, dentro del
  mismo método que cambia el estado.
- Se implementa con una interfaz de observador y una lista de observadores registrados dinámicamente en
  el sujeto.

📎 Ver en la práctica: [Ejemplo 07 — Observer](07-observer.md)

## 🧠 Concepto: State

- Permite que un objeto altere su comportamiento cuando cambia su estado interno, de forma que parezca
  que cambió de clase.
- El problema que resuelve: un campo de estado con condicionales repetidos en varios métodos, en vez de
  delegar el comportamiento al estado actual.
- Se implementa con una interfaz de estado y una clase por cada estado posible, cada una decidiendo su
  propia transición.

📎 Ver en la práctica: [Ejemplo 08 — State](08-state.md)

## 🧠 Concepto: Strategy

- Encapsula una familia de algoritmos intercambiables detrás de una interfaz común, de forma que el
  algoritmo pueda variar independientemente del código que lo usa.
- El problema que resuelve: un condicional (`if`/`switch`) que elige, dentro del código cliente, cuál
  de varios algoritmos aplicar.
- Es, con clases nuevas, el mismo diseño que ya construiste en el Módulo 11 para resolver OCP.

📎 Ver en la práctica: [Ejemplo 09 — Strategy](09-strategy.md)

## 🧠 Concepto: Template Method

- Fija el esqueleto de un algoritmo en un método `final`, dejando que las subclases redefinan solo los
  pasos que varían, sin poder cambiar la estructura general.
- El problema que resuelve: varias clases que repiten el mismo esqueleto de pasos, copiado y pegado con
  variaciones menores, con riesgo real de omitir un paso o cambiar el orden.
- Se implementa con una clase abstracta que declara el método plantilla `final` y deja los pasos que
  varían como métodos abstractos.

📎 Ver en la práctica: [Ejemplo 10 — Template Method](10-template-method.md)

## 📋 Resumen de los nueve patrones de comportamiento

| Patrón | Qué problema de comunicación o reparto resuelve | Ejemplo de MediSalud |
|---|---|---|
| Chain of Responsibility | Un condicional único decide quién resuelve una solicitud | `AprobadorNivelBasico/Intermedio/Avanzado` encadenados |
| Command | El cliente llama directo al receptor, sin poder deshacer | `HistorialDeAcciones` ejecuta y deshace `AccionDeConsultorio` |
| Iterator | El cliente accede a la estructura interna de una colección | `ColaDeSalaDeEsperaIterador` oculta el arreglo interno |
| Mediator | Varios objetos se llaman directamente entre sí | `MediadorDeAltaMedica` coordina tres áreas |
| Memento | Un objeto editable no puede deshacer cambios | `HistorialDeBorradores` guarda instantes restaurables |
| Observer | El sujeto notifica a mano a cada interesado | `ColaDeAtencion` notifica a una lista de `ObservadorDeCola` |
| State | Un campo de estado con condicionales repetidos en varios métodos | `Internacion` delega en `EstadoDeInternacion` |
| Strategy | Un condicional elige, en el cliente, cuál algoritmo aplicar | `Turno` delega en `EstrategiaDeTarifa` |
| Template Method | Un esqueleto de pasos copiado y pegado, con riesgo de omitir uno | `GeneradorDeResumen` fija el esqueleto en un método `final` |

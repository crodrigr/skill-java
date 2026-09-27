# 📚 Explicación conceptual — Módulo 14

## 🧠 Concepto: Qué es un patrón de comportamiento

- Resuelve cómo se comunican los objetos entre sí y cómo reparten responsabilidades de comportamiento,
  a diferencia de los creacionales (cómo se crean) y los estructurales (cómo se componen).
- Este módulo cubre cinco de los once patrones de comportamiento del catálogo GoF (Strategy, Observer,
  Command, State, Template Method); los seis restantes quedan para el Módulo 15.
- El diseño que resolvía OCP en el Módulo 11 (una interfaz con una implementación por caso) ya era,
  sin nombrarlo, un patrón de comportamiento — el primero de este módulo, Strategy.

📎 Ver en la práctica: [Ejemplo 01 — Qué son los patrones de comportamiento](01-que-son-los-patrones-de-comportamiento.md)

## 🧠 Concepto: Strategy

- Encapsula una familia de algoritmos intercambiables detrás de una interfaz común, de forma que el
  algoritmo pueda variar independientemente del código que lo usa.
- El problema que resuelve: un condicional (`if`/`switch`) que elige, dentro del código cliente, cuál
  de varios algoritmos aplicar.
- Es, con clases nuevas, el mismo diseño que ya construiste en el Módulo 11 para resolver OCP.

📎 Ver en la práctica: [Ejemplo 02 — Strategy](02-strategy.md)

## 🧠 Concepto: Observer

- Define una dependencia uno-a-muchos entre objetos: cuando uno cambia de estado, todos sus
  dependientes se notifican automáticamente, sin que el sujeto conozca sus clases concretas.
- El problema que resuelve: código que notifica manualmente a cada interesado, uno por uno, dentro del
  mismo método que cambia el estado.
- Se implementa con una interfaz de observador y una lista de observadores registrados dinámicamente en
  el sujeto.

📎 Ver en la práctica: [Ejemplo 03 — Observer](03-observer.md)

## 🧠 Concepto: Command

- Encapsula una solicitud como un objeto, permitiendo parametrizarla, encolarla, registrarla o
  deshacerla.
- El problema que resuelve: código cliente que invoca directamente al receptor de una acción, sin
  ningún objeto intermedio que la represente, impidiendo deshacerla o registrarla de forma uniforme.
- Se implementa con una interfaz de acción (`ejecutar()`/`deshacer()`) y un historial que la ejecuta y
  la guarda.

📎 Ver en la práctica: [Ejemplo 04 — Command](04-command.md)

## 🧠 Concepto: State

- Permite que un objeto altere su comportamiento cuando cambia su estado interno, de forma que parezca
  que cambió de clase.
- El problema que resuelve: un campo de estado con condicionales repetidos en varios métodos, en vez de
  delegar el comportamiento al estado actual.
- Se implementa con una interfaz de estado y una clase por cada estado posible, cada una decidiendo su
  propia transición.

📎 Ver en la práctica: [Ejemplo 05 — State](05-state.md)

## 🧠 Concepto: Template Method

- Fija el esqueleto de un algoritmo en un método `final`, dejando que las subclases redefinan solo los
  pasos que varían, sin poder cambiar la estructura general.
- El problema que resuelve: varias clases que repiten el mismo esqueleto de pasos, copiado y pegado con
  variaciones menores, con riesgo real de omitir un paso o cambiar el orden.
- Se implementa con una clase abstracta que declara el método plantilla `final` y deja los pasos que
  varían como métodos abstractos.

📎 Ver en la práctica: [Ejemplo 06 — Template Method](06-template-method.md)

## 📋 Resumen de los cinco patrones de comportamiento

| Patrón | Qué problema de comunicación o reparto resuelve | Ejemplo de MediSalud |
|---|---|---|
| Strategy | Un condicional elige, en el cliente, cuál algoritmo aplicar | `Turno` delega en `EstrategiaDeTarifa` |
| Observer | El sujeto notifica a mano a cada interesado | `ColaDeAtencion` notifica a una lista de `ObservadorDeCola` |
| Command | El cliente llama directo al receptor, sin poder deshacer | `HistorialDeAcciones` ejecuta y deshace `AccionDeConsultorio` |
| State | Un campo de estado con condicionales repetidos en varios métodos | `Internacion` delega en `EstadoDeInternacion` |
| Template Method | Un esqueleto de pasos copiado y pegado, con riesgo de omitir uno | `GeneradorDeResumen` fija el esqueleto en un método `final` |

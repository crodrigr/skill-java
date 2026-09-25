# 📚 Explicación conceptual — Módulo 12

## 🧠 Concepto: Qué es un patrón de diseño

- Un patrón de diseño es una solución con nombre propio, reutilizable y ya probada, a un problema de
  diseño que se repite en distintos proyectos.
- El catálogo GoF (Gang of Four) agrupa los patrones en tres categorías: **creacionales** (cómo se crean
  los objetos), **estructurales** (cómo se componen clases y objetos más grandes) y **de comportamiento**
  (cómo se comunican y reparten responsabilidades los objetos).
- Este módulo cubre solo la categoría creacional; las otras dos quedan para módulos posteriores.
- Nombrar un patrón no es decorativo: da a todo el equipo un vocabulario común para hablar de un diseño
  sin tener que explicar la solución completa cada vez.

📎 Ver en la práctica: [Ejemplo 01 — Qué son los patrones de diseño](01-que-son-los-patrones-de-diseno.md)

## 🧠 Concepto: Factory Method

- Delega la creación de un objeto a un método dedicado, en vez de que el código cliente invoque `new`
  directamente sobre un tipo concreto.
- El problema que resuelve: código cliente acoplado a clases concretas mediante `new`, repetido en
  varios lugares, difícil de extender con un tipo nuevo sin modificar cada uno de esos lugares.
- Se reconoce la necesidad de Factory Method cuando agregar un tipo nuevo obliga a revisar y modificar
  el código que ya crea objetos de los tipos existentes.
- Se implementa con un método (a menudo estático) que decide, según un parámetro o una condición, qué
  clase concreta instanciar, devolviendo siempre el tipo común (una interfaz o una superclase).

📎 Ver en la práctica: [Ejemplo 02 — Factory Method](02-factory-method.md)

## 🧠 Concepto: Abstract Factory

- Una interfaz para crear **familias completas** de objetos relacionados, sin que el código cliente
  conozca sus clases concretas.
- Se diferencia de Factory Method en que no crea un solo objeto: crea varios objetos que deben ser
  compatibles entre sí (por ejemplo, todos de la misma línea de producto).
- El problema que resuelve: crear cada miembro de una familia por separado permite, por error, mezclar
  miembros de familias distintas en un mismo conjunto.
- Se implementa con una interfaz que declara un método de creación por cada miembro de la familia, y una
  implementación concreta por cada variante de familia.

📎 Ver en la práctica: [Ejemplo 03 — Abstract Factory](03-abstract-factory.md)

## 🧠 Concepto: Builder

- Construye un objeto complejo paso a paso, separando el proceso de construcción de la representación
  final del objeto.
- El problema que resuelve: un constructor con muchos parámetros (varios opcionales, varios del mismo
  tipo) es fácil de invocar con el orden equivocado, un error que el compilador no puede detectar.
- Se implementa con una clase que acumula los datos con métodos nombrados (uno por dato opcional) y
  termina con un método que arma y devuelve el objeto final.
- En este curso, el Builder se implementa como una clase separada (no una clase anidada), sin perder
  claridad ni ser menos idiomático.

📎 Ver en la práctica: [Ejemplo 04 — Builder](04-builder.md)

## 🧠 Concepto: Prototype

- Crea un objeto nuevo copiando (clonando) una instancia existente, en vez de construirlo desde cero.
- El problema que resuelve: reconstruir un objeto casi idéntico a uno existente, repitiendo cada paso de
  configuración, es costoso y propenso a errores de omisión.
- Distingue clonado **superficial** (la copia comparte las referencias a objetos internos mutables del
  original, como una lista) de clonado **profundo** (la copia tiene su propia versión independiente de
  esos objetos internos).
- Un clonado superficial mal aplicado produce un error real: modificar la copia también modifica al
  original sin que el código lo pida.

📎 Ver en la práctica: [Ejemplo 05 — Prototype](05-prototype.md)

## 🧠 Concepto: Singleton

- Garantiza que una clase tenga una única instancia en toda la aplicación, con un punto de acceso
  global a ella.
- El problema que resuelve: si dos partes del programa crean cada una su propia instancia de una clase
  que debería ser única, cada una ve un estado distinto e incompleto.
- Se implementa con un constructor privado (nadie más puede crear instancias con `new`) y un único punto
  de acceso estático que siempre devuelve la misma instancia.
- Es también uno de los patrones más citados como mal usado: reemplazar toda dependencia por un
  Singleton acopla el código a un estado global oculto, difícil de probar — se justifica solo cuando de
  verdad debe existir una única instancia compartida.

📎 Ver en la práctica: [Ejemplo 06 — Singleton](06-singleton.md)

## 📋 Resumen de los cinco patrones creacionales

| Patrón | Qué problema de creación resuelve | Ejemplo de MediSalud |
|---|---|---|
| Factory Method | Código cliente acoplado a clases concretas mediante `new` | `FabricaDeHistoriales` crea `HistorialPapel` o `HistorialDigital` |
| Abstract Factory | Familias de objetos relacionados creadas por separado, con riesgo de mezclarse | `FabricaDeInsumos` garantiza que un combo sea todo de la misma línea |
| Builder | Constructor con muchos parámetros, fácil de invocar en el orden equivocado | `FichaPacienteBuilder` arma la ficha con métodos nombrados |
| Prototype | Reconstruir un objeto casi idéntico a uno existente desde cero | `PlanDeTratamiento.clonar()` con clonado profundo real |
| Singleton | Dos partes del programa crean cada una su propia instancia de algo que debería ser único | `RegistroDeAuditoria` con una única instancia compartida |

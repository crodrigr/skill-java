# 💡 Ejemplo 01 — Qué son los patrones de comportamiento

## 🌍 Contexto

Ya viste dos de las tres categorías del catálogo GoF: los patrones creacionales (Módulo 12, cómo se
crean los objetos) y los estructurales (Módulo 13, cómo se componen). Esta tercera categoría, los
patrones de comportamiento, resuelve un problema distinto: cómo se **comunican** los objetos entre sí y
cómo **reparten responsabilidades** de comportamiento, sin que esa comunicación quede rígida, dispersa
en condicionales, o repetida en varios lugares.

## 🗺️ Diagrama

```mermaid
flowchart TB
    GOF["Catálogo GoF"] --> C["Creacionales (Módulo 12)"]
    GOF --> E["Estructurales (Módulo 13)"]
    GOF --> B["De comportamiento (este módulo, primera mitad)"]
    B --> ST["Strategy"]
    B --> OB["Observer"]
    B --> CO["Command"]
    B --> SE["State"]
    B --> TM["Template Method"]
    B -.-> M15["Otros seis patrones\n(Módulo 15)"]
```

## 🧭 Explicación paso a paso

1. Un patrón de comportamiento no crea objetos (eso es un patrón creacional) ni los compone en
   estructuras más grandes (eso es un patrón estructural): resuelve cómo un objeto reparte su
   comportamiento, o cómo varios objetos ya existentes se comunican entre sí.
2. Este módulo cubre cinco de los once patrones de comportamiento del catálogo GoF, los de uso más
   frecuente en código Java real: Strategy, Observer, Command, State y Template Method. Los seis
   restantes (Chain of Responsibility, Iterator, Mediator, Memento, Visitor, Interpreter) quedan para
   el Módulo 15.
3. Un caso particular: en el Módulo 11 (SOLID) ya construiste, para resolver el Principio Abierto/
   Cerrado, un diseño con una interfaz y una implementación por caso, intercambiable en tiempo de
   ejecución — sin llamarlo por su nombre en ese momento. Ese diseño es, exactamente, la intención de
   Strategy, el primer patrón de este módulo.
4. Cada uno de los cinco patrones aparece primero como un problema real (un condicional que elige un
   algoritmo, una notificación manual a cada interesado, una acción sin poder deshacerse, un
   comportamiento disperso en condicionales sobre un campo de estado, un esqueleto de pasos copiado y
   pegado), y después como su solución aplicando el patrón correspondiente.

## ✅ Resultado esperado

Al terminar este módulo vas a poder mirar un diseño Java real y reconocer cuál de estos cinco patrones
de comportamiento (o ninguno) resuelve el problema de comunicación o reparto de responsabilidades que
tiene delante.

## ❓ Preguntas de repaso

**1. [Selección]** ¿Qué resuelven los patrones de comportamiento, a diferencia de los creacionales y
los estructurales?

- A. Cómo se crean los objetos.
- B. Cómo se componen clases y objetos en estructuras más grandes.
- C. Cómo se comunican los objetos entre sí y cómo reparten responsabilidades de comportamiento.
- D. Cómo se organizan los archivos de un proyecto.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: C.** Los creacionales (A) resuelven la creación de objetos; los estructurales (B)
resuelven la composición; los de comportamiento resuelven la comunicación y el reparto de
responsabilidades.

</details>

**2. [Selección]** ¿Cuántos patrones de comportamiento cubre este módulo, y cuántos quedan para el
Módulo 15?

- A. Los once, en un solo módulo.
- B. Cinco en este módulo, seis en el Módulo 15.
- C. Ninguno: los patrones de comportamiento no se dividen en dos módulos.
- D. Seis en este módulo, cinco en el Módulo 15.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Este módulo cubre Strategy, Observer, Command, State y Template Method; el
Módulo 15 cubre los seis restantes (Chain of Responsibility, Iterator, Mediator, Memento, Visitor,
Interpreter).

</details>

**3. [Abierta]** Un compañero dice que ya vio "algo parecido a Strategy" en el Módulo 11, sin que se
llamara así en ese momento. Explica a qué se refiere.

<details>
<summary>🔑 Ver respuesta</summary>

Se refiere al diseño que resolvía el Principio Abierto/Cerrado (OCP): una interfaz con una
implementación distinta por cada caso, recibida por composición en vez de decidida con un condicional.
Ese diseño, sin nombrarlo entonces, es exactamente la intención de Strategy: encapsular una familia de
algoritmos intercambiables detrás de una interfaz común.

</details>

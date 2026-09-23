# 📚 Explicación conceptual — Módulo 7

## 🧠 Concepto: Diagrama de clases

Un diagrama de clases es una forma visual de mostrar cómo se relacionan varias clases antes de escribir
su código. En este curso se dibuja con la notación `classDiagram` de Mermaid, que Visual Studio Code y
GitHub renderizan directamente dentro de un archivo Markdown.

- Cada caja representa una **clase**, con su nombre en la primera línea.
- Los atributos y métodos se listan dentro de la caja; el signo `-` marca un miembro `private` y el `+`
  uno `public` (la misma notación de modificadores del Módulo 6).
- Una flecha con punta de triángulo hueco (`SuperClase <|-- Subclase`) representa **herencia**: la
  punta siempre apunta hacia la superclase.
- Un miembro heredado **no** se vuelve a dibujar en la caja de la subclase: el diagrama solo muestra lo
  que cada clase declara por sí misma.

📎 Ver en la práctica: [Ejemplo 01 — Diagrama de clases](01-diagrama-de-clases.md)

## 🧠 Concepto: Herencia

La herencia permite que una clase (la **subclase**) reutilice los atributos y métodos de otra (la
**superclase**), en vez de repetirlos, modelando una relación "es un tipo de" con `extends`.

- Una subclase hereda **todo lo que no es `private`** de su superclase (atributos y métodos `public`,
  por defecto o `protected`).
- Un miembro `private` de la superclase sigue sin ser accesible directamente desde la subclase; solo se
  accede a través de un método heredado (`get`/`set` u otro).
- Los **constructores nunca se heredan**: cada subclase declara el suyo propio.
- Si la superclase no tiene un constructor sin parámetros, el constructor de la subclase **debe**
  invocar `super(...)` explícitamente, o el compilador lo rechaza.

📎 Ver en la práctica: [Ejemplo 02 — Herencia](02-herencia.md)

## 🧠 Concepto: this y super

- `super(...)`, dentro de un constructor de subclase, invoca el constructor de la superclase; DEBE ser
  la primera línea si se escribe explícitamente.
- `super.metodo()` invoca la versión de la superclase de un método, típicamente para reutilizarla
  dentro de la versión sobreescrita de la subclase.
- `this(...)`, dentro de un constructor, invoca **otro constructor de la misma clase**, para no repetir
  lógica de inicialización entre constructores sobrecargados.
- Con herencia real, un miembro `protected` de la superclase **sí** es accesible desde una subclase de
  otro paquete (a diferencia de lo visto en el Módulo 6, que solo demostró `protected` sin herencia).

📎 Ver en la práctica: [Ejemplo 03 — this y super](03-this-y-super.md)

## 🧠 Concepto: Polimorfismo

- Una variable de tipo superclase puede referenciar un objeto de cualquier subclase.
- El método que se ejecuta al invocar sobre esa variable se decide **en tiempo de ejecución**, según el
  tipo **real** del objeto, no según el tipo declarado de la variable (enlace dinámico).
- El polimorfismo depende de que exista una sobre-escritura real del método en la subclase; sin ella,
  siempre se ejecutaría la versión de la superclase.
- Java tiene dos formas de polimorfismo: sobrecarga (en tiempo de compilación) y sobre-escritura (en
  tiempo de ejecución).

📎 Ver en la práctica: [Ejemplo 04 — Polimorfismo](04-polimorfismo.md)

## 🧠 Concepto: Sobrecarga

- Varios métodos, o varios constructores, con el **mismo nombre** pero **distinta lista de
  parámetros**, declarados en la misma clase.
- El compilador decide, **en tiempo de compilación**, cuál versión invocar según el número y el tipo
  de los argumentos de la llamada.
- No tiene relación con la herencia: ocurre dentro de una sola clase.
- Está habilitada desde este módulo (a diferencia de los Módulos 4 a 6, que la dejaron fuera de
  alcance).

📎 Ver en la práctica: [Ejemplo 05 — Sobrecarga](05-sobrecarga.md)

## 🧠 Concepto: Sobre-escritura

- Redefinir, en una subclase, un método heredado con la **misma firma** exacta (mismo nombre, mismos
  parámetros, mismo tipo de retorno).
- Se marca con `@Override`: no cambia el comportamiento, pero hace que el compilador verifique que
  realmente se está sobreescribiendo un método de la superclase.
- El modificador de acceso de la sobre-escritura no puede volverse más restrictivo que el original.
- Se resuelve **en tiempo de ejecución** (enlace dinámico), a diferencia de la sobrecarga.

📎 Ver en la práctica: [Ejemplo 06 — Sobre-escritura](06-sobre-escritura.md)

## 🧠 Concepto: Clases abstractas

- Una clase abstracta (`abstract class`) no puede instanciarse directamente con `new`.
- Puede declarar métodos abstractos (`abstract`, sin cuerpo) que toda subclase concreta debe
  implementar, junto con métodos concretos que las subclases heredan tal cual.
- Una subclase que no implemente todos los métodos abstractos heredados debe, a su vez, declararse
  `abstract`, o el compilador la rechaza.
- Instanciar directamente una clase abstracta, o dejar un método abstracto sin implementar en una
  subclase concreta, son dos errores reales de compilación distintos.

📎 Ver en la práctica: [Ejemplo 07 — Clases abstractas](07-clases-abstractas.md)

## 🧠 Concepto: Interfaces

- Una interfaz (`interface`) es un contrato de métodos sin cuerpo (y sin estado), que una o más clases,
  no necesariamente emparentadas, pueden implementar con `implements`.
- Todo método de una interfaz es abstracto por naturaleza, sin necesidad de escribir `abstract`.
- Una clase puede implementar **varias** interfaces a la vez (a diferencia de extender clases: en Java
  solo se puede tener una superclase).
- Igual que una clase abstracta, una interfaz no puede instanciarse directamente con `new` — Java
  produce el mismo mensaje de error para ambos casos.

📎 Ver en la práctica: [Ejemplo 08 — Interfaces](08-interfaces.md)

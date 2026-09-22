# 📚 Explicación conceptual — Módulo 5

## 🧠 Concepto: ¿Qué es la programación orientada a objetos?

- Hasta ahora, cada programa vivía dentro de `main`, con variables sueltas.
- La POO agrupa los **datos** (atributos) y el **comportamiento** (métodos) de un mismo concepto de
  negocio en una **clase**.
- Un **objeto** es una instancia concreta creada a partir de una clase, con sus propios valores.
- La clase es el diseño; el objeto es lo que existe en memoria cuando el programa se ejecuta.

📎 Ver en la práctica: [Ejemplo 01 — Qué es y por qué la POO](01-que-es-y-por-que-la-poo.md)

## 🧠 Concepto: ¿Por qué la programación orientada a objetos?

- Evita repetir las mismas variables para cada paciente, libro o cualquier otro concepto de negocio.
- Agregar una instancia nueva es crear un objeto, no declarar más variables.
- El código se parece más a como se piensa el negocio ("un paciente", no "un grupo de variables").
- No cambia el resultado del programa: cambia cómo se organiza el código para llegar a él.

📎 Ver en la práctica: [Ejemplo 01 — Qué es y por qué la POO](01-que-es-y-por-que-la-poo.md)

## 🧠 Concepto: Clases y objetos

- Una **clase** es un diseño: declara qué atributos y qué métodos tendrá cualquier instancia suya.
- Un **objeto** es una instancia concreta, creada con `new NombreDeClase()`, con sus propios valores.
- Una misma clase puede dar lugar a muchos objetos, cada uno independiente de los demás.
- Los atributos de un objeto se acceden con la notación de punto: `objeto.atributo`.

📎 Ver en la práctica: [Ejemplo 02 — Clases y objetos](02-clases-y-objetos.md)

## 🧠 Concepto: Las funciones (métodos) en Java

- Un método declara: nombre, tipo de retorno (o `void`), parámetros entre paréntesis y un cuerpo.
- Un método de instancia puede leer y usar los atributos del objeto que lo invoca.
- El mismo método puede dar resultados distintos según los atributos del objeto que lo invoca.

📎 Ver en la práctica: [Ejemplo 03 — Métodos de instancia](03-metodos-de-instancia.md)

## 🧠 Concepto: Invocación de un método

- Se invoca con la notación de punto: `objeto.metodo(argumentos)`.
- Los argumentos deben coincidir en cantidad y tipo con los parámetros del método.
- El valor que devuelve un método puede guardarse, usarse directamente, o ignorarse.
- Ignorar el valor devuelto sin darse cuenta es un error lógico frecuente.

📎 Ver en la práctica: [Ejemplo 04 — Invocación de un método](04-invocacion-de-un-metodo.md)

## 🧠 Concepto: Métodos estáticos

- Un método estático (`static`) pertenece a la clase; se invoca con `Clase.metodo(...)`, sin objeto.
- Un método de instancia pertenece a cada objeto; se invoca con `objeto.metodo(...)`.
- `main` es estático porque Java lo ejecuta sin haber creado antes ningún objeto.
- Invocar un método estático sobre un objeto compila y funciona igual, pero es una mala práctica.

📎 Ver en la práctica: [Ejemplo 05 — Métodos estáticos](05-metodos-estaticos.md)

## 🧠 Concepto: Ejemplo de clase en Java

- Una clase completa junta varios atributos y varios métodos que trabajan sobre ellos.
- Sin un constructor propio, Java agrega uno automáticamente: crea el objeto con los atributos en su
  valor por defecto (`null`, `0`, `false`, según el tipo).
- Los atributos se asignan por punto, uno por uno, antes de usarlos.

📎 Ver en la práctica: [Ejemplo 06 — Ejemplo completo de una clase](06-ejemplo-completo-de-una-clase.md)

## 🧠 Concepto: Objetos en Java

- `new NombreDeClase(argumentos)` reserva memoria para un objeto nuevo y ejecuta su constructor.
- El resultado de `new` se guarda en una variable del tipo de la clase.
- Invocar un método sobre una variable que vale `null` (ningún objeto creado) detiene el programa.

📎 Ver en la práctica: [Ejemplo 07 — Constructor y objetos](07-constructor-y-objetos.md)

## 🧠 Concepto: Método constructor

- Es un método especial con el mismo nombre que la clase, sin tipo de retorno.
- Se ejecuta automáticamente cuando se crea un objeto con `new`.
- `this.atributo = parametro;` distingue el atributo del objeto del parámetro recibido cuando comparten
  nombre.
- Si una clase no declara ningún constructor, Java agrega uno automáticamente, sin parámetros, que deja
  los atributos en su valor por defecto (`null`, `0`, `false`).
- Varios objetos de la misma clase son independientes: cada uno tiene su propia copia de los atributos.

📎 Ver en la práctica: [Ejemplo 07 — Constructor y objetos](07-constructor-y-objetos.md)

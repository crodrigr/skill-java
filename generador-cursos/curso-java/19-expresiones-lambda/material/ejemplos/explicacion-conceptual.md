# 📚 Explicación conceptual — Módulo 19

> 🎯 **Idea central del módulo:** a veces necesitas pasarle a un método **un pedazo de comportamiento**
> ("haz esto con cada paciente", "dime si este paciente cumple tal condición"). Una **expresión lambda** es
> la forma corta de escribir ese pedazo de comportamiento, sin tener que crear una clase completa solo para
> eso.

## 🧠 Concepto: Expresiones lambda

### 🤔 El problema

Imagina que tienes una lista de pacientes y quieres **imprimir cada uno**. Un método que recorra la lista
sabe *recorrer*, pero ¿cómo le dices *qué hacer* con cada paciente? En Java, "lo que hay que hacer" se
describe con una **interfaz**: un contrato que dice "quien me implemente debe tener este método".

Por ejemplo, esta interfaz dice: "quien me implemente sabe hacer algo con un paciente":

```java
public interface AccionPaciente {
    void ejecutar(String paciente);
}
```

### 🐢 La forma antigua: una clase completa

Para usar esa interfaz tenías que escribir una clase entera, solo para una línea de lógica:

```java
class ImprimirPaciente implements AccionPaciente {
    @Override
    public void ejecutar(String paciente) {
        System.out.println(paciente);
    }
}

AccionPaciente accion = new ImprimirPaciente();
accion.ejecutar("Ana");
```

Son 7 líneas para decir "imprime el paciente". Casi todo es ceremonia: el nombre de la clase,
`implements`, `@Override`, `public void`…

### ⚡ La forma moderna: la expresión lambda

Una **expresión lambda** quita la ceremonia y deja solo lo esencial: *qué recibe* y *qué hace*.

```java
AccionPaciente accion = paciente -> System.out.println(paciente);
accion.ejecutar("Ana");
```

Se lee así: **"dado un `paciente`, imprímelo"**. La flecha `->` separa lo que **entra** (a la izquierda) de
lo que **se hace** (a la derecha):

```
   (parámetros)   ->   cuerpo
   └─ lo que entra ─┘   └─ lo que se hace ─┘
```

### 🔑 Tres ideas para quedarte

1. **Interfaz funcional** = una interfaz con **un solo método abstracto** (como `AccionPaciente`, que solo
   tiene `ejecutar`). Solo con ellas se puede usar una lambda.
2. La lambda **no inventa nada nuevo**: hace exactamente lo mismo que la clase de arriba, pero escrito en
   corto. Si ejecutas ambas versiones, la salida es idéntica.
3. No hace falta decir a qué interfaz pertenece la lambda: Java lo deduce por el lugar donde la usas (aquí,
   porque la guardas en una variable de tipo `AccionPaciente`).

> 💡 **Analogía:** una lambda es como dejarle una **nota adhesiva** a un compañero: "con cada paciente,
> imprime su nombre". No hace falta redactar un manual completo (la clase); basta la instrucción.

📎 Ver en la práctica: [Ejemplo 01 — ¿Qué son las expresiones lambda?](01-que-son-las-expresiones-lambda.md)
(la misma acción escrita con clase y con lambda, con la misma salida).

## 🧠 Concepto: Las interfaces estándar de Java

Escribir una interfaz propia como `AccionPaciente` para cada caso se vuelve repetitivo. Por eso Java ya
trae, en el paquete `java.util.function`, interfaces listas para los casos más comunes. Casi todo lo que
puedes querer hacer con un dato cae en una de estas cuatro preguntas:

| Si quieres… | Usa | Ejemplo cotidiano |
|---|---|---|
| **Hacer algo** con un dato (sin devolver nada) | `Consumer` | Imprimir un paciente |
| **Obtener** un dato (sin darle nada) | `Supplier` | Generar el siguiente número de turno |
| **Convertir** un dato en otro | `Function` | De un paciente, obtener su texto "Ana (34 años)" |
| **Responder sí o no** sobre un dato | `Predicate` | ¿El paciente tiene 40 años o más? |

Las siguientes secciones explican cada una con un ejemplo.

## 🧠 Concepto: Consumer

### 🤔 ¿Qué es?

`Consumer` viene de "consumidor": **recibe un dato, hace algo con él y no devuelve nada**. Es como una
trituradora de papel: le das la hoja y la procesa, pero no te devuelve nada a cambio.

Su único método es `void accept(T t)`. La `T` es el **tipo del dato** que recibe (por ejemplo, `String`).

### 💻 En código

```java
Consumer<String> imprimir = paciente -> System.out.println("Paciente: " + paciente);

imprimir.accept("Ana");   // muestra: Paciente: Ana
```

Lectura: "dado un `paciente`, imprime `Paciente: ` seguido del nombre". No hay `return` porque el método
es `void`.

### 🧭 ¿Cuándo usarlo?

Cuando la tarea es **una acción**: imprimir, guardar, enviar un correo, sumar a un contador… Si lo que
quieres es un resultado, necesitas otra interfaz.

📎 Ver en la práctica: [Ejemplo 02 — Consumer](02-consumer.md) (una acción aplicada a cada paciente de una
lista).

## 🧠 Concepto: Supplier

### 🤔 ¿Qué es?

`Supplier` viene de "proveedor": **no recibe nada y entrega un dato cada vez que se lo pides**. Es lo
contrario de `Consumer`. Piensa en la máquina que reparte turnos en una sala de espera: no le das nada, solo
pulsas el botón y sale un número.

Su único método es `T get()`: sin parámetros, devuelve un valor de tipo `T`.

### 💻 En código

```java
int[] contador = {0};
Supplier<String> generador = () -> "TURNO-" + (++contador[0]);

System.out.println(generador.get());   // TURNO-1
System.out.println(generador.get());   // TURNO-2
```

Fíjate en los paréntesis vacíos `()`: significan **"no recibe nada"**. Cada vez que llamas a `get()`, la
lambda se ejecuta de nuevo y entrega un valor.

### 🧭 ¿Cuándo usarlo?

Cuando necesitas **producir un valor bajo demanda**: un identificador nuevo, la fecha actual, un valor por
defecto que solo se calcula si hace falta.

📎 Ver en la práctica: [Ejemplo 03 — Supplier](03-supplier.md) (un generador de turnos que entrega uno
nuevo en cada llamada).

## 🧠 Concepto: Funciones lambda (Function)

### 🤔 ¿Qué es?

`Function` es una **transformación**: **recibe un dato y devuelve otro**, que puede ser de un tipo
distinto. Es como una licuadora: entran frutas, sale jugo.

Su único método es `R apply(T t)`: la `T` es el tipo de **entrada** y la `R` el tipo del **resultado**
(R de *Result*).

### 💻 En código

```java
Function<String, Integer> contarLetras = nombre -> nombre.length();

int resultado = contarLetras.apply("Ana");   // 3
```

Aquí entra un `String` ("Ana") y sale un `Integer` (3). Otro caso, más útil: convertir un paciente en un
texto listo para mostrar.

```java
Function<Paciente, String> formato =
        p -> p.getNombre() + " (" + p.getEdad() + " años)";

formato.apply(paciente);   // "Ana (34 años)"
```

### 🧭 ¿Cuándo usarlo?

Cuando quieres **convertir** algo en otra cosa: un objeto en texto, un texto en número, un precio en
precio con impuesto.

### 🆚 ¿Cómo no confundirla con las anteriores?

| | Recibe algo | Devuelve algo |
|---|---|---|
| `Consumer` | ✅ | ❌ |
| `Supplier` | ❌ | ✅ |
| `Function` | ✅ | ✅ |

📎 Ver en la práctica: [Ejemplo 04 — Funciones lambda](04-funciones-lambda.md) (transformar un paciente en
una línea de texto).

## 🧠 Concepto: Predicate

### 🤔 ¿Qué es?

`Predicate` es una **pregunta con respuesta sí o no**: recibe un dato y devuelve siempre un `boolean`
(`true` o `false`). Es como el guardia de la puerta de un evento que mira cada persona y decide: "¿pasa o
no pasa?".

Su único método es `boolean test(T t)`.

### 💻 En código

```java
Predicate<Integer> esMayorDe40 = edad -> edad >= 40;

esMayorDe40.test(55);   // true
esMayorDe40.test(30);   // false
```

La parte de la derecha de la flecha debe ser una **condición** (algo que dé `true` o `false`), como
`edad >= 40`.

### 🧭 ¿Cuándo usarlo?

Cuando necesitas **filtrar o decidir**: "¿este paciente es mayor de 40?", "¿este correo es válido?",
"¿este producto tiene stock?".

### 🆚 ¿En qué se diferencia de `Function`?

Un `Predicate` es como una `Function` cuyo resultado **siempre** es `boolean`. Por eso no necesita indicar el
tipo de salida: ya se sabe cuál es.

📎 Ver en la práctica: [Ejemplo 05 — Predicate](05-predicate.md) (filtrar pacientes según una condición de
edad).

## 🧠 Concepto: Expresiones lambda propias

### 🤔 ¿Cuándo no alcanzan las cuatro estándar?

Las cuatro interfaces anteriores reciben **un solo dato** (o ninguno). ¿Y si tu caso necesita **dos datos
de tipos distintos**? Por ejemplo, armar una nota médica a partir del nombre del paciente **y** su
diagnóstico. Ninguna de las cuatro encaja, así que creas tu propia interfaz funcional.

### 💻 En código

```java
@FunctionalInterface
public interface GeneradorNota {
    String generar(String nombrePaciente, String diagnostico);
}

GeneradorNota generador = (nombrePaciente, diagnostico) ->
        "Paciente " + nombrePaciente + ": " + diagnostico;

generador.generar("Ana", "gripe");   // "Paciente Ana: gripe"
```

Con varios parámetros se escriben **entre paréntesis y separados por comas**, antes de la flecha.

### 🛡️ ¿Para qué sirve `@FunctionalInterface`?

Es una **etiqueta de seguridad**. Le dice al compilador: "esta interfaz debe tener exactamente un método
abstracto". Si alguien (o tú mismo, después) agrega un segundo método, el compilador marca un error de
inmediato:

```
multiple non-overriding abstract methods found in interface GeneradorNota
```

Esto es útil porque una lambda **solo** funciona con un método abstracto: con dos, Java no sabría a cuál
corresponde el cuerpo de la lambda.

📎 Ver en la práctica: [Ejemplo 06 — Expresiones lambda propias](06-expresiones-lambda-propias.md) (una
interfaz propia con dos parámetros y el error real del compilador al agregar un segundo método).

## 📋 Resumen de las interfaces funcionales

| Interfaz | Método abstracto | Recibe | Devuelve | Analogía | Cuándo usarla |
|---|---|---|---|---|---|
| `Consumer<T>` | `accept(T t)` | Un valor | Nada (`void`) | Trituradora de papel | Ejecutar una acción sobre un valor |
| `Supplier<T>` | `get()` | Nada | Un valor | Máquina de turnos | Proveer un valor bajo demanda |
| `Function<T, R>` | `apply(T t)` | Un valor | Otro valor (de otro tipo) | Licuadora | Transformar un valor en otro |
| `Predicate<T>` | `test(T t)` | Un valor | `boolean` | Guardia de la puerta | Evaluar una condición |
| Interfaz propia | El que declares | Lo que necesites | Lo que necesites | — | Ninguna interfaz estándar encaja en la forma del problema |

> 🧭 **Truco para elegir:** hazte dos preguntas. **¿Recibo algo?** **¿Devuelvo algo?** Y si lo que devuelves
> es solo "sí o no", es un `Predicate`.

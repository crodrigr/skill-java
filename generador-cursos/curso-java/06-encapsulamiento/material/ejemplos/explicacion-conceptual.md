# 📚 Explicación conceptual — Módulo 6

## 🧠 Concepto: ¿Qué es encapsulamiento?

- Ocultar el estado interno de un objeto (sus atributos) y controlar cómo se accede a él y se modifica.
- En el Módulo 5 los atributos eran públicos: cualquier código podía asignarles cualquier valor.
- Encapsular no es "esconder los datos sin más": es controlar el acceso, permitiendo validar antes de
  asignar un valor.
- El acceso encapsulado se hace a través de métodos (`get`/`set`), no con la notación de punto directa.

📎 Ver en la práctica: [Ejemplo 01 — Qué es encapsulamiento](01-que-es-encapsulamiento.md)

## 🧠 Concepto: Modificador de acceso private

- Es el modificador más restrictivo: solo la misma clase donde se declara puede acceder al miembro.
- Ni siquiera otra clase del mismo paquete puede acceder a un `private` directamente.
- El acceso desde fuera de la clase se hace a través de métodos `get`/`set`.

📎 Ver en la práctica: [Ejemplo 02 — Modificador de acceso private](02-modificador-private.md)

## 🧠 Concepto: Modificador de acceso por defecto (default)

- Se aplica cuando un atributo o método no lleva ningún modificador.
- Accesible desde cualquier clase del **mismo paquete**, no desde otro paquete.
- Más permisivo que `private`, más restrictivo que `public`.

📎 Ver en la práctica: [Ejemplo 03 — Modificador de acceso por defecto](03-modificador-por-defecto.md)

## 🧠 Concepto: Modificador de acceso protected

- Sin herencia, se comporta exactamente igual que el acceso por defecto (mismo paquete sí, otro no).
- Su ampliación real (acceso desde una subclase de otro paquete) se estudia con la herencia, en un
  módulo posterior.
- El mensaje de `javac` distingue `protected` del acceso por defecto; el mensaje del panel de VS Code,
  no: los dos dan `is not visible`.

📎 Ver en la práctica: [Ejemplo 04 — Modificador de acceso protected](04-modificador-protected.md)

## 🧠 Concepto: Modificador de acceso public

- Es el modificador menos restrictivo: accesible desde cualquier clase, de cualquier paquete.
- Es el modificador habitual para los métodos `get`/`set`, no para los atributos que protegen.
- Comparación de los cuatro modificadores:

| Modificador | Misma clase | Mismo paquete | Otro paquete (sin herencia) |
|---|---|---|---|
| `private` | Sí | No | No |
| Por defecto | Sí | Sí | No |
| `protected` | Sí | Sí | No |
| `public` | Sí | Sí | Sí |

📎 Ver en la práctica: [Ejemplo 05 — Modificador de acceso public](05-modificador-public.md)

## 🧠 Concepto: Métodos get y set

- Un método **get** (accesor) devuelve el valor de un atributo `private`, sin modificarlo.
- Un método **set** (mutador) recibe un valor, lo valida y lo asigna solo si es válido.
- Ambos son `public`, aunque el atributo que exponen sea `private`.
- Un constructor que valida usa el `set` en vez de asignar el atributo directamente.

📎 Ver en la práctica: [Ejemplo 06 — Métodos get y set](06-metodos-get-y-set.md)

## 🧠 Concepto: Validación en el set

- Un `set` que recibe un valor inválido no lo asigna: conserva el valor anterior y avisa por consola.
- No lanza ninguna excepción ni detiene el programa (el curso todavía no cubre `try`/`catch`).
- Para un atributo `boolean`, el `get` se llama `isNombre()`, no `getNombre()`.
- Este comportamiento es correcto por diseño: no es un error del código, aunque se clasifique como
  `error-logico` en el sentido técnico de "hay que comparar el resultado con lo esperado".

📎 Ver en la práctica: [Ejemplo 07 — Validación en el set](07-validacion-en-el-set.md)

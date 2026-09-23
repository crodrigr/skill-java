# ❓ Quiz 01 — Herencia y Polimorfismo (formato entrevista técnica)

Este quiz simula las preguntas que podrías recibir en una entrevista técnica para un puesto de
programador Java junior. Cada pregunta indica su tipo (**Selección**, **Selección múltiple** o
**Abierta**). Respóndela primero por tu cuenta y después abre "Ver respuesta" para comparar.

---

**1. [Selección]** **Pregunta:** en un diagrama de clases con `Persona <|-- Medico`, ¿qué representa la
flecha?

- **A.** `Medico` hereda de `Persona`; la punta apunta a la superclase.
- **B.** `Persona` hereda de `Medico`.
- **C.** `Medico` y `Persona` son la misma clase.
- **D.** `Persona` usa un objeto `Medico` como atributo.

_RA: RA-1_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: A.** La punta hueca del triángulo de la flecha de herencia siempre apunta hacia
la superclase; `Medico` es la subclase que hereda de `Persona`.

</details>

**2. [Selección]** **Pregunta:** ¿cuál de estas opciones describe mejor la herencia en Java?

- **A.** Copiar el código de una clase dentro de otra.
- **B.** Modelar una relación "es un tipo de" para reutilizar atributos y métodos de una superclase.
- **C.** Declarar varias clases con el mismo nombre.
- **D.** Convertir una clase en `abstract`.

_RA: RA-2_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** La herencia modela una relación "es un tipo de" (`extends`), reutilizando lo
que la superclase ya declara en vez de repetirlo.

</details>

**3. [Selección múltiple]** **Pregunta:** dada una subclase `Medico extends Persona`, ¿cuáles
afirmaciones son verdaderas?

- **A.** `Medico` hereda los métodos `public` de `Persona`.
- **B.** `Medico` hereda los atributos `private` de `Persona` para acceso directo.
- **C.** `Medico` hereda el constructor de `Persona`.
- **D.** `Medico` no hereda ningún constructor de `Persona`.

_RA: RA-3_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A y D.** B es falsa: `private` nunca se hereda para acceso directo. C es falsa
y D lo confirma: los constructores nunca se heredan.

</details>

**4. [Abierta]** ¿Por qué el constructor de una subclase debe invocar `super(...)` cuando su superclase
no tiene un constructor sin parámetros?

_RA: RA-4_

<details>
<summary>🔑 Ver respuesta modelo</summary>

Porque la parte heredada del objeto debe inicializarse antes de que el constructor de la subclase
continúe. Si la superclase solo sabe inicializarse recibiendo un valor (no tiene constructor sin
parámetros), la subclase debe indicarle explícitamente ese valor con `super(...)`; si no lo hace, el
compilador lo rechaza.

</details>

**5. [Selección]** **Pregunta:** ¿qué diferencia hay entre `super.metodo()` y `this(...)`?

- **A.** `super.metodo()` invoca la versión de la superclase de un método; `this(...)` invoca otro
  constructor de la misma clase.
- **B.** Son sinónimos: hacen exactamente lo mismo.
- **C.** `this(...)` invoca un método de la superclase; `super.metodo()` invoca otro constructor.
- **D.** Ninguno de los dos es válido dentro de un constructor.

_RA: RA-5, RA-6_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: A.** `super.metodo()` reutiliza el comportamiento heredado de la superclase;
`this(...)` encadena constructores dentro de la misma clase.

</details>

**6. [Selección]** **Pregunta:** dado `Persona p = new Medico(...)`, ¿qué determina qué versión de
`saludar()` ejecuta `p.saludar()`?

- **A.** El tipo declarado de `p` (`Persona`).
- **B.** El tipo real del objeto al que `p` apunta (`Medico`).
- **C.** El orden en que se compilaron las clases.
- **D.** Un valor aleatorio.

_RA: RA-7_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** El enlace dinámico decide qué versión ejecutar según el tipo **real** del
objeto en tiempo de ejecución, no según el tipo declarado de la variable.

</details>

**7. [Selección múltiple]** **Pregunta:** dadas dos variables `Persona p1 = new Medico(...)` y
`Persona p2 = new Paciente(...)`, ¿cuáles afirmaciones son verdaderas?

- **A.** `p1.saludar()` y `p2.saludar()` pueden imprimir mensajes distintos.
- **B.** `p1` y `p2` tienen el mismo tipo declarado.
- **C.** El compilador decide en tiempo de compilación cuál `saludar()` se ejecuta.
- **D.** `p1` y `p2` tienen el mismo tipo real.

_RA: RA-7_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A y B.** C es falsa: se decide en tiempo de ejecución. D es falsa: sus tipos
reales son distintos (`Medico` y `Paciente`).

</details>

**8. [Selección]** **Pregunta:** ¿qué determina cuál versión sobrecargada de un método se invoca?

- **A.** El tipo real del objeto, en tiempo de ejecución.
- **B.** El número y el tipo de los argumentos, en tiempo de compilación.
- **C.** El nombre de la variable que recibe el resultado.
- **D.** El orden de declaración de los métodos.

_RA: RA-8_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** La sobrecarga se resuelve en tiempo de compilación, según el número y el
tipo de los argumentos de la llamada.

</details>

**9. [Abierta]** ¿Puede sobrecargarse un constructor igual que un método? Da un ejemplo breve.

_RA: RA-8_

<details>
<summary>🔑 Ver respuesta modelo</summary>

Sí. Por ejemplo, `Medico(String nombreCompleto, int edad, String especialidad)` y
`Medico(String nombreCompleto, int edad)` son dos constructores con el mismo nombre de clase y distinta
lista de parámetros; el segundo puede delegar en el primero con `this(...)`.

</details>

**10. [Selección]** **Pregunta:** ¿qué exige `@Override` sobre la firma del método?

- **A.** Que sea exactamente la misma que la del método de la superclase.
- **B.** Que tenga al menos un parámetro más.
- **C.** Que el nombre sea distinto.
- **D.** No exige nada sobre la firma.

_RA: RA-9_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: A.** `@Override` verifica que el método tenga exactamente la misma firma (nombre,
parámetros, tipo de retorno) que uno de la superclase; si no, el compilador lo rechaza.

</details>

**11. [Selección múltiple]** **Pregunta:** dado este fragmento, ¿es sobrecarga o sobre-escritura?

- Una clase `Recordatorio` declara `enviar()` y `enviar(String canal)`.
- Una clase `NotificacionUrgente extends Notificacion` declara `enviar()` con `@Override`.

Marca las afirmaciones verdaderas:

- **A.** El primer caso es sobrecarga: misma clase, firma distinta.
- **B.** El segundo caso es sobre-escritura: relación de herencia, misma firma.
- **C.** Ambos casos son el mismo concepto.
- **D.** El segundo caso también podría llamarse sobrecarga.

_RA: RA-10_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A y B.** C y D son falsas: son conceptos distintos, distinguibles por si hay
herencia de por medio y si la firma es idéntica o no.

</details>

**12. [Selección]** **Pregunta:** ¿por qué no se puede instanciar directamente una clase abstracta con
`new`?

- **A.** Porque el compilador la trata como incompleta: puede tener métodos sin implementación.
- **B.** Porque las clases abstractas no tienen constructor.
- **C.** Porque Java lo prohíbe solo para clases con más de un atributo.
- **D.** En realidad sí se puede, con una advertencia.

_RA: RA-11, RA-14_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: A.** Una clase abstracta puede declarar métodos abstractos sin cuerpo; instanciar
un objeto de esa clase dejaría esos métodos sin ninguna implementación real, así que el compilador lo
rechaza directamente.

</details>

**13. [Abierta]** ¿Cuándo conviene que un método de una clase abstracta sea abstracto, y cuándo
conviene que sea concreto?

_RA: RA-11_

<details>
<summary>🔑 Ver respuesta modelo</summary>

Un método debería ser abstracto cuando cada subclase necesita su propia implementación distinta (como
`calcularSueldo()`, que varía según el tipo de empleado). Debería ser concreto cuando el comportamiento
es el mismo para todas las subclases y conviene no repetirlo (como `getNombreCompleto()`, que todas
heredan igual).

</details>

**14. [Selección múltiple]** **Pregunta:** ¿cuáles afirmaciones sobre `implements` son verdaderas?

- **A.** Una clase puede implementar varias interfaces a la vez.
- **B.** Una clase puede extender una clase e implementar una o más interfaces al mismo tiempo.
- **C.** Implementar una interfaz obliga a definir todos sus métodos.
- **D.** Una interfaz puede tener atributos de instancia con estado, igual que una clase.

_RA: RA-12_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y C.** D es falsa: una interfaz no tiene estado (atributos de instancia).

</details>

**15. [Selección]** **Pregunta:** ¿cuál es el criterio principal para elegir clase abstracta frente a
interfaz?

- **A.** Si hay código o estado compartido entre subclases emparentadas, clase abstracta; si es un
  contrato sin estado entre clases no emparentadas, interfaz.
- **B.** Las interfaces son siempre mejores que las clases abstractas.
- **C.** Depende únicamente de cuántos métodos tenga la clase.
- **D.** Las clases abstractas ya no se usan en Java moderno.

_RA: RA-13_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: A.** El criterio central es si hay estado/código compartido entre clases
emparentadas (clase abstracta) o solo un contrato sin estado entre clases que no comparten superclase
(interfaz).

</details>

**16. [Selección múltiple]** **Pregunta:** ¿cuáles de estos son errores frecuentes de esta etapa?

- **A.** No invocar `super(...)` cuando la superclase no tiene constructor sin parámetros.
- **B.** Confundir sobrecarga con sobre-escritura.
- **C.** Olvidar `@Override` al sobreescribir un método.
- **D.** Instanciar directamente una clase abstracta o una interfaz.

_RA: RA-15_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B, C y D.** Los cuatro son errores reales y frecuentes de este módulo,
verificados con el compilador y el panel Problems reales.

</details>

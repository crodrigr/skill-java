# ❓ Quiz 01 — Patrones de Diseño de Comportamiento II (formato entrevista técnica)

Este quiz simula las preguntas que podrías recibir en una entrevista técnica para un puesto de
programador Java junior. Cada pregunta indica su tipo (**Selección**, **Selección múltiple** o
**Abierta**). Respóndela primero por tu cuenta y después abre "Ver respuesta" para comparar.

---

**1. [Selección]** **Pregunta:** ¿cómo se completa la categoría de patrones de comportamiento entre los
Módulos 14 y 15?

- **A.** El Módulo 14 cubre cinco patrones y este módulo repite esos mismos cinco con más detalle.
- **B.** El Módulo 14 cubre cinco patrones (Strategy, Observer, Command, State, Template Method) y
  este módulo cubre los cuatro restantes (Chain of Responsibility, Iterator, Mediator, Memento), sin
  repetir ninguno.
- **C.** Este módulo cubre los nueve patrones de comportamiento completos.
- **D.** No hay ninguna relación entre el Módulo 14 y este módulo.

_RA: RA-1_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Entre los dos módulos, sin superposición, quedan cubiertos los nueve
patrones de comportamiento del temario original.

</details>

**2. [Selección]** **Pregunta:** ¿qué resuelve el patrón Chain of Responsibility?

- **A.** Que un algoritmo se elija en tiempo de ejecución.
- **B.** Pasar una solicitud a lo largo de una cadena de manejadores hasta que uno de ellos la
  resuelva.
- **C.** Que un objeto notifique a una lista de interesados.
- **D.** Que un objeto guarde y restaure su estado interno.

_RA: RA-2_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Chain of Responsibility encadena manejadores, cada uno decidiendo si
resuelve la solicitud o la pasa al siguiente.

</details>

**3. [Selección múltiple]** **Pregunta:** un método `aprobar(solicitud)` resuelve con
`if (complejidad.equals("BASICA")) ... else if (complejidad.equals("INTERMEDIA")) ...` cuál nivel
aprueba. ¿Cuáles afirmaciones son verdaderas?

- **A.** Agregar un nivel nuevo exige agregar una rama más al condicional.
- **B.** El método concentra la decisión de todos los niveles de aprobación posibles.
- **C.** Una cadena de manejadores, cada uno con la misma interfaz, resolvería el mismo problema sin
  ese condicional.
- **D.** El código no compila mientras el condicional siga creciendo.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y C.** D es falsa: el código compila y funciona perfectamente con el
condicional — el problema es de mantenibilidad, no de compilación.

</details>

_RA: RA-3_

**4. [Abierta]** **Pregunta:** tienes el método `aprobar(solicitud)` de la pregunta anterior. Explica,
en tus palabras, cómo aplicarías Chain of Responsibility para eliminar el condicional.

_RA: RA-4_

<details>
<summary>🔑 Ver respuesta</summary>

Se declara una interfaz común (por ejemplo `ManejadorDeAprobacion`) con un método `aprobar(solicitud)`,
implementada por una clase por cada nivel. Cada manejador recibe al siguiente de la cadena por
composición (por ejemplo, en su constructor): si puede resolver la solicitud, lo hace; si no, la pasa
al siguiente llamando a su propio `aprobar(solicitud)`. Quien envía la solicitud solo conoce al primer
manejador de la cadena, sin saber cuál la va a resolver finalmente.

</details>

**5. [Selección]** **Pregunta:** ¿qué resuelve el patrón Iterator?

- **A.** Recorrer los elementos de una colección sin exponer su representación interna.
- **B.** Que una solicitud pase por una cadena de manejadores.
- **C.** Que varios objetos se comuniquen a través de un punto central.
- **D.** Que un objeto restaure un estado anterior.

_RA: RA-5_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: A.** Iterator oculta la estructura interna de una colección detrás de una
interfaz de recorrido común.

</details>

**6. [Selección múltiple]** **Pregunta:** un código cliente recorre `coleccion.getElementos()[i]` con
un `for` indexado. ¿Cuáles afirmaciones son verdaderas?

- **A.** El cliente está acoplado a que la colección use, específicamente, un arreglo.
- **B.** Si la representación interna cambiara, el código cliente también tendría que cambiar.
- **C.** Un iterador propio permitiría recorrer la colección sin ese acoplamiento.
- **D.** El problema desaparecería usando `ArrayList` en vez de un arreglo, sin cambiar nada más.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y C.** D es falsa: cambiar el tipo de la estructura interna no elimina el
acoplamiento si el cliente sigue accediendo a ella directamente por índice o método expuesto.

</details>

_RA: RA-6_

**7. [Abierta]** **Pregunta:** tienes el código cliente de la pregunta anterior. Explica, en tus
palabras, cómo aplicarías Iterator para que no dependa de la estructura interna de la colección.

_RA: RA-7_

<details>
<summary>🔑 Ver respuesta</summary>

Se declara una interfaz de iterador (por ejemplo, con `haySiguiente()`/`siguiente()`), y una clase que
la implementa conociendo la estructura interna de la colección. La colección expone un método (por
ejemplo, `crearIterador()`) que devuelve ese iterador, sin exponer nunca su arreglo o estructura
interna. El código cliente recorre la colección llamando solo a `haySiguiente()`/`siguiente()`, sin
saber cómo están guardados los elementos por dentro.

</details>

**8. [Selección]** **Pregunta:** ¿qué resuelve el patrón Mediator?

- **A.** Que un objeto recorra una colección sin conocer su estructura interna.
- **B.** Centralizar en un único objeto la comunicación entre varios objetos que, de otro modo, se
  referenciarían directamente entre sí.
- **C.** Que una solicitud pase por una cadena de manejadores.
- **D.** Que un objeto guarde y restaure su estado interno.

_RA: RA-8_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Mediator reduce el acoplamiento de todos con todos, centralizando la
comunicación en un único objeto.

</details>

**9. [Selección múltiple]** **Pregunta:** tres objetos (`AreaFacturacion`, `AreaLaboratorio`,
`AreaFarmacia`) se llaman directamente entre sí. ¿Cuáles afirmaciones son verdaderas sobre agregar un
área nueva?

- **A.** Hay que modificar la clase que origina la comunicación para que también conozca al área
  nueva.
- **B.** El acoplamiento de todos con todos crece con cada área nueva agregada.
- **C.** Un mediador centralizando la comunicación evitaría modificar las áreas existentes.
- **D.** El problema desaparecería si las tres áreas fueran interfaces en vez de clases concretas.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y C.** D es falsa: usar interfaces no elimina el acoplamiento si cada área
sigue conociendo y llamando directamente a las demás.

</details>

_RA: RA-9_

**10. [Abierta]** **Pregunta:** tienes las tres áreas de la pregunta anterior. Explica, en tus
palabras, cómo aplicarías Mediator para reducir su acoplamiento.

_RA: RA-10_

<details>
<summary>🔑 Ver respuesta</summary>

Se declara una clase mediadora (por ejemplo `MediadorDeAltaMedica`) que conoce a las tres áreas y
coordina la comunicación entre ellas. Cada área deja de conocer directamente a las otras y pasa a
conocer solo al mediador: cuando necesita avisar algo, se lo notifica al mediador, y es el mediador
quien decide a quién más avisar. Agregar un área nueva exige registrarla en el mediador, sin modificar
las áreas existentes.

</details>

**11. [Selección]** **Pregunta:** ¿qué resuelve el patrón Memento?

- **A.** Centralizar la comunicación entre varios objetos.
- **B.** Capturar el estado interno de un objeto para poder restaurarlo más tarde, sin romper su
  encapsulamiento.
- **C.** Recorrer una colección sin exponer su estructura interna.
- **D.** Pasar una solicitud por una cadena de manejadores.

_RA: RA-11_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Memento guarda instantes del estado de un objeto, restaurables más tarde,
sin exponer la estructura interna de ese estado a quien los guarda.

</details>

**12. [Selección múltiple]** **Pregunta:** un objeto editable sobrescribe su contenido cada vez que se
modifica, sin dejar ningún registro anterior. ¿Cuáles afirmaciones son verdaderas?

- **A.** Una vez aplicado un cambio, no hay forma de volver al contenido anterior.
- **B.** Guardar instantes del contenido en distintos momentos permitiría restaurar uno anterior.
- **C.** El problema es exactamente el que Memento resuelve.
- **D.** El problema desaparecería agregando un método `deshacer()` que revierta la última operación
  ejecutada.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y C.** D describe Command (Módulo 14), no Memento: revertir la última
operación exige conocer su operación contraria, mientras que Memento restaura un estado guardado sin
ejecutar ninguna operación inversa.

</details>

_RA: RA-12_

**13. [Abierta]** **Pregunta:** tienes el objeto editable de la pregunta anterior. Explica, en tus
palabras, cómo aplicarías Memento para permitir deshacer un cambio.

_RA: RA-13_

<details>
<summary>🔑 Ver respuesta</summary>

Se declara una clase "instante" (por ejemplo `InstanteDelBorrador`) que captura el contenido del objeto
en un momento dado, con constructor y lectura de visibilidad de paquete (no públicos). El objeto
original expone un método para producir un instante (`guardarInstante()`) y otro para restaurarlo
(`restaurar(instante)`). Un historial externo guarda los instantes producidos, sin necesitar leer su
contenido, y los devuelve cuando hace falta restaurar uno.

</details>

**14. [Abierta]** **Pregunta:** una biblioteca universitaria necesita coordinar la comunicación entre
tres módulos (catalogación, adquisiciones y difusión) que hoy se llaman directamente entre sí cada vez
que se incorpora un ejemplar nuevo, y ese acoplamiento crece cada vez que se agrega un módulo más.
Identifica cuál de los nueve patrones de comportamiento (los cinco del Módulo 14 o los cuatro de este)
encaja mejor para este caso y justifica por qué los otros ocho no encajan igual de bien.

_RA: RA-14_

<details>
<summary>🔑 Ver respuesta</summary>

Mediator encaja mejor: el problema es, exactamente, varios objetos que se comunican directamente entre
sí (acoplamiento de todos con todos), que Mediator resuelve centralizando esa comunicación en un único
objeto. Strategy, State y Template Method no aplican porque no hay un algoritmo, un estado interno ni
un esqueleto de pasos en juego. Observer no aplica porque no hay una relación de notificación uno-a-
muchos sobre un cambio de estado, sino una comunicación general entre varios módulos. Command no
aplica porque no hay ninguna acción que deba encapsularse para deshacerse o registrarse. Chain of
Responsibility no aplica porque no hay una solicitud que deba resolverse por uno de varios niveles en
secuencia. Iterator no aplica porque no hay ninguna colección que recorrer. Memento no aplica porque no
hay ningún estado que deba guardarse y restaurarse.

</details>

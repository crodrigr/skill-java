# ❓ Quiz 01 — Patrones de Diseño de Comportamiento (formato entrevista técnica)

Este quiz simula las preguntas que podrías recibir en una entrevista técnica para un puesto de
programador Java junior. Cada pregunta indica su tipo (**Selección**, **Selección múltiple** o
**Abierta**). Respóndela primero por tu cuenta y después abre "Ver respuesta" para comparar.

---

**1. [Selección]** **Pregunta:** ¿qué es un patrón de comportamiento y en qué se distingue de las otras
dos categorías del catálogo GoF?

- **A.** Un patrón de comportamiento controla cómo se crean los objetos; los otros dos no.
- **B.** Un patrón de comportamiento resuelve cómo se comunican los objetos entre sí y cómo reparten
  responsabilidades, a diferencia de los creacionales (creación) y los estructurales (composición).
- **C.** Un patrón de comportamiento es cualquier patrón que use interfaces.
- **D.** No hay ninguna diferencia real entre las tres categorías.

_RA: RA-1_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Los patrones de comportamiento resuelven problemas de comunicación entre
objetos y reparto de responsabilidades; los creacionales resuelven problemas de creación; los
estructurales, de composición.

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

**5. [Selección]** **Pregunta:** ¿qué resuelve el patrón Command?

- **A.** Que un objeto altere su comportamiento según su estado interno.
- **B.** Encapsular una solicitud como un objeto, permitiendo parametrizarla, registrarla o deshacerla.
- **C.** Que varias clases compartan el mismo esqueleto de pasos.
- **D.** Que un sujeto notifique a una lista de interesados.

_RA: RA-5_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Command convierte una acción en un objeto propio, con `ejecutar()`/
`deshacer()`, en vez de una llamada directa al receptor.

</details>

**6. [Selección múltiple]** **Pregunta:** un código cliente llama directamente
`consultorio.ocupar(medico)`, sin ningún objeto que represente esa acción. ¿Cuáles afirmaciones son
verdaderas?

- **A.** No hay ninguna forma uniforme de deshacer esa acción.
- **B.** Registrar un historial de qué se hizo exige que el cliente lo lleve manualmente.
- **C.** Encapsular la acción en un objeto con `ejecutar()`/`deshacer()` resolvería ambos problemas.
- **D.** El código no compila mientras la acción se invoque directamente.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y C.** D es falsa: el código compila y funciona perfectamente llamando
directo al receptor — el problema es no poder deshacer ni registrar de forma uniforme.

</details>

_RA: RA-6_

**7. [Abierta]** **Pregunta:** tienes el código cliente de la pregunta anterior. Explica, en tus
palabras, cómo aplicarías Command para poder deshacer la última acción.

_RA: RA-7_

<details>
<summary>🔑 Ver respuesta</summary>

Se declara una interfaz (por ejemplo `AccionDeConsultorio`) con `ejecutar()`/`deshacer()`, y una clase
por acción (`AccionOcuparConsultorio`) que guarda, antes de ejecutar, el dato necesario para revertirse
(el médico anterior). Un `HistorialDeAcciones` ejecuta la acción y la guarda en una lista; su método
`deshacerUltima()` toma la última acción guardada y llama a su `deshacer()`, revirtiendo el estado
exacto anterior sin que el cliente conozca la operación contraria de cada acción.

</details>

**8. [Selección]** **Pregunta:** ¿qué resuelve el patrón Iterator?

- **A.** Recorrer los elementos de una colección sin exponer su representación interna.
- **B.** Que una solicitud pase por una cadena de manejadores.
- **C.** Que varios objetos se comuniquen a través de un punto central.
- **D.** Que un objeto restaure un estado anterior.

_RA: RA-8_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: A.** Iterator oculta la estructura interna de una colección detrás de una
interfaz de recorrido común.

</details>

**9. [Selección múltiple]** **Pregunta:** un código cliente recorre `coleccion.getElementos()[i]` con
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

_RA: RA-9_

**10. [Abierta]** **Pregunta:** tienes el código cliente de la pregunta anterior. Explica, en tus
palabras, cómo aplicarías Iterator para que no dependa de la estructura interna de la colección.

_RA: RA-10_

<details>
<summary>🔑 Ver respuesta</summary>

Se declara una interfaz de iterador (por ejemplo, con `haySiguiente()`/`siguiente()`), y una clase que
la implementa conociendo la estructura interna de la colección. La colección expone un método (por
ejemplo, `crearIterador()`) que devuelve ese iterador, sin exponer nunca su arreglo o estructura
interna. El código cliente recorre la colección llamando solo a `haySiguiente()`/`siguiente()`, sin
saber cómo están guardados los elementos por dentro.

</details>

**11. [Selección]** **Pregunta:** ¿qué resuelve el patrón Mediator?

- **A.** Que un objeto recorra una colección sin conocer su estructura interna.
- **B.** Centralizar en un único objeto la comunicación entre varios objetos que, de otro modo, se
  referenciarían directamente entre sí.
- **C.** Que una solicitud pase por una cadena de manejadores.
- **D.** Que un objeto guarde y restaure su estado interno.

_RA: RA-11_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Mediator reduce el acoplamiento de todos con todos, centralizando la
comunicación en un único objeto.

</details>

**12. [Selección múltiple]** **Pregunta:** tres objetos (`AreaFacturacion`, `AreaLaboratorio`,
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

_RA: RA-12_

**13. [Abierta]** **Pregunta:** tienes las tres áreas de la pregunta anterior. Explica, en tus
palabras, cómo aplicarías Mediator para reducir su acoplamiento.

_RA: RA-13_

<details>
<summary>🔑 Ver respuesta</summary>

Se declara una clase mediadora (por ejemplo `MediadorDeAltaMedica`) que conoce a las tres áreas y
coordina la comunicación entre ellas. Cada área deja de conocer directamente a las otras y pasa a
conocer solo al mediador: cuando necesita avisar algo, se lo notifica al mediador, y es el mediador
quien decide a quién más avisar. Agregar un área nueva exige registrarla en el mediador, sin modificar
las áreas existentes.

</details>

**14. [Selección]** **Pregunta:** ¿qué resuelve el patrón Memento?

- **A.** Centralizar la comunicación entre varios objetos.
- **B.** Capturar el estado interno de un objeto para poder restaurarlo más tarde, sin romper su
  encapsulamiento.
- **C.** Recorrer una colección sin exponer su estructura interna.
- **D.** Pasar una solicitud por una cadena de manejadores.

_RA: RA-14_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Memento guarda instantes del estado de un objeto, restaurables más tarde,
sin exponer la estructura interna de ese estado a quien los guarda.

</details>

**15. [Selección múltiple]** **Pregunta:** un objeto editable sobrescribe su contenido cada vez que se
modifica, sin dejar ningún registro anterior. ¿Cuáles afirmaciones son verdaderas?

- **A.** Una vez aplicado un cambio, no hay forma de volver al contenido anterior.
- **B.** Guardar instantes del contenido en distintos momentos permitiría restaurar uno anterior.
- **C.** El problema es exactamente el que Memento resuelve.
- **D.** El problema desaparecería agregando un método `deshacer()` que revierta la última operación
  ejecutada.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y C.** D describe Command, no Memento: revertir la última
operación exige conocer su operación contraria, mientras que Memento restaura un estado guardado sin
ejecutar ninguna operación inversa.

</details>

_RA: RA-15_

**16. [Abierta]** **Pregunta:** tienes el objeto editable de la pregunta anterior. Explica, en tus
palabras, cómo aplicarías Memento para permitir deshacer un cambio.

_RA: RA-16_

<details>
<summary>🔑 Ver respuesta</summary>

Se declara una clase "instante" (por ejemplo `InstanteDelBorrador`) que captura el contenido del objeto
en un momento dado, con constructor y lectura de visibilidad de paquete (no públicos). El objeto
original expone un método para producir un instante (`guardarInstante()`) y otro para restaurarlo
(`restaurar(instante)`). Un historial externo guarda los instantes producidos, sin necesitar leer su
contenido, y los devuelve cuando hace falta restaurar uno.

</details>

**17. [Selección]** **Pregunta:** ¿qué resuelve el patrón Observer?

- **A.** Que un algoritmo varíe independientemente del cliente que lo usa.
- **B.** Que un sujeto notifique automáticamente a una lista de interesados registrados dinámicamente,
  sin conocer sus clases concretas.
- **C.** Que una acción se pueda deshacer.
- **D.** Que un objeto cambie su comportamiento según su estado interno.

_RA: RA-17_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Observer define una dependencia uno-a-muchos: cuando el sujeto cambia, todos
sus observadores se notifican, sin que el sujeto conozca sus clases concretas.

</details>

**18. [Selección múltiple]** **Pregunta:** una `ColaDeAtencion.avanzar()` llama directamente, dentro de
su propio cuerpo, a `pantalla.actualizar(...)` y a `altavoz.anunciar(...)`. ¿Cuáles afirmaciones son
verdaderas sobre agregar un tercer interesado?

- **A.** Hay que modificar `avanzar()` para agregar la llamada nueva.
- **B.** Una lista de observadores registrados dinámicamente evitaría modificar `avanzar()`.
- **C.** El problema es exactamente el que Observer resuelve.
- **D.** El problema desaparecería usando solo dos interesados para siempre.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y C.** D es una afirmación de evasión, no de diseño: el problema sigue
existiendo apenas se necesite un tercer interesado, sin importar cuánto se lo posponga.

</details>

_RA: RA-18_

**19. [Abierta]** **Pregunta:** tienes la `ColaDeAtencion` de la pregunta anterior. Explica, en tus
palabras, cómo aplicarías Observer para agregar un interesado nuevo sin modificar `avanzar()`.

_RA: RA-19_

<details>
<summary>🔑 Ver respuesta</summary>

Se declara una interfaz común (por ejemplo `ObservadorDeCola`) con un método `actualizar(paciente)`,
implementada por cada interesado. `ColaDeAtencion` mantiene una lista de `ObservadorDeCola`, agregada
con `agregarObservador(...)`, y `avanzar()` recorre esa lista llamando `actualizar(paciente)` sobre cada
elemento, sin conocer sus clases concretas. Un interesado nuevo se agrega registrándolo, sin modificar
`avanzar()`.

</details>

**20. [Selección]** **Pregunta:** ¿qué resuelve el patrón State?

- **A.** Que un objeto altere su comportamiento cuando cambia su estado interno, sin condicionales
  dispersos.
- **B.** Que un algoritmo se elija en tiempo de ejecución.
- **C.** Que una acción se pueda deshacer.
- **D.** Que varias clases compartan el mismo esqueleto de pasos.

_RA: RA-20_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: A.** State delega el comportamiento al objeto que representa el estado actual, en
vez de un condicional repetido sobre un campo de estado.

</details>

**21. [Selección múltiple]** **Pregunta:** una clase `Internacion` tiene un campo `estado: String` y dos
métodos que repiten, cada uno, `if (estado.equals("INGRESADA")) ... else if (estado.equals(...)) ...`.
¿Cuáles afirmaciones son verdaderas?

- **A.** El comportamiento válido depende de un campo de estado revisado con condicionales.
- **B.** Cualquier método nuevo que dependa del estado repetiría el mismo condicional.
- **C.** Una clase por estado, con una interfaz común, eliminaría esos condicionales.
- **D.** El problema desaparecería si `estado` fuera un `int` en vez de un `String`.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y C.** D es falsa: cambiar el tipo del campo no elimina el condicional
repetido, solo cambia qué se compara.

</details>

_RA: RA-21_

**22. [Abierta]** **Pregunta:** tienes la `Internacion` de la pregunta anterior, con sus condicionales.
Explica, en tus palabras, cómo aplicarías State para eliminarlos.

_RA: RA-22_

<details>
<summary>🔑 Ver respuesta</summary>

Se declara una interfaz común (por ejemplo `EstadoDeInternacion`) con un método por operación
(`registrarObservacion`, `darDeAlta`), implementada por una clase por cada estado posible (`Ingresada`,
`EnObservacion`, `DadaDeAlta`), cada una decidiendo si la transición es válida y, si corresponde,
cambiando el estado de `Internacion` a otro. `Internacion` guarda una referencia a su
`EstadoDeInternacion` actual y delega cada operación en él, sin ningún condicional propio.

</details>

**23. [Selección]** **Pregunta:** ¿qué resuelve el patrón Strategy?

- **A.** Que una clase tenga una única instancia.
- **B.** Que un condicional, dentro del código cliente, elija cuál de varios algoritmos aplicar.
- **C.** Que dos interfaces incompatibles se combinen.
- **D.** Que un objeto notifique a una lista de interesados.

_RA: RA-23_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Strategy encapsula una familia de algoritmos intercambiables detrás de una
interfaz común, eliminando el condicional que los elegía en el cliente.

</details>

**24. [Selección múltiple]** **Pregunta:** un `Turno.calcularTarifa()` resuelve el cálculo con
`if (modalidad.equals("PRESENCIAL")) ... else if (modalidad.equals("TELEMEDICINA")) ...`. ¿Cuáles
afirmaciones son verdaderas?

- **A.** Agregar una modalidad nueva exige agregar una rama más al condicional.
- **B.** El condicional decide, en tiempo de ejecución, cuál cálculo aplicar.
- **C.** Esto es exactamente el problema que Strategy resuelve.
- **D.** El código no compila mientras el condicional siga creciendo.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y C.** D es falsa: el código compila y funciona perfectamente con el
condicional — el problema es de mantenibilidad, no de compilación.

</details>

_RA: RA-24_

**25. [Abierta]** **Pregunta:** tienes el `Turno` de la pregunta anterior. Explica, en tus palabras, cómo
aplicarías Strategy para eliminar el condicional.

_RA: RA-25_

<details>
<summary>🔑 Ver respuesta</summary>

Se declara una interfaz (por ejemplo `EstrategiaDeTarifa`) con un método `calcular(montoBase)`, y una
implementación por modalidad (`TarifaPresencial`, `TarifaTelemedicina`). `Turno` recibe la estrategia
por composición (por ejemplo, en su constructor) y delega en ella, en vez de decidir con un condicional
cuál cálculo aplicar.

</details>

**26. [Selección]** **Pregunta:** ¿qué resuelve el patrón Template Method?

- **A.** Que un algoritmo se elija en tiempo de ejecución.
- **B.** Fijar el esqueleto de un algoritmo en un método final, delegando en subclases solo los pasos
  que varían.
- **C.** Que un sujeto notifique a una lista de interesados.
- **D.** Que una acción se pueda deshacer.

_RA: RA-26_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Template Method fija el esqueleto en un método `final`; las subclases
implementan solo los pasos declarados `abstract`, sin poder alterar el resto.

</details>

**27. [Selección múltiple]** **Pregunta:** dos clases (`ResumenDeAnalisis`, `ResumenDeImagen`) repiten,
cada una en su propio método `generar()`, el mismo esqueleto de cuatro pasos. Se crea una tercera clase
por copiado y pegado que omite uno de los pasos por error. ¿Cuáles afirmaciones son verdaderas?

- **A.** El código con el paso omitido compila y se ejecuta igual, solo que con una salida incompleta.
- **B.** Fijar el esqueleto en un método `final` de una clase abstracta común habría impedido esa
  omisión.
- **C.** El riesgo de omitir un paso crece con cada clase nueva creada por copiado y pegado.
- **D.** El compilador siempre detecta cuando un paso del esqueleto fue omitido.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y C.** D es falsa: omitir un paso al copiar y pegar un método no es un
error de sintaxis — el compilador no tiene forma de saber que ese paso "debía" estar.

</details>

_RA: RA-27_

**28. [Abierta]** **Pregunta:** tienes las dos clases de la pregunta anterior, con el esqueleto
copiado en cada una. Explica, en tus palabras, cómo aplicarías Template Method para que ninguna
subclase pueda omitir un paso.

_RA: RA-28_

<details>
<summary>🔑 Ver respuesta</summary>

Se declara una clase abstracta (por ejemplo `GeneradorDeResumen`) con un método `generar()` marcado
`final`, que ejecuta los cuatro pasos en un orden fijo, delegando en dos métodos abstractos
(`titulo()`, `obtenerDatoPropio()`) los pasos que varían según el tipo de estudio. Cada subclase
(`ResumenDeAnalisis`, `ResumenDeImagen`) implementa únicamente esos dos métodos — el resto del
esqueleto vive una sola vez, en la clase abstracta, y ninguna subclase puede saltearlo ni reordenarlo.

</details>

**29. [Abierta]** **Pregunta:** una biblioteca universitaria necesita coordinar la comunicación entre
tres módulos (catalogación, adquisiciones y difusión) que hoy se llaman directamente entre sí cada vez
que se incorpora un ejemplar nuevo, y ese acoplamiento crece cada vez que se agrega un módulo más.
Identifica cuál de los nueve patrones de comportamiento de este módulo
encaja mejor para este caso y justifica por qué los otros ocho no encajan igual de bien.

_RA: RA-29_

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

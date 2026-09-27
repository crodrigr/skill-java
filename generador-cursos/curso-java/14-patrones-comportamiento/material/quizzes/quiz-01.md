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

**2. [Selección]** **Pregunta:** ¿qué resuelve el patrón Strategy?

- **A.** Que una clase tenga una única instancia.
- **B.** Que un condicional, dentro del código cliente, elija cuál de varios algoritmos aplicar.
- **C.** Que dos interfaces incompatibles se combinen.
- **D.** Que un objeto notifique a una lista de interesados.

_RA: RA-2_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Strategy encapsula una familia de algoritmos intercambiables detrás de una
interfaz común, eliminando el condicional que los elegía en el cliente.

</details>

**3. [Selección múltiple]** **Pregunta:** un `Turno.calcularTarifa()` resuelve el cálculo con
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

_RA: RA-3_

**4. [Abierta]** **Pregunta:** tienes el `Turno` de la pregunta anterior. Explica, en tus palabras, cómo
aplicarías Strategy para eliminar el condicional.

_RA: RA-4_

<details>
<summary>🔑 Ver respuesta</summary>

Se declara una interfaz (por ejemplo `EstrategiaDeTarifa`) con un método `calcular(montoBase)`, y una
implementación por modalidad (`TarifaPresencial`, `TarifaTelemedicina`). `Turno` recibe la estrategia
por composición (por ejemplo, en su constructor) y delega en ella, en vez de decidir con un condicional
cuál cálculo aplicar.

</details>

**5. [Selección]** **Pregunta:** ¿qué resuelve el patrón Observer?

- **A.** Que un algoritmo varíe independientemente del cliente que lo usa.
- **B.** Que un sujeto notifique automáticamente a una lista de interesados registrados dinámicamente,
  sin conocer sus clases concretas.
- **C.** Que una acción se pueda deshacer.
- **D.** Que un objeto cambie su comportamiento según su estado interno.

_RA: RA-5_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Observer define una dependencia uno-a-muchos: cuando el sujeto cambia, todos
sus observadores se notifican, sin que el sujeto conozca sus clases concretas.

</details>

**6. [Selección múltiple]** **Pregunta:** una `ColaDeAtencion.avanzar()` llama directamente, dentro de
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

_RA: RA-6_

**7. [Abierta]** **Pregunta:** tienes la `ColaDeAtencion` de la pregunta anterior. Explica, en tus
palabras, cómo aplicarías Observer para agregar un interesado nuevo sin modificar `avanzar()`.

_RA: RA-7_

<details>
<summary>🔑 Ver respuesta</summary>

Se declara una interfaz común (por ejemplo `ObservadorDeCola`) con un método `actualizar(paciente)`,
implementada por cada interesado. `ColaDeAtencion` mantiene una lista de `ObservadorDeCola`, agregada
con `agregarObservador(...)`, y `avanzar()` recorre esa lista llamando `actualizar(paciente)` sobre cada
elemento, sin conocer sus clases concretas. Un interesado nuevo se agrega registrándolo, sin modificar
`avanzar()`.

</details>

**8. [Selección]** **Pregunta:** ¿qué resuelve el patrón Command?

- **A.** Que un objeto altere su comportamiento según su estado interno.
- **B.** Encapsular una solicitud como un objeto, permitiendo parametrizarla, registrarla o deshacerla.
- **C.** Que varias clases compartan el mismo esqueleto de pasos.
- **D.** Que un sujeto notifique a una lista de interesados.

_RA: RA-8_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Command convierte una acción en un objeto propio, con `ejecutar()`/
`deshacer()`, en vez de una llamada directa al receptor.

</details>

**9. [Selección múltiple]** **Pregunta:** un código cliente llama directamente
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

_RA: RA-9_

**10. [Abierta]** **Pregunta:** tienes el código cliente de la pregunta anterior. Explica, en tus
palabras, cómo aplicarías Command para poder deshacer la última acción.

_RA: RA-10_

<details>
<summary>🔑 Ver respuesta</summary>

Se declara una interfaz (por ejemplo `AccionDeConsultorio`) con `ejecutar()`/`deshacer()`, y una clase
por acción (`AccionOcuparConsultorio`) que guarda, antes de ejecutar, el dato necesario para revertirse
(el médico anterior). Un `HistorialDeAcciones` ejecuta la acción y la guarda en una lista; su método
`deshacerUltima()` toma la última acción guardada y llama a su `deshacer()`, revirtiendo el estado
exacto anterior sin que el cliente conozca la operación contraria de cada acción.

</details>

**11. [Selección]** **Pregunta:** ¿qué resuelve el patrón State?

- **A.** Que un objeto altere su comportamiento cuando cambia su estado interno, sin condicionales
  dispersos.
- **B.** Que un algoritmo se elija en tiempo de ejecución.
- **C.** Que una acción se pueda deshacer.
- **D.** Que varias clases compartan el mismo esqueleto de pasos.

_RA: RA-11_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: A.** State delega el comportamiento al objeto que representa el estado actual, en
vez de un condicional repetido sobre un campo de estado.

</details>

**12. [Selección múltiple]** **Pregunta:** una clase `Internacion` tiene un campo `estado: String` y dos
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

_RA: RA-12_

**13. [Abierta]** **Pregunta:** tienes la `Internacion` de la pregunta anterior, con sus condicionales.
Explica, en tus palabras, cómo aplicarías State para eliminarlos.

_RA: RA-13_

<details>
<summary>🔑 Ver respuesta</summary>

Se declara una interfaz común (por ejemplo `EstadoDeInternacion`) con un método por operación
(`registrarObservacion`, `darDeAlta`), implementada por una clase por cada estado posible (`Ingresada`,
`EnObservacion`, `DadaDeAlta`), cada una decidiendo si la transición es válida y, si corresponde,
cambiando el estado de `Internacion` a otro. `Internacion` guarda una referencia a su
`EstadoDeInternacion` actual y delega cada operación en él, sin ningún condicional propio.

</details>

**14. [Selección]** **Pregunta:** ¿qué resuelve el patrón Template Method?

- **A.** Que un algoritmo se elija en tiempo de ejecución.
- **B.** Fijar el esqueleto de un algoritmo en un método final, delegando en subclases solo los pasos
  que varían.
- **C.** Que un sujeto notifique a una lista de interesados.
- **D.** Que una acción se pueda deshacer.

_RA: RA-14_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Template Method fija el esqueleto en un método `final`; las subclases
implementan solo los pasos declarados `abstract`, sin poder alterar el resto.

</details>

**15. [Selección múltiple]** **Pregunta:** dos clases (`ResumenDeAnalisis`, `ResumenDeImagen`) repiten,
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

_RA: RA-15_

**16. [Abierta]** **Pregunta:** tienes las dos clases de la pregunta anterior, con el esqueleto
copiado en cada una. Explica, en tus palabras, cómo aplicarías Template Method para que ninguna
subclase pueda omitir un paso.

_RA: RA-16_

<details>
<summary>🔑 Ver respuesta</summary>

Se declara una clase abstracta (por ejemplo `GeneradorDeResumen`) con un método `generar()` marcado
`final`, que ejecuta los cuatro pasos en un orden fijo, delegando en dos métodos abstractos
(`titulo()`, `obtenerDatoPropio()`) los pasos que varían según el tipo de estudio. Cada subclase
(`ResumenDeAnalisis`, `ResumenDeImagen`) implementa únicamente esos dos métodos — el resto del
esqueleto vive una sola vez, en la clase abstracta, y ninguna subclase puede saltearlo ni reordenarlo.

</details>

**17. [Abierta]** **Pregunta:** una biblioteca universitaria necesita controlar el ciclo de vida de un
préstamo interbibliotecario, cuyo estado (solicitado, en tránsito, entregado, devuelto) determina qué
operaciones son válidas en cada momento, y agregar un estado nuevo en el futuro no debería exigir
revisar condicionales repartidos en varios métodos. Identifica cuál de los cinco patrones de
comportamiento encaja mejor para este caso y justifica por qué los otros cuatro no encajan igual de
bien.

_RA: RA-17_

<details>
<summary>🔑 Ver respuesta</summary>

State encaja mejor: el comportamiento válido depende enteramente del estado interno del préstamo, y el
requisito de agregar estados nuevos sin condicionales dispersos es exactamente el problema que State
resuelve. Strategy no aplica porque no hay un algoritmo intercambiable elegido por el cliente, sino un
comportamiento que cambia con el tiempo por sí solo. Observer no aplica porque no hay una lista de
interesados que deba notificarse ante un cambio. Command no aplica porque el problema no es encapsular
una acción para poder deshacerla o registrarla, sino organizar el comportamiento según el estado.
Template Method no aplica porque no hay un esqueleto de pasos compartido entre variantes, sino
comportamiento que varía según el estado de una única clase.

</details>

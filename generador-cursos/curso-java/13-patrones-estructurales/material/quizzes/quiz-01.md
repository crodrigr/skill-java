# ❓ Quiz 01 — Patrones de Diseño Estructurales (formato entrevista técnica)

Este quiz simula las preguntas que podrías recibir en una entrevista técnica para un puesto de
programador Java junior. Cada pregunta indica su tipo (**Selección**, **Selección múltiple** o
**Abierta**). Respondela primero por tu cuenta y después abre "Ver respuesta" para comparar.

---

**1. [Selección]** **Pregunta:** ¿qué es un patrón estructural y en qué se distingue de las otras dos
categorías del catálogo GoF?

- **A.** Un patrón estructural controla cómo se crean los objetos; los otros dos no.
- **B.** Un patrón estructural resuelve cómo se componen clases y objetos para formar estructuras más
  grandes, a diferencia de los creacionales (creación) y los de comportamiento (comunicación).
- **C.** Un patrón estructural es cualquier patrón que use interfaces.
- **D.** No hay ninguna diferencia real entre las tres categorías.

_RA: RA-1_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Los patrones estructurales resuelven problemas de composición de clases y
objetos; los creacionales resuelven problemas de creación; los de comportamiento, de comunicación y
reparto de responsabilidades.

</details>

**2. [Selección]** **Pregunta:** ¿qué resuelve el patrón Adapter?

- **A.** Que una clase tenga una única instancia.
- **B.** Que el código cliente traduzca manualmente entre dos interfaces incompatibles, repitiendo esa
  traducción en varios lugares.
- **C.** Que un objeto se pueda clonar.
- **D.** Que una interfaz tenga demasiados métodos.

_RA: RA-2_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Adapter convierte la interfaz de una clase existente en otra que el cliente
espera, sin modificarla, centralizando la traducción en un solo lugar.

</details>

**3. [Selección múltiple]** **Pregunta:** un `RegistroFacturacion` arma a mano, en dos métodos
distintos, el formato de texto que espera `FacturadorLegado.procesarPago(String)`. ¿Cuáles afirmaciones
son verdaderas?

- **A.** La lógica de traducción entre formatos está duplicada en el código cliente.
- **B.** Si el formato del facturador legado cambia, hay que modificar los dos métodos.
- **C.** Esto es exactamente el problema que Adapter resuelve.
- **D.** El código no compila mientras la traducción esté duplicada.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y C.** D es falsa: el código compila y funciona perfectamente con la
traducción duplicada — el problema es de mantenibilidad, no de compilación.

</details>

_RA: RA-3_

**4. [Abierta]** **Pregunta:** tienes el `RegistroFacturacion` de la pregunta anterior. Explica, en tus
palabras, cómo aplicarías Adapter para que la traducción viva en un solo lugar.

_RA: RA-4_

<details>
<summary>🔑 Ver respuesta</summary>

Se declara una interfaz (por ejemplo `IFacturador`) con el método que el cliente espera, y una clase
`FacturadorAdapter implements IFacturador` que envuelve una instancia de `FacturadorLegado` y hace la
traducción una sola vez, dentro de sí misma. `RegistroFacturacion` pasa a depender de `IFacturador`, no
de `FacturadorLegado` directamente, así que la traducción deja de repetirse en cada método.

</details>

**5. [Selección]** **Pregunta:** ¿qué resuelve el patrón Bridge?

- **A.** Que un subsistema complejo se acceda con una sola operación.
- **B.** Que dos dimensiones de variación mezcladas en una jerarquía produzcan una explosión
  combinatoria de subclases.
- **C.** Que un objeto se cree de forma diferida.
- **D.** Que una interfaz tenga menos métodos.

_RA: RA-5_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Bridge separa una abstracción de su implementación, cada una en su propia
jerarquía, combinadas por composición en vez de mezclarlas en una sola jerarquía de herencia.

</details>

**6. [Selección múltiple]** **Pregunta:** un sistema de notificaciones tiene una subclase por cada
combinación de tipo (urgente, rutinaria) y canal (email, SMS): 4 clases. ¿Cuáles afirmaciones son
verdaderas sobre agregar un canal nuevo (push)?

- **A.** Hacen falta dos clases nuevas, una por cada tipo existente.
- **B.** La cantidad de clases nuevas necesarias crece con cada dimensión que se mezcle en la misma
  jerarquía.
- **C.** Separando el tipo y el canal en dos jerarquías independientes, agregar el canal nuevo exigiría
  solo una clase.
- **D.** El problema desaparecería usando una sola clase con dos parámetros `String`.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y C.** D es falsa: reemplazar las subclases por parámetros `String` pierde
el polimorfismo y no resuelve el problema de diseño, solo lo esconde.

</details>

_RA: RA-6_

**7. [Abierta]** **Pregunta:** tienes el sistema de notificaciones de la pregunta anterior. Explica, en
tus palabras, cómo diseñarías una solución con Bridge que reduzca a una sola clase el costo de agregar
un canal nuevo.

_RA: RA-7_

<details>
<summary>🔑 Ver respuesta</summary>

Se separan las dos dimensiones en dos jerarquías independientes: `Notificacion` (abstracción, con
subclases `Urgente` y `Rutinaria`) y `CanalEnvio` (implementación, con subclases `Email` y `Sms`).
`Notificacion` recibe un `CanalEnvio` por composición (por ejemplo, en su constructor), en vez de que
cada combinación sea una subclase. Agregar un canal nuevo (push) exige solo una clase nueva
(`CanalPush`), que funciona automáticamente con ambos tipos de notificación existentes.

</details>

**8. [Selección]** **Pregunta:** ¿qué resuelve el patrón Composite?

- **A.** Que el cliente trate de forma uniforme un objeto individual y una composición de objetos.
- **B.** Que una clase tenga una única instancia.
- **C.** Que un objeto costoso se cargue de forma diferida.
- **D.** Que dos interfaces incompatibles se puedan combinar.

_RA: RA-8_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: A.** Composite compone objetos en una estructura de árbol para representar
jerarquías "parte-todo", permitiendo tratar hojas y compuestos con la misma interfaz.

</details>

**9. [Selección múltiple]** **Pregunta:** un método `contarPersonal()` recorre una lista y usa
`if (miembro instanceof Departamento) ... else if (miembro instanceof Colaborador) ...`. ¿Cuáles
afirmaciones son verdaderas?

- **A.** El código distingue con condicionales entre un objeto individual y uno compuesto.
- **B.** Cualquier operación nueva sobre la misma estructura repetiría el mismo patrón de `instanceof`.
- **C.** Una interfaz común implementada por ambos tipos eliminaría la necesidad de `instanceof`.
- **D.** El código no puede manejar más de un nivel de anidamiento.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y C.** D es falsa: si la recursión ya está prevista (llamando a
`contarPersonal()` sobre cada `Departamento` anidado), maneja cualquier nivel de anidamiento — el
problema no es la profundidad, es el `instanceof` repetido en cada operación nueva.

</details>

_RA: RA-9_

**10. [Abierta]** **Pregunta:** tienes el método `contarPersonal()` de la pregunta anterior, con sus
`instanceof`. Explica, en tus palabras, cómo aplicarías Composite para eliminarlos.

_RA: RA-10_

<details>
<summary>🔑 Ver respuesta</summary>

Se declara una interfaz común (por ejemplo `UnidadOrganizacional`) con un método `contarPersonal()`, que
tanto `Colaborador` (devuelve 1) como `Departamento` (recorre su lista y suma) implementan. `Departamento`
guarda una lista de `UnidadOrganizacional`, no de `Object`, así que puede llamar
`miembro.contarPersonal()` sobre cualquier elemento sin necesitar saber si es una hoja o un compuesto —
el polimorfismo hace el trabajo que antes hacía el `instanceof`.

</details>

**11. [Selección]** **Pregunta:** ¿qué resuelve el patrón Decorator?

- **A.** Que una clase tenga una única instancia.
- **B.** Agregar responsabilidades a un objeto de forma dinámica, como alternativa a la herencia para
  combinar extras opcionales.
- **C.** Que dos interfaces incompatibles se combinen.
- **D.** Que un subsistema complejo se simplifique.

_RA: RA-11_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Decorator envuelve un objeto para agregarle responsabilidades en tiempo de
ejecución, evitando una subclase por cada combinación posible de extras.

</details>

**12. [Selección múltiple]** **Pregunta:** una consulta médica tiene las subclases
`ConsultaConAnalisis`, `ConsultaConImagen` y `ConsultaConAnalisisEImagen`. ¿Cuáles afirmaciones son
verdaderas sobre agregar un tercer extra opcional (receta)?

- **A.** Hacen falta subclases nuevas para cubrir todas las combinaciones que incluyan el extra nuevo.
- **B.** La cantidad de subclases crece combinatoriamente con cada extra opcional nuevo.
- **C.** Envolviendo decoradores en vez de heredar, agregar el extra nuevo no exigiría ninguna clase de
  combinación.
- **D.** El problema no existiría si cada extra costara lo mismo.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y C.** D es falsa: el problema es la cantidad de subclases necesarias para
cubrir las combinaciones, no el costo de cada extra.

</details>

_RA: RA-12_

**13. [Abierta]** **Pregunta:** tienes las subclases de combinación de la pregunta anterior. Explica, en
tus palabras, cómo aplicarías Decorator para combinar los mismos extras sin ninguna clase de
combinación.

_RA: RA-13_

<details>
<summary>🔑 Ver respuesta</summary>

Se declara una clase base `ConsultaDecorada` que envuelve una `Consulta` y delega en ella, y una
subclase por extra (`ConAnalisis`, `ConImagen`), cada una sumando su propio costo al de la consulta que
envuelve. Para combinar extras, se envuelve un decorador dentro de otro (por ejemplo,
`new ConImagen(new ConAnalisis(new Consulta()))`), sin necesitar ninguna clase que represente esa
combinación en particular.

</details>

**14. [Selección]** **Pregunta:** ¿qué resuelve el patrón Facade?

- **A.** Que un objeto se pueda clonar.
- **B.** Una interfaz única y simplificada sobre un conjunto de interfaces de un subsistema complejo.
- **C.** Que dos dimensiones de variación se separen.
- **D.** Que un objeto costoso se cree de forma diferida.

_RA: RA-14_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Facade oculta la complejidad de orquestar varios subsistemas detrás de una
única operación de alto nivel.

</details>

**15. [Selección múltiple]** **Pregunta:** un código cliente llama, en orden, a
`VerificadorSeguro.verificar(...)`, `GestorCamas.reservar(...)`, `NotificadorPersonal.avisar(...)` y
`GeneradorHistoriaClinica.generar(...)` para admitir un paciente. ¿Cuáles afirmaciones son verdaderas?

- **A.** El cliente conoce y depende directamente de los cuatro subsistemas.
- **B.** Si el orden correcto de llamadas cambia, hay que modificar el código cliente.
- **C.** Una fachada que exponga un único método `admitirPaciente(...)` reduciría a uno la cantidad de
  clases que el cliente conoce.
- **D.** Los cuatro subsistemas dejarían de existir si se usa Facade.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y C.** D es falsa: Facade no elimina los subsistemas, los sigue usando
internamente — solo oculta esa orquestación detrás de una interfaz simple.

</details>

_RA: RA-15_

**16. [Abierta]** **Pregunta:** tienes el código cliente de la pregunta anterior. Explica, en tus
palabras, cómo diseñarías una fachada que reduzca a un único método la admisión de un paciente.

_RA: RA-16_

<details>
<summary>🔑 Ver respuesta</summary>

Se declara una clase `AdmisionFacade` que crea internamente instancias de los cuatro subsistemas
(`VerificadorSeguro`, `GestorCamas`, `NotificadorPersonal`, `GeneradorHistoriaClinica`) y expone un único
método `admitirPaciente(paciente)` que los llama en el orden correcto. El código cliente pasa a llamar
solo a `admisionFacade.admitirPaciente(paciente)`, sin conocer ni depender de ninguno de los cuatro
subsistemas por separado.

</details>

**17. [Selección]** **Pregunta:** ¿qué resuelve el patrón Flyweight?

- **A.** Que un objeto costoso se acceda con control intermedio.
- **B.** Compartir estado intrínseco (idéntico) entre muchos objetos de grano fino, para reducir el
  costo de memoria.
- **C.** Que dos interfaces incompatibles se combinen.
- **D.** Que una jerarquía de herencia se simplifique.

_RA: RA-17_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Flyweight comparte una única instancia del estado idéntico entre muchos
objetos, en vez de que cada uno cree su propia copia.

</details>

**18. [Selección múltiple]** **Pregunta:** una clase `Cama` crea internamente un nuevo `TipoDeSala` (con
su ícono y color) por cada instancia. ¿Cuáles afirmaciones son verdaderas al crear varias camas de la
misma sala?

- **A.** Cada `Cama` tiene su propia instancia de `TipoDeSala`, aunque los datos sean idénticos.
- **B.** Comparando `cama1.getTipoDeSala() == cama2.getTipoDeSala()`, el resultado es `false`.
- **C.** Una fábrica que devuelva siempre la misma instancia para el mismo nombre de sala haría que esa
  comparación diera `true`.
- **D.** El desperdicio de memoria no importa si las camas son pocas.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y C.** D es una afirmación de criterio, no del mecanismo: el patrón
funciona igual sin importar la cantidad, aunque su beneficio se nota más cuantos más objetos casi
idénticos existan.

</details>

_RA: RA-18_

**19. [Abierta]** **Pregunta:** tienes la clase `Cama` de la pregunta anterior. Explica, en tus palabras,
cómo aplicarías Flyweight para que todas las camas de la misma sala compartan la misma instancia de
`TipoDeSala`.

_RA: RA-19_

<details>
<summary>🔑 Ver respuesta</summary>

Se declara una `FabricaDeTiposDeSala` con un método estático `obtener(nombreSala, icono)` que mantiene
una caché (por ejemplo, un `Map<String, TipoDeSala>`): si ya existe una instancia para ese nombre de
sala, la devuelve; si no, la crea una vez y la guarda. `Cama` deja de crear su propio `TipoDeSala` con
`new` y en su lugar llama a `FabricaDeTiposDeSala.obtener(...)`, de forma que todas las camas de la misma
sala terminan compartiendo la misma instancia.

</details>

**20. [Selección]** **Pregunta:** ¿qué es el patrón Proxy?

- **A.** Una clase que reemplaza por completo a otra, eliminándola del diseño.
- **B.** Un sustituto que controla el acceso a otro objeto (carga diferida, control de acceso, o
  registro).
- **C.** Una forma de combinar dos interfaces incompatibles.
- **D.** Una forma de compartir estado entre muchos objetos.

_RA: RA-20_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Proxy implementa la misma interfaz que el objeto real y controla el acceso a
él, delegando solo cuando corresponde.

</details>

**21. [Selección múltiple]** **Pregunta:** un código crea una `ExpedienteClinicoReal` (cuya construcción
simula ser costosa) al abrir la ficha de un paciente, aunque no siempre se llega a consultar la
historia. ¿Cuáles afirmaciones son verdaderas?

- **A.** El costo de crear la historia se paga aunque nunca se consulte.
- **B.** Un proxy que implemente la misma interfaz podría retrasar esa creación hasta el primer acceso
  real.
- **C.** Con el proxy, si nunca se consulta la historia, el costo de crearla nunca se paga.
- **D.** El proxy elimina la necesidad de que `ExpedienteClinicoReal` exista.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y C.** D es falsa: el proxy no elimina la clase real, solo retrasa (o
controla) cuándo se crea o se usa.

</details>

_RA: RA-21_

**22. [Abierta]** **Pregunta:** tienes el código de la pregunta anterior, que crea la historia real de
inmediato. Explica, en tus palabras, cómo aplicarías Proxy para retrasar esa creación hasta el primer
acceso real.

_RA: RA-22_

<details>
<summary>🔑 Ver respuesta</summary>

Se declara una interfaz común (`ExpedienteClinico`) que implementan tanto `ExpedienteClinicoReal` como una
nueva clase `ExpedienteClinicoProxy`. El proxy guarda los datos necesarios (por ejemplo, el nombre del
paciente) pero no crea la instancia real en su constructor; recién en su método `consultar()`, si
todavía no existe, crea la `ExpedienteClinicoReal` y le delega la llamada. Así, si nunca se llama a
`consultar()`, el costo de crear la historia real nunca se paga.

</details>

**23. [Abierta]** **Pregunta:** una biblioteca universitaria necesita mostrar, en su catálogo digital,
miles de miniaturas de portadas de libros, donde cada género literario (novela, ensayo, poesía) tiene su
propio icono y color de fondo, repetidos exactamente igual para todos los libros de ese género.
Identifica cuál de los siete patrones estructurales encaja mejor para este caso y justifica por qué los
otros seis no encajan igual de bien.

_RA: RA-23_

<details>
<summary>🔑 Ver respuesta</summary>

Flyweight encaja mejor: miles de libros comparten el mismo icono y color por género, exactamente el
problema que Flyweight resuelve (estado intrínseco idéntico compartido entre muchos objetos de grano
fino). Adapter no aplica porque no hay ninguna interfaz incompatible que traducir. Bridge no aplica
porque no hay dos dimensiones de variación independientes mezcladas en una jerarquía. Composite no
aplica porque no hay una estructura de árbol parte-todo. Decorator no aplica porque no se están
combinando extras opcionales sobre un objeto. Facade no aplica porque no hay varios subsistemas que
orquestar. Proxy no aplica porque el problema no es de control de acceso ni de carga diferida, sino de
compartir estado repetido.

</details>

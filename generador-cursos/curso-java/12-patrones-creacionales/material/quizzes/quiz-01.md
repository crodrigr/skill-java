# ❓ Quiz 01 — Patrones de Diseño Creacionales (formato entrevista técnica)

Este quiz simula las preguntas que podrías recibir en una entrevista técnica para un puesto de
programador Java junior. Cada pregunta indica su tipo (**Selección**, **Selección múltiple** o
**Abierta**). Respondela primero por tu cuenta y después abre "Ver respuesta" para comparar.

---

**1. [Selección]** **Pregunta:** ¿qué es un patrón de diseño y qué distingue a los patrones
creacionales de las otras dos categorías del catálogo GoF?

- **A.** Un patrón de diseño es una librería externa; los creacionales son los que requieren instalar
  una dependencia.
- **B.** Un patrón de diseño es una solución reutilizable y con nombre propio a un problema de diseño
  recurrente; los creacionales son los que controlan cómo se crean los objetos.
- **C.** Un patrón de diseño es una regla obligatoria del lenguaje Java; los creacionales son los que
  usan la palabra clave `new`.
- **D.** No hay ninguna diferencia real entre las tres categorías del catálogo GoF.

_RA: RA-1_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Un patrón de diseño es una solución reutilizable y ya probada, con nombre
propio, a un problema de diseño recurrente. Los patrones creacionales son específicamente los que
controlan cómo se crean los objetos, a diferencia de los estructurales (cómo se componen) y los de
comportamiento (cómo se comunican).

</details>

**2. [Selección]** **Pregunta:** ¿qué problema resuelve el patrón Factory Method?

- **A.** Que una clase tenga más de un constructor.
- **B.** Que el código cliente esté acoplado a clases concretas mediante `new`, repetido en varios
  lugares, difícil de extender con un tipo nuevo.
- **C.** Que una interfaz tenga demasiados métodos.
- **D.** Que dos objetos no puedan compararse entre sí.

_RA: RA-2_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Factory Method delega la creación de un objeto a un método dedicado, para que
el código cliente no dependa directamente de las clases concretas ni tenga que modificarse para admitir
un tipo nuevo.

</details>

**3. [Selección múltiple]** **Pregunta:** una clase `RegistroClinico` tiene dos métodos,
`abrirHistorialConsulta` y `abrirHistorialInternacion`, y ambos hacen `new HistorialPapel()`
directamente. ¿Cuáles afirmaciones son verdaderas?

- **A.** Agregar un tipo de historial nuevo (por ejemplo, `HistorialDigital`) exige modificar ambos
  métodos de `RegistroClinico`.
- **B.** `RegistroClinico` está acoplado a la clase concreta `HistorialPapel`.
- **C.** Esto es exactamente el problema que Factory Method resuelve.
- **D.** No hay ningún problema, porque `RegistroClinico` compila y funciona correctamente.

_RA: RA-3_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y C.** D es falsa: que el código compile y funcione hoy no significa que el
diseño no tenga un problema — el problema aparece recién cuando hay que extenderlo, y ahí el costo se
paga.

</details>

**4. [Abierta]** **Pregunta:** tienes la clase `RegistroClinico` de la pregunta anterior, que crea
`HistorialPapel` con `new` en dos métodos distintos. Explica, en tus palabras, cómo aplicarías Factory
Method para que agregar un tipo de historial nuevo no exija modificar `RegistroClinico`.

_RA: RA-4_

<details>
<summary>🔑 Ver respuesta</summary>

Se declara una clase (por ejemplo `FabricaDeHistoriales`) con un método estático `crear(String tipo)`
que decide, según el tipo recibido, qué clase concreta instanciar (`HistorialPapel`, y más adelante
`HistorialDigital`), devolviendo siempre el tipo común `Historial`. Los dos métodos de `RegistroClinico`
dejan de invocar `new` directamente y en su lugar llaman a `FabricaDeHistoriales.crear(tipo)`. Para
agregar un tipo nuevo, alcanza con agregar una rama dentro de la fábrica: `RegistroClinico` no necesita
modificarse.

</details>

**5. [Selección]** **Pregunta:** ¿qué resuelve el patrón Abstract Factory que Factory Method, por sí
solo, no resuelve?

- **A.** Que un único objeto se cree más rápido.
- **B.** Que una familia completa de objetos relacionados se cree de forma consistente, sin mezclar
  variantes por error.
- **C.** Que una clase tenga una única instancia.
- **D.** Que un objeto se pueda clonar.

_RA: RA-5_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Factory Method crea un objeto a la vez; Abstract Factory agrupa varios
métodos de creación relacionados en una interfaz, para garantizar que todos los objetos de una familia
sean compatibles entre sí (por ejemplo, todos de la misma línea de producto).

</details>

**6. [Selección múltiple]** **Pregunta:** un `ArmadorDeCombos` crea un `Guante` y una `Mascarilla` por
separado, cada uno con un parámetro de línea (`"ESTANDAR"`/`"PREMIUM"`) independiente. ¿Cuáles
afirmaciones son verdaderas?

- **A.** Es posible, por error, armar un combo con un guante `PREMIUM` y una mascarilla `ESTANDAR`.
- **B.** Este es un caso donde Abstract Factory encaja mejor que Factory Method, porque el problema es
  sobre una familia de objetos, no sobre uno solo.
- **C.** Una `FabricaDeInsumos` con un método por insumo, implementada una vez por línea, resolvería el
  problema.
- **D.** El problema desaparecería usando Factory Method para el guante y otro Factory Method
  independiente para la mascarilla.

_RA: RA-6_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y C.** D es falsa: dos métodos fábrica independientes, uno por insumo, no
garantizan que ambos se invoquen con la misma línea — el problema de consistencia entre los dos objetos
seguiría existiendo.

</details>

**7. [Abierta]** **Pregunta:** tienes el `ArmadorDeCombos` de la pregunta anterior, que puede mezclar
líneas por error. Explica, en tus palabras, cómo diseñarías una solución con Abstract Factory que lo
evite.

_RA: RA-7_

<details>
<summary>🔑 Ver respuesta</summary>

Se declara una interfaz `FabricaDeInsumos` con un método por insumo (`crearGuante()`,
`crearMascarilla()`), y una implementación concreta por línea (`FabricaEstandar`, `FabricaPremium`), cada
una devolviendo siempre insumos de su propia línea. `ArmadorDeCombos` recibe una única instancia de
`FabricaDeInsumos` y le pide todos los insumos del combo a esa misma instancia. Como ambos insumos salen
de la misma fábrica concreta, es imposible mezclar líneas por error.

</details>

**8. [Selección]** **Pregunta:** ¿qué resuelve el patrón Builder?

- **A.** Que una clase tenga una única instancia.
- **B.** Construir un objeto complejo paso a paso, separando el proceso de construcción de su
  representación final, evitando un constructor con demasiados parámetros.
- **C.** Que dos objetos relacionados se creen de forma consistente.
- **D.** Que un objeto se pueda copiar en vez de construirse desde cero.

_RA: RA-8_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Builder resuelve el problema de construir un objeto con muchos atributos
(varios opcionales) sin necesitar un constructor de muchos parámetros ni una explosión de constructores
sobrecargados.

</details>

**9. [Selección múltiple]** **Pregunta:** una clase `FichaPaciente` tiene un constructor con seis
parámetros, dos de ellos `String` consecutivos de significado distinto (`alergias`,
`contactoEmergencia`). ¿Cuáles afirmaciones son verdaderas?

- **A.** Es posible invocar el constructor pasando el contacto de emergencia en el lugar de las
  alergias, y el compilador no lo detecta.
- **B.** Ese error de orden produce datos incorrectos en tiempo de ejecución, sin ninguna excepción.
- **C.** Cuantos más parámetros opcionales tiene el constructor, más fácil es cometer un error de este
  tipo.
- **D.** Java impide, por diseño del lenguaje, invocar un constructor con los argumentos en el orden
  equivocado.

_RA: RA-9_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y C.** D es falsa: Java no valida el orden semántico de los argumentos,
solo su tipo — dos parámetros `String` consecutivos son indistinguibles para el compilador aunque
signifiquen cosas distintas.

</details>

**10. [Abierta]** **Pregunta:** tienes la clase `FichaPaciente` de la pregunta anterior, con su
constructor de seis parámetros. Explica, en tus palabras, cómo aplicarías Builder para eliminar el
riesgo de confundir el orden de los datos opcionales.

_RA: RA-10_

<details>
<summary>🔑 Ver respuesta</summary>

Se declara una clase `FichaPacienteBuilder` con un método por cada dato opcional (`conObraSocial(...)`,
`conAlergias(...)`, `conContactoEmergencia(...)`, `conObservaciones(...)`), cada uno devolviendo el
propio builder para poder encadenar llamadas, y un método final `construir()` que arma y devuelve la
`FichaPaciente` con los datos acumulados. Cada dato se identifica por el nombre del método que lo agrega,
no por su posición, así que ya no hay forma de confundir alergias con contacto de emergencia.

</details>

**11. [Selección]** **Pregunta:** ¿qué resuelve el patrón Prototype?

- **A.** Que una interfaz tenga menos métodos.
- **B.** Crear un objeto nuevo copiando (clonando) una instancia existente, en vez de construirlo desde
  cero.
- **C.** Que una clase tenga una única instancia.
- **D.** Que el código cliente no conozca las clases concretas de una familia de objetos.

_RA: RA-11_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Prototype resuelve el costo de reconstruir un objeto casi idéntico a uno
existente, repitiendo cada paso de configuración, clonando el objeto existente en su lugar.

</details>

**12. [Selección múltiple]** **Pregunta:** un `PlanDeTratamiento` ya tiene varios medicamentos
configurados. ¿Cuáles afirmaciones son verdaderas sobre cuándo conviene usar Prototype en vez de
construir un plan nuevo con `new`?

- **A.** Conviene cuando el plan nuevo es casi idéntico al existente, y reconstruirlo desde cero
  repetiría la mayoría de la configuración.
- **B.** Conviene siempre, sin importar qué tan distinto sea el objeto nuevo del existente.
- **C.** El clonado ahorra el riesgo de omitir un paso de configuración al reconstruir manualmente.
- **D.** Prototype nunca es una alternativa razonable si el objeto tiene una lista de datos internos.

_RA: RA-12_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A y C.** B es falsa: si el objeto nuevo es muy distinto del existente, clonar y
después deshacer la mayoría de la configuración no ahorra nada. D es falsa: Prototype funciona bien con
listas de datos internos, siempre que el clonado sea profundo (copie esa lista, no solo la referencia).

</details>

**13. [Abierta]** **Pregunta:** un `PlanDeTratamiento.clonar()` implementado como
`return new PlanDeTratamiento(nuevoPaciente, this.medicamentos);` reutiliza la misma lista de
medicamentos del plan original. Explica, en tus palabras, qué problema real causa eso y cómo se corrige.

_RA: RA-13_

<details>
<summary>🔑 Ver respuesta</summary>

Como la copia y el original comparten la misma referencia a la lista de medicamentos, agregar un
medicamento a la copia también lo agrega al plan original — una fuga de datos real, sin ninguna
excepción, porque el programa compila y se ejecuta con éxito. Se corrige con un clonado profundo:
`return new PlanDeTratamiento(nuevoPaciente, new ArrayList<>(this.medicamentos));`, creando una lista
nueva e independiente para la copia, de forma que modificar una no afecte a la otra.

</details>

**14. [Selección]** **Pregunta:** ¿qué garantiza el patrón Singleton?

- **A.** Que una clase tenga como máximo diez instancias.
- **B.** Que una clase tenga una única instancia en toda la aplicación, con un punto de acceso global a
  ella.
- **C.** Que dos clases distintas compartan el mismo constructor.
- **D.** Que un objeto se pueda clonar sin perder su estado.

_RA: RA-14_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Singleton garantiza que exista una única instancia de una clase, accesible
desde cualquier parte del programa a través de un único punto de acceso.

</details>

**15. [Selección múltiple]** **Pregunta:** ¿cuáles de las siguientes afirmaciones sobre los riesgos de
Singleton son verdaderas?

- **A.** Usarlo para reemplazar toda dependencia entre clases acopla el código a un estado global
  oculto.
- **B.** Un Singleton usado en exceso puede ser más difícil de probar, porque su estado persiste entre
  distintas partes del programa.
- **C.** Singleton nunca está justificado, en ningún caso.
- **D.** El riesgo de Singleton es específico de Java y no existe en otros lenguajes orientados a
  objetos.

_RA: RA-15_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A y B.** C es falsa: Singleton está justificado cuando de verdad debe existir
una única instancia compartida (por ejemplo, un registro de auditoría central). D es falsa: el riesgo de
acoplar código a un estado global es un problema de diseño orientado a objetos en general, no específico
de Java.

</details>

**16. [Abierta]** **Pregunta:** una clase `RegistroDeAuditoria` tiene un constructor público, y dos
módulos distintos del sistema crean cada uno su propia instancia. Explica, en tus palabras, cómo la
convertirías en un Singleton correcto (sin necesitar manejo de hilos, porque el curso no lo cubre).

_RA: RA-16_

<details>
<summary>🔑 Ver respuesta</summary>

Se declara el constructor de `RegistroDeAuditoria` como `private`, para que nadie fuera de la propia
clase pueda crear instancias con `new`. Se agrega un campo `private static final RegistroDeAuditoria
instancia = new RegistroDeAuditoria();` que se inicializa una sola vez, al cargar la clase, y un método
`public static RegistroDeAuditoria getInstancia()` que siempre devuelve ese mismo campo. Cualquier código
que necesite el registro llama a `RegistroDeAuditoria.getInstancia()` en vez de crear una instancia
propia, garantizando que todos compartan la misma.

</details>

**17. [Abierta]** **Pregunta:** una biblioteca universitaria necesita un sistema nuevo: generar reportes
de préstamos en distintos formatos (resumen simple, detallado, para auditoría), donde cada formato arma
el reporte con varios pasos opcionales (encabezado, totales, gráficos, notas al pie) que pueden
combinarse de formas distintas según quién pida el reporte. Identifica cuál de los cinco patrones
creacionales encaja mejor para este caso y justifica por qué los otros cuatro no encajan igual de bien.

_RA: RA-17_

<details>
<summary>🔑 Ver respuesta</summary>

Builder encaja mejor: el reporte se arma con varios componentes opcionales que se combinan de formas
distintas según el pedido, exactamente el problema que Builder resuelve (construcción paso a paso, con
un método por componente opcional). Factory Method no alcanza porque no se trata de elegir una única
clase concreta entre varias, sino de combinar varios componentes en un mismo objeto. Abstract Factory no
aplica porque no hay familias de objetos relacionados que deban ser consistentes entre sí. Prototype no
aplica porque no se parte de un reporte ya armado para copiarlo: cada reporte se arma desde cero según el
pedido. Singleton no aplica porque no hay ningún requisito de que exista una única instancia del
generador de reportes.

</details>

# ❓ Quiz 01 — Principios de Diseño SOLID (formato entrevista técnica)

Este quiz simula las preguntas que podrías recibir en una entrevista técnica para un puesto de
programador Java junior. Cada pregunta indica su tipo (**Selección**, **Selección múltiple** o
**Abierta**). Respondela primero por tu cuenta y después abre "Ver respuesta" para comparar.

---

**1. [Selección]** **Pregunta:** ¿qué representa cada letra del acrónimo SOLID?

- **A.** Cinco fases del ciclo de vida de un proyecto de software.
- **B.** Cinco principios de diseño orientado a objetos: SRP, OCP, LSP, ISP y DIP.
- **C.** Cinco tipos de pruebas automatizadas.
- **D.** Cinco patrones de diseño con nombre propio (Singleton, Factory, etc.).

_RA: RA-1_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** SOLID agrupa cinco principios de diseño orientado a objetos: Responsabilidad
Única, Abierto/Cerrado, Sustitución de Liskov, Segregación de Interfaces e Inversión de Dependencias. No
son patrones de diseño con nombre propio ni fases de un proceso.

</details>

**2. [Selección]** **Pregunta:** ¿qué significa que una clase tenga "una sola razón para cambiar" (SRP)?

- **A.** Que la clase tiene un solo método público.
- **B.** Que la clase solo puede modificarse una vez en todo el proyecto.
- **C.** Que todos los cambios que afectan a esa clase provienen de un único motivo de negocio.
- **D.** Que la clase no tiene atributos privados.

_RA: RA-2_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: C.** Una "razón para cambiar" es un motivo de negocio (cómo se agenda, cómo se
notifica, cómo se factura), no una cantidad de métodos ni de modificaciones. Una clase con SRP responde
a un solo motivo.

</details>

**3. [Selección múltiple]** **Pregunta:** una clase `GestorCitas` tiene un único método `agendarCita`
que: (1) guarda la cita en una lista, (2) imprime un mensaje de notificación al paciente, y (3) calcula y
factura el monto de la consulta. ¿Cuáles afirmaciones son verdaderas?

- **A.** `GestorCitas` mezcla al menos tres responsabilidades distintas en el mismo método.
- **B.** Un cambio en la forma de facturar (por ejemplo, agregar impuestos) obligaría a modificar la
  misma clase que agenda citas.
- **C.** `GestorCitas` respeta SRP porque todo el código está en una sola clase, lo cual simplifica su
  lectura.
- **D.** Separar notificación y facturación en clases propias reduciría los motivos de cambio de
  `GestorCitas` a uno solo: agendar.

_RA: RA-3_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y D.** C es falsa: tener todo en una sola clase no es SRP, es justamente el
problema que SRP identifica — tener el código junto no significa tener una sola responsabilidad.

</details>

**4. [Abierta]** **Pregunta:** tienes una clase `GestorCitas` que agenda, notifica y factura en el mismo
método, tal como en la pregunta anterior. Explica, en tus palabras, cómo refactorizarías esa clase para
que respete SRP sin cambiar el comportamiento observable del programa (la salida por consola debe seguir
siendo exactamente la misma).

_RA: RA-4_

<details>
<summary>🔑 Ver respuesta</summary>

Se extraen las responsabilidades de notificar y facturar a dos clases nuevas (por ejemplo
`NotificadorCitas` y `FacturadorCitas`), cada una con un único método propio de su responsabilidad.
`GestorCitas` las recibe por constructor y, dentro de `agendarCita`, delega a cada una en el mismo orden
en que antes ejecutaba esa lógica directamente. Como cada clase nueva reproduce exactamente la misma
lógica que tenía el bloque original (mismos mensajes, mismo cálculo), la salida por consola no cambia: lo
único que cambia es la organización del código, no su comportamiento.

</details>

**5. [Selección]** **Pregunta:** ¿qué significa "abierto a extensión, cerrado a modificación" (OCP)?

- **A.** Que la clase no puede tener subclases.
- **B.** Que se puede agregar un caso nuevo sin modificar el código que ya funciona.
- **C.** Que el código fuente de la clase debe estar en un archivo de solo lectura.
- **D.** Que la clase debe declararse con la palabra clave `final`.

_RA: RA-5_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** "Abierto a extensión" significa que el diseño admite casos nuevos; "cerrado a
modificación" significa que agregar ese caso nuevo no requiere tocar el código existente que ya
funcionaba y ya fue probado.

</details>

**6. [Selección múltiple]** **Pregunta:** una clase `CalculadoraDescuento` tiene un método
`calcularDescuento(String tipoPaciente, double monto)` con una cadena `if`/`else if` que compara
`tipoPaciente` contra `"REGULAR"`, `"VIP"` y `"SEGURO"`. ¿Cuáles afirmaciones son verdaderas?

- **A.** Agregar un tipo de paciente nuevo (por ejemplo `"CORPORATIVO"`) exige modificar el único método
  existente, agregando una rama `else if` más.
- **B.** Esta estructura viola OCP: el código existente se modifica cada vez que aparece un caso nuevo.
- **C.** Reemplazar la cadena `if`/`else` por una interfaz con una implementación por tipo permitiría
  agregar el caso nuevo sin modificar ninguna de las implementaciones existentes.
- **D.** El problema desaparecería si se usara un `switch` en vez de `if`/`else if`.

_RA: RA-6_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y C.** D es falsa: cambiar la sintaxis de `if`/`else` a `switch` no cambia
el problema de fondo — seguiría siendo el mismo método el que hay que modificar para cada caso nuevo, sin
importar qué estructura de control se use.

</details>

**7. [Abierta]** **Pregunta:** tienes la clase `CalculadoraDescuento` de la pregunta anterior, con su
cadena `if`/`else if` por tipo de paciente. Explica, en tus palabras, cómo diseñarías una solución que
respete OCP, de forma que agregar un tipo de paciente nuevo no exija modificar ninguna clase existente.

_RA: RA-7_

<details>
<summary>🔑 Ver respuesta</summary>

Se declara una interfaz (por ejemplo `EstrategiaDescuento`) con un único método `calcular(double monto)`,
y una clase que la implemente por cada tipo de paciente (`DescuentoRegular`, `DescuentoVip`,
`DescuentoSeguro`), cada una con su propia fórmula. El código que antes decidía con `if`/`else` pasa a
recibir directamente la implementación correspondiente (o a elegirla en un único punto de composición,
como el método `main`). Para agregar un tipo nuevo, alcanza con declarar una clase nueva que implemente
la interfaz (`DescuentoCorporativo`): ninguna de las clases existentes (la interfaz ni las
implementaciones ya escritas) necesita modificarse.

</details>

**8. [Selección]** **Pregunta:** ¿qué significa que una subclase sea sustituible por su superclase (LSP)?

- **A.** Que la subclase debe sobrescribir todos los métodos de la superclase.
- **B.** Que un código que usa objetos de la superclase debe seguir funcionando correctamente si se le
  pasa un objeto de la subclase, sin sorpresas en el resultado.
- **C.** Que la subclase no puede agregar atributos nuevos.
- **D.** Que la superclase debe ser una interfaz, nunca una clase concreta.

_RA: RA-8_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** LSP exige que, desde el punto de vista de quien usa la superclase, sustituir
un objeto por uno de una subclase no cambie el comportamiento esperado: ni lanzando excepciones nuevas ni
devolviendo resultados silenciosamente distintos ante la misma llamada.

</details>

**9. [Selección múltiple]** **Pregunta:** `Medico.agendarCita(int diasDesdeHoy)` acepta cualquier valor
mayor o igual a 1. `MedicoResidente extends Medico` sobrescribe `agendarCita` así:

```java
@Override
public boolean agendarCita(int diasDesdeHoy) {
    if (diasDesdeHoy < 30) {
        return false;
    }
    // ... agenda la cita
    return true;
}
```

Un código que recorre una `List<Medico>` mixta (con `Medico` y `MedicoResidente`) llama a
`agendarCita(7)` sobre cada uno. ¿Cuáles afirmaciones son verdaderas?

- **A.** `MedicoResidente` estrecha la precondición heredada: exige 30 días en vez de 1.
- **B.** El código que recorre la lista obtiene `true` para `Medico` y `false` para `MedicoResidente` ante
  la misma llamada, sin ningún error ni excepción que lo avise.
- **C.** Esto es una violación de LSP, aunque el programa compile y se ejecute sin lanzar ninguna
  excepción.
- **D.** No hay ninguna violación, porque `MedicoResidente` no lanza ninguna excepción.

_RA: RA-9_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y C.** D es falsa: una violación de LSP no requiere una excepción — un
resultado silenciosamente distinto ante la misma llamada, causado por una precondición más estricta, es
una violación igual de real.

</details>

**10. [Abierta]** **Pregunta:** tienes la jerarquía `Medico`/`MedicoResidente` de la pregunta anterior,
donde `MedicoResidente` sobrescribe `agendarCita` con una precondición más estricta (30 días en vez de
1), devolviendo `false` en silencio cuando no se cumple. Explica, en tus palabras, cómo corregirías el
diseño para que respete LSP.

_RA: RA-10_

<details>
<summary>🔑 Ver respuesta</summary>

En vez de sobrescribir `agendarCita` con una condición más estricta, el mínimo de días de anticipación se
convierte en un dato propio de cada médico: `Medico` declara un atributo `diasMinimosAnticipacion` (con
valor por defecto 1) y un método `getDiasMinimosAnticipacion()` que lo expone; `agendarCita` usa ese
atributo, no un valor fijo, para decidir. `MedicoResidente` ya no sobrescribe `agendarCita`: solo fija su
propio mínimo en 30 a través del constructor. Así, cualquier código que use `Medico` sigue funcionando
igual con `MedicoResidente` — puede consultar el mínimo real antes de agendar, en vez de recibir un
resultado distinto sin explicación.

</details>

**11. [Selección]** **Pregunta:** ¿qué evita el principio de Segregación de Interfaces (ISP)?

- **A.** Que dos clases distintas implementen la misma interfaz.
- **B.** Que una clase se vea obligada a implementar métodos que no usa, por pertenecer a una interfaz
  demasiado amplia.
- **C.** Que una interfaz tenga más de un método.
- **D.** Que una clase implemente más de una interfaz a la vez.

_RA: RA-11_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** ISP busca que ninguna clase quede obligada, por el solo hecho de implementar
una interfaz, a declarar métodos que no le corresponden a su rol real.

</details>

**12. [Selección múltiple]** **Pregunta:** una interfaz `TrabajadorClinica` declara
`atenderPaciente`, `realizarCirugia` y `gestionarFacturacion`. La clase `Recepcionista` la implementa,
pero `realizarCirugia` no tiene ninguna implementación con sentido real para ese rol. ¿Cuáles de las
siguientes acciones dividen correctamente la interfaz para respetar ISP?

- **A.** Declarar tres interfaces separadas (`AtiendePacientes`, `RealizaCirugias`,
  `GestionaFacturacion`), cada una con un único método.
- **B.** Hacer que `Medico` implemente las tres interfaces nuevas, y que `Recepcionista` implemente solo
  `AtiendePacientes` y `GestionaFacturacion`.
- **C.** Dejar `TrabajadorClinica` como está, pero agregar un comentario que aclare que
  `realizarCirugia` es opcional.
- **D.** Declarar una sola interfaz nueva `TrabajadorClinicaSinCirugia` que reemplace a la original solo
  para `Recepcionista`.

_RA: RA-12_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A y B.** C es falsa: un comentario no cambia que el compilador siga exigiendo la
implementación del método. D es falsa: duplicar la interfaz original con un método menos no es dividirla
por capacidad, es un parche que no escala a un tercer rol con otra combinación de capacidades.

</details>

**13. [Selección]** **Pregunta:** ¿qué es el principio de Inversión de Dependencias (DIP)?

- **A.** Que las clases de nivel alto (como la lógica de negocio) deben depender de abstracciones, no de
  implementaciones concretas de nivel bajo.
- **B.** Que toda dependencia debe crearse con la palabra clave `new` dentro del constructor.
- **C.** Que un proyecto debe usar un framework de inyección de dependencias.
- **D.** Que las interfaces no pueden tener más de un método.

_RA: RA-13_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: A.** DIP dice que la lógica de negocio debe depender de una abstracción (una
interfaz), y que los detalles concretos (una implementación específica) dependen a su vez de esa misma
abstracción — no al revés. Esto es independiente de usar o no un framework: en Java plano, alcanza con
recibir la abstracción por constructor.

</details>

**14. [Selección múltiple]** **Pregunta:** `SistemaNotificaciones` crea `new NotificadorEmail()` dentro
de su propio constructor y lo usa en `notificarPaciente`. ¿Cuáles afirmaciones son verdaderas sobre esta
clase?

- **A.** `SistemaNotificaciones` depende directamente de un detalle concreto (`NotificadorEmail`), no de
  una abstracción.
- **B.** Para notificar por SMS en vez de email, habría que modificar el código de
  `SistemaNotificaciones`.
- **C.** Si `SistemaNotificaciones` recibiera una interfaz `CanalNotificacion` por constructor, la misma
  clase funcionaría con `NotificadorEmail` o con `NotificadorSms` sin que su código cambie entre una
  ejecución y otra.
- **D.** El problema desaparecería si `NotificadorEmail` se declarara como atributo `private final` en
  vez de `private`.

_RA: RA-14_

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A, B y C.** D es falsa: declarar el atributo como `final` no cambia que la
dependencia siga siendo un detalle concreto creado internamente — el problema es DÓNDE se decide la
implementación, no si la referencia puede reasignarse.

</details>

**15. [Abierta]** **Pregunta:** una clase `GestorTurnos` recibe una lista de personas y, dentro del mismo
método `procesarTurnos`, decide a quién atender primero comparando directamente contra el string
`"URGENTE"`, atiende al turno, imprime un comprobante y actualiza un contador de estadísticas — todo en
el mismo bloque de código. Identifica cuál o cuáles de los cinco principios SOLID viola este diseño y
justificá tu respuesta.

_RA: RA-15_

<details>
<summary>🔑 Ver respuesta</summary>

Viola al menos SRP y OCP. SRP: el método mezcla tres responsabilidades distintas (decidir prioridad,
atender el turno, generar el comprobante y actualizar estadísticas), cualquiera de las cuales podría
cambiar por su cuenta. OCP: la comparación directa contra el string `"URGENTE"` es una decisión de
prioridad "cableada" en el método — agregar una prioridad nueva (por ejemplo `"PREFERENCIAL"`) exigiría
modificar ese mismo método en vez de agregar una implementación nueva de una estrategia de prioridad.

</details>

# 📚 Explicación conceptual — Módulo 13

## 🧠 Concepto: Qué es un patrón estructural

- Un patrón estructural resuelve un problema de **cómo se componen** clases y objetos para formar
  estructuras más grandes, sin que esa composición se vuelva rígida, redundante o ineficiente.
- Se distingue de los patrones creacionales (Módulo 12, cómo se crean los objetos) y de los de
  comportamiento (módulo posterior, cómo se comunican y reparten responsabilidades).
- Los siete patrones de este módulo resuelven problemas de composición distintos entre sí: interfaz
  incompatible (Adapter), dos dimensiones de variación mezcladas (Bridge), estructuras de árbol tratadas
  con condicionales (Composite), combinaciones de extras resueltas con herencia (Decorator), un
  subsistema expuesto sin simplificar (Facade), objetos casi idénticos que desperdician memoria
  (Flyweight), y un objeto costoso o sensible usado sin ningún control (Proxy).

📎 Ver en la práctica: [Ejemplo 01 — Qué son los patrones estructurales](01-que-son-los-patrones-estructurales.md)

## 🧠 Concepto: Adapter

- Convierte la interfaz de una clase existente en otra interfaz que el cliente espera, sin modificar la
  clase existente.
- El problema que resuelve: código cliente que traduce manualmente entre dos interfaces incompatibles,
  repitiendo esa traducción en varios lugares.
- Se implementa con una clase que implementa la interfaz esperada y envuelve una instancia de la clase
  existente, traduciendo cada llamada una sola vez.

📎 Ver en la práctica: [Ejemplo 02 — Adapter](02-adapter.md)

## 🧠 Concepto: Bridge

- Separa una abstracción de su implementación para que ambas puedan variar de forma independiente.
- El problema que resuelve: mezclar dos dimensiones de variación distintas en una sola jerarquía de
  herencia produce una explosión combinatoria de subclases.
- Se implementa con dos jerarquías separadas (abstracción e implementación) combinadas por composición,
  no por herencia.

📎 Ver en la práctica: [Ejemplo 03 — Bridge](03-bridge.md)

## 🧠 Concepto: Composite

- Compone objetos en una estructura de árbol para representar jerarquías de "parte-todo", tratando
  objetos individuales y compuestos de manera uniforme.
- El problema que resuelve: código cliente que distingue con condicionales (`instanceof`) entre un
  objeto individual y uno compuesto.
- Se implementa con una interfaz común que ambos (hoja y compuesto) implementan, permitiendo recorridos
  recursivos sin ningún `instanceof`.

📎 Ver en la práctica: [Ejemplo 04 — Composite](04-composite.md)

## 🧠 Concepto: Decorator

- Agrega responsabilidades a un objeto de forma dinámica, como alternativa más flexible que la herencia
  para combinar extras opcionales.
- El problema que resuelve: representar cada combinación posible de extras como una subclase produce una
  explosión combinatoria.
- Se implementa envolviendo un objeto dentro de otro (el decorador), delegando en el objeto envuelto y
  agregando su propia responsabilidad; los decoradores se pueden anidar.

📎 Ver en la práctica: [Ejemplo 05 — Decorator](05-decorator.md)

## 🧠 Concepto: Facade

- Ofrece una interfaz única y simplificada sobre un conjunto de interfaces de un subsistema complejo.
- El problema que resuelve: código cliente que conoce y orquesta directamente demasiados subsistemas,
  en el orden correcto, para completar una sola operación de negocio.
- Se implementa con una clase que internamente crea y coordina los subsistemas, exponiendo un único
  método de alto nivel.

📎 Ver en la práctica: [Ejemplo 06 — Facade](06-facade.md)

## 🧠 Concepto: Flyweight

- Comparte el estado intrínseco (idéntico) entre muchos objetos de grano fino, para reducir el costo de
  memoria de crearlos todos por separado.
- El problema que resuelve: muchos objetos casi idénticos, cada uno repitiendo el mismo estado que
  podría compartirse.
- Se implementa con una fábrica que devuelve siempre la misma instancia para el mismo estado intrínseco,
  verificable comparando referencias con `==`.

📎 Ver en la práctica: [Ejemplo 07 — Flyweight](07-flyweight.md)

## 🧠 Concepto: Proxy

- Es un sustituto que controla el acceso a otro objeto: puede retrasar su creación hasta que realmente
  se necesita, verificar permisos antes de delegar en él, o registrar cada acceso.
- El problema que resuelve: un objeto costoso de crear o sensible de usar, creado o accedido sin ningún
  control intermedio.
- Se implementa con una clase que implementa la misma interfaz que el objeto real, y delega en él solo
  cuando corresponde.

📎 Ver en la práctica: [Ejemplo 08 — Proxy](08-proxy.md)

## 📋 Resumen de los siete patrones estructurales

| Patrón | Qué problema de composición resuelve | Ejemplo de MediSalud |
|---|---|---|
| Adapter | Interfaz incompatible traducida a mano en varios lugares | `FacturadorAdapter` envuelve `FacturadorLegado` |
| Bridge | Dos dimensiones de variación mezcladas en una jerarquía | `Notificacion` (tipo) + `CanalEnvio` (canal) separados |
| Composite | Condicionales para distinguir individuo de compuesto | `UnidadOrganizacional` uniforme para `Colaborador`/`Departamento` |
| Decorator | Combinaciones de extras resueltas con herencia | `ConsultaDecorada` combina `ConAnalisis`/`ConImagen` |
| Facade | El cliente conoce y orquesta demasiados subsistemas | `AdmisionFacade` oculta 4 subsistemas |
| Flyweight | Muchos objetos casi idénticos desperdiciando memoria | `FabricaDeTiposDeSala` comparte `TipoDeSala` |
| Proxy | Objeto costoso o sensible sin control intermedio | `ExpedienteClinicoProxy` retrasa la carga real |

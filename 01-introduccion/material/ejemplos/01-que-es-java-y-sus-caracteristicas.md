# 💡 Ejemplo 01 — ¿Qué es Java y cuáles son sus características?

## 🌍 Contexto

Java es un **lenguaje de programación**: una forma de escribir instrucciones que un
computador pueda ejecutar. Se presentó en 1995 y hoy se mantiene como un proyecto
abierto (OpenJDK) con versiones nuevas cada seis meses; cada dos años una de ellas es
de **soporte a largo plazo** (LTS). En este curso usamos Java 25, la LTS vigente.

Se usa para construir, entre otras cosas:

- Sistemas empresariales de gran tamaño (banca, salud, logística).
- Servicios web y APIs que atienden a miles de usuarios a la vez.
- Aplicaciones de escritorio y herramientas de desarrollo (los entornos Eclipse e
  IntelliJ IDEA están escritos en Java).
- Procesamiento de datos y sistemas de mensajería.

**Qué busca demostrar este ejemplo**: que Java es un lenguaje de propósito general
con características concretas, y que cada una de ellas resuelve un problema real, como
el de una organización que necesita que el mismo programa funcione en equipos muy
distintos.

## 🏥 Caso de estudio

La red de clínicas **MediSalud** tiene tres equipos:

- En recepción, un computador con **Windows** para agendar citas.
- En farmacia, un computador con **Linux** para controlar el inventario.
- En la dirección médica, una portátil con **macOS** para consultar historias clínicas.

MediSalud no quiere pagar tres programas distintos ni reescribir el sistema cada vez
que cambia un equipo. Quiere **un solo programa** que funcione en los tres. Esa es una
de las razones por las que se eligió Java.

## 🧠 Características de Java

| Característica | Qué significa | Cómo se nota en MediSalud |
|---|---|---|
| **Portable** | El mismo programa compilado corre en distintos sistemas operativos. | Recepción, farmacia y dirección usan el mismo programa. |
| **Orientado a objetos** | El programa se organiza en "objetos" que representan cosas del negocio. | `Paciente`, `Cita` y `Medico` se modelan como objetos. *(Lo veremos en módulos posteriores.)* |
| **De tipado estático** | Cada dato tiene un tipo (número, texto…) y el compilador lo revisa antes de ejecutar. | Un error como guardar un texto en un campo de edad se detecta antes de que el sistema llegue a la clínica. |
| **Con gestión automática de memoria** | Java libera solo la memoria que el programa ya no usa (recolector de basura). | El sistema puede estar encendido días sin que nadie tenga que administrar la memoria a mano. |
| **Seguro** | Verifica el código antes de ejecutarlo y no permite manejar la memoria directamente. | Se reduce el riesgo de que un error exponga datos de las historias clínicas. |
| **Multihilo** | Puede hacer varias tareas al mismo tiempo. | Recepción agenda citas mientras se generan las facturas del día. |

Otras características que verás nombradas con frecuencia: es **robusto** (detecta y
maneja errores con un mecanismo propio), tiene **buen rendimiento** (la máquina virtual
optimiza el código mientras se ejecuta) y cuenta con un **ecosistema enorme** de
bibliotecas y herramientas gratuitas.

## 🧭 Explicación paso a paso

1. Java es un **lenguaje**: sirve para escribir instrucciones. Lo que las ejecuta es
   otra pieza distinta, la máquina virtual (se explica en los Ejemplos 02 y 03).
2. La **portabilidad** es la característica que más pesa en el caso de MediSalud:
   permite escribir el programa una vez y usarlo en Windows, Linux y macOS.
3. El **tipado estático** y la **gestión automática de memoria** ayudan al
   principiante: el compilador avisa de muchos errores antes de ejecutar y no hay que
   liberar memoria a mano.
4. Ninguna característica es "mejor" en abstracto: cada una responde a una necesidad
   (portabilidad → equipos distintos; seguridad → datos sensibles; multihilo → tareas
   simultáneas).
5. En este módulo no escribimos todavía código orientado a objetos: primero
   preparamos el entorno y aprendemos a guardar y mostrar datos.

## ✅ Resultado esperado

Al terminar este ejemplo deberías poder:

- Explicar con tus palabras qué es Java y nombrar dos tipos de aplicaciones que se
  construyen con él.
- Enumerar al menos cinco características de Java y explicar qué problema resuelve
  cada una.
- Justificar por qué la portabilidad fue clave en el caso de MediSalud.

## ❓ Preguntas de repaso

**1. [Selección]** ¿Qué característica de Java permite que el mismo programa funcione
en Windows, Linux y macOS?

- **A.** Que es orientado a objetos.
- **B.** Que es portable.
- **C.** Que tiene tipado estático.
- **D.** Que es multihilo.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B**. La portabilidad es la característica que permite ejecutar
el mismo programa compilado en distintos sistemas operativos (se explica cómo en el
Ejemplo 02).

</details>

**2. [Selección múltiple]** Seleccioná **todas** las afirmaciones correctas sobre
Java.

- **A.** El compilador revisa los tipos de los datos antes de ejecutar el programa.
- **B.** El programador debe liberar la memoria manualmente cuando ya no la usa.
- **C.** Puede realizar varias tareas al mismo tiempo (multihilo).
- **D.** Solo sirve para programas de escritorio.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: A y C**. La B es falsa: Java tiene recolector de basura, que
libera la memoria automáticamente. La D es falsa: Java se usa en servicios web,
sistemas empresariales y más.

</details>

**3. [Abierta]** MediSalud cambia el computador de farmacia de Linux a Windows.
Explicá por qué, si el sistema está hecho en Java, el cambio no obliga a reescribir el
programa.

<details>
<summary>🔑 Ver respuesta modelo</summary>

**Respuesta modelo**: Porque Java es portable: el programa compilado no depende de un
sistema operativo concreto, sino de la máquina virtual de Java, que existe para
Windows, Linux y macOS. Basta con tener la máquina virtual instalada en el equipo
nuevo para ejecutar el mismo programa.

</details>

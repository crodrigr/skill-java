# ❓ Cuestionario integrador del Curso de Java — 50 preguntas

## 🎯 Objetivo

Afianzar los conceptos de los 20 módulos del curso con preguntas de **complejidad alta**: lectura
de código, detección de errores sutiles, comparación de alternativas de diseño y justificación
de decisiones. Los casos de estudio son **MediSalud** y **Biblioteca Universitaria**.

## 📊 Estructura

| Tipo | Cantidad | Puntos c/u | Subtotal |
|------|---------:|-----------:|---------:|
| Selección única | 21 | 2 | 42 |
| Selección múltiple | 14 | 2 | 28 |
| Abierta | 15 | 2 | 30 |
| **Total** | **50** | | **100** |

## 📝 Instrucciones

- Cada pregunta indica su **tipo** y el **módulo** que evalúa.
- En **selección única** existe exactamente una opción correcta.
- En **selección múltiple** pueden ser correctas una o varias opciones; se puntúa solo si el
  conjunto marcado coincide exactamente con el conjunto correcto.
- En **abierta** se responde por escrito y, cuando corresponda, con código; la solución muestra
  los elementos mínimos que debe contener una respuesta completa.
- Todo el código se ejecuta con **Java 17 o superior**, salvo indicación contraria.
- La **solución** de cada pregunta está oculta: se despliega con un clic en `🔑 Ver solución`.
  Se recomienda responder primero y solo después consultar la solución.

---

## 📘 Módulo 01 — Introducción

### Pregunta 1 · Selección única · Módulo 01

Un equipo de MediSalud compila `Principal.java` en un equipo con Ubuntu y entrega el
archivo `Principal.class` a otro equipo con Windows que solo tiene instalada una JVM.

**Pregunta**: ¿Qué afirmación explica correctamente lo que ocurre?

- A) No funciona: el `.class` contiene código máquina específico de Ubuntu y debe recompilarse en Windows.
- B) Funciona: el `.class` contiene *bytecode*, que cualquier JVM compatible interpreta sin recompilar; `javac` solo es necesario para generar el bytecode.
- C) Funciona solo si en Windows también está instalado el JDK, porque la JVM no puede ejecutar archivos `.class`.
- D) No funciona: la JVM compila el código fuente en tiempo de ejecución y requiere el archivo `.java`.

<details>
<summary>🔑 Ver solución</summary>

**Respuesta: B.**

- `javac` (parte del JDK) traduce el código fuente a *bytecode*, independiente de plataforma.
- Cada JVM (parte del JRE y del JDK) interpreta o compila a código nativo ese bytecode en tiempo de ejecución.
- A es falso (el bytecode no es código máquina). C es falso (ejecutar solo requiere JVM). D es falso (la JVM ejecuta `.class`, no `.java`).

</details>

### Pregunta 2 · Abierta · Módulo 01 (tipos y conversiones básicas)

Se dan estas tres sentencias:

```java
double promedio = (7 + 8) / 2;
int edad = (int) 3.99;
byte turno = 10;
turno = turno + 1;
```

**Pregunta**: Indicar qué valor queda en `promedio` y en `edad`, qué ocurre con la última línea
y por qué, y cómo se modifica cada línea para obtener `7.5` y una compilación correcta.

<details>
<summary>🔑 Ver solución</summary>

- `promedio` vale `7.0`: la expresión `(7 + 8) / 2` es división **entera** (`15 / 2 = 7`) y solo después se amplía a `double`.
- `edad` vale `3`: el *cast* a `int` **trunca**, no redondea.
- `turno = turno + 1;` **no compila**: `turno + 1` se promociona a `int` y asignar `int` a `byte` es una conversión con pérdida que exige *cast*.
- Correcciones: `double promedio = (7 + 8) / 2.0;` (resultado `7.5`) y `turno = (byte) (turno + 1);` o `turno += 1;` (el operador compuesto incluye el *cast* implícito).

</details>

---

## 📘 Módulo 02 — Operadores y estructuras de decisión

### Pregunta 3 · Selección única · Módulo 02

```java
int a = 5;
int b = a++ + ++a;
System.out.println(a + " " + b);
```

**Pregunta**: ¿Qué imprime el fragmento?

- A) `6 10`
- B) `7 11`
- C) `7 12`
- D) `6 11`

<details>
<summary>🔑 Ver solución</summary>

**Respuesta: C (`7 12`).**

- `a++` aporta `5` y luego `a` pasa a `6`.
- `++a` incrementa primero (`a = 7`) y aporta `7`.
- `b = 5 + 7 = 12` y `a` termina en `7`.

</details>

### Pregunta 4 · Selección múltiple · Módulo 02

```java
int x = 0;
boolean r = (x != 0) && (10 / x > 1);
boolean s = (x != 0) & (10 / x > 1);
```

**Pregunta**: ¿Cuáles afirmaciones son correctas? (marcar todas las que apliquen)

- A) La línea de `r` termina sin excepción y `r` vale `false`.
- B) La línea de `s` lanza `ArithmeticException`.
- C) `&&` evalúa siempre ambos operandos, igual que `&`.
- D) Si se cambia `(x != 0)` por `(x == 0)` en la línea de `r`, se lanza `ArithmeticException` en la línea de `r`.

<details>
<summary>🔑 Ver solución</summary>

**Respuesta: A, B y D.**

- A: `&&` hace cortocircuito; como `x != 0` es `false`, no evalúa la división.
- B: `&` evalúa ambos lados, por lo que `10 / 0` lanza `ArithmeticException`.
- C es falsa: precisamente `&&` no evalúa el segundo operando si el primero ya decide el resultado.
- D: con `x == 0` la condición es `true`, el cortocircuito no ocurre y se evalúa `10 / 0`.

</details>

---

## 📘 Módulo 03 — Estructuras repetitivas

### Pregunta 5 · Selección única · Módulo 03

```java
int suma = 0;
for (int i = 1; i <= 10; i++) {
    if (i % 2 == 0) continue;
    if (i > 7) break;
    suma += i;
}
System.out.println(suma);
```

**Pregunta**: ¿Qué valor imprime?

- A) `16`
- B) `25`
- C) `9`
- D) `24`

<details>
<summary>🔑 Ver solución</summary>

**Respuesta: A (`16`).**

- Los pares se saltan con `continue`.
- Se suman `1 + 3 + 5 + 7 = 16`.
- En `i = 9` (impar) se cumple `i > 7` y `break` termina el ciclo antes de sumar.

</details>

### Pregunta 6 · Abierta · Módulo 03 (tabla de valores)

```java
int i = 10;
int pasos = 0;
while (i > 1) {
    if (i % 2 == 0) i /= 2;
    else i = 3 * i + 1;
    pasos++;
}
```

**Pregunta**: Construir la tabla de valores (`i` y `pasos` al final de cada iteración), indicar los
valores finales y explicar qué cambia si el `while` se reemplaza por `do { ... } while (i > 1);`
y `i` empieza en `1`.

<details>
<summary>🔑 Ver solución</summary>

| Iteración | `i` | `pasos` |
|---|---:|---:|
| 1 | 5 | 1 |
| 2 | 16 | 2 |
| 3 | 8 | 3 |
| 4 | 4 | 4 |
| 5 | 2 | 5 |
| 6 | 1 | 6 |

- Valores finales: `i = 1`, `pasos = 6`.
- Con `while` e `i = 1` inicial, la condición es falsa de entrada: **0 iteraciones**, `pasos = 0`.
- Con `do-while` el cuerpo se ejecuta **al menos una vez**: `1 → 4 → 2 → 1`, es decir `pasos = 3`.
- Conclusión: `while` evalúa antes del cuerpo; `do-while` evalúa después.

</details>

---

## 📘 Módulo 04 — Cadenas y conversiones

### Pregunta 7 · Selección múltiple · Módulo 04

```java
String s1 = "hola";
String s2 = "ho" + "la";
String s3 = new String("hola");
String d  = "ho";
String s4 = d + "la";
```

**Pregunta**: ¿Cuáles expresiones evalúan a `true`?

- A) `s1 == s2`
- B) `s1 == s3`
- C) `s1 == s4`
- D) `s1.equals(s4)`
- E) `s1.equals(s3)`

<details>
<summary>🔑 Ver solución</summary>

**Respuesta: A, D y E.**

- A: `"ho" + "la"` es una expresión constante resuelta en compilación y comparte el literal del *pool*.
- B es falsa: `new String(...)` crea un objeto distinto.
- C es falsa: `d` no es `final`, así que la concatenación ocurre en ejecución y crea un objeto nuevo.
- D y E: `equals` compara contenido.

</details>

### Pregunta 8 · Selección única · Módulo 04

```java
Integer a = 127, b = 127;
Integer c = 128, d = 128;
System.out.println((a == b) + " " + (c == d) + " " + c.equals(d));
```

**Pregunta**: ¿Qué imprime?

- A) `true true true`
- B) `true false true`
- C) `false false true`
- D) `true false false`

<details>
<summary>🔑 Ver solución</summary>

**Respuesta: B.**

- `Integer.valueOf` cachea los valores entre `-128` y `127`: `a == b` compara la misma referencia (`true`).
- `128` queda fuera del caché: `c` y `d` son objetos distintos y `c == d` es `false`.
- `equals` compara el valor: `true`.

</details>

---

## 📘 Módulo 05 — Programación orientada a objetos

### Pregunta 9 · Selección única · Módulo 05

```java
class Cita {
    static int total = 0;
    int numero;
    Cita() { total++; numero = total; }
}
// ...
new Cita();
new Cita();
Cita c = new Cita();
System.out.println(c.numero + " " + Cita.total);
```

**Pregunta**: ¿Qué imprime?

- A) `1 3`
- B) `3 3`
- C) `3 1`
- D) `1 1`

<details>
<summary>🔑 Ver solución</summary>

**Respuesta: B (`3 3`).**

- `total` es estático: una sola copia compartida por todas las instancias, que llega a `3`.
- `numero` es de instancia: la tercera cita copia el valor de `total` en ese momento, `3`.

</details>

### Pregunta 10 · Abierta · Módulo 05

Se define esta clase para MediSalud:

```java
public class Medico {
    private String nombre;
    private static int totalMedicos = 0;

    public Medico(String nombre) {
        this.nombre = nombre;
        totalMedicos++;
    }

    public static int contar() {
        return totalMedicos + nombre.length();
    }
}
```

**Pregunta**: Explicar por qué no compila, qué diferencia conceptual existe entre un miembro
estático y uno de instancia, y cómo se corrige para que `contar()` devuelva solo el total de
médicos. Indicar además qué resultado da `new Medico("Ana") == new Medico("Ana")`.

<details>
<summary>🔑 Ver solución</summary>

- `contar()` es estático: pertenece a la **clase** y no recibe `this`, por lo que no puede leer `nombre`, que existe una vez por objeto.
- Miembro de instancia: cada objeto tiene su copia y su estado. Miembro estático: una sola copia, compartida, accesible sin crear objetos.
- Corrección: `return totalMedicos;` (o volver `contar()` un método de instancia si de verdad necesita `nombre`).
- `new Medico("Ana") == new Medico("Ana")` es `false`: `==` compara **identidad** (referencias) y son dos objetos distintos; el contenido solo se compara con un `equals` bien definido.

</details>

---

## 📘 Módulo 06 — Encapsulamiento

### Pregunta 11 · Selección múltiple · Módulo 06

La clase `A` está en el paquete `p1` y declara un método `protected void atender()` y un
método de visibilidad por defecto `void archivar()`.

**Pregunta**: ¿Cuáles afirmaciones son correctas?

- A) Una clase `B` del paquete `p1` (no subclase) puede invocar `atender()` sobre una instancia de `A`.
- B) Una subclase `C` de `A` en el paquete `p2` puede invocar `atender()` heredado.
- C) Una clase `D` del paquete `p2` que no hereda de `A` puede invocar `atender()` sobre una instancia de `A`.
- D) Una subclase `C` de `A` en el paquete `p2` puede invocar `archivar()` heredado.

<details>
<summary>🔑 Ver solución</summary>

**Respuesta: A y B.**

- `protected` incluye acceso desde el mismo paquete (A) y desde subclases, aunque estén en otro paquete (B).
- C es falsa: fuera del paquete y sin herencia solo se accede a `public`.
- D es falsa: la visibilidad por defecto es *package-private*; ni siquiera una subclase de otro paquete la ve.

</details>

### Pregunta 12 · Abierta · Módulo 06

```java
public class Paciente {
    private final List<String> alergias = new ArrayList<>();
    private int edad;

    public Paciente(int edad) { this.edad = edad; }
    public void setEdad(int edad) {
        if (edad < 0) throw new IllegalArgumentException("La edad no puede ser negativa");
        this.edad = edad;
    }
    public List<String> getAlergias() { return alergias; }
}
```

**Pregunta**: Identificar **dos** fallos de encapsulamiento de esta clase, explicar cómo un
cliente los aprovecha y proponer la corrección de cada uno.

<details>
<summary>🔑 Ver solución</summary>

1. **El constructor evita la validación**: `new Paciente(-5)` crea un paciente inválido. Corrección: que el constructor invoque `setEdad(edad)` (o repita la validación en un único método privado).
2. **`getAlergias()` expone la lista interna mutable**: `paciente.getAlergias().add("x")` o `.clear()` modifica el estado sin pasar por la clase. Correcciones posibles:
   - Devolver una vista inmutable: `return Collections.unmodifiableList(alergias);`
   - Devolver una copia: `return List.copyOf(alergias);`
   - Ofrecer métodos de dominio, como `agregarAlergia(String)`, que validen el dato.

Criterio: una respuesta completa nombra el problema (estado interno mutable accesible) y no solo "hay que usar `private`", ya que el campo **ya es** `private`.

</details>

---

## 📘 Módulo 07 — Herencia y polimorfismo

### Pregunta 13 · Selección única · Módulo 07

```java
class Persona {
    String saludar() { return "Persona"; }
    static String tipo() { return "P"; }
}
class Medico extends Persona {
    String saludar() { return "Medico"; }
    static String tipo() { return "M"; }
}
// ...
Persona p = new Medico();
System.out.println(p.saludar() + p.tipo());
```

**Pregunta**: ¿Qué imprime?

- A) `MedicoM`
- B) `PersonaP`
- C) `MedicoP`
- D) `PersonaM`

<details>
<summary>🔑 Ver solución</summary>

**Respuesta: C (`MedicoP`).**

- Los métodos de instancia se resuelven por el tipo **real** del objeto (polimorfismo): `Medico.saludar()`.
- Los métodos estáticos **no se sobrescriben, se ocultan**: se resuelven por el tipo **declarado** de la variable (`Persona`), es decir `Persona.tipo()`.

</details>

### Pregunta 14 · Selección única · Módulo 07

```java
static void f(long x)    { System.out.print("long "); }
static void f(Integer x) { System.out.print("Integer "); }
static void f(Object x)  { System.out.print("Object "); }
// ...
f(5);
```

**Pregunta**: ¿Qué imprime la invocación?

- A) `Integer`
- B) `Object`
- C) `long`
- D) No compila por ambigüedad.

<details>
<summary>🔑 Ver solución</summary>

**Respuesta: C (`long`).**

La resolución de sobrecarga sigue fases: primero se buscan métodos aplicables **sin boxing** (ampliación primitiva `int → long`); solo si ninguno aplica se considera el *boxing* a `Integer` y luego `Object`. Como `f(long)` aplica en la primera fase, gana.

</details>

### Pregunta 15 · Selección múltiple · Módulo 07

**Pregunta**: ¿Cuáles afirmaciones sobre clases abstractas e interfaces (Java 17) son correctas?

- A) Una interfaz puede declarar métodos `default` con cuerpo.
- B) Una clase abstracta puede tener constructor.
- C) Una clase puede implementar varias interfaces.
- D) Una interfaz puede declarar atributos de instancia modificables.
- E) Una clase abstracta puede instanciarse con `new` si no declara métodos abstractos.

<details>
<summary>🔑 Ver solución</summary>

**Respuesta: A, B y C.**

- A: los métodos `default` existen desde Java 8.
- B: el constructor de la clase abstracta se invoca desde el de las subclases con `super(...)`.
- C: una clase hereda de una sola clase, pero implementa tantas interfaces como necesite.
- D es falsa: los atributos de una interfaz son implícitamente `public static final` (constantes).
- E es falsa: una clase declarada `abstract` nunca se instancia directamente, aunque no tenga métodos abstractos.

</details>

---

## 📘 Módulo 08 — Arreglos, listas y clases genéricas

### Pregunta 16 · Selección única · Módulo 08

```java
List<Integer> l = new ArrayList<>(List.of(1, 2, 3, 4));
l.remove(1);
l.remove(Integer.valueOf(1));
System.out.println(l);
```

**Pregunta**: ¿Qué imprime?

- A) `[3, 4]`
- B) `[2, 3, 4]`
- C) `[2, 4]`
- D) `[1, 3, 4]`

<details>
<summary>🔑 Ver solución</summary>

**Respuesta: A (`[3, 4]`).**

- `remove(1)` con un `int` usa la sobrecarga `remove(int index)`: elimina el elemento en la posición 1 (el `2`) → `[1, 3, 4]`.
- `remove(Integer.valueOf(1))` usa `remove(Object)`: elimina el valor `1` → `[3, 4]`.

</details>

### Pregunta 17 · Selección múltiple · Módulo 08 (genéricos)

**Pregunta**: ¿Cuáles de estas sentencias **compilan**?

- A) `List<Object> lo = new ArrayList<String>();`
- B) `List<? extends Number> ln = new ArrayList<Integer>();`
- C) `List<? extends Number> ln = new ArrayList<Integer>(); ln.add(5);`
- D) `List<?> lw = new ArrayList<String>(List.of("x")); Object o = lw.get(0);`

<details>
<summary>🔑 Ver solución</summary>

**Respuesta: B y D.**

- A no compila: los genéricos son **invariantes**; `List<String>` no es subtipo de `List<Object>`.
- B compila: el comodín `? extends Number` admite cualquier lista de un subtipo de `Number`.
- C no compila: no se puede insertar en una lista con comodín `extends` (el compilador no sabe el tipo exacto, solo se puede leer como `Number`).
- D compila: con `List<?>` se puede leer, y lo leído es `Object`.

</details>

### Pregunta 18 · Abierta · Módulo 08

Biblioteca Universitaria maneja una lista de 200 000 libros con estos requisitos:

- Con frecuencia se insertan libros urgentes al **inicio** de la lista.
- La pantalla de consulta pagina por **posición** (`get(i)`), de forma aleatoria.
- Los recorridos completos son ocasionales.

**Pregunta**: Comparar `ArrayList` y `LinkedList` para este caso con su complejidad en cada
operación y justificar una recomendación. Indicar qué estructura alternativa del framework de
colecciones resuelve mejor el insertar al inicio y por qué no basta por sí sola.

<details>
<summary>🔑 Ver solución</summary>

| Operación | `ArrayList` | `LinkedList` |
|---|---|---|
| `get(i)` | O(1) | O(n) |
| Insertar al inicio | O(n) (desplaza elementos) | O(1) |
| Recorrido completo | O(n), buena localidad de memoria | O(n), más lento por punteros |

- Los requisitos se oponen: el inicio favorece a `LinkedList`; la paginación por posición favorece a `ArrayList`.
- Recomendación razonada: la **paginación aleatoria domina** (se ejecuta mucho más), y 200 000 elementos desplazados por una inserción siguen siendo baratos si ocurren con poca frecuencia; se elige `ArrayList`.
- Alternativa: `ArrayDeque`/`LinkedList` como cola para los urgentes al inicio, y una `ArrayList` para el resto; `ArrayDeque` ofrece O(1) en los extremos pero **no** acceso por índice, por eso no basta por sí sola.
- Cualquier respuesta que justifique con complejidad y con el patrón de uso es válida.

</details>

---

## 📘 Módulo 09 — Relaciones entre clases

### Pregunta 19 · Selección única · Módulo 09

Se describen estas relaciones en MediSalud:

- Una `Clinica` contiene varios `Medico`; si la clínica se cierra, los médicos siguen existiendo y pueden trabajar en otra.
- Un `Paciente` tiene una `HistoriaClinica` que se crea con el paciente y no tiene sentido sin él.

**Pregunta**: ¿Qué clasificación y qué implementación son las más coherentes?

- A) `Clinica`–`Medico`: composición; `Paciente`–`HistoriaClinica`: agregación. La clínica crea los médicos con `new` en su constructor.
- B) `Clinica`–`Medico`: agregación (los médicos se reciben desde fuera); `Paciente`–`HistoriaClinica`: composición (el paciente crea y posee la historia).
- C) Ambas son asociaciones unidireccionales, sin diferencia de ciclo de vida.
- D) Ambas son composición, porque en las dos hay una colección o una referencia.

<details>
<summary>🔑 Ver solución</summary>

**Respuesta: B.**

- Agregación: el "todo" referencia partes que **tienen vida propia**; los `Medico` se crean fuera y se agregan a la clínica.
- Composición: las partes **dependen** del todo; el `Paciente` crea su `HistoriaClinica` y ninguna otra clase debería compartirla ni conservarla tras eliminar al paciente.
- El criterio decisivo es el **ciclo de vida**, no la existencia de una referencia.

</details>

### Pregunta 20 · Abierta · Módulo 09

Se modela una asociación **bidireccional** entre `Paciente` (con `List<Cita> citas`) y `Cita`
(con `Paciente paciente`).

**Pregunta**: Responder:

- ¿Qué invariante debe cumplirse entre ambos lados?
- ¿Cómo se implementa `agendar` para mantenerla?
- ¿Qué error ocurre si ambos `toString()` imprimen al otro objeto completo?
- ¿En qué casos conviene preferir la asociación unidireccional?

<details>
<summary>🔑 Ver solución</summary>

- **Invariante**: si `cita` está en `paciente.getCitas()`, entonces `cita.getPaciente() == paciente`, y viceversa. Nunca debe existir un solo lado.
- **Implementación**: un único punto de entrada que actualice ambos lados, por ejemplo:

  ```java
  public void agendar(Cita cita) {
      citas.add(cita);
      cita.asignarPaciente(this);
  }
  ```

  y que el segundo método no vuelva a llamar al primero (o lo haga con una verificación) para evitar recursión infinita.
- **`toString()` mutuo**: recursión infinita y `StackOverflowError`. Se resuelve imprimiendo solo el código del otro lado.
- **Unidireccional**: cuando solo se navega en un sentido (por ejemplo, la cita conoce a su paciente y las citas de un paciente se consultan al repositorio/DAO); reduce acoplamiento y evita mantener la invariante. Es la decisión del proyecto final.

</details>

---

## 📘 Módulo 10 — Conjuntos, mapas, enumeraciones y excepciones

### Pregunta 21 · Selección única · Módulo 10

```java
Set<String> s = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
s.add("Ana");
s.add("ANA");
s.add("beto");
System.out.println(s.size() + " " + s);
```

**Pregunta**: ¿Qué imprime?

- A) `3 [ANA, Ana, beto]`
- B) `2 [Ana, beto]`
- C) `2 [ANA, beto]`
- D) `3 [Ana, ANA, beto]`

<details>
<summary>🔑 Ver solución</summary>

**Respuesta: B (`2 [Ana, beto]`).**

- Un `TreeSet` determina la igualdad con su **comparador**, no con `equals`: `"ANA"` y `"Ana"` comparan como `0`, así que `"ANA"` se descarta.
- Se conserva el primer elemento insertado (`Ana`) y el orden es el del comparador.

</details>

### Pregunta 22 · Selección múltiple · Módulo 10

**Pregunta**: ¿Cuáles afirmaciones sobre `HashMap` y `TreeMap` son correctas?

- A) `HashMap` admite una clave `null`.
- B) `TreeMap` con ordenamiento natural lanza `NullPointerException` al insertar una clave `null`.
- C) `HashMap` garantiza que el recorrido siga el orden de inserción.
- D) `TreeMap` mantiene sus claves ordenadas.
- E) `TreeMap` ofrece búsqueda y inserción en tiempo constante.

<details>
<summary>🔑 Ver solución</summary>

**Respuesta: A, B y D.**

- C es falsa: `HashMap` no garantiza ningún orden (`LinkedHashMap` sí conserva inserción).
- E es falsa: `TreeMap` es un árbol rojo-negro, O(log n); `HashMap` es O(1) promedio.

</details>

### Pregunta 23 · Selección única · Módulo 10

```java
static int f() { try { return 1; } finally { System.out.print("F"); } }
static int g() { int x = 1; try { return x; } finally { x = 2; } }
// ...
System.out.println(f() + " " + g());
```

**Pregunta**: ¿Qué imprime en consola?

- A) `1 2`
- B) `F1 2`
- C) `F1 1`
- D) `1 1`

<details>
<summary>🔑 Ver solución</summary>

**Respuesta: C (`F1 1`).**

- El bloque `finally` se ejecuta **antes** de que `f()` entregue su valor, por eso `F` se imprime primero.
- En `g()`, el valor de retorno (`1`) se evalúa y se guarda **antes** del `finally`; modificar `x` después no cambia lo que se devuelve.

</details>

### Pregunta 24 · Abierta · Módulo 10

En MediSalud existen `CitaNoEncontradaException` y `TransicionInvalidaException`, ambas
excepciones comprobadas (*checked*).

**Pregunta**: Responder:

- Justificar si deberían ser *checked* o *unchecked*.
- Explicar por qué el siguiente código no compila.
- Mostrar cómo se reescribe para tratar cada caso con un mensaje distinto.

```java
try {
    servicio.confirmarCita("C001");
} catch (Exception e) {
    System.out.println("Error general");
} catch (CitaNoEncontradaException e) {
    System.out.println("No existe la cita");
}
```

<details>
<summary>🔑 Ver solución</summary>

- **Checked** es defendible: son errores **esperables y recuperables** del dominio (código mal digitado, estado no permitido) que el llamador debe decidir cómo manejar; el compilador lo exige. *Unchecked* se reserva para errores de programación (`IllegalArgumentException`, `NullPointerException`).
- No compila: el `catch (Exception e)` captura todo, incluida `CitaNoEncontradaException`, de modo que el segundo `catch` es **inalcanzable** (*exception already caught*). Los `catch` se ordenan de lo más específico a lo más general.
- Reescritura:

  ```java
  try {
      servicio.confirmarCita("C001");
  } catch (CitaNoEncontradaException e) {
      System.out.println("No existe la cita: " + e.getMessage());
  } catch (TransicionInvalidaException e) {
      System.out.println("Estado no permitido: " + e.getMessage());
  }
  ```

  Alternativamente, un `multi-catch` (`catch (A | B e)`) si el tratamiento es el mismo (los tipos no pueden estar relacionados por herencia).

</details>

---

## 📘 Módulo 11 — Principios SOLID

### Pregunta 25 · Selección única · Módulo 11

`ServicioFacturacion.calcularMonto(Cita c)` contiene una cadena `if / else if` según el tipo
de consulta (`GENERAL`, `ESPECIALISTA`, `URGENCIAS`). Cada vez que MediSalud crea un tipo de
consulta nuevo, se modifica ese método.

**Pregunta**: ¿Qué principio se viola principalmente y cuál es la corrección más adecuada?

- A) Responsabilidad Única; separar la clase en dos.
- B) Abierto/Cerrado; extraer una interfaz `EstrategiaCosto` con una implementación por tipo, de modo que un tipo nuevo se agregue sin modificar el servicio.
- C) Sustitución de Liskov; hacer `final` el método.
- D) Segregación de Interfaces; dividir `Cita` en dos interfaces.

<details>
<summary>🔑 Ver solución</summary>

**Respuesta: B.**

- El código debe estar **abierto a extensión** y **cerrado a modificación**: cada tipo nuevo obliga a editar código ya probado.
- La solución es polimórfica (patrón *Strategy*): `ServicioFacturacion` depende de `EstrategiaCosto` y recibe la implementación adecuada.

</details>

### Pregunta 26 · Selección múltiple · Módulo 11

**Pregunta**: ¿Cuáles diseños violan el Principio de Sustitución de Liskov?

- A) `Cuadrado extends Rectangulo`, donde `setAncho` también modifica el alto, rompiendo el contrato esperado por quien usa un `Rectangulo`.
- B) `PacienteVip extends Paciente`, que sobrescribe `getDescuento()` devolviendo un valor mayor y respetando el contrato de la clase base.
- C) `LibroReferencia extends Libro`, cuyo método `prestar()` lanza `UnsupportedOperationException`, siendo que `Libro.prestar()` promete prestar.
- D) `Docente extends Usuario`, que añade el método `getDepartamento()` sin alterar ninguno heredado.

<details>
<summary>🔑 Ver solución</summary>

**Respuesta: A y C.**

- A: el código cliente que asigna ancho y espera que el alto no cambie se rompe con el subtipo.
- C: una subclase que **rechaza** un comportamiento que la base promete no es sustituible.
- B y D respetan los contratos (B solo especializa un valor; D solo agrega).

</details>

### Pregunta 27 · Abierta · Módulo 11

```java
public class ServicioPacientes {
    private final PacienteDAO dao = new PacienteDAO();
    // ...
}

public interface RepositorioClinica {
    void guardarPaciente(Paciente p);
    void guardarMedico(Medico m);
    void guardarCita(Cita c);
    Paciente buscarPaciente(String codigo);
    Medico buscarMedico(String codigo);
    Cita buscarCita(String codigo);
}
```

`ServicioFacturacion` solo usa `buscarCita`.

**Pregunta**: Identificar los dos principios SOLID comprometidos, refactorizar el diseño y
explicar qué ganancia concreta obtiene la prueba unitaria de `ServicioPacientes`.

<details>
<summary>🔑 Ver solución</summary>

- **Inversión de Dependencias (DIP)**: `ServicioPacientes` crea la clase concreta con `new PacienteDAO()`; el servicio (alto nivel) depende de un detalle (JDBC). Se refactoriza inyectando una abstracción:

  ```java
  public class ServicioPacientes {
      private final RepositorioPacientes repositorio;
      public ServicioPacientes(RepositorioPacientes repositorio) { this.repositorio = repositorio; }
  }
  ```

- **Segregación de Interfaces (ISP)**: `RepositorioClinica` obliga a `ServicioFacturacion` a depender de métodos que no usa. Se divide en `RepositorioPacientes`, `RepositorioMedicos` y `RepositorioCitas`.
- **Ganancia en pruebas**: se inyecta una implementación en memoria o un *stub* (como `RepositorioPacientesMemoria`) sin base de datos MySQL; las pruebas son rápidas, aisladas y repetibles.
- Bonus: permite cambiar JDBC por archivos sin tocar la capa de servicios.

</details>

---

## 📘 Módulo 12 — Patrones creacionales

### Pregunta 28 · Selección única · Módulo 12

**Pregunta**: ¿Cuál implementación de *Singleton* es correcta en un entorno con varios hilos?

- A) `getInstancia()` crea el objeto si `instancia == null`, sin sincronización.
- B) *Double-checked locking* con la variable `instancia` declarada sin `volatile`.
- C) Clase interna estática `Holder` que contiene `static final GestorClinica INSTANCIA = new GestorClinica();` y `getInstancia()` retorna `Holder.INSTANCIA`.
- D) Constructor `public` más un atributo estático `instancia` asignado manualmente.

<details>
<summary>🔑 Ver solución</summary>

**Respuesta: C.**

- A: condición de carrera; dos hilos pueden crear dos instancias.
- B: sin `volatile`, otro hilo puede ver una referencia publicada pero un objeto no totalmente construido (reordenamiento).
- C: la JVM garantiza la inicialización perezosa y segura de la clase interna una sola vez.
- D: el constructor público permite crear más instancias.

</details>

### Pregunta 29 · Abierta · Módulo 12

```java
public class Paciente implements Cloneable {
    private String nombre;
    private HistoriaClinica historia;   // contiene List<String> consultas

    @Override
    public Paciente clone() throws CloneNotSupportedException {
        return (Paciente) super.clone();
    }
}
```

**Pregunta**: Explicar qué defecto tiene este patrón *Prototype* cuando se clona un paciente
para crear un "gemelo" con otro nombre, mostrar la secuencia que lo evidencia y corregirlo.

<details>
<summary>🔑 Ver solución</summary>

- `super.clone()` hace una copia **superficial**: el clon y el original comparten la **misma** `HistoriaClinica` (misma referencia).
- Evidencia:

  ```java
  Paciente copia = original.clone();
  copia.getHistoriaClinica().agregarConsulta("Gripe");
  // original también "tiene" la consulta "Gripe"
  ```

- Corrección con copia profunda:

  ```java
  @Override
  public Paciente clone() throws CloneNotSupportedException {
      Paciente copia = (Paciente) super.clone();
      copia.historia = historia.clone(); // HistoriaClinica copia su lista de consultas
      return copia;
  }
  ```

  y `HistoriaClinica.clone()` debe crear una nueva `ArrayList<>(consultas)`. Alternativa: constructor de copia.

</details>

### Pregunta 30 · Selección múltiple · Módulo 12

**Pregunta**: ¿Cuáles afirmaciones sobre *Factory Method*, *Abstract Factory* y *Builder* son correctas?

- A) *Factory Method* delega en las subclases la decisión de qué clase concreta instanciar.
- B) *Abstract Factory* crea **familias** de objetos relacionados sin especificar sus clases concretas.
- C) *Builder* es útil cuando el objeto tiene muchos parámetros opcionales y se construye por pasos.
- D) *Abstract Factory* se usa para evitar tener más de una instancia de una clase.

<details>
<summary>🔑 Ver solución</summary>

**Respuesta: A, B y C.**

D describe el *Singleton*, no *Abstract Factory*.

</details>

---

## 📘 Módulo 13 — Patrones estructurales

### Pregunta 31 · Selección única · Módulo 13

```java
// Factura base = 100000
Factura f = new FacturaConDescuentoAfiliado(          // -10 % sobre lo que recibe
                new FacturaConRecargoNocturno(base)); // +15000 fijos
```

**Pregunta**: ¿Cuánto vale el total de `f`?

- A) `105 000`
- B) `103 500`
- C) `100 000`
- D) `101 500`

<details>
<summary>🔑 Ver solución</summary>

**Respuesta: B (`103 500`).**

- El *Decorator* más interno se aplica primero: `100 000 + 15 000 = 115 000`.
- Luego el descuento del 10 %: `115 000 × 0,9 = 103 500`.
- Invertir el orden daría `100 000 × 0,9 + 15 000 = 105 000`: **el orden de decoración importa** cuando las operaciones no conmutan.

</details>

### Pregunta 32 · Selección múltiple · Módulo 13

**Pregunta**: ¿Cuáles emparejamientos patrón–propósito son correctos?

- A) *Facade*: ofrecer una interfaz simple que oculta la coordinación de varios subsistemas.
- B) *Adapter*: hacer compatible una interfaz existente con la que espera el cliente.
- C) *Proxy*: mantener la **misma interfaz** del objeto real y controlar el acceso a él.
- D) *Decorator*: **cambiar la interfaz** del objeto envuelto para adaptarla a otro cliente.

<details>
<summary>🔑 Ver solución</summary>

**Respuesta: A, B y C.**

D es falsa: el *Decorator* conserva la interfaz y **agrega responsabilidades**; cambiar la interfaz es propio del *Adapter*.

</details>

### Pregunta 33 · Abierta · Módulo 13

Biblioteca Universitaria se organiza así: `Biblioteca → Sección → Estante → Libro`, con
secciones que pueden contener subsecciones.

**Pregunta**: Diseñar con *Composite* una solución que permita calcular el **total de libros**
de cualquier nodo con una sola llamada. Mostrar la interfaz común, la hoja y el compuesto, y
explicar la ventaja frente a un recorrido con `instanceof`.

<details>
<summary>🔑 Ver solución</summary>

```java
public interface ElementoBiblioteca {
    int contarLibros();
}

public class Libro implements ElementoBiblioteca {
    public int contarLibros() { return 1; }
}

public class Contenedor implements ElementoBiblioteca {   // Sección, Estante...
    private final List<ElementoBiblioteca> hijos = new ArrayList<>();
    public void agregar(ElementoBiblioteca e) { hijos.add(e); }
    public int contarLibros() {
        return hijos.stream().mapToInt(ElementoBiblioteca::contarLibros).sum();
    }
}
```

- Hoja y compuesto se tratan **uniformemente**, a cualquier profundidad.
- Sin *Composite*, el cliente necesitaría `instanceof`, casts y recursión manual, y cada tipo de nodo nuevo obligaría a modificar ese código (viola Abierto/Cerrado).

</details>

### Pregunta 34 · Selección única · Módulo 13

Biblioteca Universitaria mantiene 500 000 objetos `Ejemplar` en memoria. Cada uno tiene
`titulo`, `autor` e `isbn` (iguales para todos los ejemplares de un mismo libro), y además
`codigoBarras` y `estadoPrestamo` (distintos por ejemplar).

**Pregunta**: Aplicando *Flyweight*, ¿qué se comparte y qué se mantiene por ejemplar?

- A) Se comparte `codigoBarras`; el resto queda por ejemplar.
- B) Se comparten `titulo`, `autor` e `isbn` en un objeto `Libro` único por título (estado **intrínseco**); `codigoBarras` y `estadoPrestamo` son estado **extrínseco**/propio de cada ejemplar.
- C) No se comparte nada: *Flyweight* solo sirve para imágenes.
- D) Se comparte `estadoPrestamo` para que todos los ejemplares cambien a la vez.

<details>
<summary>🔑 Ver solución</summary>

**Respuesta: B.**

- El estado **intrínseco** (invariante e idéntico entre ejemplares) se comparte en un `Libro` reutilizado desde una fábrica/caché.
- El estado **extrínseco** (propio de cada ejemplar) permanece fuera del objeto compartido.
- D es incorrecta: compartir el estado de préstamo haría que prestar un ejemplar marcara todos como prestados.

</details>

---

## 📘 Módulo 14 — Patrones de comportamiento

### Pregunta 35 · Selección única · Módulo 14

**Pregunta**: ¿Cuál enunciado distingue correctamente *State* de *Strategy*?

- A) Son idénticos: ambos reemplazan condicionales y no existe diferencia conceptual.
- B) En *State* el propio objeto cambia de comportamiento al cambiar su estado interno y los estados suelen conocer sus transiciones; en *Strategy* el **cliente** elige el algoritmo y las estrategias son independientes entre sí.
- C) *Strategy* gestiona transiciones válidas entre estados y *State* elige algoritmos intercambiables.
- D) *State* solo se usa con enumeraciones y *Strategy* solo con clases abstractas.

<details>
<summary>🔑 Ver solución</summary>

**Respuesta: B.**

- `EstadoCita` (`PENDIENTE → CONFIRMADA → ATENDIDA`, con transiciones válidas) es *State*.
- `EstrategiaCostoConsultaGeneral` / `...Especialista` son algoritmos intercambiables elegidos desde fuera: *Strategy*.
- C invierte los roles; D es falsa (ambos pueden usar clases, interfaces o enums).

</details>

### Pregunta 36 · Selección múltiple · Módulo 14

**Pregunta**: ¿Cuáles son riesgos reales del patrón *Observer* en una aplicación como MediSalud?

- A) Fuga de memoria si un observador no se desregistra y el sujeto lo mantiene referenciado.
- B) `ConcurrentModificationException` si un observador se elimina de la lista del sujeto mientras este la recorre para notificar.
- C) Bloqueo del sujeto si un observador hace trabajo lento de forma síncrona durante la notificación.
- D) *Observer* impide que haya más de un observador por sujeto.

<details>
<summary>🔑 Ver solución</summary>

**Respuesta: A, B y C.**

- Mitigaciones: desregistrar observadores, recorrer una copia de la lista, o delegar el trabajo en otro hilo (como hace `ObservadorCitaNotificacion` al encolar en `HiloNotificaciones`).
- D es falsa: justamente el patrón admite **muchos** observadores.

</details>

### Pregunta 37 · Abierta · Módulo 14

MediSalud quiere un historial con **deshacer** para las acciones "agendar cita" y "cancelar cita".

**Pregunta**: Diseñar la solución con *Command*: definir la interfaz y el historial, y explicar el
conflicto que aparece al deshacer una cancelación dado que `CANCELADA` es un estado final en la
máquina de *State*, y cómo *Memento* ayuda a resolverlo.

<details>
<summary>🔑 Ver solución</summary>

```java
public interface Comando {
    void ejecutar();
    void deshacer();
}

// Historial: pila de comandos ejecutados
Deque<Comando> historial = new ArrayDeque<>();
void ejecutar(Comando c) { c.ejecutar(); historial.push(c); }
void deshacerUltimo()    { if (!historial.isEmpty()) historial.pop().deshacer(); }
```

- Deshacer **agendar** = eliminar la cita (o marcarla anulada), operación sencilla.
- Deshacer **cancelar** exige pasar de `CANCELADA` a `PENDIENTE` o `CONFIRMADA`, transición que la máquina de estados **prohíbe**. Se resuelve de dos formas: o una operación interna de restauración que no pase por `puedeTransicionarA` (y se documenta como excepción deliberada), o un método de dominio explícito.
- *Memento*: antes de ejecutar, el comando guarda una **instantánea** del estado previo de la cita (estado y motivo, por ejemplo) y `deshacer()` la restaura. Así no depende de "adivinar" el estado anterior.
- Observación: se debe decidir también si restaurar notifica a los observadores.

</details>

---

## 📘 Módulo 15 — Introducción a la concurrencia

### Pregunta 38 · Selección única · Módulo 15

```java
Thread t = new Thread(() -> System.out.println(Thread.currentThread().getName()));
t.setName("worker");
t.run();
t.start();
t.join();
```

**Pregunta**: ¿Qué imprime, y en qué orden?

- A) `worker` y luego `worker`
- B) `main` y luego `worker`
- C) `worker` y luego `main`
- D) Solo `worker`, porque `run()` no hace nada.

<details>
<summary>🔑 Ver solución</summary>

**Respuesta: B.**

- `t.run()` es una llamada de método normal: se ejecuta en el hilo **actual** (`main`) y de forma síncrona.
- `t.start()` crea el hilo nuevo, que ejecuta `run()` con el nombre `worker`; `join()` espera a que termine.

</details>

### Pregunta 39 · Selección múltiple · Módulo 15

**Pregunta**: ¿Cuáles afirmaciones sobre `interrupt()` y `join()` son correctas?

- A) Si el hilo está en `Thread.sleep(...)`, `interrupt()` provoca una `InterruptedException` en ese hilo.
- B) `interrupt()` termina forzosamente el hilo de inmediato.
- C) Si el hilo está ejecutando código normal, `interrupt()` activa su bandera de interrupción, que el propio hilo debe consultar.
- D) `join()` hace que el hilo que lo invoca espere a que el otro hilo termine.

<details>
<summary>🔑 Ver solución</summary>

**Respuesta: A, C y D.**

B es falsa: la interrupción en Java es **cooperativa**; el hilo decide cuándo y cómo terminar. Por eso `HiloNotificaciones.detener()` solicita el cierre y `Principal` espera con `join()`.

</details>

---

## 📘 Módulo 16 — Sincronización de hilos y procesos atómicos

### Pregunta 40 · Selección única · Módulo 16

```java
private volatile int contador = 0;

void incrementar() { contador++; }   // 2 hilos, 1000 invocaciones cada uno
```

**Pregunta**: ¿Qué se puede afirmar del valor final de `contador`?

- A) Siempre `2000`, porque `volatile` hace atómico el incremento.
- B) Puede ser menor que `2000`: `volatile` garantiza visibilidad, pero `contador++` son tres pasos (leer, sumar, escribir) que pueden intercalarse.
- C) Siempre `1000`, porque cada hilo sobrescribe al otro.
- D) Lanza `ConcurrentModificationException`.

<details>
<summary>🔑 Ver solución</summary>

**Respuesta: B.**

- Corrección: `AtomicInteger.incrementAndGet()` o `synchronized` en el método.
- `volatile` resuelve la **visibilidad** y el reordenamiento, no la **atomicidad** de operaciones compuestas.

</details>

### Pregunta 41 · Abierta · Módulo 16

```java
void transferirCita(AgendaMedico origen, AgendaMedico destino, Cita cita) {
    synchronized (origen) {
        synchronized (destino) {
            origen.quitar(cita);
            destino.agregar(cita);
        }
    }
}
// Hilo 1: transferirCita(agendaA, agendaB, c1)
// Hilo 2: transferirCita(agendaB, agendaA, c2)
```

**Pregunta**: Explicar qué falla puede ocurrir, cuáles son las condiciones necesarias para que
ocurra y proponer una corrección.

<details>
<summary>🔑 Ver solución</summary>

- **Interbloqueo (*deadlock*)**: el hilo 1 toma el candado de A y espera el de B; el hilo 2 toma el de B y espera el de A. Ninguno avanza.
- **Condiciones de Coffman**: exclusión mutua, retención y espera, no apropiación y espera circular. Basta romper una.
- **Corrección frecuente**: imponer un **orden global** de adquisición (por ejemplo, por código de médico):

  ```java
  AgendaMedico primero = origen.getCodigo().compareTo(destino.getCodigo()) < 0 ? origen : destino;
  AgendaMedico segundo = (primero == origen) ? destino : origen;
  synchronized (primero) { synchronized (segundo) { /* ... */ } }
  ```

- Alternativas: `ReentrantLock.tryLock` con tiempo de espera, o un único candado para toda la operación (menos concurrencia).

</details>

### Pregunta 42 · Selección múltiple · Módulo 16

**Pregunta**: ¿Cuáles afirmaciones sobre `synchronized` son correctas?

- A) Un método de instancia `synchronized` bloquea el monitor de `this`.
- B) Un método estático `synchronized` bloquea el monitor del objeto `Class`.
- C) Dos hilos que invocan dos métodos `synchronized` **distintos** sobre el **mismo** objeto se excluyen entre sí.
- D) Dos hilos que invocan un método `synchronized` sobre **objetos diferentes** de la misma clase se excluyen entre sí.

<details>
<summary>🔑 Ver solución</summary>

**Respuesta: A, B y C.**

- C: ambos métodos usan el mismo monitor (`this`).
- D es falsa: cada objeto tiene su propio monitor, por lo que no se bloquean entre sí (salvo que el método sea estático).

</details>

---

## 📘 Módulo 17 — Manejo de archivos

### Pregunta 43 · Selección única · Módulo 17

```java
class Cita implements Serializable {
    private transient List<ObservadorCita> observadores = new ArrayList<>();
    public void agregarObservador(ObservadorCita o) { observadores.add(o); }
}
// Se serializa una Cita a disco, se lee de nuevo y se invoca agregarObservador(...)
```

**Pregunta**: ¿Qué ocurre, suponiendo que la clase **no** define `readObject`?

- A) Funciona: `transient` conserva el valor inicial `new ArrayList<>()`.
- B) Lanza `NotSerializableException` al escribir.
- C) La lista queda en `null` tras deserializar y `agregarObservador` lanza `NullPointerException`.
- D) La lista se serializa igualmente porque `ObservadorCita` es una interfaz.

<details>
<summary>🔑 Ver solución</summary>

**Respuesta: C.**

- Los campos `transient` no se serializan, y al deserializar **no se ejecutan los inicializadores de campo** ni el constructor de una clase `Serializable`; el campo queda en su valor por defecto (`null`).
- Corrección (la que usa el proyecto): `private void readObject(ObjectInputStream in) { in.defaultReadObject(); observadores = new ArrayList<>(); }`.

</details>

### Pregunta 44 · Abierta · Módulo 17

**Pregunta**: Responder sobre serialización:

- ¿Qué ocurre al serializar un objeto con un atributo cuya clase no implementa `Serializable`?
- ¿Cómo se resuelve sin modificar esa clase?
- ¿Qué papel cumple `serialVersionUID`?
- Si una clase `Serializable` hereda de una clase que no lo es, ¿qué pasa con los campos de la superclase al deserializar?

<details>
<summary>🔑 Ver solución</summary>

- Se lanza `NotSerializableException` en tiempo de ejecución al escribir.
- Si no se puede modificar la clase del atributo: declararlo `transient` y reconstruirlo en `readObject`, o serializar solo los datos necesarios (por ejemplo, el código) y volver a buscar el objeto al leer.
- `serialVersionUID` identifica la **versión** de la clase; si el archivo guardado tiene un identificador distinto al de la clase actual, la lectura falla con `InvalidClassException`. Declararlo explícito permite evolucionar la clase de forma controlada.
- La superclase no serializable no aporta estado al archivo: durante la deserialización se ejecuta su **constructor sin argumentos** (obligatorio y accesible) y sus campos quedan con lo que ese constructor asigne, no con los valores que tenían al guardar.

</details>

---

## 📘 Módulo 18 — Java Database Connectivity

### Pregunta 45 · Selección múltiple · Módulo 18

**Pregunta**: ¿Cuáles afirmaciones sobre JDBC son correctas?

- A) `PreparedStatement` con parámetros `?` previene la inyección SQL porque los valores se envían separados de la sentencia.
- B) `executeUpdate` devuelve la cantidad de filas afectadas.
- C) Los índices de columna y de parámetro en JDBC comienzan en `1`.
- D) Concatenar la entrada del usuario con `+` en un `Statement` es seguro si el texto se valida en la vista.

<details>
<summary>🔑 Ver solución</summary>

**Respuesta: A, B y C.**

D es falsa: la validación en la vista no protege contra cadenas manipuladas (por ejemplo `' OR '1'='1`); la defensa correcta está en la capa de acceso a datos con parámetros.

</details>

### Pregunta 46 · Abierta · Módulo 18

Se analiza este método de `CitaDAO` del proyecto:

```java
private Cita mapearCita(ResultSet resultado) throws SQLException {
    Paciente paciente = repositorioPacientes.buscarPorCodigo(resultado.getString("paciente_codigo"));
    Medico medico = repositorioMedicos.buscarPorCodigo(resultado.getString("medico_codigo"));
    // ...
}
```

Cada `buscarPorCodigo` abre su propia conexión con `ConexionBD.obtenerConexion()`. La tabla
`citas` tiene 30 filas y se invoca `listarTodas()`.

**Pregunta**: Calcular cuántas consultas SQL y conexiones se realizan, nombrar el problema,
proponer una alternativa con SQL y señalar un riesgo adicional si el código de paciente no existe.

<details>
<summary>🔑 Ver solución</summary>

- Consultas: `1 + 30 × 2 = 61`; conexiones abiertas: `61` (una por consulta).
- Problema: **N+1 consultas**: una consulta principal más dos por fila.
- Alternativa: una sola consulta con `JOIN`:

  ```sql
  SELECT c.codigo, c.fecha, c.estado,
         p.codigo AS p_codigo, p.nombre_completo AS p_nombre, p.edad,
         m.codigo AS m_codigo, m.nombre_completo AS m_nombre, m.especialidad
  FROM citas c
  JOIN pacientes p ON p.codigo = c.paciente_codigo
  JOIN medicos   m ON m.codigo = c.medico_codigo
  ```

  Y construir las entidades desde las columnas del propio `ResultSet`. Otras opciones válidas: reutilizar una conexión o cachear pacientes/médicos ya cargados.
- Riesgo: `buscarPorCodigo` devuelve `null` si no existe, y construir `new Cita(codigo, null, ...)` deja la cita inconsistente y provoca `NullPointerException` más adelante (por ejemplo en `toString`). La clave foránea de la tabla lo evita en la práctica, pero el código no debería asumirlo.

</details>

---

## 📘 Módulo 19 — Expresiones lambda

### Pregunta 47 · Selección única · Módulo 19

```java
int base = 10;
Supplier<Integer> s = () -> base + 1;
base++;
```

**Pregunta**: ¿Qué ocurre al compilar?

- A) Compila; `s.get()` devuelve `12`.
- B) Compila; `s.get()` devuelve `11`.
- C) Error de compilación: las variables locales usadas en una lambda deben ser `final` o *efectivamente finales*, y `base++` la modifica.
- D) Lanza `IllegalStateException` en ejecución.

<details>
<summary>🔑 Ver solución</summary>

**Respuesta: C.**

El compilador rechaza capturar una variable local que se reasigna en cualquier punto (`local variables referenced from a lambda expression must be final or effectively final`). Los atributos de instancia sí pueden modificarse, o puede usarse un contenedor mutable (`AtomicInteger`), aunque con cautela.

</details>

### Pregunta 48 · Selección múltiple · Módulo 19

**Pregunta**: ¿Cuáles de estas interfaces pueden llevar la anotación `@FunctionalInterface` sin error de compilación?

- A) Una interfaz con un único método abstracto y dos métodos `default`.
- B) Una interfaz con dos métodos abstractos.
- C) Una interfaz con un método abstracto propio y la declaración de `boolean equals(Object o)`.
- D) Una interfaz que no tiene ningún método abstracto.

<details>
<summary>🔑 Ver solución</summary>

**Respuesta: A y C.**

- A: los métodos `default` no cuentan.
- B y D: con dos abstractos o ninguno no es funcional.
- C: redeclarar un método público de `Object` (como `equals`) no cuenta como abstracto adicional; es el caso de `Comparator`.

</details>

---

## 📘 Módulo 20 — Stream API

### Pregunta 49 · Selección única · Módulo 20

```java
List<String> l = List.of("b", "a", "c");
Stream<String> s = l.stream().filter(x -> { System.out.print("f" + x + " "); return true; });
System.out.println("listo");
```

**Pregunta**: ¿Qué imprime?

- A) `fb fa fc listo`
- B) `listo fb fa fc`
- C) `listo`
- D) `fb fa fc`

<details>
<summary>🔑 Ver solución</summary>

**Respuesta: C (`listo`).**

Las operaciones intermedias (`filter`, `map`) son **perezosas**: nada se ejecuta hasta que se invoca una operación terminal (`collect`, `forEach`, `count`...). Como no hay terminal, la lambda nunca se invoca.

</details>

### Pregunta 50 · Abierta · Módulo 20

Con `List<Cita> citas` de MediSalud, cada `Cita` tiene `getMedico().getCodigo()` y `getEstado()`.

**Pregunta**: Escribir con Stream API un fragmento que imprima el número de citas `CANCELADA`
por médico, ordenado de mayor a menor (y por código ante empates). Explicar por qué
`groupingBy` no ordena por sí solo y qué hace `flatMap` si se quisiera además obtener todos los
diagnósticos distintos de todos los pacientes (`getHistoriaClinica().getConsultas()`).

<details>
<summary>🔑 Ver solución</summary>

```java
Map<String, Long> canceladasPorMedico = citas.stream()
        .filter(c -> c.getEstado() == EstadoCita.CANCELADA)
        .collect(Collectors.groupingBy(c -> c.getMedico().getCodigo(), Collectors.counting()));

canceladasPorMedico.entrySet().stream()
        .sorted(Map.Entry.<String, Long>comparingByValue().reversed()
                .thenComparing(Map.Entry.comparingByKey()))
        .forEach(e -> System.out.println(e.getKey() + " = " + e.getValue()));
```

- `groupingBy` produce por defecto un `HashMap`, sin orden garantizado; el orden se logra ordenando las entradas (o recolectando a un `LinkedHashMap`) en un segundo paso.
- `flatMap` convierte cada elemento en un stream y **aplana** los resultados en uno solo:

  ```java
  List<String> diagnosticos = pacientes.stream()
          .flatMap(p -> p.getHistoriaClinica().getConsultas().stream())
          .distinct()
          .collect(Collectors.toList());
  ```

  Con `map` se obtendría un `Stream<List<String>>` (listas anidadas), no un flujo de textos.

</details>

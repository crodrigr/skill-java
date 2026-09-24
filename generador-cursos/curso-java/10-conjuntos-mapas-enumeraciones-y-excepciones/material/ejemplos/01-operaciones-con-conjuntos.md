# 💡 Ejemplo 01 — Operaciones con conjuntos

## 🌍 Contexto

Hasta ahora, para agrupar varios datos usaste un arreglo o una `List` (Módulo 8). Pero ninguna de las
dos evita que un mismo valor se repita: si necesitás garantizar que cada especialidad médica aparezca
una sola vez, tienes que comprobarlo vos mismo antes de cada `add`. Un `Set` hace esa comprobación por
vos: agregar un valor que ya está no cambia nada.

**Qué busca demostrar este ejemplo**: cómo declarar un `Set`, usar sus operaciones más comunes, y
combinar dos conjuntos con unión, intersección y diferencia.

## 🏥 Caso de estudio

MediSalud: el conjunto de especialidades disponibles en una clínica, y cómo se combinan las
especialidades de dos clínicas distintas.

## 🌳 Árbol de archivos (como se vería en VS Code)

```text
Modulo10ConjuntosMapasEnumeracionesYExcepciones/
└── src/
    └── com/
        └── medisalud/
            └── Demo.java
```

## 💻 Archivo: Demo.java

```java
package com.medisalud;

import java.util.HashSet;
import java.util.Set;

public class Demo {
    public static void main(String[] args) {
        // Datos de entrada
        Set<String> especialidades = new HashSet<>();
        especialidades.add("Cardiologia");
        especialidades.add("Pediatria");
        especialidades.add("Cardiologia");

        System.out.println("size=" + especialidades.size());
        System.out.println("contiene Cardiologia=" + especialidades.contains("Cardiologia"));
        especialidades.remove("Pediatria");
        System.out.println("size tras remove=" + especialidades.size());
        for (String especialidad : especialidades) {
            System.out.println("- " + especialidad);
        }

        // Dos clinicas con sus propias especialidades
        Set<String> clinicaNorte = new HashSet<>();
        clinicaNorte.add("Cardiologia");
        clinicaNorte.add("Pediatria");
        Set<String> clinicaSur = new HashSet<>();
        clinicaSur.add("Pediatria");
        clinicaSur.add("Dermatologia");

        Set<String> union = new HashSet<>(clinicaNorte);
        union.addAll(clinicaSur);
        System.out.println("union.size=" + union.size());

        Set<String> interseccion = new HashSet<>(clinicaNorte);
        interseccion.retainAll(clinicaSur);
        System.out.println("interseccion=" + interseccion);

        Set<String> diferencia = new HashSet<>(clinicaNorte);
        diferencia.removeAll(clinicaSur);
        System.out.println("diferencia=" + diferencia);
    }
}
```

## 🧭 Explicación paso a paso

1. `especialidades.add("Cardiologia")` se llama dos veces: la segunda no agrega nada, porque el `Set`
   ya tiene ese valor.
2. `contains` comprueba si un valor está en el conjunto; `remove` lo saca; `size` da la cantidad actual
   de valores distintos.
3. `clinicaNorte` y `clinicaSur` son dos `Set<String>` independientes; para combinarlos sin modificar
   los originales, se copia uno (`new HashSet<>(clinicaNorte)`) y se opera sobre la copia.
4. `union.addAll(clinicaSur)` agrega a la copia todos los valores de `clinicaSur` que todavía no tenía:
   el resultado es la unión de ambos conjuntos.
5. `interseccion.retainAll(clinicaSur)` deja en la copia solo los valores que también están en
   `clinicaSur`: el resultado es la intersección.
6. `diferencia.removeAll(clinicaSur)` saca de la copia los valores que también están en `clinicaSur`: el
   resultado es la diferencia (lo exclusivo de `clinicaNorte`).

## ✅ Resultado esperado

```text
size=2
contiene Cardiologia=true
size tras remove=1
- Cardiologia
union.size=3
interseccion=[Pediatria]
diferencia=[Cardiologia]
```

## 🧪 Casos de prueba

| Entrada | Operación | Salida esperada |
|---|---|---|
| `especialidades` con `"Cardiologia"` agregada dos veces y `"Pediatria"` una vez | `especialidades.size()` | `2` |
| `clinicaNorte = {"Cardiologia", "Pediatria"}`, `clinicaSur = {"Pediatria", "Dermatologia"}` | Unión | 3 elementos |
| Mismo caso | Intersección | `[Pediatria]` |
| Mismo caso | Diferencia (`clinicaNorte` menos `clinicaSur`) | `[Cardiologia]` |

## 🔍 Análisis: errores frecuentes

- Operar directamente sobre uno de los conjuntos originales en vez de sobre una copia: `a.retainAll(b)`
  **modifica** `a` de forma permanente; si necesitás conservar `a` intacto, primero haz
  `new HashSet<>(a)`.
- Esperar que un `Set` mantenga el orden en que se agregaron los valores: `HashSet` no lo garantiza (a
  diferencia de una `List`).

## ❓ Preguntas de repaso

**1. [Selección]** ¿Qué pasa al hacer `especialidades.add("Cardiologia")` cuando esa especialidad ya
está en el `Set`?

- **A.** Se agrega una segunda vez.
- **B.** No pasa nada: el conjunto no crece.
- **C.** Se lanza una excepción.
- **D.** Reemplaza el valor anterior.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta correcta: B.** Un `Set` no permite duplicados: agregar un valor ya presente no tiene ningún
efecto.

</details>

**2. [Selección múltiple]** ¿Cuáles afirmaciones son verdaderas sobre `retainAll`?

- **A.** Calcula la intersección entre dos conjuntos.
- **B.** Modifica el conjunto sobre el que se invoca.
- **C.** Modifica el conjunto que se le pasa como argumento.
- **D.** Nunca cambia el tamaño del conjunto.

<details>
<summary>🔑 Ver respuesta</summary>

**Respuestas correctas: A y B.** C es falsa: el argumento no se modifica. D es falsa: el conjunto puede
achicarse si hay elementos que no están en ambos.

</details>

**3. [Abierta]** ¿Por qué conviene operar sobre una copia (`new HashSet<>(original)`) en vez de sobre el
conjunto original al calcular una unión, intersección o diferencia?

<details>
<summary>🔑 Ver respuesta</summary>

**Respuesta esperada:** porque `addAll`, `retainAll` y `removeAll` modifican el conjunto sobre el que se
invocan. Si necesitás conservar los conjuntos originales para seguir usándolos después, tienes que operar
sobre una copia, no sobre el original.

</details>

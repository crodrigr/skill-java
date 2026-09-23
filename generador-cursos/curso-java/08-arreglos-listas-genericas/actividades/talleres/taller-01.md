# 🛠️ Taller 01 — De arreglo a lista: empleados de MediSalud

## 🎯 Objetivo (RA-10, RA-11, RA-12, RA-15)

Migrar una colección de objetos de un arreglo de tamaño fijo a un `ArrayList` de tamaño dinámico,
comprobando en el camino por qué un arreglo no alcanza cuando la cantidad de elementos crece.

## 🌍 Contexto

**MediSalud** ya tiene, del Módulo 7, la jerarquía `Empleado` (abstracta) → `Medico`/`Enfermero`, cada
una con su propia versión de `calcularSueldo()`. Ahora quiere calcular la nómina total de su equipo,
guardando a cada empleado en una colección y recorriéndola de forma polimórfica.

## 🪜 Pasos

1. Crea el proyecto `DeArregloAArrayList` en Visual Studio Code (**Java: Create Java Project... → No
   build tools**) y copia (completas, sin cambios) las clases `Empleado.java`, `Medico.java` y
   `Enfermero.java` del Módulo 7.
2. Declara un arreglo `Empleado[] equipo` de tamaño fijo (3) con instancias de `Medico` y de
   `Enfermero`.
3. Recorre el arreglo con `for-each`, invocando `calcularSueldo()` sobre cada elemento y sumando el
   total: cada tipo ejecuta su propia versión, aunque la variable de recorrido es de tipo `Empleado`
   (polimorfismo a través de una colección).
4. Comprueba, intentando agregar un cuarto empleado al arreglo (`equipo[3] = ...`), que el arreglo
   tiene tamaño fijo: no lo detecta ni el compilador ni el panel Problems, pero el programa termina con
   una excepción real al ejecutarse.
5. Migra la misma lógica a `List<Empleado> equipo = new ArrayList<>();`, con `add(...)` en vez de
   asignación por índice.
6. Agrega un cuarto empleado a la `ArrayList` (algo que el arreglo original no podía hacer) y vuelve a
   calcular el total con el mismo bucle `for-each`, sin cambiar la lógica de recorrido.
7. Ejecuta con los casos de prueba, cambiando solo los datos de entrada.

## 💡 Ejemplo resuelto (parcial)

```java
Empleado[] equipo = new Empleado[3];
equipo[0] = new Medico("Ana Torres", "Cardiología");
equipo[1] = new Enfermero("Luis Peña", 4);
equipo[2] = new Medico("Marta Salinas", "Pediatría");

double total = 0.0;
for (Empleado empleado : equipo) {
    System.out.println(empleado.getNombreCompleto() + ": " + empleado.calcularSueldo());
    total += empleado.calcularSueldo();
}
System.out.println("Total: " + total);
```

Completa la migración a `ArrayList<Empleado>` siguiendo el mismo patrón que el Ejemplo 08.

## 📦 Entregable

```text
DeArregloAArrayList
└── src
    └── com
        └── medisalud
            ├── Empleado.java
            ├── Medico.java
            ├── Enfermero.java
            └── Demo.java
```

## 🧪 Casos de prueba

**Versión con arreglo** (`Empleado[3]`):

```text
Ana Torres: 1500.0
Luis Peña: 1200.0
Marta Salinas: 1500.0
Total: 4200.0
```

**Versión migrada a `ArrayList<Empleado>`** (con el cuarto empleado agregado):

```text
Ana Torres: 1500.0
Luis Peña: 1200.0
Marta Salinas: 1500.0
Carlos Ibáñez: 1150.0
Total: 5350.0
```

## 📏 Criterios de evaluación

- El arreglo `Empleado[3]` recorre e invoca `calcularSueldo()` de forma polimórfica sobre `Medico` y
  `Enfermero`, sumando el total correcto.
- La `ArrayList<Empleado>` reproduce la misma lógica de recorrido y suma, sin cambios en el bucle
  `for-each`.
- La versión migrada agrega un cuarto empleado que el arreglo original no podía guardar.

# 🟢 Básico 05 — ¿Termina este bucle?

## 🧩 Problema

En la **Biblioteca Universitaria** se escribieron cuatro bucles. Para cada uno, **sin ejecutarlo**,
diagnostica: ¿termina el programa?, ¿cuántas veces se ejecuta el bloque? y ¿qué problema tiene, si lo
tiene? Algunos de estos programas **no terminan**, por eso este ejercicio pide **leerlos, no
ejecutarlos**.

## 💻 Código o contexto de partida

En los cuatro casos, `libro`, `total`, `renovacion` y `dia` son las variables declaradas en el propio
fragmento.

**Bucle A**

```java
int libro = 1;
int total = 0;
while (libro <= 5) {
    total += 1500;
}
```

**Bucle B**

```java
int libro = 1;
while (libro <= 5);
{
    libro++;
}
```

**Bucle C** (la biblioteca permite **3** renovaciones)

```java
int renovaciones = 0;
for (int renovacion = 0; renovacion <= 3; renovacion++) {
    renovaciones++;
}
```

**Bucle D**

```java
double multa = 0.0;
for (int dia = 1; dia <= 3; dia++) {
    multa += 1500.0;
}
```

Completa la tabla:

| Bucle | ¿Termina? | ¿Cuántas veces se ejecuta el bloque? | ¿Qué problema tiene? |
|---|---|---|---|
| A | | | |
| B | | | |
| C | | | |
| D | | | |

## 📏 Criterios de evaluación de la solución

- Los cuatro diagnósticos son correctos.
- En A se identifica que nada dentro del bucle cambia la variable de la condición.
- En B se identifica que el `;` deja el `while` con un cuerpo vacío.
- En C se identifica la repetición de más (empieza en 0 y usa `<=`) y cuántas veces se ejecuta.
- En D se justifica por qué el bucle es correcto.

## 🚧 Restricciones

- **No ejecutes los bucles A y B**: no terminan.
- Basta con leer el código y seguir las variables a mano.

## 📊 Dificultad

Básico

## 🎓 Resultados de aprendizaje

- **RA-1**: explicar qué hace una iteración y cuándo un bucle debe terminar.
- **RA-11**: identificar el bucle infinito y el error de una repetición de más.

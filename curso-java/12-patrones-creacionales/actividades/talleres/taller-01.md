# 🛠️ Taller 01 — Sistema de Admisión de Pacientes de MediSalud

## 🎯 Objetivo

Diseñar, desde cero, un sistema de admisión de pacientes que combine cuatro de los cinco patrones
creacionales: Factory Method, Abstract Factory, Builder y Singleton (RA-4, RA-7, RA-10, RA-16, RA-17).

## 🌍 Contexto

MediSalud quiere renovar su proceso de admisión. El sistema debe: crear el tipo de admisión correcto
(ambulatoria o de internación) sin que el código que registra la admisión conozca las clases concretas;
armar un kit de bienvenida (pulsera + carpeta) consistente según el tipo de cobertura del paciente
(particular u obra social); armar la ficha de admisión paso a paso, con datos obligatorios y opcionales;
y concentrar todas las admisiones —sin importar desde qué módulo del hospital se registren— en un único
registro consultable.

## 🪜 Pasos

1. **Factory Method**: Declara una interfaz `Admision` y sus dos implementaciones
   (`AdmisionAmbulatoria`, `AdmisionInternacion`). Declara `FabricaDeAdmisiones` con un método
   `crear(String tipoIngreso)` que decida cuál instanciar.
2. **Abstract Factory**: Declara interfaces `Pulsera` y `Carpeta`, cada una con dos implementaciones
   (particular, obra social). Declara `FabricaDeKitsDeAdmision` con un método por insumo, y una
   implementación concreta por línea (`FabricaKitParticular`, `FabricaKitObraSocial`).
3. **Builder**: Declara `FichaAdmisionBuilder` que arme la ficha paso a paso, con datos obligatorios
   (paciente, tipo de ingreso) recibidos en su propio constructor, y datos opcionales (médico derivante,
   obra social, observaciones) agregados con métodos nombrados.
4. **Singleton**: Declara `RegistroCentralDeAdmisiones` con constructor privado y un único punto de
   acceso estático, que concentre todas las admisiones registradas.
5. **Integración**: Con todas las piezas anteriores, admití al menos dos pacientes de tipos de ingreso
   distintos, con kits de líneas distintas y fichas con datos distintos, desde dos módulos separados del
   hospital (por ejemplo, recepción y guardia), y verifica que los resultados coincidan con los
   `## 🧪 Casos de prueba`.

## 💡 Ejemplo resuelto

Así se ve el método que decide qué tipo de admisión crear, una vez resuelto el paso 1 (Factory Method):
recibe el tipo de ingreso y devuelve siempre el tipo común `Admision`, sin que quien lo llama necesite
conocer las clases concretas:

```java
public static Admision crear(String tipoIngreso) {
    if (tipoIngreso.equals("AMBULATORIA")) {
        return new AdmisionAmbulatoria();
    } else if (tipoIngreso.equals("INTERNACION")) {
        return new AdmisionInternacion();
    }
    throw new IllegalArgumentException("Tipo de ingreso desconocido: " + tipoIngreso);
}
```

El resto del diseño (la fábrica abstracta de kits, el builder de la ficha, el registro central) queda
para vos.

## 📦 Entregable

```text
AdmisionDePacientes/
└── com/medisalud/
    ├── Admision.java                 (interfaz, Factory Method)
    ├── Admision....java              (dos implementaciones)
    ├── FabricaDeAdmisiones.java      (Factory Method)
    ├── Pulsera.java, Carpeta.java    (interfaces, Abstract Factory)
    ├── Pulsera....java, Carpeta....java  (dos implementaciones cada una)
    ├── FabricaDeKitsDeAdmision.java  (interfaz, Abstract Factory)
    ├── FabricaKit....java            (dos implementaciones)
    ├── FichaAdmision.java            (constructor de paquete, Builder)
    ├── FichaAdmisionBuilder.java     (Builder)
    ├── RegistroCentralDeAdmisiones.java  (Singleton)
    ├── GestorDeAdmisiones.java       (orquesta los cuatro patrones)
    └── Demo.java
```

## 🧪 Casos de prueba

| Entrada | Operación | Resultado esperado |
|---|---|---|
| Admisión ambulatoria, kit particular, ficha con médico derivante | Registro desde el módulo de recepción | Se registra correctamente, con los tres datos consistentes entre sí |
| Admisión de internación, kit de obra social, ficha con obra social | Registro desde el módulo de guardia | Se registra correctamente, con los tres datos consistentes entre sí |
| Ambas admisiones registradas | `RegistroCentralDeAdmisiones.getInstancia().totalAdmisiones()` | `2`, sin importar desde qué módulo se consulte |

## 📏 Criterios de evaluación

- `FabricaDeAdmisiones` es el único lugar que decide qué clase concreta de `Admision` instanciar.
- Ningún kit de bienvenida mezcla insumos de líneas distintas.
- `FichaAdmisionBuilder` permite construir una ficha con solo los datos obligatorios, o con cualquier
  combinación de los opcionales.
- `RegistroCentralDeAdmisiones` tiene una única instancia, verificable comparando referencias obtenidas
  desde módulos distintos.
- El programa compila, se ejecuta y produce los resultados de la tabla de casos de prueba.

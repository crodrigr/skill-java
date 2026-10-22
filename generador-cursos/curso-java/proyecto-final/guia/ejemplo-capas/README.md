# 🧱 Ejemplo ejecutable — Interfaces, capas e inyección

Código completo de la [guía de repaso](../guia-repaso-interfaces-capas.md): una lista de
tareas por consola. Sin dependencias externas. Requiere JDK 17 o superior.

```bash
mkdir out
javac -encoding UTF-8 -d out $(find src -name '*.java')
java -cp out com.tareas.Principal archivo     # guarda en tareas.txt (persiste)
java -cp out com.tareas.Principal memoria     # guarda en memoria (se pierde al salir)
java -cp out:mysql-connector-j.jar com.tareas.Principal mysql   # MySQL (ver abajo)
```

Menú: `1` crear, `2` listar, `3` completar, `0` salir.

## 🗂️ Estructura — capas: presentación, negocio y persistencia

```
src/com/tareas/
├── Principal.java                           ← Paso 4: ensambla e inyecta
├── model/Tarea.java                         ← modelo (transversal)
├── presentacion/                            ← 1 · capa de presentación
│   ├── vista/VistaConsola.java
│   └── controlador/ControladorTareas.java
├── negocio/                                 ← 2 · capa de negocio
│   └── service/ServicioTareas.java          ← Paso 3
└── persistence/                             ← 3 · capa de persistencia
    ├── dao/TareaDAO.java                    ← Paso 2c: el SQL (JDBC)
    └── repository/
        ├── RepositorioTareas.java           ← Paso 1: contrato
        ├── RepositorioTareasMemoria.java    ← Paso 2a
        ├── RepositorioTareasArchivo.java    ← Paso 2b
        └── RepositorioTareasMySQL.java      ← Paso 2c (delega en TareaDAO)
antes/ServicioAcoplado.java              ← Paso 0: el ❌ acoplado
Prueba.java                              ← prueba del servicio sin archivos
```

## 🐬 Modo MySQL (opcional)

Compila sin dependencias (usa `java.sql`), pero para **ejecutarlo** hace falta el conector
MySQL en el classpath y una base creada (`CREATE DATABASE tareas;`; la tabla la crea el DAO).

```bash
export TAREAS_DB_URL=jdbc:mysql://localhost:3306/tareas TAREAS_DB_USER=root TAREAS_DB_PASS=secreto
java -cp out:mysql-connector-j.jar com.tareas.Principal mysql
```

## 🪜 Paso a paso

```bash
# Paso 0 — el ❌ acoplado
javac -d out0 antes/ServicioAcoplado.java && java -cp out0 ServicioAcoplado

# Paso 1 y 2 — contrato e implementaciones (compilan; aún no hay nada que ejecutar)
javac -d out2 src/com/tareas/model/*.java src/com/tareas/persistence/dao/*.java src/com/tareas/persistence/repository/*.java

# Paso 3 — el servicio
javac -d out3 src/com/tareas/model/*.java src/com/tareas/persistence/dao/*.java src/com/tareas/persistence/repository/*.java src/com/tareas/negocio/service/*.java

# Paso 4 — todo conectado
mkdir -p out && javac -encoding UTF-8 -d out $(find src -name '*.java') && java -cp out com.tareas.Principal

# Prueba sin menú ni archivos (usa el out/ del paso 4)
javac -cp out -d out Prueba.java && java -cp out Prueba
```

Limpieza: `rm -rf out0 out2 out3 out tareas.txt`.

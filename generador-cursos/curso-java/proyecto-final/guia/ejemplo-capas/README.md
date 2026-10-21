# 🧱 Ejemplo ejecutable — Interfaces, capas e inyección

Código completo de la [guía de repaso](../guia-repaso-interfaces-capas.md). No necesita
Docker ni MySQL: `PacienteDAO` simula JDBC e imprime el SQL que ejecutaría.

## ⚡ Ejecución rápida

```bash
./ejecutar.sh            # usa PacienteDAO (simulado)
./ejecutar.sh memoria    # usa RepositorioPacientesMemoria
./ejecutar.sh log        # usa AlmacenPacientesLog (ejercicio corto)
```

Requiere JDK 17 o superior. Se abre un menú: `1` registrar, `2` listar, `3` buscar, `0` salir.

> 🔎 Fijate que el menú, el controlador y el servicio son **los mismos** en los tres
> modos. Solo cambia la línea que crea el repositorio en `Principal.java`.

## 🗂️ Estructura

```
src/com/medisalud/
├── Principal.java                          ← Paso 4: ensambla e inyecta
├── vista/VistaConsola.java                 ← pide y muestra datos
├── controlador/ControladorMediSalud.java   ← traduce la acción del usuario
├── service/ServicioPacientes.java          ← Paso 3: depende de la interfaz
├── repository/
│   ├── RepositorioPacientes.java           ← Paso 1: el contrato
│   ├── RepositorioPacientesMemoria.java    ← Paso 2a
│   ├── PacienteDAO.java                    ← Paso 2b (JDBC simulado)
│   └── AlmacenPacientesLog.java            ← ejercicio corto resuelto
└── entity/Paciente.java
antes/ServicioAcoplado.java                 ← el ❌ "antes"
```

## 🪜 Paso a paso (compilando cada etapa)

Desde esta carpeta; cada comando suma clases a la etapa anterior.

**Paso 0 — El ❌ antes (acoplado)**

```bash
javac -d out0 antes/ServicioAcoplado.java && java -cp out0 ServicioAcoplado
```

**Paso 1 — El contrato.** Compila: solo hay firmas, todavía no hay nada que ejecutar.

```bash
javac -d out1 src/com/medisalud/entity/Paciente.java src/com/medisalud/repository/RepositorioPacientes.java
```

**Paso 2 — Dos implementaciones.** El compilador exige que cumplan todos los métodos
(probá borrar uno de `RepositorioPacientesMemoria` y compilar).

```bash
javac -d out2 src/com/medisalud/entity/*.java src/com/medisalud/repository/*.java
```

**Paso 3 — El servicio recibe la interfaz.**

```bash
javac -d out3 src/com/medisalud/entity/*.java src/com/medisalud/repository/*.java src/com/medisalud/service/*.java
```

**Paso 4 — Alguien lo conecta.** Con `Principal`, vista y controlador, el programa corre:

```bash
./ejecutar.sh dao
```

Limpieza: `rm -rf out0 out1 out2 out3 out`.

## 🧪 Probar sin MySQL ni menú

Creá `Prueba.java` en esta carpeta:

```java
import com.medisalud.entity.Paciente;
import com.medisalud.repository.RepositorioPacientesMemoria;
import com.medisalud.service.ServicioPacientes;

public class Prueba {
    public static void main(String[] args) {
        ServicioPacientes servicio = new ServicioPacientes(new RepositorioPacientesMemoria());
        servicio.registrarPaciente(new Paciente("Ana Pérez", "P-001", 30));
        System.out.println(servicio.listarTodos().size());   // 1
    }
}
```

```bash
./ejecutar.sh memoria < /dev/null   # compila en out/
javac -cp out -d out Prueba.java && java -cp out Prueba
```

## 🏋️ Para practicar

1. Cambiá en `Principal` el repositorio por defecto a memoria. ¿Qué otra clase tuviste que tocar?
2. Agregá `RepositorioPacientesArchivo` (guarda en un `.txt`) y un nuevo modo `archivo`.
3. Quitá `buscarPorCodigo` de `RepositorioPacientesMemoria` y observá el error del compilador.

# 🔑 Clave del Quiz 01 — Herencia y Polimorfismo

Material docente. No enlazar desde archivos de audiencia estudiante (salvo la subsección
"Soluciones" de `specs/07-herencia-y-polimorfismo.md`).

| N.º | Tipo | Respuesta correcta / síntesis | RA |
|---|---|---|---|
| 1 | Selección | A — la flecha de herencia apunta hacia la superclase. | RA-1 |
| 2 | Selección | B — la herencia modela una relación "es un tipo de". | RA-2 |
| 3 | Selección múltiple | A y D — se heredan los miembros `public`, nunca los constructores. | RA-3 |
| 4 | Abierta | La parte heredada debe inicializarse antes de continuar; sin `super(...)` explícito el compilador rechaza el programa. | RA-4 |
| 5 | Selección | A — `super.metodo()` reutiliza comportamiento heredado; `this(...)` encadena constructores. | RA-5, RA-6 |
| 6 | Selección | B — el enlace dinámico se decide según el tipo real del objeto. | RA-7 |
| 7 | Selección múltiple | A y B — pueden imprimir mensajes distintos, y ambas variables comparten el tipo declarado `Persona`. | RA-7 |
| 8 | Selección | B — la sobrecarga se resuelve en tiempo de compilación, según los argumentos. | RA-8 |
| 9 | Abierta | Sí: por ejemplo, dos constructores `Medico` con distinta lista de parámetros, uno delegando en el otro con `this(...)`. | RA-8 |
| 10 | Selección | A — `@Override` exige la misma firma exacta que el método de la superclase. | RA-9 |
| 11 | Selección múltiple | A y B — el primer caso es sobrecarga (misma clase, firma distinta); el segundo es sobre-escritura (herencia, misma firma). | RA-10 |
| 12 | Selección | A — una clase abstracta puede tener métodos sin implementación. | RA-11, RA-14 |
| 13 | Abierta | Abstracto cuando cada subclase necesita su propia versión; concreto cuando el comportamiento es común a todas. | RA-11 |
| 14 | Selección múltiple | A, B y C — una interfaz no tiene estado (D es falsa). | RA-12 |
| 15 | Selección | A — estado/código compartido entre emparentadas (clase abstracta) frente a contrato sin estado entre no emparentadas (interfaz). | RA-13 |
| 16 | Selección múltiple | A, B, C y D — los cuatro son errores reales y frecuentes de este módulo. | RA-15 |

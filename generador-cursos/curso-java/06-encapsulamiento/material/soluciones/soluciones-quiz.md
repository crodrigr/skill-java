# 🔑 Clave del Quiz 01 — Encapsulamiento

Material docente. No enlazar desde archivos de audiencia estudiante (salvo la subsección
"Soluciones" de `specs/06-encapsulamiento.md`).

| N.º | Tipo | Respuesta correcta / síntesis | RA |
|---|---|---|---|
| 1 | Selección | A — el encapsulamiento oculta el estado interno y controla su acceso. | RA-1 |
| 2 | Abierta | Un `set` validado puede rechazar un valor sin sentido de negocio; un atributo público no. | RA-1 |
| 3 | Selección | B — se lee a través del método `get`, no de la notación de punto. | RA-8 |
| 4 | Selección múltiple | A, C y D — un `set` que valida puede rechazar un valor inválido sin asignarlo. | RA-9 |
| 5 | Selección | B — `private` no permite el acceso ni desde el mismo paquete. | RA-2, RA-3 |
| 6 | Selección | B — el acceso por defecto permite el mismo paquete, no otro. | RA-4 |
| 7 | Abierta | Sin herencia no se diferencian; la diferencia real aparece con una subclase de otro paquete. | RA-5 |
| 8 | Selección múltiple | A y C — `public` es el menos restrictivo y el habitual para get/set. | RA-6 |
| 9 | Selección múltiple | A — `private` para el dato exclusivo, `public` para el que debe verse siempre. | RA-7 |
| 10 | Abierta | Un `set` validado rechaza valores sin sentido (por ejemplo, un cupo negativo); un atributo público no puede impedirlo. | RA-9, RA-10 |
| 11 | Selección | B — `isActivo()`, la convención para atributos `boolean`. | RA-11 |
| 12 | Selección múltiple | A y B — acceso directo a un `private` y saltarse la validación del `set`. | RA-12 |

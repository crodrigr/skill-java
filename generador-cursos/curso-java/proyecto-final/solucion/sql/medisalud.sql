-- Base de datos del proyecto final MediSalud (Curso de Java)
-- Crea la base de datos y las tablas que usan PacienteDAO, MedicoDAO y CitaDAO.
-- Se puede ejecutar más de una vez: no borra datos existentes.

CREATE DATABASE IF NOT EXISTS medisalud
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE medisalud;

CREATE TABLE IF NOT EXISTS pacientes (
    codigo VARCHAR(20) PRIMARY KEY,
    nombre_completo VARCHAR(100) NOT NULL,
    edad INT NOT NULL
);

CREATE TABLE IF NOT EXISTS medicos (
    codigo VARCHAR(20) PRIMARY KEY,
    nombre_completo VARCHAR(100) NOT NULL,
    especialidad VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS citas (
    codigo VARCHAR(20) PRIMARY KEY,
    paciente_codigo VARCHAR(20) NOT NULL,
    medico_codigo VARCHAR(20) NOT NULL,
    fecha DATE NOT NULL,
    estado VARCHAR(20) NOT NULL,
    CONSTRAINT fk_citas_paciente FOREIGN KEY (paciente_codigo) REFERENCES pacientes (codigo),
    CONSTRAINT fk_citas_medico FOREIGN KEY (medico_codigo) REFERENCES medicos (codigo)
);

CREATE DATABASE SIH;

USE SIH;

select * from persona;

CREATE TABLE Persona (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    titulo VARCHAR(50),
    nombrePila VARCHAR(50),
    segundoNombre VARCHAR(50),
    apellidos VARCHAR(100)
);

CREATE TABLE Paciente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    persona_id INT,
    fechaNacimiento DATE,
    fechaIngreso DATE,
    diagnostico VARCHAR(255),
    pabellon VARCHAR(100),
    FOREIGN KEY (persona_id) REFERENCES Persona(id)
);

CREATE TABLE Hospital (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    direccion VARCHAR(255) NOT NULL
);

CREATE TABLE Departamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    hospital_id INT,
    FOREIGN KEY (hospital_id) REFERENCES Hospital(id)
);


CREATE TABLE Personal (
    id INT AUTO_INCREMENT PRIMARY KEY,
    persona_id INT,
    fechaVinculacion DATE,
    salario DOUBLE,
    departamento_id INT,
    tipo ENUM('Administrativo', 'Tecnico', 'Operaciones'),
    FOREIGN KEY (persona_id) REFERENCES Persona(id),
    FOREIGN KEY (departamento_id) REFERENCES Departamento(id)
);

CREATE TABLE Doctor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    personal_id INT,
    tipoDoctor ENUM('Asociado', 'Junior'),
    equipo_id INT,
    FOREIGN KEY (personal_id) REFERENCES Personal(id)
);


CREATE TABLE Hospital_Persona (
    hospital_id INT,
    persona_id INT,
    PRIMARY KEY (hospital_id, persona_id),
    FOREIGN KEY (hospital_id) REFERENCES Hospital(id),
    FOREIGN KEY (persona_id) REFERENCES Persona(id)
);

CREATE TABLE Doctor_Equipo (
    equipo_id INT,
    doctor_id INT,
    PRIMARY KEY (equipo_id, doctor_id),
    FOREIGN KEY (doctor_id) REFERENCES Doctor(id)
);

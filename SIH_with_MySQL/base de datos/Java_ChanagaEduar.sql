use bbttxz9endss4agxfh8n;
-- crear tabla de los hospitales
create table hospital(
id int primary key auto_increment,
nombre varchar(50) not null,
direccion varchar(50) not null
);


-- crear tabla de los departamentos
create table departamento(
id int primary key auto_increment,
id_hospital int,
nombre varchar(50) not null,
foreign key (id_hospital) references hospital(id)      
);

-- crear tabla del personal
create table personal(
id int primary key auto_increment,
id_departamento int,
fecha_vinculacion date not null,
nombres varchar(50) not null,
apellidos varchar(50) not null,
direccion varchar(100),
titulo varchar(30),
tipo enum('administrativo','tecnico','operaciones') not null,
salario decimal(10,2),
foreign key (id_departamento) references departamento(id)
);

-- creacion de la tabla Equipo
create table equipo(
id int primary key auto_increment,
id_lider int,
nombre varchar(50) not null,
foreign key (id_lider) references personal(id)
);

-- creacion de la tabla equipo_personal
create table equipo_personal(
id_equipo int,
id_personal int,
primary key (id_equipo,id_personal),
foreign key (id_equipo) references equipo(id),
foreign key (id_personal) references personal(id)
);

-- creacion de la tabla pabellon
create table pabellon(
id int primary key auto_increment,
id_hospital int,
nombre varchar(50) not null,
capacidad int,
ubicacion varchar(50),
foreign key (id_hospital) references hospital(id)
);


-- creacion de la tabla paciente
create table paciente(
id int primary key auto_increment,
id_doctor int,
id_pabellon int,
nombres varchar(50) not null,
apellidos varchar(50) not null,
direccion varchar(50) not null,
fecha_nacimiento date not null,
fecha_ingreso date not null,
foreign key (id_doctor) references personal(id),
foreign key (id_pabellon) references pabellon(id)
);


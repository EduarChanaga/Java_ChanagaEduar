
create table equipos(
id int primary key auto_increment,
nombre varchar(50)
);

create table partidos(
id int primary key auto_increment,
id_equipo_local int,
id_equipo_visitante int,
fecha date,
foreign key (id_equipo_local) references equipos(id),
foreign key (id_equipo_visitante) references equipos(id)
);

create table info_partido(
id int primary key auto_increment,
partido int,
foreign key (partido) references partidos(id),
cestas_equipo_local int,
cestas_equipo_visitante int,
estado_del_partido enum("en juego","finalizado"),
tipo enum("liga","playoffs"),
ganador int,
foreign key (ganador) references equipos(id)
);
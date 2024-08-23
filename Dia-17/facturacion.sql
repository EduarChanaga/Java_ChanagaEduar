
use bo4hf6vuzqtbnpwqptnc;

create table cliente(
    id int AUTO_INCREMENT PRIMARY KEY,
    nombre varchar(100),
    tipo varchar(100)
);

create table producto(
    id int AUTO_INCREMENT PRIMARY KEY,
    nombre varchar(100),
    precio decimal(10,2)
);

create table factura(
    id int AUTO_INCREMENT PRIMARY KEY,
    cliente_id int,
    Foreign Key (cliente_id) REFERENCES cliente(id),
    total decimal(10,2)
);

create table factura_producto(
    factura_id int,
    producto_id int,
    cantidad int,
    PRIMARY KEY(factura_id, producto_id),
    Foreign Key (factura_id) REFERENCES factura(id),
    Foreign Key (producto_id) REFERENCES producto(id)
);
create table Administrativo(
id_admin int auto_increment primary key,
usuario_admin varchar(40) unique not null,
clave_admin varchar(10) not null);

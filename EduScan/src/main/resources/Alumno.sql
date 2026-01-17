create table Alumno(
id_Alumno int auto_increment primary key,
Nombre_Alumno varchar(30) not null,
Paterno_Alumno varchar(30) not null,
Materno_Alumno varchar(30) not null,
Matricula_Alumno varchar(8) not null unique,
Activo varchar(10) DEFAULT 'true');


create table Profesor(
id_Profesor int auto_increment primary key,
Nombre_Profesor varchar(50) not null,
Paterno_Profesor varchar(30) not null,
Materno_Profesor varchar(30) not null,
Matricula_Profesor varchar(10) unique not null);

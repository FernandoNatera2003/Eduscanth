create table Grupo(
id_Grupo int auto_increment primary key,
Num_Grupo int not null unique,
Semestre_Grupo int not null,
id_Carrera int,
foreign key(id_Carrera) references Carrera(id_Carrera)); 

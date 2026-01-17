create table Materia_Profesor_Grupo(
id_Mat_Pro_Gru int auto_increment primary key,
id_Materia int,
id_Profesor int,
id_Grupo int,
foreign key(id_Materia) references Materia(id_Materia),
foreign key(id_Profesor) references Profesor(id_Profesor),
foreign key (id_Grupo) references Grupo(id_Grupo));

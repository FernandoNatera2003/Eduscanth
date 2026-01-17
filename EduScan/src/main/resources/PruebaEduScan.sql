create table Carrera(id_Carrera int auto_increment primary key,Nombre_Carrera varchar(50) not null unique);
#separador 
create table Materia(id_Materia int auto_increment primary key,Nombre_Materia varchar(200) not null unique);

create table Profesor(id_Profesor int auto_increment primary key,Nombre_Profesor varchar(50) not null,Paterno_Profesor varchar(30) not null,Materno_Profesor varchar(30) not null,Matricula_Profesor varchar(10) unique not null);

create table Grupo(id_Grupo int auto_increment primary key,Num_Grupo int not null unique,Semestre_Grupo int,id_Carrera int ,foreign key(id_Carrera) references Carrera(id_Carrera)); 

create table Materia_Profesor_Grupo(id_Mat_Pro_Gru int auto_increment primary key,id_Materia int,id_Profesor int,id_Grupo int,foreign key(id_Materia) references Materia(id_Materia),foreign key(id_Profesor) references Profesor(id_Profesor),foreign key (id_Grupo) references Grupo(id_Grupo));
#separador 
create table Alumno(id_Alumno int auto_increment primary key,Nombre_Alumno varchar(30),Paterno_Alumno varchar(30),Materno_Alumno varchar(30),Matricula_Alumno varchar(8));

create table Laboratorio(id_Laboratorio int auto_increment primary key,nombre_Laboratorio varchar(50) not null unique);

create table Administrativo(id_admin int auto_increment primary key,usuario_admin varchar(40) unique not null,clave_admin varchar(10) not null);

#insert into Administrativo(usuario_admin,clave_admin) values('Admin','Admin');

#insert into Administrativo(usuario_admin,clave_admin) values('Admin2','Admin2');

#insert into Administrativo(usuario_admin,clave_admin) values('Admin3','Admin3');

show databases;
use ETH;
#use pruebaeduscan;
drop database pruebaeduscan;
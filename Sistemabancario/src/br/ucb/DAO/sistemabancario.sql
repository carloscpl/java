create database sistemabancario;

use sistemabancario;

CREATE TABLE usuario ( 
idUsuario int(10) AUTO_INCREMENT,   
usuario varchar(25) NOT NULL UNIQUE,   
senha varchar(45) NOT NULL,   
nome varchar(45) NOT NULL UNIQUE,  
perfil varchar(45) NOT NULL, 
PRIMARY KEY (idUsuario));

insert into usuario values(null,'carlos','carlos123','Carlos pereira Lopes','ADMINISTRADOR');

create table banco(
idBanco int(5) primary key auto_increment,
banco char(50) not null);

create table agencia(
idAgencia int(5) primary key auto_increment,
nomeAgencia char(55) not null,
idBanco int(5) not null,
constraint fk_banco foreign key(idBanco)
references banco(idBanco));

create table cliente(
idCliente int(5) primary key auto_increment,
nome varchar(50) not null,
rg varchar(7) not null,
cpf varchar(11) not null);

create table conta(
idConta int(5) primary key auto_increment,
idAgencia int(5) not null,
operacao int(3) not null,
conta int(7) not null,
tipoConta char(50) not null,
idCliente int(5) not null,
constraint fk_agencia foreign key(idAgencia)
references agencia(idAgencia),
constraint fk_cliente foreign key(idCliente)
references cliente(idCliente));


create table tb_usuario (
	id bigint NOT NULL auto_increment,
	nome varchar(100) NOT NULL,
	login varchar(50) NOT NULL,
	senha varchar(100) NOT NULL,
	PRIMARY key(id)
);

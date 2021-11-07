create table tb_perfil(
	id bigint not null auto_increment primary key,
	nome varchar(100) not null
);

create table perfis_autor(
	autor_id int not null,
	perfil_id bigint not null,
	
	primary key(autor_id,perfil_id),
	
	foreign key(autor_id) references tb_autor(id),
	foreign key(perfil_id) references tb_perfil(id)
);


insert into tb_perfil values(1, "ROLE_ADMIN");
insert into tb_perfil values(2, "ROLE_USER");
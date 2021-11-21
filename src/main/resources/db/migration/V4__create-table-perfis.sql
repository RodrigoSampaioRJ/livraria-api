create table tb_perfil(
	id bigint not null auto_increment primary key,
	nome varchar(100) not null
);

create table perfis_usuarios(
	usuario_id bigint not null,
	perfil_id bigint not null,
	
	primary key(usuario_id,perfil_id),
	
	foreign key(usuario_id) references tb_usuario(id),
	foreign key(perfil_id) references tb_perfil(id)
);


insert into tb_perfil values(1, 'ROLE_ADMIN');
insert into tb_perfil values(2, 'ROLE_USER');

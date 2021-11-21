package br.com.alura.livraria.service;

import java.util.Random;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.alura.livraria.dto.UsuarioDto;
import br.com.alura.livraria.dto.UsuarioFormDto;
import br.com.alura.livraria.infra.EnvioEmail;
import br.com.alura.livraria.model.Perfil;
import br.com.alura.livraria.model.Usuario;
import br.com.alura.livraria.repositories.PerfilRepository;
import br.com.alura.livraria.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private EnvioEmail emailService;
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private ModelMapper modelMapper;

	public Page<UsuarioDto> listar(Pageable paginacao){
		
		return repository.findAll(paginacao).map(u -> modelMapper.map(u, UsuarioDto.class));
	}

	@Transactional
	public UsuarioDto cadastrar(UsuarioFormDto dto) {
		Usuario usuario = modelMapper.map(dto, Usuario.class);
		
		Perfil perfil = perfilRepository.getById(dto.getPerfilId());
		
		usuario.setId(null);
		
		usuario.addPerfil(perfil);
		
		String senha = new Random().nextInt(999999) + "";
		
		usuario.setSenha(bCryptPasswordEncoder.encode(senha));
		
		repository.save(usuario);
		
		String mensagem = String.format("Olá %s!, \n\nSeu cadastro foi confirmado no sitema, segue abaixo seus dados de acesso.\n\nLogin:%s\nSenha:%s",
				usuario.getNome(), usuario.getLogin(), senha);
		
		emailService.enviarEmail(usuario.getEmail(),"Carteira - Bem vindo(a)", mensagem);
		
		return modelMapper.map(usuario, UsuarioDto.class);
	}

}

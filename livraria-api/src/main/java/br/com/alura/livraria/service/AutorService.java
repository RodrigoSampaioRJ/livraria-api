package br.com.alura.livraria.service;

import java.util.Random;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.alura.livraria.dto.AutorDto;
import br.com.alura.livraria.dto.AutorFormDto;
import br.com.alura.livraria.model.Autor;
import br.com.alura.livraria.model.Perfil;
import br.com.alura.livraria.repositories.AutorRepository;
import br.com.alura.livraria.repositories.PerfilRepository;

@Service
public class AutorService {
	
	@Autowired
	private AutorRepository repository;
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional
	public AutorDto cadastrar(AutorFormDto dto) {
		
		Perfil perfil = perfilRepository.getById(dto.getPerfilId());
		
		Autor autor = modelMapper.map(dto, Autor.class);

		String senha = new Random().nextInt(999999) + "";
		
		autor.addPerfil(perfil);
		autor.setPassword(encoder.encode(senha));
		
		repository.save(autor);
		
		AutorDto autorDto = modelMapper.map(autor, AutorDto.class);
		
		return autorDto;
		
	}
	
	public Page<AutorDto> listar(Pageable paginacao){
		
		return repository.findAll(paginacao).map(autor -> modelMapper.map(autor, AutorDto.class));
	}

	public AutorDto detalhar(Long id) {
		
		AutorDto autorDto = modelMapper.map(repository.findById(id).orElseThrow(() -> new EntityNotFoundException()), AutorDto.class);
		
		return autorDto;

	}

	@Transactional
	public void deletar(Long id) {
		
		repository.deleteById(id);
		
	}

}

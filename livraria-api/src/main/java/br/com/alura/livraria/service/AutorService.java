package br.com.alura.livraria.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alura.livraria.dto.AutorFormDto;
import br.com.alura.livraria.model.Autor;
import br.com.alura.livraria.repositories.AutorRepository;

@Service
public class AutorService {
	
	@Autowired
	private AutorRepository repository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@Transactional
	public AutorFormDto cadastrar(AutorFormDto dto) {
		Autor autor = modelMapper.map(dto, Autor.class);
		
		repository.save(autor);
		
		return modelMapper.map(autor, AutorFormDto.class);
	}
	
	public Page<Autor> listar(Pageable paginacao){
		return repository.findAll(paginacao);
	}

}

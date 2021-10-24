package br.com.alura.livraria.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alura.livraria.dto.AutorDto;
import br.com.alura.livraria.dto.LivroDto;
import br.com.alura.livraria.dto.LivroFormDto;
import br.com.alura.livraria.model.Livro;
import br.com.alura.livraria.repositories.AutorRepository;
import br.com.alura.livraria.repositories.LivroRepository;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository repository;
	
	@Autowired
	private AutorRepository autorRepository;
	
	private ModelMapper mapper = new ModelMapper();
	
	@Transactional
	public LivroDto cadastrar(LivroFormDto dto) {
		
		Livro livro = mapper.map(dto, Livro.class);
		
		repository.save(livro);
		
		LivroDto livroDto = mapper.map(livro, LivroDto.class);
		
		AutorDto autorDto = mapper.map(autorRepository.findById(dto.getAutor().getId()).get(), AutorDto.class);
		
		livroDto.setAutor(autorDto);
		
		return livroDto;
	}
	
	public Page<LivroDto> listar(Pageable paginacao){
		
		return repository.findAll(paginacao).map(l -> mapper.map(l, LivroDto.class));
		
	}

}

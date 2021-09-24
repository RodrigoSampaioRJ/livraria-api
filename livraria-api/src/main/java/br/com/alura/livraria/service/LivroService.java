package br.com.alura.livraria.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.livraria.dto.LivroDto;
import br.com.alura.livraria.dto.LivroFormDto;
import br.com.alura.livraria.model.Livro;
import br.com.alura.livraria.repositories.LivroRepository;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository repository;
	
	private ModelMapper mapper = new ModelMapper();
	
	
	public void cadastrar(LivroFormDto dto) {
		Livro livro = mapper.map(dto, Livro.class);
		
		repository.save(livro);
	}
	
	public List<LivroDto> listar(){
		return repository.findAll().stream().map(l -> mapper.map(l, LivroDto.class)).collect(Collectors.toList());
	}

}

package br.com.alura.livraria.service;


import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import br.com.alura.livraria.dto.AtualizacaoLivroDto;
import br.com.alura.livraria.dto.LivroDto;
import br.com.alura.livraria.dto.LivroFormDto;
import br.com.alura.livraria.model.Autor;
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
	public LivroDto cadastrar(LivroFormDto dto, Autor logado) {
		
		Livro livro = mapper.map(dto, Livro.class);
		
		livro.setAutor(logado);
		
		repository.save(livro);
		
		LivroDto livroDto = mapper.map(livro, LivroDto.class);	
		
		return livroDto;
	}
	
	public Page<LivroDto> listar(Pageable paginacao, Autor logado){
		
		return repository.findAllByAutor(paginacao, logado).map(l -> mapper.map(l, LivroDto.class));
		
	}

	public LivroDto detalhar(Long id, Autor logado) {
		LivroDto livroDto = mapper.map(repository.findByIdAndAutor(id, logado).orElseThrow(() -> new EntityNotFoundException()), LivroDto.class);
			
		return livroDto;
	}

	@Transactional
	public void deletar(Long id, Autor logado) {
		
		Livro livro = repository.getById(id);
		
		if(!livro.getAutor().equals(logado)) {
			lancaExceptionAccessDenied();
		}
		
		repository.deleteById(id);
		
	}

	public LivroDto atualizar(AtualizacaoLivroDto atualizacaoLivroDto, Autor logado) {
		
		Livro livro = repository.getById(atualizacaoLivroDto.getId());
		
		if(!livro.getAutor().equals(logado)) {
			lancaExceptionAccessDenied();
		}
		
		livro.atualizar(atualizacaoLivroDto);
		
		return mapper.map(livro, LivroDto.class);
		
		
	}

	private void lancaExceptionAccessDenied() {
		throw new AccessDeniedException("");
	}
}

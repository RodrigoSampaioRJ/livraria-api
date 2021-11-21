package br.com.alura.livraria.service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	@Autowired
	private ModelMapper modelMapper;

	@Transactional
	public LivroDto cadastrar(LivroFormDto dto) {

		Livro livro = modelMapper.map(dto, Livro.class);

		Long autorId = dto.getAutorId();

		try {
			Autor autor = autorRepository.getById(autorId);
			livro.setAutor(autor);
			livro.setId(null);

			repository.save(livro);

			return modelMapper.map(livro, LivroDto.class);

		} catch (EntityNotFoundException e) {
			throw new IllegalArgumentException("Autor não existente");
		}

	}

	public Page<LivroDto> listar(Pageable paginacao) {

		return repository.findAll(paginacao).map(l -> modelMapper.map(l, LivroDto.class));

	}

	public LivroDto detalhar(Long id) {
		LivroDto livroDto = modelMapper.map(
				repository.findById(id).orElseThrow(() -> new EntityNotFoundException()),
				LivroDto.class);

		return livroDto;
	}

	@Transactional
	public void deletar(Long id) {
		
		Livro livro = new Livro();
		
		try {
			livro = repository.findById(id).get();
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Livro não existente");
		}

		repository.delete(livro);

	}

	public LivroDto atualizar(AtualizacaoLivroDto atualizacaoLivroDto) {

		Livro livro = repository.getById(atualizacaoLivroDto.getId());

		livro.atualizar(atualizacaoLivroDto);

		return modelMapper.map(livro, LivroDto.class);

	}

}

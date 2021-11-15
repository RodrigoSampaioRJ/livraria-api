package br.com.alura.livraria.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sun.istack.NotNull;

import br.com.alura.livraria.dto.AtualizacaoLivroDto;
import br.com.alura.livraria.dto.LivroDto;
import br.com.alura.livraria.dto.LivroFormDto;
import br.com.alura.livraria.model.Autor;
import br.com.alura.livraria.service.LivroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/livro")
@Api(tags = "Livros")
public class LivroController {
	
	@Autowired
	private LivroService service;
	
	@PostMapping
	@ApiOperation("Cadastrar um novo Livro")
	public ResponseEntity<LivroDto> cadastrar(@RequestBody @Valid LivroFormDto dto, UriComponentsBuilder uriBuilder,@ApiIgnore @AuthenticationPrincipal Autor logado) {
		
		LivroDto livroDto = service.cadastrar(dto, logado);
		
		URI uri = uriBuilder.path("livro/{id}").buildAndExpand(livroDto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(livroDto);
	}
	
	@GetMapping
	@ApiOperation("Listar todos os Livros")
	public Page<LivroDto> listar(Pageable paginacao,@ApiIgnore @AuthenticationPrincipal Autor logado){
		return service.listar(paginacao, logado);
	}
	
	@GetMapping("/{id}")
	@ApiOperation("Detalha um Livro específico")
	public ResponseEntity<LivroDto> detalhar(@PathVariable @NotNull Long id,@ApiIgnore @AuthenticationPrincipal Autor logado){
		
		LivroDto livroDto = service.detalhar(id, logado);
		
		
		return ResponseEntity.ok(livroDto);
	}
	@DeleteMapping("/{id}")
	@ApiOperation("Deleta um Livro")
	public ResponseEntity<LivroDto> deletar(@PathVariable @NotNull Long id,@ApiIgnore @AuthenticationPrincipal Autor logado){
		service.deletar(id, logado);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	@ApiOperation("Atualiza um Livro")
	public LivroDto atualizar(AtualizacaoLivroDto atualizacaoLivroDto,@ApiIgnore @AuthenticationPrincipal Autor logado) {
		return service.atualizar(atualizacaoLivroDto, logado);
	}
	
	

}

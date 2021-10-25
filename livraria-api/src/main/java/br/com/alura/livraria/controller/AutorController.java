package br.com.alura.livraria.controller;

import java.net.URI;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.livraria.dto.AutorDto;
import br.com.alura.livraria.dto.AutorFormDto;
import br.com.alura.livraria.model.Autor;
import br.com.alura.livraria.service.AutorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/autor")
@Api(tags = "Autores")
public class AutorController {
	
	@Autowired
	private AutorService service;
	
	@PostMapping
	@ApiOperation("Cadastrar um novo Autor")
	public ResponseEntity<AutorDto> cadastrar(@RequestBody @Valid AutorFormDto dto, UriComponentsBuilder uriBuilder) {
		
		AutorDto autorDto = service.cadastrar(dto);
		
		URI uri = uriBuilder.path("autor/{id}").buildAndExpand(autorDto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(autorDto);
		

	}
	
	@GetMapping("/{id}")
	@ApiOperation("Detalha um Autor específico")
	public ResponseEntity<AutorDto> detalhar(@PathVariable @NotNull Long id){
		AutorDto autorDto = service.detalhar(id);
		
		return ResponseEntity.ok(autorDto);
	}
	
	@GetMapping
	@ApiOperation("Listar todos os autores")
	public Page<Autor> listar(Pageable paginacao){
		return service.listar(paginacao);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation("Deleta um Autor")
	public ResponseEntity<AutorDto> deletar(@PathVariable @NotNull Long id){
		service.deletar(id);
		
		return ResponseEntity.noContent().build();
	}

}

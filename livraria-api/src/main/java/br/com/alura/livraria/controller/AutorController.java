package br.com.alura.livraria.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.livraria.dto.AutorFormDto;
import br.com.alura.livraria.model.Autor;
import br.com.alura.livraria.service.AutorService;

@RestController
@RequestMapping("/autor")
public class AutorController {
	
	@Autowired
	private AutorService service;
	
	@PostMapping
	public ResponseEntity<AutorFormDto> cadastrar(@RequestBody @Valid AutorFormDto dto, UriComponentsBuilder uriBuilder) {
		
		AutorFormDto autorFormDto = service.cadastrar(dto);
		
		URI uri = uriBuilder.path("autor/{id}").buildAndExpand(autorFormDto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(autorFormDto);
		

	}
	
	@GetMapping
	public Page<Autor> listar(Pageable paginacao){
		return service.listar(paginacao);
	}

}

package br.com.alura.livraria.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.livraria.dto.AutorFormDto;
import br.com.alura.livraria.model.Autor;
import br.com.alura.livraria.service.AutorService;

@RestController
@RequestMapping("/autor")
public class AutorController {
	
	@Autowired
	private AutorService service;
	
	@PostMapping
	public void cadastrar(@RequestBody @Valid AutorFormDto dto) {
		
		service.cadastrar(dto);
	}
	
	@GetMapping
	public Page<Autor> listar(Pageable paginacao){
		return service.listar(paginacao);
	}

}

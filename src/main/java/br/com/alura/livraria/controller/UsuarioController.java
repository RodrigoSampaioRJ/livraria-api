package br.com.alura.livraria.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.livraria.dto.UsuarioDto;
import br.com.alura.livraria.dto.UsuarioFormDto;
import br.com.alura.livraria.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/usuarios")
@Api(tags = "Usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	@GetMapping
	@ApiOperation("Listar todos os Usuarios")
	public Page<UsuarioDto> listar(@PageableDefault(size = 10) Pageable paginacao){
		
		return service.listar(paginacao);
	}
	
	@PostMapping
	@ApiOperation("Cadastrar um novo Usuario")
	public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioFormDto dto, UriComponentsBuilder uriBuilder) {
		
		UsuarioDto usuarioDto = service.cadastrar(dto);
		
		URI uri = uriBuilder.path("usuarios/{id}").buildAndExpand(usuarioDto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(usuarioDto);
		
		
	}

}

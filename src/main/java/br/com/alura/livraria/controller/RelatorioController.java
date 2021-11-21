package br.com.alura.livraria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.livraria.dto.RelatorioLivrariaDto;
import br.com.alura.livraria.service.RelatorioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/relatorios")
@Api(tags = "Relatórios")
public class RelatorioController {
	
	@Autowired
	private RelatorioService service;

	@ApiOperation("Relatório de livros")
	@GetMapping("/livraria")
	public List<RelatorioLivrariaDto> relatorioLivraria(){
		return service.relatorioLivraria();
	}
}

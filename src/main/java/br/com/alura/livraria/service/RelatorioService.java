package br.com.alura.livraria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.livraria.dto.RelatorioLivrariaDto;
import br.com.alura.livraria.repositories.LivroRepository;

@Service
public class RelatorioService {
	
	@Autowired
	private LivroRepository repository;
	
	public List<RelatorioLivrariaDto> relatorioLivraria(){
		
		return repository.relatorioLivraria();
		
	}

}

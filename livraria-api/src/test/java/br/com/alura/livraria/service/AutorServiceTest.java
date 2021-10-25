package br.com.alura.livraria.service;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alura.livraria.dto.AutorDto;
import br.com.alura.livraria.dto.AutorFormDto;
import br.com.alura.livraria.repositories.AutorRepository;

@ExtendWith(MockitoExtension.class)
class AutorServiceTest {
	
	@Mock
	private AutorRepository repository;
	
	@InjectMocks
	private AutorService service;

	

	@Test
	void deveriaCadastrarAutor() {
		AutorFormDto formDto = new AutorFormDto("Rodrigo", "digo@gmail.com", LocalDate.of(1997, 02, 26), "Este é o mini curriculo teste");
		
		
	}

}
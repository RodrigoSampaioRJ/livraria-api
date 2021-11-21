package br.com.alura.livraria.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.alura.livraria.dto.AutorDto;
import br.com.alura.livraria.dto.AutorFormDto;
import br.com.alura.livraria.model.Autor;
import br.com.alura.livraria.repositories.AutorRepository;
import br.com.alura.livraria.repositories.PerfilRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AutorServiceTest {
	
	@Mock
	private AutorRepository repository;
	
	@Mock
	private PerfilRepository perfilRepository;
	
	@Mock
	private BCryptPasswordEncoder encoder;
	
	@Mock
	private ModelMapper modelMapper;
	
	@InjectMocks
	private AutorService service;

	

	@Test
	void deveriaCadastrarAutor() {
		AutorFormDto formDto = new AutorFormDto("Rodrigo", "digo@gmail.com", LocalDate.of(1997, 02, 26), "Este é o mini curriculo teste");
		
		Mockito.when(modelMapper.map(formDto, Autor.class)).thenReturn(new Autor("Rodrigo", "digo@gmail.com", LocalDate.of(1997, 02, 26), "Este é meu mini curriculo"));
		
		Mockito.when(service.cadastrar(formDto)).thenReturn(new AutorDto(1L, formDto.getName(), formDto.getDataDeNascimento(), formDto.getEmail()));
		
		AutorDto autorDto = new AutorDto(
				1L,
				formDto.getName(),
				formDto.getDataDeNascimento(),
				formDto.getEmail());
		
		Mockito.verify(repository).save(Mockito.any());
		
		assertEquals(formDto.getName(), autorDto.getName());
		assertEquals(formDto.getEmail(), autorDto.getEmail());
		assertEquals(formDto.getDataDeNascimento(), autorDto.getDataDeNascimento());

	}

}

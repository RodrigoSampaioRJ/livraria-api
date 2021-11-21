package br.com.alura.livraria.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import br.com.alura.livraria.infra.security.TokenService;
import br.com.alura.livraria.model.Autor;
import br.com.alura.livraria.model.Perfil;
import br.com.alura.livraria.model.Usuario;
import br.com.alura.livraria.repositories.AutorRepository;
import br.com.alura.livraria.repositories.PerfilRepository;
import br.com.alura.livraria.repositories.UsuarioRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class LivroControllerTest {
	
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private AutorRepository autorRepository;
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	@Autowired
	private TokenService tokenService;
	
	private String token;
	
	private Usuario logado;
	
	@BeforeEach
	public void before() {
		Perfil perfil = perfilRepository.findById(1L).get();
		
		this.logado = new Usuario("Rodrigo", "rodrigo", "123456");
		
		logado.addPerfil(perfil);
		
		repository.save(logado);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(logado , logado.getNome());
		
		this.token = tokenService.gerarToken(authentication);
	}
	
	@Test
	void deveriaCadastrarLivroComDadosCompletos() throws Exception {
		
		Long id = autorRepository.save(criaAutor()).getId();
		
		String json = 
				"{"
				+ "\"titulo\":\"O jovem programador 4\","
				+ "\"dataLancamento\":\"2021-09-15\","
				+ "\"paginas\":125,"
				+ "\"autorId\":"+id+"}"
				+ "}";
		
		
		mvc
		.perform(
				post("/livro")
				.header("Authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isCreated())
				.andExpect(header().exists("Location"))
				;

	}
	

	@Test
	void naoDeveriaCadastrarLivroComDadosIncompletos() throws Exception {
		String json = "{}";
		
		mvc
		.perform(
				post("/livro")
				.header("Authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
		.andExpect(status().isBadRequest());
	}
	
	private Autor criaAutor() {
		return new Autor("Rodrigo", "autor@email.com", LocalDate.parse("1997-02-26"), "Este é meu mini curriculo");
	}


}

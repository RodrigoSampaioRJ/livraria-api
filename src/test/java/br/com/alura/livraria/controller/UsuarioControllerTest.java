package br.com.alura.livraria.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import br.com.alura.livraria.model.Perfil;
import br.com.alura.livraria.model.Usuario;
import br.com.alura.livraria.repositories.PerfilRepository;
import br.com.alura.livraria.repositories.UsuarioRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class UsuarioControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private Usuario usuario;
	
	private String token;
	
	@BeforeEach
	public void before() {	
		Perfil perfil = perfilRepository.findById(1L).get();
		
		this.usuario = new Usuario("Rodrigo", "rodrigo", "123456");
		usuario.addPerfil(perfil);
		
		usuarioRepository.save(usuario);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(usuario , usuario.getLogin());
		this.token = tokenService.gerarToken(authentication );
		
	}

	@Test
	void naoDeveriaCadastrarComDadosIncompletos() throws Exception {
		String json = "{}";


		
		mvc
		.perform(
				post("/usuarios")
				.header("Authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isBadRequest());
		
		
	}
	
	@Test
	void deveriaCadastrarComDadosCompletos() throws Exception {
		String json = "{\n"
				+ "  \"email\": \"rodrigo@gmail.com\",\n"
				+ "  \"login\": \"rodrigo\",\n"
				+ "  \"nome\": \"Rodrigo\",\n"
				+ "  \"perfilId\": 1\n"
				+ "}";
		
		
		
		mvc
		.perform(
				post("/usuarios")
				.header("Authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isCreated())
				.andExpect(header().exists("Location"));
		
	}
	
	
	

}

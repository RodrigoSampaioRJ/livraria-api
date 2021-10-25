package br.com.alura.livraria.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import br.com.alura.livraria.model.Autor;
import br.com.alura.livraria.repositories.AutorRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class AutorControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private AutorRepository repository;

	@Test
	void naoDeveriaCadastrarAutorComDadosIncompletos() throws Exception {
		String json = "{}";
		
		mvc
		.perform(
				post("/autor")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	void deveriaCadastrarComDadosCompletos() throws Exception {
		String json = "{\r\n"
				+ "    \"name\": \"Vinicius\",\r\n"
				+ "    \"email\": \"vini.15rj@gmail.com\",\r\n"
				+ "    \"dataDeNascimento\": \"1997-06-26\",\r\n"
				+ "    \"miniCurriculo\": \"Este é meu mini curriculo22223222\"\r\n"
				+ "}";
		
		String jsonEsperado = "{"
				
				+ "\"name\":\"Vinicius\","
				+ "\"dataDeNascimento\":\"1997-06-26\","
				+ "\"email\":\"vini.15rj@gmail.com\"}";
		
		
		mvc
		.perform(
				post("/autor")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isCreated())
				.andExpect(header().exists("Location"))
				.andExpect(content().json(jsonEsperado));

	}
	
	@Test
	void deveriaDeletarAutor() throws Exception {
		
		Autor autor = new Autor("Vinicius", "vini@gmail.com", LocalDate.of(1996, 02, 12), "Mini Curriculo");
		
		String id = repository.save(autor).getId().toString();
		

		mvc.perform(
				delete("/autor/" + id))		
		.andExpect(status().isNoContent());

	}
	
	@Test
	void deveriaDetalharAutor() throws Exception  {
		Autor autor = new Autor("Vinicius", "vini@gmail.com", LocalDate.of(1996, 02, 12), "Mini Curriculo");
		
		String id = repository.save(autor).getId().toString();
		
		String jsonEsperado = "{"
				+ "\"id\":" + id + ","
				+ "\"name\":\"Vinicius\","
				+ "\"dataDeNascimento\":\"1996-02-12\","
				+ "\"email\":\"vini@gmail.com\"}";
		
		System.out.println(id);
		mvc
		.perform(
				get("/autor/" + id))
				.andExpect(status().isOk())
				.andExpect(content().json(jsonEsperado));

	}

}

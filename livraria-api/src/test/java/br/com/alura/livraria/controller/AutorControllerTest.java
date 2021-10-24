package br.com.alura.livraria.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class AutorControllerTest {
	
	@Autowired
	private MockMvc mvc;

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
		
		mvc
		.perform(
				post("/autor")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isCreated())
				.andExpect(header().exists("Location"))
				.andExpect(content().json(json));
		
	}

}

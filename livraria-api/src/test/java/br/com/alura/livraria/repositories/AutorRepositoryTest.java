package br.com.alura.livraria.repositories;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.alura.livraria.model.Autor;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
class AutorRepositoryTest {
	
	@Autowired
	private AutorRepository repository;

	@Test
	void deveriaCadastrarUmAutor() {
		Autor a1 = new Autor("Rodrigo", "digo@gmail.com", LocalDate.of(1997, 02, 26),"Este é meu curriculo");
		
		Autor autorRetorno = repository.save(a1);
		
		Assertions.assertThat(autorRetorno)
		.extracting(Autor::getId,autor -> a1.getName(),autor -> a1.getEmail(), autor -> a1.getDataDeNascimento(), autor -> a1.getMiniCurriculo())
		.containsExactly(
				autorRetorno.getId(),
				"Rodrigo",
				"digo@gmail.com", 
				LocalDate.of(1997, 02, 26),
				"Este é meu curriculo");
	}


}

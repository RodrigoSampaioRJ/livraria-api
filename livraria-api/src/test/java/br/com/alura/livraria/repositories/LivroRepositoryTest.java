package br.com.alura.livraria.repositories;

import java.time.LocalDate;
import java.util.List;

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

import br.com.alura.livraria.dto.RelatorioLivrariaDto;
import br.com.alura.livraria.model.Autor;
import br.com.alura.livraria.model.Livro;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
class LivroRepositoryTest {

	
	@Autowired
	private LivroRepository repository;
	
	@Autowired
	private TestEntityManager em;

	@Test
	void deveRetornarRelatorioDeLivros() {
		Autor a1 = new Autor("Rodrigo", "digo@gmail.com", LocalDate.of(1997, 02, 26),"Este é meu curriculo");
		Autor a2 = new Autor("Alexandra", "alexandra@gmail.com", LocalDate.of(1997, 02, 24),"Este é meu curriculo");
		a1.setPassword("123456");
		a2.setPassword("123456");
		em.persist(a1);
		em.persist(a2);
		
		Livro l1 = new Livro("Livro1", LocalDate.of(2021, 10, 10), 125, a1);
		Livro l2 = new Livro("Livro2", LocalDate.of(2021, 10, 10), 125, a2);
		
		em.persist(l1);
		em.persist(l2);
		
		List<RelatorioLivrariaDto> relatorio = repository.relatorioLivraria();
		
		Assertions
		.assertThat(relatorio)
		.hasSize(2)
		.extracting(RelatorioLivrariaDto::getAutor, RelatorioLivrariaDto::getQuantidadeLivros, RelatorioLivrariaDto::getPercentual)
		.containsExactlyInAnyOrder(
				Assertions.tuple("Rodrigo", 1L, 50.0),
				Assertions.tuple("Alexandra", 1L, 50.0)
				);
	}

}

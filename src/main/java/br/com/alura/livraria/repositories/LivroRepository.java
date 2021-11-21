package br.com.alura.livraria.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.alura.livraria.dto.RelatorioLivrariaDto;
import br.com.alura.livraria.model.Autor;
import br.com.alura.livraria.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{
	
	@Query("select new br.com.alura.livraria.dto.RelatorioLivrariaDto("
	        + "a.autor.name, "
			+ "count(*), "
			+ "count(*) * 1.0 / (select count(*) from Livro a2) * 100.0 as percentual) "
			+ "from Livro a group by a.autor order by percentual desc")
	List<RelatorioLivrariaDto> relatorioLivraria();

	Page<Livro> findAllByAutor(Pageable paginacao, Autor autor);

	Optional<Livro> findByIdAndAutor(Long id, Autor logado); 
	


}

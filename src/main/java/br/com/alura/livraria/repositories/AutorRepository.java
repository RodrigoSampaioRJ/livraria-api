package br.com.alura.livraria.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.livraria.model.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor , Long>{

	Optional<Autor> findByName(String username);
	
	

}

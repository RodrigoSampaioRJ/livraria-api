package br.com.alura.livraria.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.alura.livraria.model.Usuario;

@Repository	
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Optional<Usuario> findByNome(String nome);
	
	Optional<Usuario> findByLogin(String login);

	
	@Query("select u from Usuario u join fetch u.perfis where u.id = :idUsuario")
	Optional<Usuario> findByIdWithProfiles(Long idUsuario);
	
	@Query("select u from Usuario u join fetch u.perfis where u.id = :idUsuario")
	Usuario getByIdWithProfiles(Long idUsuario);

}

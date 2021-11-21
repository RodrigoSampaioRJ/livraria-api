package br.com.alura.livraria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.livraria.model.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long>{

}

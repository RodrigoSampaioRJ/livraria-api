package br.com.alura.livraria.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_autor")
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private LocalDate dataDeNascimento;
    private String miniCurriculo;
    @OneToMany(mappedBy = "autor")
    @JsonManagedReference
    private List<Livro> livros;
    
	public Autor(String name, String email, LocalDate dataDeNascimento, String miniCurriculo) {
		this.name = name;
		this.email = email;
		this.dataDeNascimento = dataDeNascimento;
		this.miniCurriculo = miniCurriculo;
	}

}

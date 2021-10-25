package br.com.alura.livraria.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "tb_autor")
@AllArgsConstructor
@NoArgsConstructor
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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Livro> livros;
    
	public Autor(String name, String email, LocalDate dataDeNascimento, String miniCurriculo) {
		this.name = name;
		this.email = email;
		this.dataDeNascimento = dataDeNascimento;
		this.miniCurriculo = miniCurriculo;
	}

}

package br.com.alura.livraria.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "tb_livro")
@AllArgsConstructor
@NoArgsConstructor
public class Livro {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String titulo;

	@PastOrPresent
	private LocalDate dataLancamento;

	@NotNull
	private Integer paginas;

	@ManyToOne
	@JoinColumn(name = "id_autor")
	@JsonBackReference
	private Autor autor;

	public Livro(String titulo,LocalDate dataLancamento,Integer paginas,
			Autor autor) {
		this.titulo = titulo;
		this.dataLancamento = dataLancamento;
		this.paginas = paginas;
		this.autor = autor;
	}
	
	

}

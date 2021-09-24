package br.com.alura.livraria.dto;

import java.time.LocalDate;

import br.com.alura.livraria.model.Autor;
import lombok.Data;

@Data
public class LivroDto {
	
	private String titulo;
	private LocalDate dataLancamento;
	private Integer paginas;
	private Autor autor;
	
	//Para retornar somente o nome do autor na listagem de livros;
	public String getAutor() {
		return autor.getName();
	}

}

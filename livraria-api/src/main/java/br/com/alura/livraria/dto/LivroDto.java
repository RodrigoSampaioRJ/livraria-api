package br.com.alura.livraria.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonGetter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroDto {
	
	private Long id;
	private String titulo;
	private LocalDate dataLancamento;
	private Integer paginas;
	private AutorDto autor;
	
	//Para retornar somente o nome do autor na listagem de livros;
	@JsonGetter
	public AutorDto getAutor() {
		return autor;
	}

}

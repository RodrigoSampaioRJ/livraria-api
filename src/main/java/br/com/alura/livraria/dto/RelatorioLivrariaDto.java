package br.com.alura.livraria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioLivrariaDto {
	
	private String autor;
	private Long quantidadeLivros;
	private Double percentual;

}

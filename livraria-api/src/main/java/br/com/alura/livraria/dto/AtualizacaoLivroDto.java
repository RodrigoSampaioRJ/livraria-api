package br.com.alura.livraria.dto;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AtualizacaoLivroDto extends LivroFormDto{
	
	@NotNull
	private Long id;

}

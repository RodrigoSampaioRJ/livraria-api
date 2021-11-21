package br.com.alura.livraria.dto.erros;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Erro400Dto {
	
	private String mensagem;
	private String campo;

}

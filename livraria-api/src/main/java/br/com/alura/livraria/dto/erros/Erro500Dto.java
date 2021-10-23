package br.com.alura.livraria.dto.erros;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Erro500Dto {

	private String erro;
	private String mensagem;
	private String uri;
	private LocalDateTime timestamp;

}

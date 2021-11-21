package br.com.alura.livraria.infra;

import org.springframework.scheduling.annotation.Async;

public interface EnvioEmail {

	void enviarEmail(String destinatario, String assunto, String mensagem);

}
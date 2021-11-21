package br.com.alura.livraria.infra;

import org.springframework.stereotype.Service;

@Service
public interface EnvioEmail {

	void enviarEmail(String destinatario, String assunto, String mensagem);

}
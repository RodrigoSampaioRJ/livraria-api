package br.com.alura.livraria.infra;

import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Profile("default")
public class EnvioEmailServiceFake implements EnvioEmail {
	
	@Override
	@Async
	public void enviarEmail(String destinatario, String assunto, String mensagem) {
		
		System.out.println("Email: ");
		System.out.println("Destinatario: " + destinatario);
		System.out.println("Assunto: " + assunto);
		System.out.println("Mensagem: " + mensagem);

	}

}

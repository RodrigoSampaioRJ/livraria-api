package br.com.alura.livraria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableAsync
@SpringBootApplication
public class LivrariaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivrariaApiApplication.class, args);
	}

}

package br.com.alura.livraria.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.models.Contact;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxSwaggerConfigurations {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)  
		          .select()                                  
		          .apis(RequestHandlerSelectors.any())              
		          .paths(PathSelectors.any())                          
		          .build()
		          .apiInfo(apiInfo());  
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
				"API Carteira de Investimentos",
				"TESTE",
				"Termos de Uso",
				"Termos de Serviço",
				"Rita Cury",
				"Licença da Api", "2");
		
	}
}

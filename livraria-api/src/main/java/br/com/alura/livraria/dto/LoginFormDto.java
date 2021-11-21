package br.com.alura.livraria.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginFormDto {
	
	@NotBlank
	private String name;
	@NotBlank
	private String senha;

}

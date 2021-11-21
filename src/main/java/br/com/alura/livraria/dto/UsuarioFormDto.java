package br.com.alura.livraria.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UsuarioFormDto {
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String login;
	
	@Email
	@NotBlank
	private String email;
	
	@NotNull
	private Long perfilId;

}

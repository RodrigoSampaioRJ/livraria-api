package br.com.alura.livraria.dto;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import lombok.Data;

@Data
public class AutorFormDto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank
	private String name;
	
	@NotBlank
    private String email;
	
	@Past
    private LocalDate dataDeNascimento;
	
	@NotBlank
    private String miniCurriculo;

	public AutorFormDto(String name, String email, LocalDate dataDeNascimento,
			 String miniCurriculo) {
		this.name = name;
		this.email = email;
		this.dataDeNascimento = dataDeNascimento;
		this.miniCurriculo = miniCurriculo;
	}

	
	
	

}

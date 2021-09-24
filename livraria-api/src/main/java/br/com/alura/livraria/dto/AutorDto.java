package br.com.alura.livraria.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AutorDto {
	
	private String name;
	private LocalDate dataNascimento;
	private String email;

}

package br.com.alura.livraria.dto;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroFormDto {
	
	@NotBlank
	private String titulo;
	
	@PastOrPresent
	private LocalDate dataLancamento;
	
	@Min(value = 100)
	@NotNull
	private Integer paginas;
	
	@NotNull
	private Long autorId;
	

}
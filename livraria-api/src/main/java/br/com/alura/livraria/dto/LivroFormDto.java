package br.com.alura.livraria.dto;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import br.com.alura.livraria.model.Autor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroFormDto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String titulo;
	
	@PastOrPresent
	private LocalDate dataLancamento;
	
	@Min(value = 100)
	@NotNull
	private Integer paginas;
	

}
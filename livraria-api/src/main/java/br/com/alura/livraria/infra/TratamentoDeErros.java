package br.com.alura.livraria.infra;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.alura.livraria.dto.erros.Erro400Dto;
import br.com.alura.livraria.dto.erros.Erro404Dto;
import br.com.alura.livraria.dto.erros.Erro500Dto;

@RestControllerAdvice
public class TratamentoDeErros {

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public List<Erro400Dto> tratamentoErro400(MethodArgumentNotValidException exc){
		
		return exc
				.getFieldErrors()
				.stream()
				.map(erro -> new Erro400Dto(erro.getDefaultMessage(), erro.getField()))
				.collect(Collectors.toList());		
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public Erro500Dto tratamentoErro500(Exception ex, HttpServletRequest req){
		return new Erro500Dto(ex.getClass().toString(),
				"Erro interno do servidor",
				req.getRequestURI(),
				LocalDateTime.now());
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public Erro404Dto tratamentoErro404(IllegalArgumentException ex) {
		
		return new Erro404Dto(ex.getMessage());
	}
	
	
	
	
	
}

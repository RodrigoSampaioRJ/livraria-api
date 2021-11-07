package br.com.alura.livraria.infra.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.alura.livraria.model.Autor;
import br.com.alura.livraria.repositories.AutorRepository;

public class VerificaTokenFilter extends OncePerRequestFilter {
	
	
	private TokenService tokenService;
	private AutorRepository autorRepository;
	
	
	public VerificaTokenFilter(TokenService tokenService, AutorRepository autorRepository) {
		this.tokenService = tokenService;
		this.autorRepository = autorRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = request.getHeader("Authorization");
		

		if(token == null || token.isBlank()) {
			
			filterChain.doFilter(request, response);
			
			return;
		}
		
		token = token.replace("Bearer ", "");
		
		boolean tokenValido = tokenService.verificaToken(token);
		
		Long autorId = tokenService.getAutorId(token);
		
		Autor logado = autorRepository.findById(autorId).get();
		
		if(tokenValido) {
			
			Authentication authentication =  new UsernamePasswordAuthenticationToken(logado, null, logado.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		filterChain.doFilter(request, response);
	}

}

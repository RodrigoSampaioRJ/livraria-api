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

import br.com.alura.livraria.model.Usuario;
import br.com.alura.livraria.repositories.UsuarioRepository;

public class VerificaTokenFilter extends OncePerRequestFilter {
	
	
	private TokenService tokenService;
	private UsuarioRepository usuarioRepository;
	
	
	public VerificaTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
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
		
		Usuario logado = usuarioRepository.findById(autorId).get();
		
		if(tokenValido) {
			
			Authentication authentication =  new UsernamePasswordAuthenticationToken(logado, null, logado.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		filterChain.doFilter(request, response);
	}

}

package br.com.alura.livraria.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.alura.livraria.dto.LoginFormDto;
import br.com.alura.livraria.dto.TokenDto;
import br.com.alura.livraria.model.Autor;
import br.com.alura.livraria.repositories.AutorRepository;

@Service
public class AutenticacaoService implements UserDetailsService{
	
	@Autowired
	private AutorRepository repository;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Autor autor = repository.findByName(username).orElseThrow(() -> new UsernameNotFoundException(""));
		
		return autor;
	}

	public TokenDto autenticar(LoginFormDto dto) {
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(dto.getName(), dto.getPassword());
		
		authentication = authenticationManager.authenticate(authentication);
		
		return new TokenDto(tokenService.gerarToken(authentication));
	}

}

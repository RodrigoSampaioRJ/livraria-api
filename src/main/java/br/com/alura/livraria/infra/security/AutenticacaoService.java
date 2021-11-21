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
import br.com.alura.livraria.model.Usuario;
import br.com.alura.livraria.repositories.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = repository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException(""));
		
		return usuario;
	}

	public TokenDto autenticar(LoginFormDto dto) {
		
		
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(dto.getName(), dto.getSenha());
		
		authentication = authenticationManager.authenticate(authentication);
		
		return new TokenDto(tokenService.gerarToken(authentication));
	}

}

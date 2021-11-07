package br.com.alura.livraria.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "tb_autor")
@AllArgsConstructor
@NoArgsConstructor
public class Autor implements UserDetails {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    private String email;
    private LocalDate dataDeNascimento;
    private String miniCurriculo;
    @OneToMany(mappedBy = "autor")
    @JsonManagedReference
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Livro> livros;
    @ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "perfis_autor",
				joinColumns = @JoinColumn(name = "autor_id"),
				inverseJoinColumns = @JoinColumn(name = "perfil_id"))
    @Lazy
    private List<Perfil> perfis = new ArrayList<>();
    
	public Autor(String name, String email, LocalDate dataDeNascimento, String miniCurriculo) {
		this.name = name;
		this.email = email;
		this.dataDeNascimento = dataDeNascimento;
		this.miniCurriculo = miniCurriculo;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.perfis;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}

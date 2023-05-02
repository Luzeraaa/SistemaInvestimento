package br.com.smu.smuinvestimentos.domain.usuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.smu.smuinvestimentos.domain.investidor.dto.DadosCadastroInvestidor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Usuario")
@Table(name = "usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of  = "id")
public class Usuario implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String login;
	
	private String senha;
	
	public Usuario(DadosCadastroInvestidor dados) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
		String senhaBCrypt = encoder.encode(dados.senha());			
			
		this.login = dados.login();
		this.senha = senhaBCrypt;
		
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		// Lista de perfis de acesso
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		// Adiciona o perfil de usuário padrão
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		// Adiciona o perfil de administrador
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		 
		return true;
	}

	@Override
	public boolean isEnabled() {
		 
		return true;
	}

}

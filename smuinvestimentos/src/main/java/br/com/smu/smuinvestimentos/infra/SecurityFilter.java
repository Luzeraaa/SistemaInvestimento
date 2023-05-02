package br.com.smu.smuinvestimentos.infra;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.smu.smuinvestimentos.domain.usuario.Usuario;
import br.com.smu.smuinvestimentos.domain.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	
	@Autowired
	private TokenService service;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		var tokenJWT = recuperarToken(request);
		
		if(tokenJWT != null) {

			System.out.println("Entrou");
			String subject = service.getSubject(tokenJWT);
			UserDetails usuarioApi = usuarioRepository.findByLogin(subject);
			
			Authentication authentication = new UsernamePasswordAuthenticationToken(usuarioApi, null, usuarioApi.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		filterChain.doFilter(request, response); 
		
	}

	private String recuperarToken(HttpServletRequest request) {
		
		 var autorizacao = request.getHeader("Authorization");
		
		 if(autorizacao != null) {
			 return autorizacao.replace("Bearer ", "");
		 }
		
		 return null;
		
	}
	
}

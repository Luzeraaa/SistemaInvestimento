package br.com.smu.smuinvestimentos.infra;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.smu.smuinvestimentos.domain.usuario.Usuario;


@Service
public class TokenService {

	@Value("${api.security.token.secret}")
	private String secret;
	
	
	public String gerarToken(Usuario usuario) {
		
		try {
		    var algorithm = Algorithm.HMAC256(secret);
		    
		    return JWT.create() 
		        .withIssuer("API SMU investimentos")
		        .withSubject(usuario.getLogin())
		        .withExpiresAt(dataExpiracao())
		        .sign(algorithm);
		} catch (JWTCreationException exception){
			throw new RuntimeException("Erro ao gerar token jwt", exception);
		}
	}

	//Passar o token JWT e verificar se esta valido e devolver o usuario dentro do token
	public String getSubject(String token){
		
		try {
		    Algorithm algoritmo = Algorithm.HMAC256(secret);
		    return  JWT.require(algoritmo)
		        .withIssuer("API SMU investimentos")
		        .build()
		        .verify(token)
		        .getSubject();
		        
		} catch (JWTVerificationException exception) {
		    throw new RuntimeException("TOKEN JWT inválido ou expirado");
		}
		
	}

	private Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
	}
	
	
}

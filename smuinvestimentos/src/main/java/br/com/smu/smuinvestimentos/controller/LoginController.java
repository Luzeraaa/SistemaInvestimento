package br.com.smu.smuinvestimentos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.smu.smuinvestimentos.domain.usuario.Usuario;
import br.com.smu.smuinvestimentos.domain.usuario.dto.DadosLogin;
import br.com.smu.smuinvestimentos.infra.DadosToken;
import br.com.smu.smuinvestimentos.infra.TokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {

	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenService service;
	
	
	@PostMapping
	public ResponseEntity login(@RequestBody @Valid DadosLogin dados) {
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
		Authentication authentication = manager.authenticate(authenticationToken);
		
		String token = service.gerarToken((Usuario)authentication.getPrincipal());
		
		return ResponseEntity.ok(new DadosToken(token));
		
	}
	
	
	
}


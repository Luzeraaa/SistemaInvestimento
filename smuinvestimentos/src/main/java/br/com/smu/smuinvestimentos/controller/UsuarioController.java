package br.com.smu.smuinvestimentos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.smu.smuinvestimentos.domain.Conta.Conta;
import br.com.smu.smuinvestimentos.domain.Conta.ContaRepository;

@RequestMapping("admin")
@RestController
public class UsuarioController {

	@Autowired
	private ContaRepository repository;
	
	private final int CODIGO = 1234;
	
	
	@PostMapping("/{numeroConta}/{codigo}/{ativo}") 
	public ResponseEntity desativar(@PathVariable int numeroConta, @PathVariable int codigo, @PathVariable boolean ativo) {
		
		if(codigo == this.CODIGO) {
			
			Conta conta = repository.findByNumero(numeroConta);
			
			if(conta == null) {
				throw new RuntimeException("Conta não encontrada");
			}
			
			conta.setAtivo(ativo);
			repository.save(conta);
		}
		
		
		return ResponseEntity.ok("Conta alterada com sucesso");
		
	}
	
}

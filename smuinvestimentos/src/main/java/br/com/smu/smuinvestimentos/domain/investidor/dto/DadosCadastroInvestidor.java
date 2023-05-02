package br.com.smu.smuinvestimentos.domain.investidor.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DadosCadastroInvestidor(
		
		@NotBlank
		String nome,
		
		@NotBlank
		String login,
		
		@NotNull
		LocalDateTime dataNascimento,
		
		@NotBlank
		String senha,
		
		@Size(max = 11, min = 11)
		String cpf,
		
		@Size(max = 14, min = 14)
		String cnpj
		
		
		) {

}

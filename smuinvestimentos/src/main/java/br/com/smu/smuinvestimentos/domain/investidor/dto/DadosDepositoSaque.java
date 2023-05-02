package br.com.smu.smuinvestimentos.domain.investidor.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosDepositoSaque(
		
		@NotNull
		int numeroConta,
		
		@NotNull
		BigDecimal valor
		
		) {

}

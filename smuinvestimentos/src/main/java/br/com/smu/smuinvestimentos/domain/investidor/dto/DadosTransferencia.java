package br.com.smu.smuinvestimentos.domain.investidor.dto;

import java.math.BigDecimal;

public record DadosTransferencia(
		
		int numeroContaOrigem,
		
		int numeroContaDestino,
		
		BigDecimal valor
		
		) {

}

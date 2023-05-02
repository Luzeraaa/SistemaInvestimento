package br.com.smu.smuinvestimentos.domain.Conta.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.smu.smuinvestimentos.domain.Conta.Extrato;

public record DadosExtrato(
		
		String descricao,
		
		LocalDateTime dataOperacao,
		
		BigDecimal valor,
		
		int numeroConta
		
		) {
	
	public DadosExtrato(Extrato dados) {
		this(dados.getDescricao(), dados.getData(), dados.getValor(), dados.getConta().getNumero());
	}
}

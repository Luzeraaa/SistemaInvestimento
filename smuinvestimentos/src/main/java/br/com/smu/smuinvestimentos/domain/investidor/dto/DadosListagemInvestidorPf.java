package br.com.smu.smuinvestimentos.domain.investidor.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.smu.smuinvestimentos.domain.investidor.InvestidorPessoaFisica;

public record DadosListagemInvestidorPf(

		String nome,

		String login,

		int numeroConta,

		LocalDateTime dataNascimento,
		
		String cpf,
		
		BigDecimal saldo,
		
		boolean ativo

) {

	public DadosListagemInvestidorPf(InvestidorPessoaFisica dados) {
		this(dados.getNome(),
				dados.getLogin(),
				dados.getConta().getNumero(),
				dados.getDataNascimento(),
				dados.getCpf(),
				dados.getConta().getSaldo(),
				dados.getConta().getAtivo());
	}


}

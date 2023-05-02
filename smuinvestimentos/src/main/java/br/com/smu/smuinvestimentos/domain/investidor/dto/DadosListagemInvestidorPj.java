package br.com.smu.smuinvestimentos.domain.investidor.dto;

import java.time.LocalDateTime;

import br.com.smu.smuinvestimentos.domain.investidor.InvestidorPessoaJuridica;

public record DadosListagemInvestidorPj(

		String nome,

		String login,

		int numeroConta,

		LocalDateTime dataNascimento,

		String cnpj

) {

	public DadosListagemInvestidorPj(InvestidorPessoaJuridica dados) {
		this(dados.getNome(), dados.getLogin(), dados.getConta().getNumero(), dados.getDataNascimento(),
				dados.getCnpj());
	}
}

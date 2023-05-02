package br.com.smu.smuinvestimentos.domain.investidor;

import br.com.smu.smuinvestimentos.domain.Conta.Conta;
import br.com.smu.smuinvestimentos.domain.investidor.dto.DadosCadastroInvestidor;
import br.com.smu.smuinvestimentos.domain.usuario.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity(name = "InvestidorPessoaFisica")
@Table(name = "investidor_pessoa_fisica")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InvestidorPessoaFisica extends Investidor {
	
	@Column(unique = true)
	private String cpf;

	public InvestidorPessoaFisica(DadosCadastroInvestidor dados, Conta conta) {

		super(dados.nome(), dados.dataNascimento(), dados.login(), dados.senha(), conta);
		this.cpf = dados.cpf();
	}
	
}


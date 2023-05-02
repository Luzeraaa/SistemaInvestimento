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


@Entity(name = "InvestidorPessoaJuridica")
@Table(name = "investidor_pessoa_juridica")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InvestidorPessoaJuridica extends Investidor {
	  
	@Column(unique = true)
	private String cnpj;
	
	
    public InvestidorPessoaJuridica(DadosCadastroInvestidor dados, Conta conta) {
		super(dados.nome(), dados.dataNascimento(), dados.login(), dados.senha(), conta);
		this.cnpj = dados.cnpj();
	}
}


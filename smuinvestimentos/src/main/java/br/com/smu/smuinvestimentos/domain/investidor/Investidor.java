package br.com.smu.smuinvestimentos.domain.investidor;

import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.smu.smuinvestimentos.domain.Conta.Conta;
import br.com.smu.smuinvestimentos.domain.investidor.dto.DadosCadastroInvestidor;
import br.com.smu.smuinvestimentos.domain.usuario.Usuario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Investidor")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Investidor {


	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private LocalDateTime dataNascimento;

    private String login;
    
    private String senha;

    @OneToOne(mappedBy = "investidor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Conta conta;
    
    private boolean ativo;
    


	public Investidor(String nome, LocalDateTime dataNascimento, String login, String senha, Conta conta) {
			
			
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
		String senhaBCrypt = encoder.encode(senha);			
			
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.login = login;
		this.senha = senhaBCrypt;
		this.conta = conta;
		this.ativo = true;
		
	}
	
	   public static Investidor fromDadosCadastro(DadosCadastroInvestidor dados, Conta conta) {
		   
	        if (dados.cpf() != null && dados.cnpj() == null) {
	            return new InvestidorPessoaFisica(dados, conta);
	        } else if (dados.cnpj() != null && dados.cpf() == null) {
	            return new InvestidorPessoaJuridica(dados, conta);
	        } else {
	            throw new IllegalArgumentException("Informe apenas CPF ou CNPJ");
	        }
	    }
	   
	   public void setAtivo(boolean ativo) {
		  this.ativo = ativo;
	   }
}


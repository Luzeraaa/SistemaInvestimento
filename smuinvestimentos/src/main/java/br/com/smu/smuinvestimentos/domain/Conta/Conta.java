package br.com.smu.smuinvestimentos.domain.Conta;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import br.com.smu.smuinvestimentos.domain.investidor.Investidor;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Conta")
@Table(name = "conta")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer numero;

    private BigDecimal saldo;
    
    private boolean ativo;

    @OneToOne
    @JoinColumn(name = "investidor_id")
    private Investidor investidor;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "conta_id")
    private Set<Extrato> extrato = new HashSet<>();
    
    public Conta(int numero) {
        this.numero = numero;
        this.saldo = BigDecimal.ZERO;
        this.ativo = true;
    }

    public void setInvestidor(Investidor investidor) {
		this.investidor = investidor;
	}
    
    public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
    

	public boolean getAtivo() {
		return this.ativo;
	}
    
	public void sacar(BigDecimal valor) {
		
		if(valor.compareTo(saldo) > 0) {
			throw new RuntimeException("Valor não pode ser maior do que saldo");
		}
		
		this.saldo = this.saldo.subtract(valor);
		
	}
	
	public void depositar(BigDecimal valor) {
		
		if(valor.compareTo(new BigDecimal("0")) == 0) {
			throw new RuntimeException("Valor deve ser maior do que 0");
		}
		
		this.saldo = this.saldo.add(valor);
		
	}
	
	public void transfererir(BigDecimal valor, Conta contaDestino) {
		
		if(contaDestino == this) {
			throw new RuntimeException("Conta destino igual a conta de origem");
		}
		
		this.sacar(valor);
		contaDestino.depositar(valor);

		
	}

	
}

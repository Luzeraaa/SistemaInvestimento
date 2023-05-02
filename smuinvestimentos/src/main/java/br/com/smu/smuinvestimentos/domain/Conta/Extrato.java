package br.com.smu.smuinvestimentos.domain.Conta;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Extrato")
@Table(name = "extrato")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Extrato {

	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;

	    private String descricao;

	    private LocalDateTime data;

	    private BigDecimal valor;

	    @ManyToOne
	    @JoinColumn(name = "conta_id")
	    private Conta conta;

	public Extrato(String descricao, BigDecimal valor, Conta conta) {

		this.descricao = descricao;
		this.data = LocalDateTime.now();
		this.valor = valor;
		this.conta = conta;

	}
}

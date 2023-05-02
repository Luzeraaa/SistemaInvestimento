package br.com.smu.smuinvestimentos.domain.Conta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContaRepository extends JpaRepository<Conta, Long> {

	Conta findByNumero(int numero);

    @Query("SELECT MAX(numero) FROM Conta")
    Integer findMaxNumero();
	
	    
}

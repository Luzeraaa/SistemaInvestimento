package br.com.smu.smuinvestimentos.domain.Conta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExtratoRepository extends JpaRepository<Extrato, Long> {

	
	@Query("SELECT e FROM Extrato e WHERE e.conta = :conta")
	Page<Extrato> findAll(@Param("conta") Conta conta, Pageable paginacao);

	
}

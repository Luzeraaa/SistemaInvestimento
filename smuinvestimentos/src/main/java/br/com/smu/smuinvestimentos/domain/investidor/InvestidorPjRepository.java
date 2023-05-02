package br.com.smu.smuinvestimentos.domain.investidor;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestidorPjRepository extends JpaRepository<InvestidorPessoaJuridica, Long> {

	Page<InvestidorPessoaJuridica> findAll(Pageable paginacao);

	InvestidorPessoaJuridica findByCnpjOrLogin(String cnpj, String login);
}

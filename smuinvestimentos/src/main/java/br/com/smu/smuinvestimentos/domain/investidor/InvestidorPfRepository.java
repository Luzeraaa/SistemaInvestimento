package br.com.smu.smuinvestimentos.domain.investidor;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestidorPfRepository extends JpaRepository<InvestidorPessoaFisica, Long> {

	Page<InvestidorPessoaFisica> findAll(Pageable paginacao);

	InvestidorPessoaFisica findByCpfOrLogin(String cpf, String login);
}

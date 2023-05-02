package br.com.smu.smuinvestimentos.domain.investidor;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.smu.smuinvestimentos.domain.Conta.Conta;
import br.com.smu.smuinvestimentos.domain.Conta.ContaRepository;
import br.com.smu.smuinvestimentos.domain.investidor.dto.DadosCadastroInvestidor;
import br.com.smu.smuinvestimentos.domain.usuario.Usuario;
import br.com.smu.smuinvestimentos.domain.usuario.UsuarioRepository;

@Service
public class InvestidorService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private InvestidorPfRepository pfRepository;

	@Autowired
	private InvestidorPjRepository pjRepository;

	@Autowired
	private ContaRepository contaRepository;

	public Long cadastrarPf(DadosCadastroInvestidor dados) {

		// Gerado um número aleatório para conta
		Random random = new Random();
		int numero = random.nextInt(10000) + 1;

		Conta conta = new Conta(numero);

		Investidor investidor;

		InvestidorPessoaFisica pf = pfRepository.findByCpfOrLogin(dados.cpf(), dados.login());

		if (pf == null) {

			investidor = new InvestidorPessoaFisica(dados, conta);
			pfRepository.save((InvestidorPessoaFisica) investidor);

		} else {
			throw new RuntimeException("Investidor  já cadastrado");
		}

		Usuario usuario = usuarioRepository.findByLogin(dados.login());

		if (usuario == null) {
			usuario = new Usuario(dados);
			usuarioRepository.save(usuario);
		} else {
			throw new RuntimeException("Usuário já cadastrado");
		}

		conta.setInvestidor(investidor);
		contaRepository.save(conta);

		return investidor.getId();

	}
	
	public Long cadastrarPj(DadosCadastroInvestidor dados) {

		// Gerado um número aleatório para conta
		Random random = new Random();
		int numero = random.nextInt(10000) + 1;

		Conta conta = new Conta(numero);

		Investidor investidor;
		InvestidorPessoaJuridica pj = pjRepository.findByCnpjOrLogin(dados.cnpj(), dados.login());

		if (pj == null) {

			investidor = new InvestidorPessoaJuridica(dados, conta);
			pjRepository.save((InvestidorPessoaJuridica) investidor);

		} else {
			throw new RuntimeException("Investidor já cadastrado");
		}

		Usuario usuario = usuarioRepository.findByLogin(dados.login());

		if (usuario == null) {
			usuario = new Usuario(dados);
			usuarioRepository.save(usuario);
		} else {
			throw new RuntimeException("Usuário já cadastrado");
		}

		conta.setInvestidor(investidor);
		contaRepository.save(conta);

		return investidor.getId();

	}

}

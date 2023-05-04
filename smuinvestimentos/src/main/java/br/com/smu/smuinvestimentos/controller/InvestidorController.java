package br.com.smu.smuinvestimentos.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.smu.smuinvestimentos.domain.Conta.Conta;
import br.com.smu.smuinvestimentos.domain.Conta.ContaRepository;
import br.com.smu.smuinvestimentos.domain.Conta.Extrato;
import br.com.smu.smuinvestimentos.domain.Conta.ExtratoRepository;
import br.com.smu.smuinvestimentos.domain.Conta.dto.DadosExtrato;
import br.com.smu.smuinvestimentos.domain.investidor.InvestidorPfRepository;
import br.com.smu.smuinvestimentos.domain.investidor.InvestidorPjRepository;
import br.com.smu.smuinvestimentos.domain.investidor.InvestidorService;
import br.com.smu.smuinvestimentos.domain.investidor.dto.DadosCadastroInvestidor;
import br.com.smu.smuinvestimentos.domain.investidor.dto.DadosDepositoSaque;
import br.com.smu.smuinvestimentos.domain.investidor.dto.DadosListagemInvestidorPf;
import br.com.smu.smuinvestimentos.domain.investidor.dto.DadosListagemInvestidorPj;
import br.com.smu.smuinvestimentos.domain.investidor.dto.DadosTransferencia;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/investidor")
public class InvestidorController {

	@Autowired
	private InvestidorPfRepository pfRepository;

	@Autowired
	private InvestidorPjRepository pjRepository;

	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private ExtratoRepository extratoRepository;

	
	@Autowired InvestidorService service;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosCadastroInvestidor dados,
			UriComponentsBuilder uriBuilder) {

		Long id;
		if(dados.cpf() != null && dados.cnpj() == null) {
			System.out.println("PF");
			id = service.cadastrarPf(dados);	
		}else if(dados.cpf() == null && dados.cnpj() != null) {
			System.out.println("PJ");
			id = service.cadastrarPj(dados);
		}else {
			System.out.println(dados);
			throw new RuntimeException("Digite apenas cpf ou cnpj");
		}
		
		
		URI uri = uriBuilder.path("/investidor/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(uri).body("Investidor cadastrado com sucesso!");
	}
	
	
	@PutMapping("/depositar")
	@Transactional
	public ResponseEntity depositar(@RequestBody @Valid DadosDepositoSaque dados) {
		
		Conta conta = contaRepository.findByNumero(dados.numeroConta());
		
		if(conta == null) {
			throw new RuntimeException("Conta não encontrada");
		}
		
		Extrato extrato = new Extrato("DEPOSITO", dados.valor(), conta);
		extratoRepository.save(extrato);
		
		conta.depositar(dados.valor());
		contaRepository.save(conta);
		
		return ResponseEntity.ok("Valor depositado: " + dados.valor()+"\nNúmero conta: " + dados.numeroConta()
							+ "\nSaldo: " + conta.getSaldo());
	}
	
	@PutMapping("/sacar")
	@Transactional
	public ResponseEntity sacar(@RequestBody @Valid DadosDepositoSaque dados) {
		
		Conta conta = contaRepository.findByNumero(dados.numeroConta());
		
		if(conta == null) {
			throw new RuntimeException("Conta não encontrada");
		}
		
		Extrato extrato = new Extrato("SAQUE", dados.valor(), conta);
		extratoRepository.save(extrato);
		
		conta.sacar(dados.valor());
		contaRepository.save(conta);
		
		return ResponseEntity.ok("Valor retirado: " + dados.valor()+"\nNúmero conta: " + dados.numeroConta()
							+ "\nSaldo: " + conta.getSaldo());
	}
	
	@PutMapping("/transferir")
	@Transactional
	public ResponseEntity transferir(@RequestBody @Valid DadosTransferencia dados) {
		
		Conta contaOrigem  = contaRepository.findByNumero(dados.numeroContaOrigem());
		Conta contaDestino = contaRepository.findByNumero(dados.numeroContaDestino());
		
		if(contaOrigem == null || contaDestino == null) {
			throw new RuntimeException("Conta origem e/ou conta destino não encontrada");
		}
		
		Extrato extrato = new Extrato("TRANSFERÊNCIA - ORIGEM: " + dados.numeroContaOrigem()
										+ " ORIGEM: " + dados.numeroContaOrigem(), dados.valor(), contaOrigem);
		extratoRepository.save(extrato);
		
		contaOrigem.transfererir(dados.valor(), contaDestino);
		
		contaRepository.save(contaOrigem);
		contaRepository.save(contaDestino);
		
		return ResponseEntity.ok("Valor transferido: " + dados.valor()+"\nNúmero conta destino: " + dados.numeroContaDestino()
							+ "\nSaldo: " + contaOrigem.getSaldo());
	}
	
	@GetMapping("/pessoa_fisica")
	public ResponseEntity<Page<DadosListagemInvestidorPf>> listarPessoaFisica(@PageableDefault(size = 10) Pageable paginacao) {

		Page page = pfRepository.findAll(paginacao).map(DadosListagemInvestidorPf::new); 

		return ResponseEntity.ok(page);

	}
	
	@GetMapping("/pessoa_juridica")
	public ResponseEntity<Page<DadosListagemInvestidorPj>> listarPessoaJuridica(@PageableDefault(size = 10) Pageable paginacao) {

		Page page = pjRepository.findAll(paginacao).map(DadosListagemInvestidorPj::new); 

		return ResponseEntity.ok(page);

	}
	
	@GetMapping("/extrato/{numeroConta}")
	public ResponseEntity<Page<DadosExtrato>> listarExtrato(@PageableDefault(size = 10) Pageable paginacao, @PathVariable int numeroConta) {

		System.out.println("Numero conta: " + numeroConta);
		Conta conta = contaRepository.findByNumero(numeroConta);
		
		if(conta == null) {
			throw new RuntimeException("Conta não possui extrato");
		}
		
		Page page = extratoRepository.findAll(conta, paginacao).map(DadosExtrato::new); 

		return ResponseEntity.ok(page);

	}
	

}

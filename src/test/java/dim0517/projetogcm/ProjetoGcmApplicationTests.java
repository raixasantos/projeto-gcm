package dim0517.projetogcm;
import static org.junit.Assert.assertEquals;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import dim0517.projetogcm.enums.TipoConta;
import dim0517.projetogcm.model.DadosConta;
import dim0517.projetogcm.model.NovaConta;
import dim0517.projetogcm.model.Saldo;
import dim0517.projetogcm.service.ContaService;

@SpringBootTest
class ProjetoGcmApplicationTests {
	ContaService contaService = new ContaService();
	
	@Test
	void testeNovaContaSimples() {
		NovaConta conta = new NovaConta(1, 100, TipoConta.SIMPLES);
		String saida = contaService.criarConta(conta);
		assertEquals(saida,"Conta criada com sucesso!");
	}
	@Test
	void testeNovaContaPoupanca() {
		NovaConta conta = new NovaConta(2, 100, TipoConta.POUPANCA);
		String saida = contaService.criarConta(conta);
		assertEquals(saida,"Conta criada com sucesso!");
	}
	@Test
	void testeNovaContaBonus() {
		NovaConta conta = new NovaConta(3, 100, TipoConta.BONUS);
		String saida = contaService.criarConta(conta);
		assertEquals(saida,"Conta criada com sucesso!");
	}
	@Test
	void testeConsultarContaSimples() {
		NovaConta conta = new NovaConta(1, 100, TipoConta.SIMPLES);
		contaService.criarConta(conta);
		DadosConta saida = contaService.consultarDados(1);
		DadosConta dadosContaSimplesEsperado = new DadosConta(100, 0, TipoConta.SIMPLES);
		assertEquals(saida, dadosContaSimplesEsperado);
	}
	@Test
	void testeConsultarContaPoupanca() {
		NovaConta conta = new NovaConta(2, 100, TipoConta.POUPANCA);
		contaService.criarConta(conta);
		DadosConta saida = contaService.consultarDados(2);
		DadosConta dadosContaSimplesEsperado = new DadosConta(100, 0, TipoConta.POUPANCA);
		assertEquals(saida, dadosContaSimplesEsperado);
	}
	@Test
	void testeConsultarContaBonus() {
		NovaConta conta = new NovaConta(3, 100, TipoConta.BONUS);
		contaService.criarConta(conta);
		DadosConta saida = contaService.consultarDados(3);
		DadosConta dadosContaSimplesEsperado = new DadosConta(100, 10, TipoConta.BONUS); 
		assertEquals(saida, dadosContaSimplesEsperado);
	}
	@Test
	void testeConsultarSaldoConta() {
		NovaConta conta = new NovaConta(1, 100, TipoConta.SIMPLES);
		contaService.criarConta(conta);
		Saldo saida = contaService.consultarSaldo(1);
		Saldo saldoEsperado = new Saldo(100); 
		assertEquals(saida, saldoEsperado);
	}
}

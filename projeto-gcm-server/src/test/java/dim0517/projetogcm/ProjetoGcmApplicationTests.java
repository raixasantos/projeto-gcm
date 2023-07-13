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
		DadosConta dadosContaPoupancaEsperado = new DadosConta(100, 0, TipoConta.POUPANCA);
		assertEquals(saida, dadosContaPoupancaEsperado);
	}
	@Test
	void testeConsultarContaBonus() {
		NovaConta conta = new NovaConta(3, 100, TipoConta.BONUS);
		contaService.criarConta(conta);
		DadosConta saida = contaService.consultarDados(3);
		DadosConta dadosContaBonusEsperado = new DadosConta(100, 10, TipoConta.BONUS); 
		assertEquals(saida, dadosContaBonusEsperado);
	}
	@Test
	void testeConsultarSaldoConta() {
		NovaConta conta = new NovaConta(1, 100, TipoConta.SIMPLES);
		contaService.criarConta(conta);
		Saldo saida = contaService.consultarSaldo(1);
		Saldo saldoEsperado = new Saldo(100); 
		assertEquals(saida, saldoEsperado);
	}
	@Test
	void testeCreditoNormal() {
		NovaConta conta = new NovaConta(1, 100, TipoConta.SIMPLES);
		contaService.criarConta(conta);
		contaService.creditar(1, 100);
		Saldo saida = contaService.consultarSaldo(1);
		Saldo saldoEsperado = new Saldo(200); 
		assertEquals(saida, saldoEsperado);
	}
	@Test
	void testeCreditoNegativo() {
		NovaConta conta = new NovaConta(1, 100, TipoConta.SIMPLES);
		contaService.criarConta(conta);
		String saida = contaService.creditar(1, -100);
		String saidaEsperada = "Valor negativo não é permitido!";
		assertEquals(saida, saidaEsperada);
	}
	@Test
	void testeCreditoBonus() {
		NovaConta conta = new NovaConta(1, 100, TipoConta.BONUS);
		contaService.criarConta(conta);
		contaService.creditar(1, 500);
		int saida = contaService.consultarDados(1).getPontuacao();
		int pontuacaoEsperada = 15;
		assertEquals(saida, pontuacaoEsperada);
	}
	@Test
	void testeDebitoNormal() {
		NovaConta conta = new NovaConta(1, 100, TipoConta.SIMPLES);
		contaService.criarConta(conta);
		contaService.debitar(1, 100);
		Saldo saida = contaService.consultarSaldo(1);
		Saldo saldoEsperado = new Saldo(0); 
		assertEquals(saida, saldoEsperado);
	}
	@Test
	void testeDebitoNegativo() {
		NovaConta conta = new NovaConta(1, 100, TipoConta.SIMPLES);
		contaService.criarConta(conta);
		String saida = contaService.debitar(1, -100);
		String saidaEsperada = "Valor negativo não é permitido!";
		assertEquals(saida, saidaEsperada);
	}
	@Test
	void testeTransferenciaNegativo() {
		NovaConta conta1 = new NovaConta(1, 100, TipoConta.SIMPLES);
		NovaConta conta2 = new NovaConta(2, 100, TipoConta.SIMPLES);
		contaService.criarConta(conta1);
		contaService.criarConta(conta2);
		String saida = contaService.transferir(1,2, -100);
		String saidaEsperada = "Valor negativo não é permitido!";
		assertEquals(saida, saidaEsperada);
	}
	@Test
	void testeTransferenciaOrigemNegativo() {
		NovaConta conta1 = new NovaConta(1, 50, TipoConta.SIMPLES);
		NovaConta conta2 = new NovaConta(2, 100, TipoConta.SIMPLES);
		contaService.criarConta(conta1);
		contaService.criarConta(conta2);
		String saida = contaService.transferir(1,2, 100);
		String saidaEsperada = "Valor maior do que o disponível na conta de origem!";
		assertEquals(saida, saidaEsperada);
	}
	@Test
	void testeTransferenciaBonus() {
		NovaConta conta1 = new NovaConta(1, 150, TipoConta.SIMPLES);
		NovaConta conta2 = new NovaConta(2, 100, TipoConta.BONUS);
		contaService.criarConta(conta1);
		contaService.criarConta(conta2);
		int pontuacao = contaService.consultarDados(2).getPontuacao();
		String saida = contaService.transferir(1,2, 150);
		String saidaEsperada = "Valor transferido na conta!";
		assertEquals(saida, saidaEsperada);
		pontuacao = contaService.consultarDados(2).getPontuacao();
		int pontuacaoEsperada = 11;
		assertEquals(pontuacao, pontuacaoEsperada);
	}

	@Test
	 void testeRendimento() {
	 	NovaConta conta1 = new NovaConta(1, 200, TipoConta.POUPANCA);
	 	contaService.criarConta(conta1);
	 	contaService.renderJuros(1, 10.5);
	 	Saldo saida = contaService.consultarSaldo(1);
	 	Saldo saidaEsperada = new Saldo(221);
	 	assertEquals(saida, saidaEsperada);
	}
}

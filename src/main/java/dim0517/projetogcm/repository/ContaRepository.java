package dim0517.projetogcm.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import dim0517.projetogcm.enums.TipoConta;
import dim0517.projetogcm.model.Conta;
import dim0517.projetogcm.model.DadosConta;
import dim0517.projetogcm.model.NovaConta;
import dim0517.projetogcm.model.Saldo;

@Repository
public class ContaRepository {
    private Map<Integer, Conta> contas = new HashMap<>();

    public Conta criarConta(NovaConta novaConta) {
        int pontuacao = 0;
        if(novaConta.getTipo() == TipoConta.BONUS){
            pontuacao = 10;
        }
        Conta conta = new Conta(novaConta.getIdentificador(), novaConta.getSaldo(), pontuacao, novaConta.getTipo());
        return contas.put(conta.getIdentificador(), conta);
    }
    
    public boolean findById(int id){
        if (contas.containsKey(id)) {
            return true;
        }
        return false;
    }

    public Saldo consultarSaldo(int idConta) {
        return new Saldo(contas.get(idConta).getSaldo());
    }

    public double consultaDeSaldo(int idConta) {
        return contas.get(idConta).getSaldo();
    }

    public DadosConta consultarDados(int idConta) {
        Conta conta = contas.get(idConta);
        return new DadosConta(conta.getSaldo(), conta.getPontuacao(), conta.getTipo());
    }

    public double creditar(int idConta, double valor) {
        Conta conta = contas.get(idConta);
        double saldoAtual = conta.getSaldo();
        contas.get(idConta).setSaldo(saldoAtual + valor);
        int pontuacaoAtual = contas.get(idConta).getPontuacao();
        if(conta.getPontuacao() >= 10 && conta.getTipo() == TipoConta.BONUS)
            contas.get(idConta).setPontuacao(pontuacaoAtual + (int)(valor/100));
        return contas.get(idConta).getSaldo();
    }

    public double debitar(int idConta, double valor) {
        Conta conta = contas.get(idConta);
        double saldoAtual = conta.getSaldo();
        contas.get(idConta).setSaldo(saldoAtual - valor);
        return contas.get(idConta).getSaldo();
    }
}

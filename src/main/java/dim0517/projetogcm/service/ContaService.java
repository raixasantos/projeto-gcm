package dim0517.projetogcm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dim0517.projetogcm.model.DadosConta;
import dim0517.projetogcm.model.NovaConta;
import dim0517.projetogcm.model.Saldo;
import dim0517.projetogcm.repository.ContaRepository;

@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;

    String contaNaoEncontrada = "Conta(s) não encontrada(s).";

    public String criarConta(NovaConta novaConta) {
        if (contaRepository.findById(novaConta.getIdentificador())) {
            return "Conta já existe.";
        }
        contaRepository.criarConta(novaConta);
        return "Conta criada com sucesso!";
    }

    public Saldo consultarSaldo(int idConta) {
        /*if (contaRepository.findById(idConta)) {
            return contaNaoEncontrada;
        }*/
        return contaRepository.consultarSaldo(idConta);
    }

    public DadosConta consultarDados(int idConta) {
        /*if (contaRepository.findById(idConta)) {
            return contaNaoEncontrada;
        }*/
        return contaRepository.consultarDados(idConta);
    }

    public String creditar(int idConta, double valor) {
        if (contaRepository.findById(idConta)) {
            return contaNaoEncontrada;
        }
        contaRepository.creditar(idConta, valor);
        return "Valor creditado na conta!";
    }

    public String debitar(int idConta, double valor) {
        if (contaRepository.findById(idConta)) {
            return contaNaoEncontrada;
        }
        contaRepository.debitar(idConta, valor);
        return "Valor debitado na conta!";
    }

    public String transferir(int idOrigem, int idDestino, double valor) {
        if (contaRepository.findById(idOrigem) && contaRepository.findById(idDestino)) {
            return contaNaoEncontrada;
        }
        contaRepository.debitar(idOrigem, valor);
        contaRepository.creditar(idDestino, valor);
        return "Valor transferido na conta!";
    }

    public String renderJuros(int idConta, double valor) {
        if (contaRepository.findById(idConta)) {
            return contaNaoEncontrada;
        }
        contaRepository.creditar(idConta, contaRepository.consultaDeSaldo(idConta)*(valor/100));
        return "Valor de juros aplicado na conta!";
    }
}

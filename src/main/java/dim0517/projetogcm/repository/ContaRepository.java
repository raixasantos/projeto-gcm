package dim0517.projetogcm.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dim0517.projetogcm.model.Conta;

public class ContaRepository {
    private Map<Integer, Conta> contas = new HashMap<>();
    
    public Conta findById(int id){
        if (contas.containsKey(id)) {
            return contas.get(id);
        }
        return null;
    }

    public double saldo() {
        return this.saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double creditar(double valor) {
        this.saldo += valor;
        if(pontuacao >= 10)
            pontuacao += (int)(valor/100);
        return saldo;
    }

    public double debitar(double valor) {
        this.saldo -= valor;
        return saldo;
    }

    public String getDados(){
        String tipo = "";
        if(pontuacao > -1){
            tipo = "Bônus";
        }
        else if(ehPoupanca){
            tipo = "Poupança";
        }
        else{
            tipo = "Simples";
        }

        String retorno = "Tipo: "+tipo+"\nNúmero: "+identificador+"\nSaldo: "+saldo;
        if(pontuacao > -1)
            retorno += "\nBônus: "+pontuacao;
        return retorno;
    }
}

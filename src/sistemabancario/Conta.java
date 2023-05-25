package sistemabancario;

public class Conta {
    private int identificador;
    public double saldo;
    public int pontuacao;
    public boolean ehPoupanca;


    public Conta() {}

    public Conta(int identificador) {
        this.identificador = identificador;
        this.saldo = 0.0;
        this.pontuacao = -1;
        this.ehPoupanca = false;
    }

    public int identificador() {
        return this.identificador;
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
}

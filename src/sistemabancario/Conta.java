package sistemabancario;

public class Conta {
    private int identificador;
    private double saldo;

    public Conta() {}

    public Conta(int identificador) {
        this.identificador = identificador;
        this.saldo = 0.0;
    }

    public int identificador() {
        return this.identificador;
    }

    public double saldo() {
        return this.saldo;
    }

    public double creditar(double valor) {
        this.saldo += valor;
        return saldo;
    }

    public double debitar(double valor) {
        this.saldo -= valor;
        return saldo;
    }
}

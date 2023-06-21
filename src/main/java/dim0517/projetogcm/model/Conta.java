package dim0517.projetogcm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Conta {
    private int identificador;
    private double saldo;
    private int pontuacao;
    private boolean ehPoupanca;
}
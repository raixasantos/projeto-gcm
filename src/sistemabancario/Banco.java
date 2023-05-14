package sistemabancario;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    public static void main(String[] args) {
        List<Conta> contas = new ArrayList<>();
        GUI.menu(contas);
    }
}

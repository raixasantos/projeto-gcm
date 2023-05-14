package sistemabancario;

import java.util.List;
import java.util.Scanner;

public class GUI {
    private static Integer paginaMenu;
    private static Scanner scanner = new Scanner(System.in);
    private static String escolha;

    GUI() {}

    public static void limparConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static Conta cadastrarConta() {
        int identificador;

        limparConsole();
        System.out.print("Digite o número da nova conta: ");
        identificador = scanner.nextInt();
        limparConsole();

        return new Conta(identificador);
    }

    public static void exibirAssinatura() {
        System.out.println("=============================");
        System.out.println("        Banco GCM            ");
        System.out.println("=============================");
    }

    public static void exibirDespedida() {
        scanner.close();
        System.out.println("Obrigado e até a próxima!");
        System.exit(0);
    }

    public static void exibirOpcoesMenu() {
        switch (paginaMenu) {
            case 0:
                System.out.println("1 - Cadastrar nova conta");
                break;
            case 1:
                System.out.println("1 - Outras operações");
                break;
        }
        System.out.println("0 - Sair");
        System.out.print("Selecione uma opção: ");
    }

    public static void menu(List<Conta> contas) {
        Conta conta = new Conta();
        paginaMenu = 0;

        while (true) {
            exibirAssinatura();
            exibirOpcoesMenu();
            escolha = scanner.nextLine();

            limparConsole();

            switch (paginaMenu) {
                case 0:
                    switch (escolha) {
                        case "1":
                            conta = cadastrarConta();
                            scanner.nextLine();
                            if (conta == null)
                                break;
                            contas.add(conta);
                            System.out.println("Dados inseridos com sucesso.");
                            paginaMenu++;
                            break;
                        case "0":
                            exibirDespedida();
                            break;
                        default:
                            System.out.println("Opção inválida!");
                    }
                    break;
                case 1:
                    switch (escolha) {
                        case "1":
                            System.out.println("Op 01");
                            break;
                        case "0":
                            exibirDespedida();
                            break;
                        default:
                            System.out.println("Opção inválida!");
                    }
                    break;
            }
        }
    }
}

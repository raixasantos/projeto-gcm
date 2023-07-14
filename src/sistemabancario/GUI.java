package sistemabancario;

import java.util.List;
import java.util.Scanner;

public class GUI {
    private static Integer paginaMenu;
    private static Scanner scanner = new Scanner(System.in);
    private static String escolha;

    GUI() { 
        
    }

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

    public static double consultarSaldo(List<Conta> contas) {
        limparConsole(); 
        System.out.print("Digite o número da conta: ");
        int identificador = scanner.nextInt();
        for(Conta cont : contas) {
            if (cont.identificador() == identificador) {
                return cont.saldo();
            }
        }
        limparConsole();

        return -1;
    }

    public static String consultarDados(List<Conta> contas) {
        limparConsole(); 
        System.out.print("Digite o número da conta: ");
        int identificador = scanner.nextInt();
        for(Conta cont : contas) {
            if (cont.identificador() == identificador) {
                return cont.getDados();
            }
        }
        limparConsole();

        return "";
    }

    public static double credito(List<Conta> contas) {
        limparConsole(); 
        System.out.print("Digite o número da conta: ");
        int identificador = scanner.nextInt();
        Conta conta = null;
        for(Conta cont : contas) {
            if (cont.identificador() == identificador) {
                conta = cont;
            }
        }
        System.out.print("Digite o valor que deseja: ");
        double valor = scanner.nextDouble();
        if(conta != null && valor >0) {
            double saldo = conta.creditar(valor);
            limparConsole();
            return saldo;
        }

        limparConsole();
        return -1;
    }

    public static double debito(List<Conta> contas) {
        limparConsole(); 
        System.out.print("Digite o número da conta: ");
        int identificador = scanner.nextInt();
        Conta conta = null;
        for(Conta cont : contas) {
            if (cont.identificador() == identificador) {
                conta = cont;
            }
        }
        System.out.print("Digite o valor que deseja: ");
        double valor = scanner.nextDouble();
        if(conta.ehPoupanca == false)
            if(conta.saldo() - valor < -1000)
                return -1;
        if(conta.ehPoupanca == false)
            if(conta.saldo() - valor < -1000)
                return -1;
        if(conta != null && conta.saldo() > 0 && valor > 0) {
            double saldo = conta.debitar(valor);
            limparConsole();
            return saldo;
        }

        limparConsole();
        return -1;
    }

    public static double transferencia(List<Conta> contas) {
        limparConsole(); 
        System.out.print("Digite o número da conta de origem: ");
        int identificadorOrigem = scanner.nextInt();
        Conta contaOrigem = null;
        for(Conta cont : contas) {
            if (cont.identificador() == identificadorOrigem) {
                contaOrigem = cont;
            }
        }
        System.out.print("Digite o número da conta de destino: ");
        int identificadorDestino = scanner.nextInt();
        Conta contaDestino = null;
        for(Conta cont : contas) {
            if (cont.identificador() == identificadorDestino) {
                contaDestino = cont;
            }
        }
        System.out.print("Digite o valor que deseja: ");
        double valor = scanner.nextDouble();
        if(contaOrigem != null && contaOrigem.saldo() > 0 && contaDestino != null
                && valor > 0) {
            contaDestino.creditar(valor);
            if(contaDestino.pontuacao >= 10){
                contaDestino.pontuacao -= (int)(valor/300);
            }
            double saldo = contaOrigem.debitar(valor);
            limparConsole();
            return saldo;
        }

        limparConsole();
        return -1;
    }

    public static double renderJuros(List<Conta> contas) {
        limparConsole(); 
        System.out.print("Digite o número da conta: ");
        int identificador = scanner.nextInt();
        Conta conta = null;
        for(Conta cont : contas) {
            if (cont.identificador() == identificador) {
                conta = cont;
            }
        }
        if(!conta.ehPoupanca)
            return -1;

        System.out.print("Digite o juros que deseja aplicar: ");
        double juros = scanner.nextDouble();
        if(conta != null && conta.saldo() > 0 && juros > 0) {
            double saldo = conta.creditar(conta.saldo()*(juros/100));
            limparConsole();
            return saldo;
        }

        limparConsole();
        return -1;
    }

    public static double renderJuros(List<Conta> contas) {
        limparConsole(); 
        System.out.print("Digite o número da conta: ");
        int identificador = scanner.nextInt();
        Conta conta = null;
        for(Conta cont : contas) {
            if (cont.identificador() == identificador) {
                conta = cont;
            }
        }
        if(!conta.ehPoupanca)
            return -1;

        System.out.print("Digite o juros que deseja aplicar: ");
        double juros = scanner.nextDouble();
        if(conta != null && conta.saldo() > 0 && juros > 0) {
            double saldo = conta.creditar(conta.saldo()*(juros/100));
            limparConsole();
            return saldo;
        }

        limparConsole();
        return -1;
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
                System.out.println("2 - Consultar saldo");
                System.out.println("3 - Crédito");
                System.out.println("4 - Débito");
                System.out.println("5 - Transferência");
                System.out.println("6 - Cadastrar nova conta bônus");
                System.out.println("7 - Cadastrar nova conta poupança");
                System.out.println("8 - Render Juros em Poupança");
                System.out.println("9 - Consultar dados de conta");
                break;
            case 1:
                System.out.println("1 - Outras operações");
                break;
        }
        System.out.println("0 - Sair");
        System.out.print("Selecione uma opção: ");
    }

    public static void menu(List<Conta> contas) {
        paginaMenu = 0;

        while (true) {
            exibirAssinatura();
            exibirOpcoesMenu();
            escolha = scanner.nextLine();

            double saldo;

            limparConsole();

            switch (paginaMenu) {
                case 0:
                    switch (escolha) {
                        case "1":
                            Conta conta = new Conta();
                            conta = cadastrarConta();
                            System.out.print("Informe um saldo inicial para sua conta: ");
                            conta.setSaldo(scanner.nextDouble());
                            System.out.print("Informe um saldo inicial para sua conta: ");
                            conta.setSaldo(scanner.nextDouble());
                            scanner.nextLine();
                            if (conta == null)
                                break;
                            contas.add(conta);
                            System.out.println("Dados inseridos com sucesso.");
                            break;
                        case "2":
                            saldo = consultarSaldo(contas);
                            scanner.nextLine();
                            if (saldo == -1) {
                                System.out.println("Conta inválida!");
                                break;
                            }
                            System.out.println("O saldo da sua conta é: " + saldo);
                            break;
                        case "3":
                            saldo = credito(contas);
                            scanner.nextLine();
                            if (saldo == -1) {
                                System.out.println("Conta inválida!");
                                break;
                            }
                            System.out.println("Operação realizada com sucesso.\nO saldo da sua conta é: " + saldo);
                            break;
                        case "4":
                            saldo = debito(contas);
                            scanner.nextLine();
                            if (saldo == -1) {
                                System.out.println("Conta inválida ou saldo insuficiente!");
                                break;
                            }
                            System.out.println("Operação realizada com sucesso.\nO saldo da sua conta é: " + saldo);
                            break;
                        case "5":
                            saldo = transferencia(contas);
                            scanner.nextLine();
                            if (saldo == -1) {
                                System.out.println("Conta inválida ou saldo insuficiente!");
                                break;
                            }
                            System.out.println("Operação realizada com sucesso.\nO saldo da conta de origem é: " + saldo);
                            break;
                        case "6":
                            Conta contaBonus = new Conta();
                            contaBonus = cadastrarConta();
                            contaBonus.pontuacao = 10;
                            scanner.nextLine();
                            if (contaBonus == null)
                                break;
                            contas.add(contaBonus);
                            System.out.println("Dados inseridos com sucesso.");
                            break;
                        case "7":
                            Conta contaPoupanca= new Conta();
                            contaPoupanca = cadastrarConta();
                            contaPoupanca.ehPoupanca = true;
                            System.out.print("Informe um saldo inicial para sua conta poupança: ");
                            contaPoupanca.saldo = scanner.nextDouble();
                            scanner.nextLine();
                            limparConsole();
                            if (contaPoupanca == null)
                                break;
                            contas.add(contaPoupanca);
                            System.out.println("Dados inseridos com sucesso.");
                            break;
                        case "8":
                            saldo = renderJuros(contas);
                            scanner.nextLine();
                            if (saldo == -1) {
                                System.out.println("Conta inválida ou inválida para a operação!");
                                break;
                            }
                            System.out.println("O saldo da sua conta é: " + saldo);
                            break;
                        case "9":
                            String dados = consultarDados(contas);
                            scanner.nextLine();                           
                            System.out.println(dados);
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

package conta_bancaria;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

import conta_bancaria.controller.ContaController;
import conta_bancaria.model.Conta;
import conta_bancaria.model.ContaCorrente;
import conta_bancaria.model.ContaPoupanca;
import conta_bancaria.util.Cores;

public class Menu {

    // Global
    private static final Scanner leia = new Scanner(System.in);
    private static final ContaController contaController = new ContaController();

    public static void main(String[] args) {

        int opcao;

        // Criar dados de teste
        criarContasTeste();

        while (true) {

            System.out.println(Cores.TEXT_GREEN_BOLD_BRIGHT + Cores.ANSI_BLACK_BACKGROUND
                    + "*****************************************************");
            System.out.println("                                                     ");
            System.out.println("                BANCO DO PALMEIRAS                   ");
            System.out.println("                                                     ");
            System.out.println("*****************************************************");
            System.out.println("                                                     ");
            System.out.println("            1 - Criar Conta                          ");
            System.out.println("            2 - Listar todas as Contas               ");
            System.out.println("            3 - Buscar Conta por Número              ");
            System.out.println("            4 - Atualizar Dados da Conta             ");
            System.out.println("            5 - Apagar Conta                         ");
            System.out.println("            6 - Sacar                                ");
            System.out.println("            7 - Depositar                            ");
            System.out.println("            8 - Transferir valores entre Contas      ");
            System.out.println("            9 - Listar Contas por Titular            ");
            System.out.println("            0 - Sair                                 ");
            System.out.println("                                                     ");
            System.out.println("*****************************************************");
            System.out.println("Entre com a opção desejada:                          ");
            System.out.println("                                                     " + Cores.TEXT_RESET);

            try {
                opcao = leia.nextInt();
                leia.nextLine();
            } catch (InputMismatchException e) {
                opcao = -1;
                System.out.println("\nDigite um número inteiro entre 0 e 9");
                leia.nextLine();
            }

            if (opcao == 0) {
                System.out.println(Cores.TEXT_WHITE_BOLD + "\nBanco do Palmeiras - Sua paixão começa aqui!");
                sobre();
                leia.close();
                System.exit(0);
            }

            switch (opcao) {
            case 1 -> {
                System.out.println(Cores.TEXT_WHITE + "Criar Conta\n\n");
                cadastrarConta();
                keyPress();
            }
            case 2 -> {
                System.out.println(Cores.TEXT_WHITE + "Listar todas as Contas\n\n");
                listarContas();
                keyPress();
            }
            case 3 -> {
                System.out.println(Cores.TEXT_WHITE + "Consultar dados da Conta - por número\n\n");
                procurarContaPorNumero();
                keyPress();
            }
            case 4 -> {
                System.out.println(Cores.TEXT_WHITE + "Atualizar dados da Conta\n\n");
                atualizarConta();
                keyPress();
            }
            case 5 -> {
                System.out.println(Cores.TEXT_WHITE + "Apagar a Conta\n\n");
                deletarConta();
                keyPress();
            }
            case 6 -> {
                System.out.println(Cores.TEXT_WHITE + "Saque\n\n");
                sacar();
                keyPress();
            }
            case 7 -> {
                System.out.println(Cores.TEXT_WHITE + "Depósito\n\n");
                depositar();
                keyPress();
            }
            case 8 -> {
                System.out.println(Cores.TEXT_WHITE + "Transferência entre Contas\n\n");
                transferir();
                keyPress();
            }
            case 9 -> {
                System.out.println(Cores.TEXT_WHITE + "Listar Contas por Titular\n\n");
                listarPorTitular();
                keyPress();
            }
            default -> {
                System.out.println(Cores.TEXT_RED_BOLD + "\nOpção Inválida!\n" + Cores.TEXT_RESET);
                keyPress();
            }
            }
        }
    }

    public static void sobre() {
        System.out.println("\n*********************************************************");
        System.out.println("Projeto Desenvolvido por: ");
        System.out.println("Kauê Dota - bancopalmeiras@palmeiras.org");
        System.out.println("github.com/bancopalmeiras");
        System.out.println("*********************************************************");
    }

    public static void keyPress() {
        System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para continuar...");
        leia.nextLine();
    }

    public static void criarContasTeste() {
        contaController.cadastrar(
                new ContaCorrente(contaController.gerarNumero(), 456, 1, "Thuany Silva", 1000000.00f, 100000.00f));
        contaController.cadastrar(
                new ContaPoupanca(contaController.gerarNumero(), 456, 2, "Marcia Condarco", 1000000.00f, 10));
    }

    public static void listarContas() {
        contaController.listarTodas();
    }

    public static void cadastrarConta() {
        System.out.println("Digite o número da agência: ");
        int agencia = leia.nextInt();

        System.out.println("Digite o nome do titular da conta: ");
        leia.skip("\\R?");
        String titular = leia.nextLine();

        System.out.println("Digite o tipo da conta (1 - CC | 2 - CP): ");
        int tipo = leia.nextInt();

        System.out.println("Digite o saldo da conta: ");
        float saldo = leia.nextFloat();

        switch (tipo) {
        case 1 -> {
            System.out.println("Digite o limite da conta: ");
            float limite = leia.nextFloat();
            leia.nextLine();
            contaController.cadastrar(
                    new ContaCorrente(contaController.gerarNumero(), agencia, tipo, titular, saldo, limite));
        }
        case 2 -> {
            System.out.println("Digite o dia do aniversário da conta: ");
            int aniversario = leia.nextInt();
            leia.nextLine();
            contaController.cadastrar(
                    new ContaPoupanca(contaController.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
        }
        default -> System.out.println(Cores.TEXT_RED + "Tipo de conta inválida!" + Cores.TEXT_RESET);
        }
    }

    public static void procurarContaPorNumero() {
        System.out.println("Digite o número da conta: ");
        int numero = leia.nextInt();
        leia.nextLine();
        contaController.procurarPorNumero(numero);
    }

    public static void deletarConta() {
        System.out.println("Digite o número da conta: ");
        int numero = leia.nextInt();
        leia.nextLine();

        Optional<Conta> conta = contaController.buscarNaCollection(numero);

        if (conta.isPresent()) {
            System.out.printf("\nTem certeza que você deseja excluir a conta numero %d (S/N)?", numero);
            String confirmacao = leia.nextLine();

            if (confirmacao.equalsIgnoreCase("S"))
                contaController.deletar(numero);
            else
                System.out.println("\nOperação cancelada!");
        } else {
            System.out.printf("\nA conta número %d não foi encontrada!", numero);
        }
    }

    public static void atualizarConta() {
        // ... (mantém a lógica de atualização que você já tinha implementado)
    }

    public static void sacar() {
        System.out.println("Digite o número da conta: ");
        int numero = leia.nextInt();

        System.out.println("Digite o valor do saque: ");
        float valor = leia.nextFloat();
        leia.nextLine();

        contaController.sacar(numero, valor);
    }

    public static void depositar() {
        System.out.println("Digite o número da conta: ");
        int numero = leia.nextInt();

        System.out.println("Digite o valor do depósito: ");
        float valor = leia.nextFloat();
        leia.nextLine();

        contaController.depositar(numero, valor);
    }

    public static void transferir() {
        System.out.println("Digite o número da conta de origem: ");
        int numeroOrigem = leia.nextInt();

        System.out.println("Digite o número da conta de destino: ");
        int numeroDestino = leia.nextInt();

        System.out.println("Digite o valor da transferência: ");
        float valor = leia.nextFloat();
        leia.nextLine();

        contaController.transferir(numeroOrigem, numeroDestino, valor);
    }

    public static void listarPorTitular() {
        System.out.println("Digite o nome do titular: ");
        String titular = leia.nextLine();
        contaController.listarPorTitular(titular);
    }
    
}
package conta_bancaria;

import java.util.Scanner;

import conta_bancaria.model.Conta;
import conta_bancaria.model.ContaCorrente;
import conta_bancaria.util.Cores;

public class Menu {
	public static void main(String[] args) {

		Scanner leia = new Scanner(System.in);

		int opcao;
		
		Conta c1 = new Conta(1, 123, 1, "Isabella", 200000.00f);
		
		c1.visualizar();
		
		Conta c2 = new Conta(1, 123, 2, "Kaue", 400000.00f);
		
		c2.visualizar();
		
		/* Alteração de Saldo */
		
		c1.setSaldo(300000.00f);
		c1.setTitular("Isabella Bruno");
		c1.visualizar();
		
		System.out.println("\nSacar R$ 300.000,00 da conta C2: " + (c2.sacar(500000.00f) ? 
				"Saque efetuado com sucesso!" : "Saldo Insuficiente"));
		
		/**
		 * if Ternário
		 * 
		 * condição "?" ação se for verdadeiro ":" ação se for falso
		 * */
		System.out.println("\nSacar R$ 1.000,00 da conta C2: " + (c2.sacar(1000.00f) ? 
				"Saque efetuado com sucesso!" : "Saldo Insuficiente"));
		
		c2.visualizar();
		
		/* Deposito na Conta c2*/
		
		c2.depositar(50000.00f);
		c2.visualizar();
		
		/* Instanciar Objetos da Classe ContaCorrente*/
		
		ContaCorrente cc1 = new ContaCorrente(3, 789, 1, "Raquel", 200000.00f, 2000.00f);
		cc1.visualizar();
		
		System.out.println("\nSacar R$ 3.000,00 da conta Cc1: " + (cc1.sacar(3000.00f) ?
				"Saque efetuado com sucesso! | Saldo " + cc1.getSaldo() : "Saldo Insuficiente | Saldo: " + cc1.getSaldo()));
		
		cc1.depositar(2000.00f);
		cc1.visualizar();

		while (true) {

			System.out.println(Cores.TEXT_RED_BRIGHT + Cores.ANSI_WHITE_BACKGROUND
					+ "*****************************************************");
			System.out.println("                                                     ");
			System.out.println("                BANCO DO AMOR                        ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("                                                     ");
			System.out.println("            1 - Criar Conta                          ");
			System.out.println("            2 - Listar todas as Contas               ");
			System.out.println("            3 - Buscar Conta por Numero              ");
			System.out.println("            4 - Atualizar Dados da Conta             ");
			System.out.println("            5 - Apagar Conta                         ");
			System.out.println("            6 - Sacar                                ");
			System.out.println("            7 - Depositar                            ");
			System.out.println("            8 - Transferir valores entre Contas      ");
			System.out.println("            0 - Sair                                 ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("Entre com a opção desejada:                          ");
			System.out.println("                                                     " + Cores.TEXT_RESET);

			opcao = leia.nextInt();

			if (opcao == 0) {
				System.out.println(Cores.TEXT_WHITE_BOLD + "\nBanco do Amor - Sua paixão começa aqui!");
				sobre();
				leia.close();
				System.exit(0);
			}

			switch (opcao) {
			case 1:
				System.out.println(Cores.TEXT_WHITE + "Criar Conta\n\n");

				break;
			case 2:
				System.out.println(Cores.TEXT_WHITE + "Listar todas as Contas\n\n");

				break;
			case 3:
				System.out.println(Cores.TEXT_WHITE + "Consultar dados da Conta - por número\n\n");

				break;
			case 4:
				System.out.println(Cores.TEXT_WHITE + "Atualizar dados da Conta\n\n");

				break;
			case 5:
				System.out.println(Cores.TEXT_WHITE + "Apagar a Conta\n\n");

				break;
			case 6:
				System.out.println(Cores.TEXT_WHITE + "Saque\n\n");

				break;
			case 7:
				System.out.println(Cores.TEXT_WHITE + "Depósito\n\n");

				break;
			case 8:
				System.out.println(Cores.TEXT_WHITE + "Transferência entre Contas\n\n");

				break;
			default:
				System.out.println(Cores.TEXT_RED_BOLD + "\nOpção Inválida!\n" + Cores.TEXT_RESET);
				break;
			}
		}
	}

	public static void sobre() {
		System.out.println("\n*********************************************************");
		System.out.println("Projeto Desenvolvido por: Kauê Dota ");
		System.out.println("Banco do Amor - contato@bancodoamor.org");
		System.out.println("github.com/kauedota");
		System.out.println("*********************************************************");
	}

}
package br.com.mesttra.bancomil;

import java.io.IOException;
import java.util.Scanner;

import br.com.mesttra.bancomil.cliente.Cliente;
import br.com.mesttra.bancomil.cliente.ClientePf;
import br.com.mesttra.bancomil.cliente.ClientePj;
import br.com.mesttra.bancomil.dao.ClienteDAO;
import br.com.mesttra.bancomil.service.FuncoesGerente;
import br.com.mesttra.bancomil.util.Menu;
import br.com.mesttra.bancomil.util.SaldoInsuficienteException;

public class Main {

	static Cliente[] clientes = new Cliente[49];

	public static void main(String[] args) throws InterruptedException {
		Scanner input = new Scanner(System.in);
		
		ClienteDAO clienteDAO = new ClienteDAO();
		testes(clienteDAO, input);
		
		FuncoesGerente gerente = new FuncoesGerente();
		Menu ui = new Menu();
		PopulaBanco();

		int cadastrados = 0;
		limpaConsole();
		int operacao;
		Integer numeroDaConta;
		boolean flag = true;
		while (flag) {
			input = new Scanner(System.in);
			ui.cabecalho();
			ui.opcoes();
			operacao = input.nextInt();
			try {
				switch (operacao) {
				case 1:
					limpaConsole();
					input = new Scanner(System.in);
					ui.opcaoCadastro();
					int op = verificaOperacaoCadastro(input);
					if (op == 1) {
						cadastrados = gerente.cadastrarClientePj(clientes, cadastrados);
						Thread.sleep(2500);
						limpaConsole();
					} else if (op == 2) {
						cadastrados = gerente.cadastrarClientePf(clientes, cadastrados);
						Thread.sleep(2500);
						limpaConsole();
					} else {
						System.out.println("OPERAÇÃO CANCELADA");
						Thread.sleep(2500);
						limpaConsole();
						break;
					}
					break;

				case 2:
					limpaConsole();
					input = new Scanner(System.in);
					ui.consultarCliente();
					numeroDaConta = input.nextInt();
					String cliente = gerente.consultarCliente(numeroDaConta, clientes);
					System.out.println(cliente);
					break;

				case 3:
					limpaConsole();
					input = new Scanner(System.in);
					ui.removerCliente();
					numeroDaConta = input.nextInt();
					gerente.removerCliente(numeroDaConta, clientes);
					break;

				case 4:
					limpaConsole();
					input = new Scanner(System.in);
					ui.transferenciaBancaria();
					ui.transFonte();
					Integer numeroFonte = input.nextInt();
					ui.transDestino();
					Integer numeroDestino = input.nextInt();
					ui.valorTrans();
					Double valorTrans = input.nextDouble();
					try {
						gerente.fazerTransferencia(numeroFonte, valorTrans, numeroDestino, clientes);
					} catch (SaldoInsuficienteException e) {
						System.out.println("Saldo insuficiente na conta depositante.");
					}
					break;

				case 5:
					limpaConsole();
					input = new Scanner(System.in);
					ui.alterarLimite();
					numeroDaConta = input.nextInt();
					Double limiteAtual = gerente.consultaLimite(numeroDaConta, clientes);
					System.out.print("Limite atual da conta [ " + numeroDaConta + " ]: " + limiteAtual);
					System.out.print("\nNovo limite: ");
					Double novoLimite = input.nextDouble();
					gerente.alterarLimite(numeroDaConta, novoLimite, clientes);
					break;

				case 6:
					limpaConsole();
					input = new Scanner(System.in);
					ui.deposito();
					numeroDaConta = input.nextInt();
					ui.valorDeposito();
					Double valorDeposito = input.nextDouble();
					gerente.adicionarSaldo(numeroDaConta, valorDeposito, clientes);
					break;

				case 7:
					limpaConsole();
					input = new Scanner(System.in);
					ui.clientesDevedores();
					gerente.consultarClientesNegativados(clientes);
					break;
					
				case 8:
					limpaConsole();
					input = new Scanner(System.in);
					ui.relatorio();
					gerente.relatorio(clientes);
					break;
				default:
					ui.sairDoSistema();
					flag = false;
				}
			} catch (NullPointerException ex) {
				System.out.println("Usuário não encontrado.");
			}
		}

	}

	public static int verificaOperacaoCadastro(Scanner input) {
		int op = input.nextInt();
		while (op < 0 && op > 2) {
			System.out.println("Opção inválida: ");
			op = input.nextInt();
		}
		return op;
	}


	public static void limpaConsole() {
		try {
			if (System.getProperty("os.name").contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else
				Runtime.getRuntime().exec("clear");
		} catch (IOException | InterruptedException ex) {
		}
	}
	
	public static void PopulaBanco() {


		Cliente cliente0 = new ClientePj(123456, 21, "11921252121", 0.0, 50.0, "31.735.079/0001-22", 
				"Cliente1 Fantasia", "NomeClientePJ1");
		Cliente cliente1 = new ClientePj(654321, 21, "79996513762", 0.0, 500.0, "81.758.069/0001-80", 
				"Cliente2 Fantasia", "NomeClientePJ2");
		Cliente cliente2 = new ClientePj(159357, 25, "71952144558", 0.0, 5000.0, "88.633.321/0001-74", 
				"Cliente3 Fantasia", "NomeClientePJ3");
		Cliente cliente3 = new ClientePj(752586, 25, "81952654122", 0.0, 100.0, "47.763.323/0001-09", 
				"Cliente4 Fantasia", "NomeClientePJ4");
		Cliente cliente4 = new ClientePf(485121, 20, "79956554515", 0.0, 1000.0, "839.312.180-90", "NomeClientePF1",
				21);
		Cliente cliente5 = new ClientePf(412212, 21, "73988532426", 0.0, 150.0, "783.049.570-00", "NomeClientePF2", 42);
		Cliente cliente6 = new ClientePf(213212, 25, "73981131894", 0.0, 80.0, "084.791.830-04", "NomeClientePF3", 55);
		Cliente cliente7 = new ClientePf(212215, 20, "73935341422", 0.0, 170.0, "033.875.560-80", "NomeClientePF4", 19);

		clientes[0] = cliente0;
		clientes[1] = cliente1;
		clientes[2] = cliente2;
		clientes[3] = cliente3;
		clientes[4] = cliente4;
		clientes[5] = cliente5;
		clientes[6] = cliente6;
		clientes[7] = cliente7;

	}
	
	public static void testes(ClienteDAO clienteDAO, Scanner input) {
		while (true) {
			System.out.println("1-Cadastro");
			System.out.println("2-Ver cliente");
			System.out.println("3-Excluir");
			System.out.println("4-Sair");
			System.out.println("5-Depositar");
			System.out.print("Resposta: ");
			int resp=input.nextInt();
			System.out.println();
			
			ClientePf cliente1 = new ClientePf();
			ClientePj cliente2 = new ClientePj();
			
			switch(resp) {
			
			case 1:
				//Cadastro PF
				cliente1 = new ClientePf(1001, 1, "12996291100", 200.00, 1000.00, "47663159870", "Nicolas", 19);
				clienteDAO.cadastrarClientePF(cliente1);
		
				//Cadastro PJ
			    cliente2 = new ClientePj(1002, 1, "12981164404", 800.00, 5000.00, "5954563454356", "Combater o cancer", "Cloreto");
				clienteDAO.cadastrarClientePJ(cliente2);
				break;
				
			case 2:
				//Ver clientePF
				System.out.println(clienteDAO.consultarCliente(cliente1, 1001));
				
				//Ver clientePJ
				System.out.println(clienteDAO.consultarCliente(cliente2, 1002));
				
			case 3:
				//Excluir PF
				clienteDAO.removerCliente(cliente1, 1001);
				
				//Excluir PJ
				clienteDAO.removerCliente(cliente2, 1002);
				break;
				
			case 4:
				return;
				
			case 5:
				clienteDAO.depositar(cliente1, 400.00, 1001);
				clienteDAO.depositar(cliente2, 2000.00, 1002);
			}
	
		}		
	}

}

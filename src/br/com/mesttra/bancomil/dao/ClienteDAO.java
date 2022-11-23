package br.com.mesttra.bancomil.dao;

import java.sql.Connection;

import br.com.mesttra.bancomil.connectionFactory.ConnectionFactory;

public class ClienteDAO {

	private Connection conn;
	
	public ClienteDAO() {
		this.conn = new ConnectionFactory().getConnection();
		System.out.println("Conectado");
		System.out.println();
	}
	
	public void cadastrarCliente() {
		
	}
	
//	System.out.println("OPERAÇÕES:");
//	System.out.println("\t - Cadastrar cliente \t\t [1]");
//	System.out.println("\t - Consultar cliente \t\t [2]");
//	System.out.println("\t - Remover cliente \t\t [3]");
//	System.out.println("\t - Realizar transferência \t [4]");
//	System.out.println("\t - Alterar limite \t\t [5]");
//	System.out.println("\t - Depositar em conta \t\t [6]");
//	System.out.println("\t - Clientes devedores \t\t [7]");
//	System.out.println("\t - Gerar relatório \t\t [8]\n");
//	System.out.println("\t - SAIR DO SISTEMA \t\t [0]\n");
	
	public void consultarCliente() {
		
	}
	
	public void removerCliente() {
		
	}
	
	public void realizarTransferencia() {
		
	}
	
	public void alterarLimite() {
		
	}
	
	public void depositar() {
		
	}
	
	public void clientesDevedores() {
		
	}
	
	public void relatorio() {
		
	}
}

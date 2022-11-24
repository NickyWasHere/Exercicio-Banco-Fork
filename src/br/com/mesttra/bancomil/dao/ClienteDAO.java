package br.com.mesttra.bancomil.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.mesttra.bancomil.cliente.Cliente;
import br.com.mesttra.bancomil.cliente.ClientePf;
import br.com.mesttra.bancomil.connectionFactory.ConnectionFactory;

public class ClienteDAO {

	private Connection conn;
	
	public ClienteDAO() {
		this.conn = new ConnectionFactory().getConnection();
		System.out.println("Conectado");
		System.out.println();
	}
	
	public void cadastrarCliente(Cliente cliente) {
		if (cliente instanceof ClientePf) {
			String sql = "INSERT INTO clientePF values (?, ?, ?, ?, ?, ?, ?, ?)";
			
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, 0);
				stmt.setInt(2, 0);
				stmt.setString(3, "");
				stmt.setDouble(4, 0);
				stmt.setDouble(5, 0);
				stmt.setString(6, "");
				stmt.setString(7, "");
				stmt.setInt(8, 0);
				stmt.execute();
				stmt.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} else {
			String sql = "INSERT INTO clientePJ values (?, ?, ?, ?, ?, ?, ?, ?)";
			
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, 0);
				stmt.setInt(2, 0);
				stmt.setString(3, "");
				stmt.setDouble(4, 0);
				stmt.setDouble(5, 0);
				stmt.setString(6, "");
				stmt.setString(7, "");
				stmt.setString(8, "");
				stmt.execute();
				stmt.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
	
	public void consultarCliente(Cliente cliente, int numero) {
		if (cliente instanceof ClientePf) {
			String sql = "SELECT * FROM clientePF WHERE numero = ?";
			
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, numero);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} else {
			
		}		
	}
	
	public void removerCliente(Cliente cliente, int numero) {
		if (cliente instanceof ClientePf) {
			String sql = "DELETE FROM clientePF WHERE numero = ?";
			
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, numero);
				stmt.execute();
				stmt.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} else {
			String sql = "DELETE FROM clientePJ WHERE numero = ?";
			
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, numero);
				stmt.execute();
				stmt.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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
	
	public void retornarClientePF(PreparedStatement stmt) {
		try {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ClientePf cliente = new ClientePf();
				
//				private Integer numero;
//				private Integer agencia;
//				private String telefone;
//				private Double saldo;
//				private Double limite;
//				private String cpf;
//				private String nome;
//				private Integer idade;
				
//				private String cnpj;
//				private String nomeSocial;
//				private String nomeFantasia;
				
				cliente.setNumero(rs.getInt("numero"));
				cliente.setAgencia(rs.getInt("agencia"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setSaldo(rs.getDouble("saldo"));
				cliente.setLimite(rs.getDouble("limite"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void retornarClientePJ(PreparedStatement stmt) {
		
	}
}

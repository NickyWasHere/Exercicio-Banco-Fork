package br.com.mesttra.bancomil.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.mesttra.bancomil.cliente.Cliente;
import br.com.mesttra.bancomil.cliente.ClientePf;
import br.com.mesttra.bancomil.cliente.ClientePj;
import br.com.mesttra.bancomil.connectionFactory.ConnectionFactory;

public class ClienteDAO {

	private Connection conn;
	
	public ClienteDAO() {
		this.conn = new ConnectionFactory().getConnection();
		System.out.println("Conectado");
		System.out.println();
	}
	
	public void cadastrarClientePF(ClientePf cliente) {
		String sql = "INSERT INTO clientePF values (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cliente.getNumero());
			stmt.setInt(2, cliente.getAgencia());
			stmt.setString(3, cliente.getTelefone());
			stmt.setDouble(4, cliente.getSaldo());
			stmt.setDouble(5, cliente.getLimite());
			stmt.setString(6, cliente.getCpf());
			stmt.setString(7, cliente.getNome());
			stmt.setInt(8, cliente.getIdade());
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public void cadastrarClientePJ(ClientePj cliente) {
		String sql = "INSERT INTO clientePJ values (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cliente.getNumero());
			stmt.setInt(2, cliente.getAgencia());
			stmt.setString(3, cliente.getTelefone());
			stmt.setDouble(4, cliente.getSaldo());
			stmt.setDouble(5, cliente.getLimite());
			stmt.setString(6, cliente.getCnpj());
			stmt.setString(7, cliente.getRazaoSocial());
			stmt.setString(8, cliente.getNomeFantasia());
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Cliente consultarCliente(Cliente cliente, int numero) {
		if (cliente instanceof ClientePf) {
			String sql = "SELECT * FROM clientePF WHERE numero = ?";
			
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, numero);
				
				ClientePf clientePf = retornarClientePF(stmt);
				return clientePf;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} else {
			String sql = "SELECT * FROM clientePJ WHERE numero = ?";
			
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, numero);
				
				ClientePj clientePj = retornarClientePJ(stmt);
				return clientePj;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		
		return null;
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
	
	public void depositar(Cliente cliente, double saldo, int numero) {
		if (cliente instanceof ClientePf) {
			String sql = "UPDATE clientePF SET saldo = saldo + ? WHERE numero = ?";
			
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				
				stmt.setDouble(1, saldo);
				stmt.setInt(2, numero);
				stmt.execute();
				stmt.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} else {
			String sql = "UPDATE clientePJ SET saldo = saldo + ? WHERE numero = ?";
			
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				
				stmt.setDouble(1, saldo);
				stmt.setInt(2, numero);
				stmt.execute();
				stmt.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void clientesDevedores() {
		
	}
	
	public void relatorio() {
		
	}
	
	public ClientePf retornarClientePF(PreparedStatement stmt) {
		try {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ClientePf cliente = new ClientePf();
				
				cliente.setNumero(rs.getInt("numero"));
				cliente.setAgencia(rs.getInt("agencia"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setSaldo(rs.getDouble("saldo"));
				cliente.setLimite(rs.getDouble("limite"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setNome(rs.getString("nome"));
				cliente.setIdade(rs.getInt("idade"));
				
				return cliente;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ClientePj retornarClientePJ(PreparedStatement stmt) {
		try {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ClientePj cliente = new ClientePj();
				
				cliente.setNumero(rs.getInt("numero"));
				cliente.setAgencia(rs.getInt("agencia"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setSaldo(rs.getDouble("saldo"));
				cliente.setLimite(rs.getDouble("limite"));
				cliente.setCnpj(rs.getString("cnpj"));
				cliente.setRazaoSocial(rs.getString("razaoSocial"));
				cliente.setNomeFantasia(rs.getString("nomeFantasia"));
				
				return cliente;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}

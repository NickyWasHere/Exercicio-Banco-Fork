package br.com.mesttra.bancomil.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	public Cliente consultarClientePf(int numero) {
		String sql = "SELECT * FROM clientePF WHERE numero = ?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, numero);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ClientePf clientePf = new ClientePf();
				
				clientePf.setNumero(rs.getInt("numero"));
				clientePf.setAgencia(rs.getInt("agencia"));
				clientePf.setTelefone(rs.getString("telefone"));
				clientePf.setSaldo(rs.getDouble("saldo"));
				clientePf.setLimite(rs.getDouble("limite"));
				clientePf.setCpf(rs.getString("cpf"));
				clientePf.setNome(rs.getString("nome"));
				clientePf.setIdade(rs.getInt("idade"));
				
				return clientePf;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return null;
	}
	
	public Cliente ConsultarClientePj(int numero) {
		String sql = "SELECT * FROM clientePJ WHERE numero = ?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, numero);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ClientePj clientePj = new ClientePj();
				
				clientePj.setNumero(rs.getInt("numero"));
				clientePj.setNumero(rs.getInt("numero"));
				clientePj.setAgencia(rs.getInt("agencia"));
				clientePj.setTelefone(rs.getString("telefone"));
				clientePj.setSaldo(rs.getDouble("saldo"));
				clientePj.setLimite(rs.getDouble("limite"));
				clientePj.setCnpj(rs.getString("cnpj"));
				clientePj.setRazaoSocial(rs.getString("razaoSocial"));
				clientePj.setNomeFantasia(rs.getString("nomeFantasia"));
				
				return clientePj;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		return null;
	}
	
	public void removerClientePf(int numero) {
		String sql = "DELETE FROM clientePF WHERE numero = ?";	
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, numero);
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removerClientePj(int numero) {
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
	
	public void realizarTransferenciaPfToPf(double valor, int origem, int destino) {
		String sqlOrigem = "UPDATE clientePF SET saldo = saldo - ? WHERE numero = ?";
		String sqlDestino = "UPDATE clientePF SET saldo = saldo + ? WHERE numero = ?";
		
		try {
			PreparedStatement stmt1 = conn.prepareStatement(sqlOrigem);
			PreparedStatement stmt2 = conn.prepareStatement(sqlDestino);
			
			stmt1.setDouble(1, valor);
			stmt1.setInt(2, origem);
			stmt1.execute();
			stmt1.close();
			
			stmt2.setDouble(1, valor);
			stmt2.setInt(2, destino);
			stmt2.execute();
			stmt2.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void realizarTransferenciaPfToPj(double valor, int origem, int destino) {
		String sqlOrigem = "UPDATE clientePF SET saldo = saldo - ? WHERE numero = ?";
		String sqlDestino = "UPDATE clientePJ SET saldo = saldo + ? WHERE numero = ?";
		
		try {
			PreparedStatement stmt1 = conn.prepareStatement(sqlOrigem);
			PreparedStatement stmt2 = conn.prepareStatement(sqlDestino);
			
			stmt1.setDouble(1, valor);
			stmt1.setInt(2, origem);
			stmt1.execute();
			stmt1.close();
			
			stmt2.setDouble(1, valor);
			stmt2.setInt(2, destino);
			stmt2.execute();
			stmt2.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void realizarTransferenciaPjToPf(double valor, int origem, int destino) {
		String sqlOrigem = "UPDATE clientePJ SET saldo = saldo - ? WHERE numero = ?";
		String sqlDestino = "UPDATE clientePF SET saldo = saldo + ? WHERE numero = ?";
		
		try {
			PreparedStatement stmt1 = conn.prepareStatement(sqlOrigem);
			PreparedStatement stmt2 = conn.prepareStatement(sqlDestino);
			
			stmt1.setDouble(1, valor);
			stmt1.setInt(2, origem);
			stmt1.execute();
			stmt1.close();
			
			stmt2.setDouble(1, valor);
			stmt2.setInt(2, destino);
			stmt2.execute();
			stmt2.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void realizarTransferenciaPjToPj(double valor, int origem, int destino) {
		String sqlOrigem = "UPDATE clientePJ SET saldo = saldo - ? WHERE numero = ?";
		String sqlDestino = "UPDATE clientePJ SET saldo = saldo + ? WHERE numero = ?";
		
		try {
			PreparedStatement stmt1 = conn.prepareStatement(sqlOrigem);
			PreparedStatement stmt2 = conn.prepareStatement(sqlDestino);
			
			stmt1.setDouble(1, valor);
			stmt1.setInt(2, origem);
			stmt1.execute();
			stmt1.close();
			
			stmt2.setDouble(1, valor);
			stmt2.setInt(2, destino);
			stmt2.execute();
			stmt2.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void alterarLimitePf(double valor, int numero) {
		String sql = "UPDATE clientePF SET limite = ? WHERE numero = ?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, valor);
			stmt.setInt(2, numero);
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void alterarLimitePj(double valor, int numero) {
		String sql = "UPDATE clientePJ SET limite = ? WHERE numero = ?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, valor);
			stmt.setInt(2, numero);
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void depositarPf(double saldo, int numero) {
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
	}
	
	public void depositarPj(double saldo, int numero) {
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
	
	public ArrayList<ClientePf> clientesDevedoresPF() {
		String sql = "SELECT * FROM clientePF WHERE saldo < 0";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			return retornarClientePf(stmt);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ArrayList<ClientePj> clientesDevedoresPJ() {
		String sql = "SELECT * FROM clientePJ WHERE saldo < 0";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			return retornarClientePj(stmt);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ArrayList<Cliente> relatorio() {
		String sql1 = "SELECT * FROM clientePF";
		String sql2 = "SELECT * FROM clientePJ";
		
		try {
			PreparedStatement stmt1 = conn.prepareStatement(sql1);
			PreparedStatement stmt2 = conn.prepareStatement(sql2);
		
			ArrayList<ClientePf> clientesPf = retornarClientePf(stmt1);
			ArrayList<ClientePj> clientesPj = retornarClientePj(stmt2);
			
			ArrayList<Cliente> clientes = new ArrayList<>();
			clientes.addAll(clientesPf);
			clientes.addAll(clientesPj);
			
			return clientes;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ArrayList<ClientePf> retornarClientePf(PreparedStatement stmt) {
		ArrayList<ClientePf> clientes = new ArrayList<>();
		
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
				
				clientes.add(cliente);
			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return clientes;
	}
	
	public ArrayList<ClientePj> retornarClientePj(PreparedStatement stmt) {
		ArrayList<ClientePj> clientes = new ArrayList<>();
		
		try {			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ClientePj cliente = new ClientePj();
				
				cliente.setNumero(rs.getInt("numero"));
				cliente.setNumero(rs.getInt("numero"));
				cliente.setAgencia(rs.getInt("agencia"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setSaldo(rs.getDouble("saldo"));
				cliente.setLimite(rs.getDouble("limite"));
				cliente.setCnpj(rs.getString("cnpj"));
				cliente.setRazaoSocial(rs.getString("razaoSocial"));
				cliente.setNomeFantasia(rs.getString("nomeFantasia"));
				
				clientes.add(cliente);
			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return clientes;
	}
}

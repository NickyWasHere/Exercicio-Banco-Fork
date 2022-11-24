package br.com.mesttra.bancomil.cliente;

public class ClientePf extends Cliente {

	private String cpf;
	private String nome;
	private Integer idade;

	public ClientePf() {
		
	}
	
	public ClientePf(Integer numero, Integer agencia, String telefone, Double saldo, Double limite, String cpf,
			String nome, Integer idade) {
		super(numero, agencia, telefone, saldo, limite);
		this.cpf = cpf;
		this.nome = nome;
		this.idade = idade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	@Override
	public String toString() {
		String cliente = ("Nome: " + getNome() + "\n" + "Idade: " + getIdade() + "\n" + "Cpf: " + getCpf() + "\n"
				+ "Numero: " + getNumero() + "\n" + "Agencia: " + getAgencia() + "\n" + "Telefone: " + getTelefone()
				+ "\n" + "Saldo: " + getSaldo() + "\n" + "Limite: " + getLimite() + "\n");

		return cliente;
	}

}

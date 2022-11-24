package br.com.mesttra.bancomil.cliente;

public class ClientePj extends Cliente {

	private String cnpj;
	private String razaoSocial;
	private String nomeFantasia;

	public ClientePj() {
		
	}
	
	public ClientePj(Integer numero, Integer agencia, String telefone, Double saldo, Double limite, String cnpj,
			 String razaoSocial, String nomeFantasia) {
		super(numero, agencia, telefone, saldo, limite);
		this.cnpj = cnpj;
		this.nomeFantasia = nomeFantasia;
		this.razaoSocial = razaoSocial;
	}
	
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	@Override
	public String toString() {
		String cliente = ("Numero: " + getNumero() + "\n" + "Agencia: " + getAgencia() + "\n" + "Telefone: "
				+ getTelefone() + "\n" + "Saldo: " + getSaldo() + "\n" + "Limite: " + getLimite() + "\n" + "Cnpj: "
				+ getCnpj() + "\n" + "Razão social: " + getRazaoSocial() + "\n"
				+ "Nome fantasia: " + getNomeFantasia() + "\n");

		return cliente;
	}
}

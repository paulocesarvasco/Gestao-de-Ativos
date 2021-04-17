package maps;

public class Ativo {

	private String nome;
	private double preco;
	private String tipo;
	private double quantidade;
	
	// Ativos nao podem ser criados sem uma verificacaoo previa de seu tipo
	public Ativo(String nome, double preco, double quantidade, String tipo) {
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		this.tipo = tipo;
	}
	
	public String getNome() {
		return this.nome;
	}
	public double getPreco() {
		return this.preco;
	}
	public String getTipo() {
		return this.tipo;
	}
	
	public double getQuantidade() {
		return this.quantidade;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}
}
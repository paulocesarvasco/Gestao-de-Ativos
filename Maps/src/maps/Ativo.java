package maps;

// Ativo abstrai em uma estrutura as informações que compõem cada ativo
public class Ativo {

	private String nome;
	private double preco;
	private String tipo;
	
	// Ativos nao podem ser criados sem uma verificacaoo previa de seu tipo
	public Ativo(String nome, double preco, String tipo) {
		this.nome = nome;
		this.preco = preco;
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
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
}
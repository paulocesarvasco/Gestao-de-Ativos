package maps;


// Classe registro contém as informações básicas para o registro de cada ativo
public class Registro {

	private String nome;
	private double quantidadeTotal;
	private double valorMercadoTotal;
	private double rendimento;
	private double lucro;
	private String tipo;
	
	public Registro (String nome, String tipo, double quantidade, double valor, double rendimento, double lucro) {
		this.nome = nome;
		this.quantidadeTotal = quantidade;
		this.valorMercadoTotal = valor;
		this.rendimento = rendimento;
		this.lucro = lucro;
		this.tipo = tipo;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public double getQuantidade() {
		return this.quantidadeTotal;
	}
	
	public double getValorMercado() {
		return this.valorMercadoTotal;
	}
	
	public double getRendimento() {
		return this.rendimento;
	}
	
	public double getLucro() {
		return this.lucro;
	}
	
	public String getTipo() {
		return this.tipo;
	}
}

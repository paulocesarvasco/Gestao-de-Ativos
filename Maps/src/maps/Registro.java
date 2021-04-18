package maps;

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
	
}

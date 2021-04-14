package maps;

// A classe movimentacao contém as informações referentes a cada movimentacao do saldo da conta
public class Movimentacao {

	private Double valor;
	private String descricao;
	
	public Movimentacao(Double valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
	}
}

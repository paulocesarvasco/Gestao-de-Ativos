package maps;

// A classe movimentacao contém as informações referentes a cada movimentacao do saldo da conta
public class Movimentacao {

	private Ativo ativo;
	private double quantidade;
	private double valor;
	private boolean compra;
	
	public Movimentacao(Ativo ativo, double quantidade, double valor) {
		this.ativo = ativo;
		this.quantidade = quantidade;
		this.valor = valor;
	}
	
	public Ativo getAtivo() {
		return this.ativo;
	}
	
	public double getQuantidade() {
		return this.quantidade;
	}
	
	public double getValor() {
		return this.valor;
	}
	
	// A flag compra em verdadeiro indica que a movimentacao é referente a uma compra,
	// caso falsa, representa uma venda
	public void setTipo(boolean compra) {
		this.compra = compra;
	}
	
	public boolean getTipo() {
		return this.compra;
	}
}

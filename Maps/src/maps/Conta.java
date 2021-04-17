package maps;

import java.util.ArrayList;
import java.util.List;

public class Conta {
	
	private double saldo = 0;
	private List<Movimentacao> movimentacao = new ArrayList<Movimentacao>();

	public double getSaldo() {
		return this.saldo;
	}
	
	public boolean venda(Double valor) {
		
		Movimentacao movimentacaoAtual= new Movimentacao(valor, "Venda");
		this.saldo += valor;
		this.movimentacao.add(movimentacaoAtual);
		return true;
	}
	
	public boolean compra(Double valor) {
		
		if (this.saldo < valor) {
			return false;
		}
		this.saldo -= valor;
		Movimentacao movimentacaoAtual= new Movimentacao(valor, "Compra");
		this.movimentacao.add(movimentacaoAtual);
		return true;
	}
}

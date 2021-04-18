package maps;

import java.util.ArrayList;
import java.util.List;

// Contam contém como atributo princical o saldo o qual será usado nas operações de compra e
// venda de ativos, além de uma lista onde será lançadas todas a operações que alteram o valor
// do saldo
public class Conta {
	
	private double saldo = 0;
	private List<String> movimentacao = new ArrayList<String>();

	public double getSaldo() {
		return this.saldo;
	}
	
	public boolean vender(double valor) {
		
		this.saldo += valor;
		String descricao = "Venda - " + String.valueOf(valor);
		this.movimentacao.add(descricao);
		return true;
	}
	
	public boolean comprar(double valor) {
		
		if (this.saldo < valor) {
			return false;
		}
		this.saldo -= valor;
		String descricao = "Venda - " + String.valueOf(valor);
		this.movimentacao.add(descricao);
		return true;
	}
	
	// Depositar realiza um acréscimo no valor do saldo usado apenas para realizar os testes
	// de compra e venda dos ativos
	public void depositar(double valor) {
		this.saldo += valor;
	}
}

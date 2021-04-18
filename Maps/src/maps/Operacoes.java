package maps;

import java.util.ArrayList;
import java.util.List;

// Operações representa as operações que podem ser realizadas com um ativo - compra e venda
public class Operacoes {
	
	private List<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();
	
	public boolean Compra(Conta conta, Ativos listaAtivos, Movimentacao movimentacao) {
		
		boolean erro = false;

		// Verifica se o ativo a ser comprado consta na lista
		erro = listaAtivos.buscaAtivo(movimentacao.getAtivo());
		if (!erro) {
			return false;
		}
		// Laçamento do valor na operação no saldo da conta
		erro = conta.comprar(movimentacao.getValor());
		if (!erro) {
			return false;
		}
		
		// Registro da movimentacao
		movimentacao.setTipo(true);
		this.movimentacoes.add(movimentacao);
		return true;
	}
	
	public boolean Venda(Conta conta, Ativos listaAtivos, Movimentacao movimentacaoAtual) {
		
		boolean erro = false;
		double quantidadeTotal = 0;

		// Verifica se o ativo a ser comprado consta na lista
		erro = listaAtivos.buscaAtivo(movimentacaoAtual.getAtivo());
		if (!erro) {
			return false;
		}
		
		// Verifica se a quantidade total de ativos é suficiente para movimentacao
		for (Movimentacao movimentacaoAnterior : this.movimentacoes) {
			if (movimentacaoAnterior.getAtivo().getNome().equals(movimentacaoAtual.getAtivo().getNome())) {
				if (movimentacaoAnterior.getTipo()) {
					quantidadeTotal += movimentacaoAnterior.getQuantidade();
				} else {
					quantidadeTotal -= movimentacaoAnterior.getQuantidade();
				}
			}
		}
		if (quantidadeTotal < movimentacaoAtual.getQuantidade()) {
			return false;
		}
		
		// Laçamento do valor na operação no saldo da conta
		erro = conta.vender(movimentacaoAtual.getValor());
		if (!erro) {
			return false;
		}
			
		// Registro da movimentacao
		movimentacaoAtual.setTipo(false);
		this.movimentacoes.add(movimentacaoAtual);
		
		return true;
	}

}

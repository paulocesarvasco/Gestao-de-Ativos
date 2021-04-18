package maps;

import java.util.ArrayList;
import java.util.List;

public class Consulta {

	private List<Registro> registros = new ArrayList<Registro>();
	
	public boolean criarRegistros(List<Movimentacao> movimentacoes, List<Ativo> ativos) {
		
		String nomeAtivo, tipoAtivo;
		double quantidadeTotal, valorMercado, rendimento, lucro;
		
		Registro novoRegistro = null;
		
		// ativosRegistrados tem como objetivo apenas auxiliar a marcação de quais ativos já tiveram
		// suas movimentações registradas
		List<String> ativosRegistrados = new ArrayList<String>();
		
		this.registros.clear();
		
		for (Movimentacao movimentacao : movimentacoes) {
	
			nomeAtivo = movimentacao.getAtivo().getNome();

			if (!ativosRegistrados.contains(nomeAtivo)) {
				quantidadeTotal = contabilizarAtivo(movimentacoes, nomeAtivo);
				valorMercado = contabilizarValor(ativos, nomeAtivo, quantidadeTotal);
				rendimento = contabilizarRendimento(ativos, movimentacoes, nomeAtivo);
				lucro = contabilizarLucro(movimentacoes, nomeAtivo);
				tipoAtivo = movimentacao.getAtivo().getTipo();
			
				novoRegistro = new Registro(nomeAtivo, tipoAtivo, quantidadeTotal, valorMercado, rendimento, lucro);
				this.registros.add(novoRegistro);
				ativosRegistrados.add(nomeAtivo);
			}
		}
		
		// Ordena a lista de registro de acordo com o nome dos ativos
		Collections.sort(registros, new Comparator<Registro>() {
		      public int compare(Registro registro_1, Registro registro_2) {
		          return registro_1.getNome().compareTo(registro_2.getNome());
		      }
		  });

		return true;
	}
	
	// Soma todas as movimentações tipo compra e subtrai todas movimentaçãoes tipo venda
	private double contabilizarAtivo(List<Movimentacao> movimentacoes, String nomeAtivo) {
		
		double quantidadeTotal = 0;
		
		for (Movimentacao movimentacao : movimentacoes) {
			if(movimentacao.getAtivo().getNome().equals(nomeAtivo)) {
				if (movimentacao.getTipo()) {
					quantidadeTotal += movimentacao.getQuantidade();
				} else {
					quantidadeTotal -= movimentacao.getQuantidade();
				}
			}
		}
		return quantidadeTotal;
	}
	
	private double contabilizarValor(List<Ativo> ativos, String nomeAtivo, double quantidade) {
		
		// Valor dummy que indicará se ocorreu falha na operação
		double valorMercado = -1;
		
		for (Ativo ativo : ativos) {
			if(ativo.getNome().equals(nomeAtivo)) {
				valorMercado = ativo.getPreco()*quantidade;
				break;
			}
		}
		return valorMercado;
	}
	
	private double contabilizarRendimento(List<Ativo> ativos, List<Movimentacao> movimentacoes, String nomeAtivo) {
		
		double precoMedio = -1, precoMercado = -1, precoTotal = 0;
		int quantidadeOperacoes = 0;
		
		for (Movimentacao movimentacao : movimentacoes) {
			if(movimentacao.getAtivo().getNome().equals(nomeAtivo)) {
				precoTotal += movimentacao.getValor();
				quantidadeOperacoes++;
			}
		}
		if (precoTotal < 0) {
			return precoTotal;
		}
		
		precoMedio = precoTotal/quantidadeOperacoes;
		
		for (Ativo ativo : ativos) {
			if (ativo.getNome().equals(nomeAtivo)) {
				precoMercado = ativo.getPreco();
				break;
			}
		}
		
		if (precoMercado < 0) {
			return precoMercado;
		}
		
		return precoMercado/precoMedio;
	}
	
	private double contabilizarLucro(List<Movimentacao> movimentacoes, String nomeAtivo) {
		
		double lucro = 0;
		
		for (Movimentacao movimentacao : movimentacoes) {
			if(movimentacao.getAtivo().getNome().equals(nomeAtivo)) {
				if (movimentacao.getTipo()) {
					lucro += movimentacao.getValor();
				} else {
					lucro -= movimentacao.getValor();
				}
			}
		}
		return lucro;
	}
}

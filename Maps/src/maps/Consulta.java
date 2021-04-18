package maps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Consulta {

	// Para cada ativo será gerado um registro que será adicionado a lista de registros
	// ordenada em ordem alfabética
	private List<Registro> registros = new ArrayList<Registro>();
	
	public boolean criarRegistros(List<Movimentacao> movimentacoes, List<Ativo> ativos) {
		
		String nomeAtivo, tipoAtivo;
		double quantidadeTotal, valorMercado, rendimento, lucro;
		
		Registro novoRegistro = null;
		
		// ativosRegistrados tem como objetivo apenas auxiliar a marcação de quais ativos já tiveram
		// suas movimentações registradas
		List<String> ativosRegistrados = new ArrayList<String>();
		
		// Sempre que for requisitado uma lista de registros, a lista atual é apagada
		// e uma nova lista é criada
		this.registros.clear();
		
		for (Movimentacao movimentacao : movimentacoes) {
	
			nomeAtivo = movimentacao.getAtivo().getNome();

			// Se o nome do ativo já estiver presente na lista, passa pro próximo elemento da lista de movimentacoes
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
	
	// Método usado apenas para debugar os valores de cada registro
	public boolean verRegistros() {
		
		int index = 1;
		
		if (this.registros.isEmpty()) {
			return false;
		}

		System.out.printf("Numero de ativos registrados: %d\n", this.registros.size());
		for (Registro registro : this.registros) {
			System.out.printf("	Ativo %d: %s\n", index, registro.getNome());
			System.out.printf("		Quantidade total: %.2f\n", registro.getQuantidade());
			System.out.printf("		Valor de mercado: %.2f\n", registro.getValorMercado());
			System.out.printf("		Rendimento: %.2f\n", registro.getRendimento());
			System.out.printf("		Lucro: %.2f\n", registro.getLucro());
			System.out.println("");
			index++;
		}
		
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
		
		// O preco medio foi contabilizado como a soma do valor de todas a operacoes (compra e venda)
		// dividido pelo numero total de operacoes
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
					lucro -= movimentacao.getValor();
				} else {
					lucro += movimentacao.getValor();
				}
			}
		}
		return lucro;
	}
	
	public List<Registro> getListaMovimentacoes() {
		return this.registros;
	}
}

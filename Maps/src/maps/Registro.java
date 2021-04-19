package maps;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

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
	
	public Registro (List<Movimentacao> movimentacoes, List<Ativo> ativos, String nomeAtivo, String tipoAtivo) {
		this.nome = nomeAtivo;
		this.quantidadeTotal = contabilizarAtivo(movimentacoes, nomeAtivo);
		this.valorMercadoTotal = contabilizarValor(ativos, nomeAtivo, this.quantidadeTotal);
		this.rendimento = contabilizarRendimento(ativos, movimentacoes, nomeAtivo);
		this.lucro = contabilizarLucro(movimentacoes, nomeAtivo);
		this.tipo = tipoAtivo;
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
		return arredondar(valorMercado);
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
		return arredondar(precoMercado/precoMedio);
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
	
	private double arredondar(double valor) {
		
		 BigDecimal valorArredondado = BigDecimal.valueOf(valor);
		 valorArredondado = valorArredondado.setScale(2, RoundingMode.HALF_DOWN);
		 
		 return valorArredondado.doubleValue();
	}
}

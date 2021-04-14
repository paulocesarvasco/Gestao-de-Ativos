package maps;

import java.util.Objects;

public class Ativos {

	private String nome;
	private double preco;
	private String tipo;
	
	// Ativos nao podem ser criados sem uma verificacaoo previa de seu tipo
	private Ativos(String nome, double preco, String tipo) {
		this.nome = nome;
		this.preco = preco;
		this.tipo = tipo;
	}
	
	// Método para criar um novo ativo que verifica se seu tipo é valido
	public Ativos criarAtivo (String nome, double preco, String tipo) {
		
		if (Objects.equals(tipo, "RV") || Objects.equals(tipo, "RF") || Objects.equals(tipo, "FUNDO")) {
			Ativos ativo = new Ativos(nome, preco, tipo);
			return ativo;
		} else {
			return null;
		}
	}
}

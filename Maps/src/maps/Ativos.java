package maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Ativos contém uma lista de ativos que podem ser manipulados seguindo a métodologia CRUD
public class Ativos {

	private List<Ativo> ativos = new ArrayList<Ativo>();
	
	
	// Método para criar um novo ativo que verifica se seu tipo é valido
	public Ativo criarAtivo (String nome, double preco, double quantidade, String tipo) {
		if (Objects.equals(tipo, "RV") || Objects.equals(tipo, "RF") || Objects.equals(tipo, "FUNDO")) {
			Ativo ativo = new Ativo(nome, preco, tipo);
			return ativo;
		}
		return null;
	}
	
	public void addAtivo(Ativo ativo) {
		this.ativos.add(ativo);
	}
	
	public Ativo buscaAtivo(String nome) {
		
		for (Ativo ativo : this.ativos) {
			if (Objects.equals(ativo.getNome(), nome)) {
				return ativo;
			}
		}
		return null;
	}
	
	public boolean buscaAtivo(Ativo nomeAtivo) {
		return ativos.contains(nomeAtivo);
	}
	
	public boolean removeAtivo(String nome) {
		
		Ativo ativo = buscaAtivo(nome);
		
		if (ativo == null) {
			return false;
		}

		this.ativos.remove(ativo);
		return true;
	}
	
	public boolean atualizaAtivo(String ativoBuscado, String novoNome, double novoPreco) {
		
		Ativo ativo = buscaAtivo(ativoBuscado);
		
		if (ativo == null) {
			return false;
		}
		
		ativo.setNome(novoNome);
		ativo.setPreco(novoPreco);
		return true;
	}
	
}

package maps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Consulta {

	// Para cada ativo será gerado um registro que será adicionado a lista de registros
	// ordenada em ordem alfabética
	private static List<Registro> registros = new ArrayList<Registro>();
	
	public static boolean criarRegistros(List<Movimentacao> movimentacoes, List<Ativo> ativos) {
		
		String nomeAtivo;
		Registro novoRegistro = null;
		
		// ativosRegistrados tem como objetivo apenas auxiliar a marcação de quais ativos já tiveram
		// suas movimentações registradas
		List<String> ativosRegistrados = new ArrayList<String>();
		
		// Sempre que for requisitado uma lista de registros, a lista atual é apagada
		// e uma nova lista é criada
		registros.clear();
		
		for (Movimentacao movimentacao : movimentacoes) {
			nomeAtivo = movimentacao.getAtivo().getNome();
			// Se o nome do ativo já estiver presente na lista, passa pro próximo elemento da lista de movimentacoes
			if (!ativosRegistrados.contains(nomeAtivo)) {
				novoRegistro = new Registro(movimentacoes, ativos, nomeAtivo, movimentacao.getAtivo().getTipo());
				registros.add(novoRegistro);
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
	public static boolean verRegistros() {
		
		int index = 1;
		
		if (registros.isEmpty()) {
			return false;
		}

		System.out.printf("Numero de ativos registrados: %d\n", registros.size());
		for (Registro registro : registros) {
			System.out.printf("	Ativo %d: %s\n", index, registro.getNome());
			System.out.printf("		Quantidade total: %f\n", registro.getQuantidade());
			System.out.printf("		Valor de mercado: %f\n", registro.getValorMercado());
			System.out.printf("		Rendimento: %f\n", registro.getRendimento());
			System.out.printf("		Lucro: %f\n", registro.getLucro());
			System.out.println("");
			index++;
		}
		
		return true;
	}
}

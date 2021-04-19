package maps;

import java.util.List;

// Na classe main são realizados os testes das operações implementadas na API
public class Main {

	public static void main(final String[] args) {
		
		// Flag que indicará se uma operação foi realizada sem erros
		boolean sucesso;

		Conta conta = new Conta(10000);
		Ativos ativos = new Ativos();
		Operacoes operacoes = new Operacoes();
		Consulta consulta = new Consulta();
		Ativo ativo = null;
		Movimentacao movimentacao = null;
		
		System.out.printf("Teste: Criar ativo - ");
		ativo = ativos.criarAtivo("Petrobras", 22.95, "RV");
		if (ativo != null) {
			System.out.println("Sucesso");
		} else {
			System.out.println("Falha");
		}
		
		System.out.printf("Teste: Adicionar ativo a lista - ");
		sucesso = ativos.addAtivo(ativo);
		if (sucesso) {
			System.out.println("Sucesso");
		} else {
			System.out.println("Falha");
		}
		
		System.out.printf("Teste: Criar ativo com tipo inválido - ");
		ativo = ativos.criarAtivo("Petrobras", 22.95, "RR");
		if (ativo != null) {
			System.out.println("Sucesso");
		} else {
			System.out.println("Falha");
		}
		
		System.out.printf("Teste: Atualizar preco ativo - ");
		sucesso = ativos.atualizaAtivo("Petrobras", "Petrobras", 26.542);
		if (sucesso) {
			System.out.println("Sucesso");
		} else {
			System.out.println("Falha");
		}
		
		System.out.printf("Teste: Remover ativo lista - ");
		ativo = ativos.criarAtivo("Vale", 108.67, "RV");
		if (ativo == null) {
			System.out.printf("Falha - ");
		}
		sucesso = ativos.addAtivo(ativo);
		if (!sucesso) {
			System.out.println("Falha - ");
		}
		sucesso = ativos.removeAtivo(ativo.getNome());
		if (sucesso) {
			System.out.println("Sucesso");
		} else {
			System.out.println("Falha");
		}
		
		System.out.printf("Teste: Remover ativo inexistente - ");
		sucesso = ativos.removeAtivo("Vale");
		if (sucesso) {
			System.out.println("Sucesso");
		} else {
			System.out.println("Falha");
		}
		
		System.out.printf("Teste: Compra ativo - ");
		ativo = ativos.buscaAtivo("Petrobras");
		if (ativo == null) {
			System.out.printf("Falha - ");
		}
		movimentacao = new Movimentacao(ativo, 5.96, 300);
		sucesso = operacoes.Compra(conta, ativos, movimentacao);
		if (!sucesso) {
			System.out.println("Falha");
		}
		if (conta.getSaldo() == 9700) {
			System.out.println("Sucesso");
		} else {
			System.out.println("Falha");
		}
		
		System.out.printf("Teste: Compra ativo sem saldo suficiente - ");
		ativo = ativos.buscaAtivo("Petrobras");
		if (ativo == null) {
			System.out.printf("Falha - ");
		}
		movimentacao = new Movimentacao(ativo, 15.74, 15000);
		sucesso = operacoes.Compra(conta, ativos, movimentacao);
		if (!sucesso) {
			System.out.println("Falha");
		}
		
		System.out.printf("Teste: Venda ativo - ");
		ativo = ativos.buscaAtivo("Petrobras");
		if (ativo == null) {
			System.out.printf("Falha - ");
		}
		movimentacao = new Movimentacao(ativo, 2.56, 550);
		sucesso = operacoes.Venda(conta, ativos, movimentacao);
		if (!sucesso) {
			System.out.println("Falha");
		}
		if (conta.getSaldo() == 10250) {
			System.out.println("Sucesso");
		} else {
			System.out.println("Falha");
		}
		
		System.out.printf("Teste: Venda ativo quantidade insuficiente - ");
		ativo = ativos.buscaAtivo("Petrobras");
		if (ativo == null) {
			System.out.printf("Falha - ");
		}
		movimentacao = new Movimentacao(ativo, 5, 5000);
		sucesso = operacoes.Venda(conta, ativos, movimentacao);
		if (!sucesso) {
			System.out.println("Falha");
		}
		
		System.out.println("Teste dos campos de um registro: ");
		consulta.criarRegistros(operacoes.getMovimentacoes(), ativos.getAtivos());
		List<Registro> registros = consulta.getRegistros();
		Registro registro = registros.get(0);
		System.out.printf("	Nome ativo: ");
		if (registro.getNome() == "Petrobras") {
			System.out.println("Sucesso");
		} else {
			System.out.println("Falha");
		}
		System.out.printf("	Tipo ativo: ");
		if (registro.getTipo() == "RV") {
			System.out.println("Sucesso");
		} else {
			System.out.println("Falha");
		}
		System.out.printf("	Quantidade total: ");
		if (registro.getQuantidade() == 3.4) {
			System.out.println("Sucesso");
		} else {
			System.out.println("Falha");
		}
		System.out.printf("	Valor de mercado: ");
		if (registro.getValorMercado() == 90.24) {
			System.out.println("Sucesso");
		} else {
			System.out.println("Falha");
		}
		System.out.printf("	Remdimento: ");
		if (registro.getRendimento() == 0.06) {
			System.out.println("Sucesso");
		} else {
			System.out.println("Falha");
		}
		System.out.printf("	Lucro: ");
		if (registro.getLucro() == 250.0) {
			System.out.println("Sucesso");
		} else {
			System.out.println("Falha");
		}
	}

}

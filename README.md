# Maps

## Resumo
Software desenvolvido para o processo seletivo da Maps Soluções Financeiras.
Para cada uma das funcionalidades descrita no problema foi criada uma classe com os métodos necessários para a implementação da solução. Abaixo está descrita cada uma delas:
    - **Conta**: classe que contém o saldo e uma lista com os registros de movimentação, contendo a natureza (compra e venda) e seu valor correspondente.
    - **Ativos**: classe que contém uma lista com todos os ativos registrados, ativos podem ser adionados, removidos, atualizados e procurados na lista. Para adicionar um novo ativo primeiro deve ser criado um objeto do tipo *Ativo*.
    - **Operacoes**: contém métodos para compra e venda de ativos, o ativo a ser comprado ou vendido deve estar contido na lista de ativos. O valor da transação é lançado no saldo da conta que deve ser passado como parâmetro das mesmas.
    - **Consulta**: classe contendo métodos para a geração e obtenção da lista contendo todas as informações referentes a um ativo movimentado, para realizar uma consulta primeiro deve-se gera-la para que os novos lançamentos entrem nas estatísticas do ativo. Caso um ativo esteja registrado e esse não foi comprado ou vendido ele não constará nessa lista. Sempre que um relatório de registros for gerado o anterior será sobreescrito.

Além das classes listadas acima, foram criadas "classes bases" para servirem de estruturas básicas que compõem um determinado objeto, são elas:
    - **Ativo**: contendo nome, preço e tipo.
    - **Movimentacao**: contendo ativo a ser movimentado, quantidade, valor e um indicado de compra ou venda.
    - **Registro**: bloco básico que será salvo na lista de movimentações, nessa classe também constam os métodos que calculam quantidade total, rendimento, lucro, etc, de cada um dos ativos movimentados.

Foi escolhido a lista como estrutura de dados para guardar os dados devido a facilidade que o java oferece para manipula-las. Os valores finais disponíveis nas consultas estão arrendondados para baixo limitandos em duas casas decimais.

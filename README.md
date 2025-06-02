                                                                
Descrição Geral
Este projeto implementa um sistema backend para processar eventos de pedidos de clientes enviados via RabbitMQ e armazená-los no MongoDB. Os pedidos podem ser consultados por meio de uma API REST com suporte a paginação.
Objetivo Principal
Processar pedidos de clientes recebidos por mensageria e disponibilizá-los de forma estruturada via API.
Entrada dos Dados
Os eventos chegam pela fila `payflow-order-created` (RabbitMQ), no seguinte formato JSON:
{
"codigoPedido": 1001,
"codigoCliente": 1,
"itens": [
{ "produto": "lápis", "quantidade": 100, "preco": 1.10 },
{ "produto": "caderno", "quantidade": 10, "preco": 1.00 }
]
}
Regras de Negócio Implementadas
Ao consumir um evento:
- Cria um novo pedido (OrderEntity);
- Converte os itens do pedido (OrderItem);
- Calcula o valor total (quantidade × preço);
- Persiste os dados no MongoDB;
- Disponibiliza o histórico de pedidos por cliente via API REST com paginação.
Tecnologias Utilizadas
- Spring Boot
- RabbitMQ
- MongoDB Compass
- Jackson / Spring Data
- Lombok
- Java 17+
Modo de Uso
1. Faça o download do projeto e extraia em sua IDE (Eclipse, IntelliJ ou VSCode).
2. Certifique-se de ter o Docker instalado e funcionando.
3. No terminal, na raiz do projeto, execute: docker-compose up
4. Abra o MongoDB Compass para visualizar os dados.
5. Com o Docker rodando, execute o projeto Spring Boot.
6. No navegador, acesse o RabbitMQ em: http://localhost:15672 (Login: guest / Senha: guest)
7. Vá até a aba "Queues and Stream", selecione payflow-order-created, e clique em "Publish Message".
8. Cole o JSON de exemplo para simular um pedido.
Observações Importantes
- O projeto funciona em IntelliJ, VS Code e Eclipse, porém:
- O Eclipse pode exibir falsos positivos em algumas inferências genéricas (ApiResponse<>), sem afetar a execução;
- Verifique se todas as classes envolvidas na serialização (como OrderItem) possuem construtores válidos. Construtores incompletos podem causar dados incompletos no MongoDB, mesmo sem erros visíveis no console.

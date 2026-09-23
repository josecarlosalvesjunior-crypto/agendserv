# AgendServ

**SIA — Sistema Inteligente de Agendamento**: sistema de agendamento de serviços feito em **Java**, executado pelo **console** e conectado a um banco de dados **PostgreSQL**. Permite gerenciar clientes, consultar prestadores e serviços e gerar um relatório completo com os agendamentos e pagamentos.

O objetivo do sistema é organizar a agenda de serviços em um só lugar, evitando problemas como conflito de horários, agendamentos duplicados e perda de informações.

Projeto desenvolvido para praticar **modelagem de banco de dados**, **SQL** e a integração do Java com o banco usando **JDBC**.

---

## Funcionalidades

| Opção | Descrição |
|-------|-----------|
| 1 | **Inserir cliente**: cadastra um cliente com nome, CPF, e-mail e telefone |
| 2 | **Listar clientes**: mostra código, nome, CPF e e-mail de todos os clientes |
| 3 | **Atualizar cliente**: busca pelo código e altera nome, e-mail e telefone |
| 4 | **Excluir cliente**: remove o cliente pelo código |
| 5 | **Listar prestadores**: mostra código, nome, especialidade e status dos prestadores |
| 6 | **Listar serviços**: mostra código, nome e valor dos serviços |
| 7 | **Relatório completo (JOIN)**: exibe cada agendamento com cliente, prestador, serviço, data, hora, status e pagamento |
| 0 | **Sair**: encerra o programa |

### Regras do banco de dados

```
CPF do cliente        →  11 caracteres e único
E-mail do cliente     →  único
Status do prestador   →  Ativo | Inativo
Status do agendamento →  Pendente | Confirmado | Concluido | Cancelado
Forma de pagamento    →  PIX | Credito | Debito | Dinheiro
Status do pagamento   →  Pendente | Pago | Cancelado
```

- Um agendamento sempre pertence a **um cliente** e a **um serviço**.
- Um serviço sempre pertence a **um prestador**.
- Um pagamento sempre pertence a **um agendamento**.
- Se alguma regra do banco for violada (CPF repetido, por exemplo), o programa mostra a mensagem de erro e continua funcionando.

---

## Modelagem do banco

O banco `agendamento_servicos` tem 6 tabelas:

| Tabela | Descrição |
|--------|-----------|
| `Cliente` | Dados de quem agenda o serviço |
| `Prestador` | Profissional que atende, com especialidade e status |
| `Servico` | Serviço oferecido por um prestador, com duração e valor |
| `Disponibilidade` | Dias e horários em que o prestador atende |
| `Agendamento` | Reserva de um serviço por um cliente, com data, hora e status |
| `Pagamento` | Pagamento de um agendamento, com valor, forma e status |

### Relacionamentos

```
Cliente 1 ──── N Agendamento      (um cliente faz vários agendamentos)
Agendamento 1 ──── N Pagamento    (um agendamento gera pagamentos)
Servico 1 ──── N Agendamento      (um serviço pode ser agendado várias vezes)
Prestador 1 ──── N Servico        (um prestador oferece vários serviços)
Prestador 1 ──── N Disponibilidade (um prestador tem várias disponibilidades)
```

Os diagramas **conceitual** (`Conceitual_1.png`) e **lógico** (`Lógico_1.png`) estão na raiz do repositório, junto com os PDFs que explicam o sistema e as entidades.

### Views, procedures e functions

Criadas no arquivo `procedure_view_function.sql`:

| Objeto | Tipo | O que faz |
|--------|------|-----------|
| `vw_relatorio_agendamentos` | View | Relatório completo de clientes, prestadores, serviços, agendamentos e pagamentos |
| `vw_pagamentos` | View | Detalhes dos pagamentos com o nome do cliente |
| `confirmar_agendamento(cod)` | Procedure | Altera o status do agendamento para **Confirmado** |
| `cancelar_agendamento(cod)` | Procedure | Altera o status do agendamento para **Cancelado** |
| `fn_nome_cliente(cod)` | Function | Retorna o nome do cliente |
| `fn_total_pago_cliente(cod)` | Function | Retorna o total já pago pelo cliente |

---

## Estrutura do projeto

```
agendserv/
├── sistema_agendamento/
│   ├── src/
│   │   ├── conexao/     conexao.java
│   │   ├── dao/         clientedao, prestadordao, servicodao,
│   │   │                agendamentodao, disponibilidadedao,
│   │   │                pagamentodao, relatoriodao
│   │   ├── entidades/   cliente, prestador, servico,
│   │   │                disponiblidade, agendamento, pagamento
│   │   ├── main/        main.java
│   │   └── module-info.java
│   └── bin/             classes compiladas
├── script_DDL&DML.sql
├── procedure_view_function.sql
├── Conceitual_1.png
├── Lógico_1.png
├── Descrição do sistema.pdf
├── Explicação entidades e relacionamentos.pdf
└── README.md
```

| Pacote | Responsabilidade |
|--------|------------------|
| `entidades` | Classes que representam as tabelas do banco (`cliente`, `prestador`, `servico`, `disponiblidade`, `agendamento`, `pagamento`), com atributos e `getters`/`setters` |
| `dao` | Uma classe DAO por tabela, com os comandos SQL (inserir, listar, atualizar e excluir). Também tem o `relatoriodao`, que faz o relatório completo com `JOIN` |
| `conexao` | Classe `conexao`, que abre a conexão com o PostgreSQL via JDBC |
| `main` | Ponto de entrada do programa. Exibe o menu em loop e chama os DAOs conforme a opção escolhida |

---

## Tecnologias e conceitos

- **Java**
- **PostgreSQL**
- **JDBC** para conectar o Java ao banco
- Padrão **DAO** (separação entre a lógica do programa e o acesso ao banco)
- `PreparedStatement` para executar os comandos SQL com parâmetros
- `try-with-resources` para fechar conexões automaticamente
- Modularização com `module-info.java`
- `Scanner` para leitura de dados no console
- Modelagem conceitual e lógica de banco de dados
- SQL: `CREATE TABLE`, `INSERT`, `UPDATE`, `DELETE`, `SELECT`, `INNER JOIN`, `LEFT JOIN`, `VIEW`, `PROCEDURE` e `FUNCTION` (PL/pgSQL)
- Estruturas de controle: `do-while`, `switch` e `if/else`

---

## Como executar

Precisa ter o **JDK**, o **PostgreSQL** e o **driver JDBC do PostgreSQL** (arquivo `.jar`) instalados.

1. Crie o banco de dados:
   ```sql
   CREATE DATABASE agendamento_servicos;
   ```
2. No banco criado, execute o arquivo `script_DDL&DML.sql` (cria as tabelas e insere dados de exemplo).
3. Em seguida, execute o arquivo `procedure_view_function.sql` (cria as views, procedures e functions).
4. Abra o arquivo `sistema_agendamento/src/conexao/conexao.java` e ajuste a **URL**, o **usuário** e a **senha** para os do seu PostgreSQL.
5. Adicione o driver JDBC do PostgreSQL ao projeto (na IDE, em *Build Path* / *Bibliotecas*) e execute a classe `main`.

---

## Exemplo de uso

```
===== SISTEMA DE AGENDAMENTO =====
1 - Inserir Cliente
2 - Listar Clientes
3 - Atualizar Cliente
4 - Excluir Cliente
5 - Listar Prestadores
6 - Listar Serviços
7 - Relatório Completo (JOIN)
0 - Sair
Opção: 1
Nome: Ana Lima
CPF: 11122233344
Email: ana@email.com
Telefone: 71911112222
Cliente cadastrado!

Opção: 2
1 | João Silva | 12345678901 | joao@email.com
2 | José Carlos | 12345490901 | josecarlos@email.com
3 | Ana Lima | 11122233344 | ana@email.com

Opção: 5
1 | Maria Santos | Cabeleireira | Ativo
2 | Carla Souza | Cabeleireira | Ativo

Opção: 0
Sistema encerrado.
```

---

Feito por José Carlos 🚀

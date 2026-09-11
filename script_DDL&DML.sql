CREATE TABLE Cliente (
    cod_cliente SERIAL,
    nome_cliente VARCHAR(100) NOT NULL,
    cpf_cliente CHAR(11) NOT NULL UNIQUE,
    email_cliente VARCHAR(100) NOT NULL UNIQUE,
    telefone_cliente VARCHAR(15),
    
    CONSTRAINT cod_cliente
        PRIMARY KEY (cod_cliente)
);

CREATE TABLE Prestador (
    cod_prestador SERIAL PRIMARY KEY,
    nome_prestador VARCHAR(100) NOT NULL,
    especialidade VARCHAR(100),
    email_prestador VARCHAR(100) UNIQUE,
    telefone_prestador VARCHAR(15),
    status_fornecedor VARCHAR(10),
	CHECK (status_fornecedor IN ('Ativo', 'Inativo'))
);

CREATE TABLE Servico (
    cod_servico SERIAL PRIMARY KEY,
    nome_servico VARCHAR(100) NOT NULL,
    descricao_servico VARCHAR(255),
    duracao_servico INT NOT NULL,
    valor_servico DECIMAL(10,2) NOT NULL,
    cod_prestador INT NOT NULL,

    CONSTRAINT fk_servico_prestador
        FOREIGN KEY (cod_prestador)
        REFERENCES Prestador(cod_prestador)
);

CREATE TABLE Disponibilidade (
    cod_disponibilidade SERIAL PRIMARY KEY,
    dia_semana VARCHAR(20) NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fim TIME NOT NULL,
    cod_prestador INT NOT NULL,

    CONSTRAINT fk_disponibilidade_prestador
        FOREIGN KEY (cod_prestador)
        REFERENCES Prestador(cod_prestador)
);

CREATE TABLE Agendamento (
    cod_agendamento SERIAL PRIMARY KEY,
	data_agendamento DATE NOT NULL,
    hora_agendamento TIME NOT NULL,
	status_agendamento VARCHAR(20)
	CHECK (status_agendamento IN (
        'Pendente',
        'Confirmado',
        'Concluido',
        'Cancelado')
	),
    descricao_agendamento VARCHAR(200),
    cod_cliente INT NOT NULL,
    cod_servico INT NOT NULL,

    CONSTRAINT fk_agendamento_cliente
        FOREIGN KEY (cod_cliente)
        REFERENCES Cliente(cod_cliente),

    CONSTRAINT fk_agendamento_servico
        FOREIGN KEY (cod_servico)
        REFERENCES Servico(cod_servico)
);

CREATE TABLE Pagamento (
    cod_pagamento SERIAL PRIMARY KEY,
    valor_pagamento DECIMAL(10,2) NOT NULL,
    data_pagamento DATE,
    forma_pagamento VARCHAR(20),
	CHECK (forma_pagamento IN (
        'PIX',
        'Credito',
        'Debito',
        'Dinheiro')
	),
	status_pagamento VARCHAR(20),
    CHECK (status_pagamento IN (
        'Pendente',
        'Pago',
        'Cancelado')
	),
    cod_agendamento INT NOT NULL,

    CONSTRAINT fk_pagamento_agendamento
        FOREIGN KEY (cod_agendamento)
        REFERENCES Agendamento(cod_agendamento)
);


INSERT INTO Cliente (
    nome_cliente,
    cpf_cliente,
    email_cliente,
    telefone_cliente
)
VALUES (
    'João Silva',
    '12345678901',
    'joao@email.com',
    '71999999999'
);

INSERT INTO Cliente (
    nome_cliente,
    cpf_cliente,
    email_cliente,
    telefone_cliente
)
VALUES (
    'José Carlos',
    '12345490901',
    'josecarlos@email.com',
    '71888888888'
);

INSERT INTO Prestador (
    nome_prestador,
    especialidade,
    email_prestador,
    telefone_prestador,
    status_fornecedor
)
VALUES (
    'Maria Santos',
    'Cabeleireira',
    'mariasantos@email.com',
    '71888887777',
    'Ativo'
);

INSERT INTO Prestador (
    nome_prestador,
    especialidade,
    email_prestador,
    telefone_prestador,
    status_fornecedor
)
VALUES (
    'Carla Souza',
    'Cabeleireira',
    'carlasouza@email.com',
    '71666688888',
    'Ativo'
);

INSERT INTO Servico (
    nome_servico,
    descricao_servico,
    duracao_servico,
    valor_servico,
    cod_prestador
)
VALUES (
    'Corte Feminino',
    'Corte de cabelo feminino',
    60,
    50.00,
    1
);

INSERT INTO Servico (
    nome_servico,
    descricao_servico,
    duracao_servico,
    valor_servico,
    cod_prestador
)
VALUES (
    'Hidratação capilar',
    'Hidratação capilar feminino',
    120,
    90.00,
    2
);

INSERT INTO Disponibilidade (
    dia_semana,
    hora_inicio,
    hora_fim,
    cod_prestador
)
VALUES (
    'Segunda-feira',
    '08:00:00',
    '18:00:00',
    1
);

INSERT INTO Disponibilidade (
    dia_semana,
    hora_inicio,
    hora_fim,
    cod_prestador
)
VALUES (
    'Quarta-feira',
    '08:00:00',
    '18:00:00',
    2
);

INSERT INTO Agendamento (
    data_agendamento,
    hora_agendamento,
    status_agendamento,
    descricao_agendamento,
    cod_cliente,
    cod_servico
)
VALUES (
    '2026-05-30',
    '14:00:00',
    'Pendente',
    'Primeiro atendimento',
    1,
    1
);

INSERT INTO Agendamento (
    data_agendamento,
    hora_agendamento,
    status_agendamento,
    descricao_agendamento,
    cod_cliente,
    cod_servico
)
VALUES (
    '2026-05-31',
    '10:00:00',
    'Pendente',
    'Primeiro atendimento',
    2,
    2
);

INSERT INTO Pagamento (
    valor_pagamento,
    data_pagamento,
    forma_pagamento,
    status_pagamento,
    cod_agendamento
)
VALUES (
    50.00,
    '2026-05-30',
    'PIX',
    'Pago',
    1
);

INSERT INTO Pagamento (
    valor_pagamento,
    data_pagamento,
    forma_pagamento,
    status_pagamento,
    cod_agendamento
)
VALUES (
    90.00,
    '2026-05-31',
    'Dinheiro',
    'Pago',
    1
);

UPDATE Servico
SET valor_servico = 60.00
WHERE cod_servico = 1;

UPDATE Cliente
SET telefone_cliente = '71977777777'
WHERE cod_cliente = 1;

DELETE FROM Pagamento
WHERE cod_pagamento = 1;

SELECT * FROM Prestador
WHERE status_fornecedor = 'Ativo';

SELECT * FROM Servico
WHERE valor_servico > 50;

SELECT * FROM Agendamento
WHERE status_agendamento = 'Confirmado';

SELECT
    pg.cod_pagamento,
    c.nome_cliente,
    pg.valor_pagamento,
    pg.forma_pagamento,
    pg.status_pagamento
FROM Pagamento pg
INNER JOIN Agendamento a
    ON pg.cod_agendamento = a.cod_agendamento
INNER JOIN Cliente c
    ON a.cod_cliente = c.cod_cliente;

SELECT
    c.nome_cliente,
    p.nome_prestador,
    s.nome_servico,
    a.data_agendamento,
    a.hora_agendamento,
    a.status_agendamento,
    pg.valor_pagamento,
    pg.forma_pagamento,
    pg.status_pagamento
FROM Cliente c
INNER JOIN Agendamento a
    ON c.cod_cliente = a.cod_cliente
INNER JOIN Servico s
    ON a.cod_servico = s.cod_servico
INNER JOIN Prestador p
    ON s.cod_prestador = p.cod_prestador
INNER JOIN Pagamento pg
    ON a.cod_agendamento = pg.cod_agendamento;
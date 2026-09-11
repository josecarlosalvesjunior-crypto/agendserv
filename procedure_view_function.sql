-- a view mostra um relatorio completo de todas tabelas

CREATE VIEW vw_relatorio_agendamentos AS
SELECT
    c.cod_cliente,
    c.nome_cliente,
    p.cod_prestador,
    p.nome_prestador,
    s.cod_servico,
    s.nome_servico,
    a.cod_agendamento,
    a.data_agendamento,
    a.hora_agendamento,
    a.status_agendamento,
    pg.cod_pagamento,
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


SELECT * FROM vw_relatorio_agendamentos;

-- essa view é somente do pagamento, exibindo os detalhes do mesmo

CREATE VIEW vw_pagamentos AS
SELECT
    pg.cod_pagamento,
    c.nome_cliente,
    pg.valor_pagamento,
    pg.forma_pagamento,
    pg.status_pagamento,
    pg.data_pagamento
FROM Pagamento pg
INNER JOIN Agendamento a
    ON pg.cod_agendamento = a.cod_agendamento
INNER JOIN Cliente c
    ON a.cod_cliente = c.cod_cliente;


SELECT * FROM vw_pagamentos;

-- procedure pra alterar o status do agendamento pra confirmado
CREATE OR REPLACE PROCEDURE confirmar_agendamento(p_cod_agendamento INT)
LANGUAGE plpgsql
AS $BODY$
BEGIN
    UPDATE Agendamento
    SET status_agendamento = 'Confirmado'
    WHERE cod_agendamento = p_cod_agendamento;
END;
$BODY$;

CALL confirmar_agendamento(1);

-- procedure pra cancelar o agendamento

CREATE OR REPLACE PROCEDURE cancelar_agendamento(p_cod_agendamento INT)
LANGUAGE plpgsql
AS $BODY$
BEGIN
    UPDATE Agendamento
    SET status_agendamento = 'Cancelado'
    WHERE cod_agendamento = p_cod_agendamento;
END;
$BODY$;

CALL cancelar_agendamento(1);

-- function que retorna o nome do cliente

CREATE FUNCTION fn_nome_cliente(p_cod_cliente INT)
RETURNS VARCHAR(100)
LANGUAGE plpgsql
AS $BODY$
DECLARE
    v_nome VARCHAR(100);
BEGIN
    SELECT nome_cliente
    INTO v_nome
    FROM Cliente
    WHERE cod_cliente = p_cod_cliente;
    RETURN v_nome;
END;
$BODY$;

SELECT fn_nome_cliente(1);

-- function que retorna o valor total pago do cliente

CREATE FUNCTION fn_total_pago_cliente(p_cod_cliente INT)
RETURNS DECIMAL(10,2)
LANGUAGE plpgsql
AS $BODY$
DECLARE
    v_total DECIMAL(10,2);
BEGIN
    SELECT COALESCE(SUM(pg.valor_pagamento), 0)
    INTO v_total
    FROM Pagamento pg
    INNER JOIN Agendamento a
        ON pg.cod_agendamento = a.cod_agendamento
    WHERE a.cod_cliente = p_cod_cliente
      AND pg.status_pagamento = 'Pago';
    RETURN v_total;
END;
$BODY$;

SELECT fn_total_pago_cliente(1);
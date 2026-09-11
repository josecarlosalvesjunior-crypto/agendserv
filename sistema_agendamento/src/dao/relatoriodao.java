package dao;

import conexao.conexao;

import java.sql.*;

public class relatoriodao {

    public void relatorioCompleto() {

        String sql = """
            SELECT
                a.cod_agendamento,
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
            LEFT JOIN Pagamento pg
                ON a.cod_agendamento = pg.cod_agendamento
            ORDER BY a.cod_agendamento
            """;

        try (
            Connection conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {

            while(rs.next()) {

                System.out.println("\n========================");

                System.out.println(
                    "Agendamento: "
                    + rs.getInt("cod_agendamento"));

                System.out.println(
                    "Cliente: "
                    + rs.getString("nome_cliente"));

                System.out.println(
                    "Prestador: "
                    + rs.getString("nome_prestador"));

                System.out.println(
                    "Serviço: "
                    + rs.getString("nome_servico"));

                System.out.println(
                    "Data: "
                    + rs.getDate("data_agendamento"));

                System.out.println(
                    "Hora: "
                    + rs.getTime("hora_agendamento"));

                System.out.println(
                    "Status: "
                    + rs.getString("status_agendamento"));

                System.out.println(
                    "Pagamento: R$ "
                    + rs.getDouble("valor_pagamento"));

                System.out.println(
                    "Forma: "
                    + rs.getString("forma_pagamento"));

                System.out.println(
                    "Status Pagamento: "
                    + rs.getString("status_pagamento"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
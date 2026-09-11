package dao;

import conexao.conexao;
import entidades.pagamento;

import java.sql.*;

public class pagamentodao {

    public void inserir(pagamento pagamento) {

        String sql = """
            INSERT INTO Pagamento
            (
                valor_pagamento,
                data_pagamento,
                forma_pagamento,
                status_pagamento,
                cod_agendamento
            )
            VALUES (?, ?, ?, ?, ?)
            """;

        try (
            Connection conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setDouble(1, pagamento.getValorPagamento());
            stmt.setDate(2, pagamento.getDataPagamento());
            stmt.setString(3, pagamento.getFormaPagamento());
            stmt.setString(4, pagamento.getStatusPagamento());
            stmt.setInt(5, pagamento.getCodAgendamento());

            stmt.executeUpdate();

            System.out.println("Pagamento registrado!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listar() {

        String sql = "SELECT * FROM Pagamento";

        try (
            Connection conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {

            while(rs.next()) {

                System.out.println(
                    rs.getInt("cod_pagamento")
                    + " | R$ "
                    + rs.getDouble("valor_pagamento")
                    + " | "
                    + rs.getString("status_pagamento")
                );
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void atualizar(pagamento pagamento) {

        String sql = """
            UPDATE Pagamento
            SET status_pagamento = ?,
                forma_pagamento = ?
            WHERE cod_pagamento = ?
            """;

        try (
            Connection conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, pagamento.getStatusPagamento());
            stmt.setString(2, pagamento.getFormaPagamento());
            stmt.setInt(3, pagamento.getCodPagamento());

            stmt.executeUpdate();

            System.out.println("Pagamento atualizado!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void excluir(int codigo) {

        String sql =
            "DELETE FROM Pagamento WHERE cod_pagamento = ?";

        try (
            Connection conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, codigo);

            stmt.executeUpdate();

            System.out.println("Pagamento removido!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
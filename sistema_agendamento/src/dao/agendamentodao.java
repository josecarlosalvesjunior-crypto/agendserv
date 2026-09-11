package dao;

import conexao.conexao;
import entidades.agendamento;

import java.sql.*;

public class agendamentodao {

    public void inserir(agendamento agendamento) {

        String sql = """
            INSERT INTO Agendamento
            (
                data_agendamento,
                hora_agendamento,
                status_agendamento,
                descricao_agendamento,
                cod_cliente,
                cod_servico
            )
            VALUES (?, ?, ?, ?, ?, ?)
            """;

        try (
            Connection conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setDate(1, agendamento.getDataAgendamento());
            stmt.setTime(2, agendamento.getHoraAgendamento());
            stmt.setString(3, agendamento.getStatusAgendamento());
            stmt.setString(4, agendamento.getDescricaoAgendamento());
            stmt.setInt(5, agendamento.getCodCliente());
            stmt.setInt(6, agendamento.getCodServico());

            stmt.executeUpdate();

            System.out.println("Agendamento cadastrado!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listar() {

        String sql = "SELECT * FROM Agendamento";

        try (
            Connection conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {

            while(rs.next()) {

                System.out.println(
                    rs.getInt("cod_agendamento")
                    + " | "
                    + rs.getDate("data_agendamento")
                    + " | "
                    + rs.getTime("hora_agendamento")
                    + " | "
                    + rs.getString("status_agendamento")
                );
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void atualizar(agendamento agendamento) {

        String sql = """
            UPDATE Agendamento
            SET status_agendamento = ?,
                descricao_agendamento = ?
            WHERE cod_agendamento = ?
            """;

        try (
            Connection conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, agendamento.getStatusAgendamento());
            stmt.setString(2, agendamento.getDescricaoAgendamento());
            stmt.setInt(3, agendamento.getCodAgendamento());

            stmt.executeUpdate();

            System.out.println("Agendamento atualizado!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void excluir(int codigo) {

        String sql =
            "DELETE FROM Agendamento WHERE cod_agendamento = ?";

        try (
            Connection conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, codigo);

            stmt.executeUpdate();

            System.out.println("Agendamento removido!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
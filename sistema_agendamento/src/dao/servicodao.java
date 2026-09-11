package dao;

import conexao.conexao;
import entidades.servico;

import java.sql.*;

public class servicodao {

    public void inserir(servico servico) {

        String sql = """
            INSERT INTO Servico
            (
                nome_servico,
                descricao_servico,
                duracao_servico,
                valor_servico,
                cod_prestador
            )
            VALUES (?, ?, ?, ?, ?)
            """;

        try (
            Connection conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, servico.getNomeServico());
            stmt.setString(2, servico.getDescricaoServico());
            stmt.setInt(3, servico.getDuracaoServico());
            stmt.setDouble(4, servico.getValorServico());
            stmt.setInt(5, servico.getCodPrestador());

            stmt.executeUpdate();

            System.out.println("Serviço cadastrado!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listar() {

        String sql = "SELECT * FROM Servico";

        try (
            Connection conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {

            while (rs.next()) {

                System.out.println(
                        rs.getInt("cod_servico")
                        + " | "
                        + rs.getString("nome_servico")
                        + " | R$ "
                        + rs.getDouble("valor_servico")
                );
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void atualizar(servico servico) {

        String sql = """
            UPDATE Servico
            SET valor_servico = ?,
                duracao_servico = ?
            WHERE cod_servico = ?
            """;

        try (
            Connection conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setDouble(1, servico.getValorServico());
            stmt.setInt(2, servico.getDuracaoServico());
            stmt.setInt(3, servico.getCodServico());

            stmt.executeUpdate();

            System.out.println("Serviço atualizado!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void excluir(int codigo) {

        String sql =
                "DELETE FROM Servico WHERE cod_servico = ?";

        try (
            Connection conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, codigo);

            stmt.executeUpdate();

            System.out.println("Serviço removido!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
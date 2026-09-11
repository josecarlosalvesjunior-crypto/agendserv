package dao;

import conexao.conexao;
import entidades.prestador;

import java.sql.*;

public class prestadordao {

    public void inserir(prestador prestador) {

        String sql = """
            INSERT INTO Prestador
            (
                nome_prestador,
                especialidade,
                email_prestador,
                telefone_prestador,
                status_fornecedor
            )
            VALUES (?, ?, ?, ?, ?)
            """;

        try (
            Connection conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, prestador.getNomePrestador());
            stmt.setString(2, prestador.getEspecialidade());
            stmt.setString(3, prestador.getEmailPrestador());
            stmt.setString(4, prestador.getTelefonePrestador());
            stmt.setString(5, prestador.getStatusFornecedor());

            stmt.executeUpdate();

            System.out.println("Prestador cadastrado!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listar() {

        String sql = "SELECT * FROM Prestador";

        try (
            Connection conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {

            while (rs.next()) {

                System.out.println(
                        rs.getInt("cod_prestador")
                        + " | "
                        + rs.getString("nome_prestador")
                        + " | "
                        + rs.getString("especialidade")
                        + " | "
                        + rs.getString("status_fornecedor")
                );
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void atualizar(prestador prestador) {

        String sql = """
            UPDATE Prestador
            SET especialidade = ?,
                telefone_prestador = ?,
                status_fornecedor = ?
            WHERE cod_prestador = ?
            """;

        try (
            Connection conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, prestador.getEspecialidade());
            stmt.setString(2, prestador.getTelefonePrestador());
            stmt.setString(3, prestador.getStatusFornecedor());
            stmt.setInt(4, prestador.getCodPrestador());

            stmt.executeUpdate();

            System.out.println("Prestador atualizado!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void excluir(int codigo) {

        String sql =
                "DELETE FROM Prestador WHERE cod_prestador = ?";

        try (
            Connection conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, codigo);

            stmt.executeUpdate();

            System.out.println("Prestador removido!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
package dao;

import conexao.conexao;
import entidades.disponiblidade;

import java.sql.*;

public class disponibilidadedao {

    public void inserir(disponiblidade disponibilidade) {

        String sql = """
            INSERT INTO Disponibilidade
            (
                dia_semana,
                hora_inicio,
                hora_fim,
                cod_prestador
            )
            VALUES (?, ?, ?, ?)
            """;

        try (
            Connection conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, disponibilidade.getDiaSemana());
            stmt.setTime(2, disponibilidade.getHoraInicio());
            stmt.setTime(3, disponibilidade.getHoraFim());
            stmt.setInt(4, disponibilidade.getCodPrestador());

            stmt.executeUpdate();

            System.out.println("Disponibilidade cadastrada!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listar() {

        String sql = "SELECT * FROM Disponibilidade";

        try (
            Connection conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {

            while(rs.next()) {

                System.out.println(
                    rs.getInt("cod_disponibilidade")
                    + " | "
                    + rs.getString("dia_semana")
                    + " | "
                    + rs.getTime("hora_inicio")
                    + " - "
                    + rs.getTime("hora_fim")
                );
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void atualizar(disponiblidade disponibilidade) {

        String sql = """
            UPDATE Disponibilidade
            SET dia_semana = ?,
                hora_inicio = ?,
                hora_fim = ?
            WHERE cod_disponibilidade = ?
            """;

        try (
            Connection conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, disponibilidade.getDiaSemana());
            stmt.setTime(2, disponibilidade.getHoraInicio());
            stmt.setTime(3, disponibilidade.getHoraFim());
            stmt.setInt(4, disponibilidade.getCodDisponibilidade());

            stmt.executeUpdate();

            System.out.println("Disponibilidade atualizada!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void excluir(int codigo) {

        String sql =
            "DELETE FROM Disponibilidade WHERE cod_disponibilidade = ?";

        try (
            Connection conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, codigo);

            stmt.executeUpdate();

            System.out.println("Disponibilidade removida!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
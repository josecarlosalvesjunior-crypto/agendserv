package dao;

import conexao.conexao;
import entidades.cliente;

import java.sql.*;

public class clientedao {

    public void inserir(cliente cliente) {

        String sql = """
            INSERT INTO Cliente
            (nome_cliente, cpf_cliente,
             email_cliente, telefone_cliente)
            VALUES (?, ?, ?, ?)
            """;

        try (
            Connection conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, cliente.getNomeCliente());
            stmt.setString(2, cliente.getCpfCliente());
            stmt.setString(3, cliente.getEmailCliente());
            stmt.setString(4, cliente.getTelefoneCliente());

            stmt.executeUpdate();

            System.out.println("Cliente cadastrado!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listar() {

        String sql = "SELECT * FROM Cliente";

        try (
            Connection conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {

            while (rs.next()) {

                System.out.println(
                        rs.getInt("cod_cliente")
                        + " | "
                        + rs.getString("nome_cliente")
                        + " | "
                        + rs.getString("cpf_cliente")
                        + " | "
                        + rs.getString("email_cliente")
                );
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void atualizar(cliente cliente) {

        String sql = """
            UPDATE Cliente
            SET nome_cliente = ?,
                email_cliente = ?,
                telefone_cliente = ?
            WHERE cod_cliente = ?
            """;

        try (
            Connection conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, cliente.getNomeCliente());
            stmt.setString(2, cliente.getEmailCliente());
            stmt.setString(3, cliente.getTelefoneCliente());
            stmt.setInt(4, cliente.getCodCliente());

            stmt.executeUpdate();

            System.out.println("Cliente atualizado!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void excluir(int codigo) {

        String sql =
                "DELETE FROM Cliente WHERE cod_cliente = ?";

        try (
            Connection conn = conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, codigo);

            stmt.executeUpdate();

            System.out.println("Cliente removido!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
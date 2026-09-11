package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexao {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/agendamento_servicos";

    private static final String USER =
            "postgres";

    private static final String PASSWORD =
            "19100109";

    public static Connection conectar()
            throws SQLException {

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }
}
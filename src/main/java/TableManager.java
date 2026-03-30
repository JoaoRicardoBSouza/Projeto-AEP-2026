import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableManager {

    public static void criarTabelaPostagem(Connection connection){

        String sql = "CREATE TABLE IF NOT EXISTS postagem (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "conteudo VARCHAR(255) NOT NULL," +
                "tipo VARCHAR(50) NOT NULL," +
                "concluida BOOLEAN NOT NULL DEFAULT FALSE" +
                ");";

        try (Statement stmt = connection.createStatement()){
            stmt.execute(sql);

            System.out.println("Tabela recriada com sucesso!");
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }


}

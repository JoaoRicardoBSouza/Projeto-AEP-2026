import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostagemDAO {

    private Connection connection;

    public PostagemDAO(Connection connection){
        this.connection = connection;
    }

    public boolean publicarPostagem(Postagem postagem){
        String sql = "INSERT INTO postagem(conteudo, tipo) VALUES(?, ?)";

        try(PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setString(1, postagem.getConteudo());
            pstmt.setString(2, postagem.getTipo());

            int linhasAfetadas = pstmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }

    public List<Postagem> listarTodasPostagens() {
        List<Postagem> postagem = new ArrayList<>();
        String sql = "SELECT * FROM postagem ORDER BY id";

        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Postagem postagem1 = new Postagem();
                postagem1.setId(rs.getInt("id"));
                postagem1.setConteudo(rs.getString("conteudo"));
                postagem1.setTipo(rs.getString("tipo"));
                postagem1.setConcluida(rs.getBoolean("concluida"));

                postagem.add(postagem1);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar postagens: " + e.getMessage());
        }

        return postagem;
    }

    public Postagem buscarPostagemPorId(int id){
        String sql = "SELECT * FROM postagem WHERE id = ?";

        try(PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setInt(1,id);

            try (ResultSet rs = pstmt.executeQuery()){
                if (rs.next()){
                    Postagem p = new Postagem();
                    p.setId(rs.getInt("id"));
                    p.setConteudo(rs.getString("conteudo"));
                    p.setTipo(rs.getString("tipo"));
                    p.setConcluida(rs.getBoolean("concluida"));

                    return p;
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }

        return null;
    }

    public boolean atualizarConclusao(int id, boolean concluida){
        String sql = "UPDATE postagem SET concluida = ? WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setBoolean(1, concluida);
            pstmt.setInt(2, id);

            int linhasAfetadas = pstmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
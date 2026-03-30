import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE POSTAGENS ===");

        try (Connection connection = ConexaoBanco.getConnection();
             Scanner leitor = new Scanner(System.in)) {

            TableManager.criarTabelaPostagem(connection);

            PostagemDAO postagemDAO = new PostagemDAO(connection);

            System.out.print("Digite seu email: ");
            String email = leitor.nextLine();

            boolean isAdmin = email.endsWith("@admin.com");

            int opcao;
            do {
                exibirMenu(isAdmin);
                System.out.print("Escolha uma opção: ");

                try {
                    opcao = Integer.parseInt(leitor.nextLine());

                    switch (opcao) {
                        case 1:
                            criarPostagem(postagemDAO, leitor);
                            break;
                        case 2:
                            listarPostagens(postagemDAO);
                            break;
                        case 3:
                            if (isAdmin) {
                                marcarComoConcluida(postagemDAO, leitor);
                            } else {
                                System.out.println("Opção inválida!");
                            }
                            break;
                        case 0:
                            System.out.println("Saindo...");
                            break;
                        default:
                            System.out.println("Opção inválida!");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Digite um número válido!");
                    opcao = -1;
                }

            } while (opcao != 0);

        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void exibirMenu(boolean isAdmin) {
        System.out.println("\n=== MENU ===");
        System.out.println("1. Criar postagem");
        System.out.println("2. Listar postagens");

        if (isAdmin) {
            System.out.println("3. Marcar como concluída");
        }

        System.out.println("0. Sair");
    }

    private static void criarPostagem(PostagemDAO dao, Scanner leitor) {
        System.out.println("\n--- NOVA POSTAGEM ---");

        System.out.print("Conteúdo: ");
        String conteudo = leitor.nextLine();

        System.out.print("Tipo: ");
        String tipo = leitor.nextLine();

        Postagem postagem = new Postagem(conteudo, tipo, false);

        boolean sucesso = dao.publicarPostagem(postagem);

        System.out.println(sucesso ? "Postagem criada!" : "Erro ao criar postagem.");
    }

    private static void listarPostagens(PostagemDAO dao) {
        System.out.println("\n--- LISTA DE POSTAGENS ---");

        List<Postagem> lista = dao.listarTodasPostagens();

        if (lista.isEmpty()) {
            System.out.println("Nenhuma postagem encontrada.");
            return;
        }

        for (Postagem p : lista) {
            System.out.println(
                    "ID: " + p.getId() +
                            " | Conteúdo: " + p.getConteudo() +
                            " | Tipo: " + p.getTipo() +
                            " | Concluída: " + (p.isConcluida() ? "Sim" : "Não")
            );
        }
    }

    private static void marcarComoConcluida(PostagemDAO dao, Scanner leitor) {
        System.out.println("\n--- MARCAR COMO CONCLUÍDA ---");

        try {
            System.out.print("ID da postagem: ");
            int id = Integer.parseInt(leitor.nextLine());

            boolean sucesso = dao.atualizarConclusao(id, true);

            if (sucesso) {
                System.out.println("Postagem marcada como concluída!");
            } else {
                System.out.println("Postagem não encontrada.");
            }

        } catch (NumberFormatException e) {
            System.out.println("ID inválido!");
        }
    }
}
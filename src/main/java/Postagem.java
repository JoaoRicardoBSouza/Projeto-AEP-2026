public class Postagem {

    private int id;
    private String conteudo;
    private String tipo;
    private boolean concluida;

    public Postagem(String conteudo, String tipo, boolean concluida) {
        this.conteudo = conteudo;
        this.tipo = tipo;
        this.concluida = concluida;
    }

    public Postagem(int id, String conteudo, String tipo, boolean concluida) {
        this.id = id;
        this.conteudo = conteudo;
        this.tipo = tipo;
        this.concluida = concluida;
    }

    public Postagem() {

    }

    public Postagem(int id, String conteudo) {
    }

    public String getConteudo() {
        return conteudo;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }
}

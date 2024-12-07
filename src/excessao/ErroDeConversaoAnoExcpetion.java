package excessao;

public class ErroDeConversaoAnoExcpetion extends RuntimeException {

    private String mensagem;
    public ErroDeConversaoAnoExcpetion(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String getMessage() {
        return this.mensagem;
    }
}

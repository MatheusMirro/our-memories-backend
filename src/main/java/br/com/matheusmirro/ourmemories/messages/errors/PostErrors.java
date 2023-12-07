package br.com.matheusmirro.ourmemories.messages.errors;

public class PostErrors extends RuntimeException {
    public static final String INVALID_FILE = "Tamanho do arquivo inválido!";
    public static final String POST_NOT_FOUND = "A postagem não foi encontrada!";
    public static final String TRY_AGAIN = "Algo deu errado! Tente fazer o comentário em poucos minutos!";
    public static final String ERROR_SEND_LIKE = "Algo deu errado! Tente curtir em poucos minutos!";
    // Adicione outras mensagens conforme necessário

    public PostErrors(String message) {
        super(message);
    }
}

package br.com.matheusmirro.ourmemories.messages.errors;

public class TokenErrors extends RuntimeException {
    public static final String ERROR_GENERATING_TOKEN = "Ocorreu um erro na geração do token!";
    public static final String INVALID_JWT_TOKEN = "Token JWT inválido!";

    public TokenErrors(String message) {
        super(message);
    }
}

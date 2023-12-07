package br.com.matheusmirro.ourmemories.messages.errors;

public class UserErrors extends RuntimeException {
    public static final String USER_ALREADY_IN = "Já existe esse usuário cadastrado!";
    public static final String USER_NOT_AUTHENTICATED = "Usuário não autenticado!";

    public UserErrors(String message) {
        super(message);
    }
}

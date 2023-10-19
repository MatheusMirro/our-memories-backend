package br.com.matheusmirro.ourmemories.auth.domain.user;

public record RegisterDTO(String login, String password, UserRole role) {
}

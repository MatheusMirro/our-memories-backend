package br.com.matheusmirro.ourmemories.auth.domain.user;

public record RegisterDTO(String username, String password, UserRole role) {
}

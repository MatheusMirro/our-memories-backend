package br.com.matheusmirro.ourmemories.auth.domain.user;

public enum UserRole {
    ADMIN("admin"),
    USER("user");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public String setRole() {
        return role;
    }

}

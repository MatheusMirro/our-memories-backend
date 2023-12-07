package br.com.matheusmirro.ourmemories.controllers.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenRequest {
    private String token;

    public TokenRequest(String token) {
        this.token = token;
    }
}

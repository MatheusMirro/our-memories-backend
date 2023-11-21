package br.com.matheusmirro.ourmemories.controllers.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentRequest {
    private String text;

    public CommentRequest(String text) {
        this.text = text;
    }
}

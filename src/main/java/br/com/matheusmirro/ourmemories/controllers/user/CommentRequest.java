package br.com.matheusmirro.ourmemories.controllers.user;

import lombok.Data;

@Data
public class CommentRequest {
    private String text;

    public CommentRequest(String text) {
        this.text = text;
    }
}

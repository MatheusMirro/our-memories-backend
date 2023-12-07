package br.com.matheusmirro.ourmemories.controllers.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LikeRequest {
    private Integer value;

    public LikeRequest(Integer value) {
        this.value = value;
    }
}

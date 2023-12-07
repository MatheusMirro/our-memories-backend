package br.com.matheusmirro.ourmemories.controllers.user;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheusmirro.ourmemories.repository.user.ILikeRepository;
import br.com.matheusmirro.ourmemories.service.user.LikeService;

@RestController
@RequestMapping("/like")
public class LikeController {

    private final LikeService likeService;

    public LikeController(ILikeRepository likeRepository, LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> like(@PathVariable("id") UUID id, Authentication authentication) {
        LikeRequest likeRequest = new LikeRequest();
        likeRequest.setValue(1); // Valor padrão do like

        return likeService.likePost(likeRequest, authentication, id);
    }
}

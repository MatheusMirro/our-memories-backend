package br.com.matheusmirro.ourmemories.controllers.user;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheusmirro.ourmemories.repository.post.ICommentRepository;
import br.com.matheusmirro.ourmemories.service.user.CommentaryService;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/p")
public class CommentaryController {

    private final CommentaryService commentaryService;

    public CommentaryController(ICommentRepository commentRepository,
            CommentaryService commentaryService) {
        this.commentaryService = commentaryService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> commentary(@RequestBody CommentRequest commentRequest, @PathVariable("id") UUID id,
            String username,
            Authentication authentication) {
        return commentaryService.commentary(commentRequest, id, username, authentication);
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentRequest>> getPhotoComments(@PathVariable("id") UUID id,
            Authentication authentication) {
        return commentaryService.getPhotoComments(authentication, id);
    }
}

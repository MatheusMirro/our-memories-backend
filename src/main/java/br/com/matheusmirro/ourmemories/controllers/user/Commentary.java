package br.com.matheusmirro.ourmemories.controllers.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheusmirro.ourmemories.model.post.CommentModel;
import br.com.matheusmirro.ourmemories.model.post.PostModel;
import br.com.matheusmirro.ourmemories.model.user.UserModel;
import br.com.matheusmirro.ourmemories.repository.post.ICommentRepository;
import br.com.matheusmirro.ourmemories.repository.post.IPostRepository;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/p")
public class Commentary {

    @Autowired
    private ICommentRepository commentRepository;

    @Autowired
    IPostRepository postRepository;

    @PostMapping("/{id}")
    public String commentary(@RequestBody CommentRequest commentRequest, @PathVariable("id") UUID id, String username,
            Authentication authentication) {

        if (authentication == null || !authentication.isAuthenticated()) {
            return "Usuário não autenticado.";
        }
        // precisa ser verificada a logica.
        try {
            UserModel authenticatedUser = (UserModel) authentication.getPrincipal();
            if (authenticatedUser.getUsername().equals(username)) {
                return "Usuário não autorizado.";
            }

            Optional<PostModel> optionalPost = postRepository.findById(id);

            if (optionalPost.isPresent()) {
                PostModel postModel = optionalPost.get();

                CommentModel comment = new CommentModel();
                comment.setUser(authenticatedUser);
                comment.setPost(postModel);
                comment.setText(commentRequest.getText());
                comment.setCommentDate(LocalDateTime.now());

                commentRepository.save(comment);
                return "Comentário recebido!!!";
            } else {
                return "Post não encontrado!";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Algo deu errado aqui: " + e.getMessage();
        }
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentRequest>> getPhotoComments(@PathVariable("id") UUID id,
            Authentication authentication) {

        try {
            if (authentication != null && authentication.isAuthenticated()) {
                UserModel authenticatedUser = (UserModel) authentication.getPrincipal();

                if (authenticatedUser != null) {

                    List<CommentModel> comments = commentRepository.findAllByPost_Id(id);
                    List<CommentRequest> CommentRequests = new ArrayList<>();

                    for (CommentModel comment : comments) {
                        CommentRequest response = new CommentRequest(
                                comment.getText());
                        CommentRequests.add(response);
                    }

                    return ResponseEntity.ok(CommentRequests);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.notFound().build();
    }

}

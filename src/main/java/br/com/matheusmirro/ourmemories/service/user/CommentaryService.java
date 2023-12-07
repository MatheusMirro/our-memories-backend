package br.com.matheusmirro.ourmemories.service.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.matheusmirro.ourmemories.controllers.user.CommentRequest;
import br.com.matheusmirro.ourmemories.messages.errors.PostErrors;
import br.com.matheusmirro.ourmemories.messages.errors.UserErrors;
import br.com.matheusmirro.ourmemories.messages.success.PostSuccess;
import br.com.matheusmirro.ourmemories.model.post.PostModel;
import br.com.matheusmirro.ourmemories.model.user.CommentModel;
import br.com.matheusmirro.ourmemories.model.user.UserModel;
import br.com.matheusmirro.ourmemories.repository.post.ICommentRepository;
import br.com.matheusmirro.ourmemories.repository.post.IPostRepository;

@Service
public class CommentaryService {

    private final ICommentRepository commentRepository;
    private final IPostRepository postRepository;

    public CommentaryService(ICommentRepository commentRepository, IPostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public ResponseEntity<String> commentary(CommentRequest commentRequest, UUID id, String username,
            Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(UserErrors.USER_NOT_AUTHENTICATED);
        }

        try {
            UserModel authenticatedUser = (UserModel) authentication.getPrincipal();
            Optional<PostModel> optionalPost = postRepository.findById(id);

            if (optionalPost.isPresent()) {
                PostModel postModel = optionalPost.get();

                CommentModel comment = new CommentModel();
                comment.setUser(authenticatedUser);
                comment.setPost(postModel);
                comment.setText(commentRequest.getText());
                comment.setCommentDate(LocalDateTime.now());

                commentRepository.save(comment);

                return ResponseEntity.status(HttpStatus.ACCEPTED).body(PostSuccess.COMMENT_RECEIVED);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(PostErrors.POST_NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(PostErrors.TRY_AGAIN);
        }
    }

    public ResponseEntity<List<CommentRequest>> getPhotoComments(Authentication authentication, UUID id) {
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

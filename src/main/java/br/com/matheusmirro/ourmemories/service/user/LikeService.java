package br.com.matheusmirro.ourmemories.service.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import br.com.matheusmirro.ourmemories.controllers.user.LikeRequest;
import br.com.matheusmirro.ourmemories.messages.errors.PostErrors;
import br.com.matheusmirro.ourmemories.model.post.PostModel;
import br.com.matheusmirro.ourmemories.model.user.LikeModel;
import br.com.matheusmirro.ourmemories.model.user.UserModel;
import br.com.matheusmirro.ourmemories.repository.post.IPostRepository;
import br.com.matheusmirro.ourmemories.repository.user.ILikeRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class LikeService {

    private final ILikeRepository likeRepository;
    private final IPostRepository postRepository;

    public LikeService(ILikeRepository likeRepository, IPostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
    }

    public ResponseEntity<String> likePost(LikeRequest likeRequest, Authentication authentication, UUID postId) {
        try {
            if (authentication != null && authentication.isAuthenticated()) {
                UserModel authenticatedUser = (UserModel) authentication.getPrincipal();

                UUID userId = authenticatedUser.getId();

                Optional<LikeModel> existingLike = likeRepository.findByUserIdAndPostId(userId, postId);

                if (existingLike.isPresent()) {
                    // Remover o like existente
                    likeRepository.delete(existingLike.get());
                    return ResponseEntity.ok().build();
                }

                Optional<PostModel> optionalPost = postRepository.findById(postId);

                if (optionalPost.isPresent()) {
                    PostModel postModel = optionalPost.get();
                    LikeModel like = new LikeModel();
                    like.setUser(authenticatedUser);
                    like.setPost(postModel);
                    like.setTotalLikes(likeRequest.getValue());

                    likeRepository.save(like);

                    return ResponseEntity.ok().build();
                } else {
                    return ResponseEntity.notFound().build();
                }
            }
        } catch (Exception e) {
            throw new Error(PostErrors.ERROR_SEND_LIKE + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
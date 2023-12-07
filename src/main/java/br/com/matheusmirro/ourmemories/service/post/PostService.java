package br.com.matheusmirro.ourmemories.service.post;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.matheusmirro.ourmemories.messages.errors.PostErrors;
import br.com.matheusmirro.ourmemories.messages.success.PostSuccess;
import br.com.matheusmirro.ourmemories.model.post.PostModel;
import br.com.matheusmirro.ourmemories.model.user.UserModel;
import br.com.matheusmirro.ourmemories.repository.post.IPostRepository;

@Service
public class PostService {

    @Autowired
    private final IPostRepository postRepository;

    public PostService(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // upload multiple images
    public ResponseEntity<String> uploadingPost(MultipartFile[] uploadinFiles, Authentication authentication) {
        try {
            // get user details from token
            var authenticatedUser = (UserModel) authentication.getPrincipal();
            for (MultipartFile file : uploadinFiles) {
                if (!isValidImage(file)) {
                    return ResponseEntity.badRequest().body(PostErrors.INVALID_FILE + file.getOriginalFilename());
                }

                // create post object with file details
                PostModel postModel = new PostModel();
                postModel.setFile_name(file.getOriginalFilename());
                postModel.setFile_type(file.getContentType());
                postModel.setFile_size(file.getSize());
                postModel.setUpload_date(LocalDateTime.now());
                postModel.setUser(authenticatedUser);
                postRepository.save(postModel);
            }
        } catch (Exception e) {
            var error = e.getMessage();
            System.out.println(error);
        }
        return ResponseEntity.accepted().body(PostSuccess.UPLOAD_SUCCESSFULY);
    }

    // check if uploaded file is a valid image
    private boolean isValidImage(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return false;
        }
        return true;
    }
}

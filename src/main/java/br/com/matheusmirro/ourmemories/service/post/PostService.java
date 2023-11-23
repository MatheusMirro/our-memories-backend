package br.com.matheusmirro.ourmemories.service.post;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.matheusmirro.ourmemories.infra.security.TokenService;
import br.com.matheusmirro.ourmemories.model.post.PostModel;
import br.com.matheusmirro.ourmemories.model.user.UserModel;
import br.com.matheusmirro.ourmemories.repository.post.IPostRepository;

@Service
public class PostService {

    @Autowired
    private IPostRepository postRepository;

    @Autowired
    TokenService tokenService;

    public PostService (IPostRepository postRepository, TokenService tokenService) {
        this.postRepository = postRepository;
        this.tokenService = tokenService;
    }

    public ResponseEntity<String> uploadingPost(MultipartFile[] uploadingFiles,
            Authentication authentication) {

        try {
            // identify user by token getting his name
            var authenticatedUser = (UserModel) authentication.getPrincipal();
            for (MultipartFile file : uploadingFiles) {
                if (!isValidImage(file)) {
                    return ResponseEntity.badRequest().body("Arquivo inválido: " + file.getOriginalFilename());
                }

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
        return ResponseEntity.accepted().body("Upload feito com sucesso!");
    }
    private boolean isValidImage(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            System.out.println("Arquivo vazio ou nulo.");
            return false;
        }

        return true;
    }

}

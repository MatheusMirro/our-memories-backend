package br.com.matheusmirro.ourmemories.controllers.post;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.matheusmirro.ourmemories.infra.security.TokenService;
import br.com.matheusmirro.ourmemories.model.post.PostModel;
import br.com.matheusmirro.ourmemories.model.user.UserModel;
import br.com.matheusmirro.ourmemories.repository.post.IPostRepository;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private IPostRepository postRepository;

    @Autowired
    TokenService tokenService;

    @PostMapping("/picture")
    public ResponseEntity<String> uploadingPost(@RequestPart("uploadingFiles") MultipartFile[] uploadingFiles,
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
        // Adicione mais verificações, se necessário, como tamanho máximo, extensão,
        // etc.

        return true;
    }
}

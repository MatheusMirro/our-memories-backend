package br.com.matheusmirro.ourmemories.controllers.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.matheusmirro.ourmemories.controllers.post.FileResponse;
import br.com.matheusmirro.ourmemories.model.post.PostModel;
import br.com.matheusmirro.ourmemories.model.user.UserModel;
import br.com.matheusmirro.ourmemories.repository.post.IPostRepository;
import br.com.matheusmirro.ourmemories.repository.user.IUserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IPostRepository postRepository;

    @PostMapping("/register")
    public ResponseEntity<String> create(@RequestBody UserModel userModel) {
        var findUser = this.userRepository.findByUsername(userModel.getUsername());

        var hashedPassword = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());

        userModel.setPassword(hashedPassword);

        if (findUser == null) {
            this.userRepository.save(userModel);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Usuário criado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe!");
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<FileResponse>> listOfPosts(@PathVariable String username,
            Authentication authentication) {
        try {
            if (authentication != null && authentication.isAuthenticated()) {
                UserModel authenticatedUser = (UserModel) authentication.getPrincipal();
                if (authenticatedUser.getUsername().equals(username)) {
                    List<PostModel> postModels = postRepository.findAllByUser_Username(username);

                    List<FileResponse> fileResponses = new ArrayList<>();

                    for (PostModel postModel : postModels) {
                        FileResponse response = new FileResponse(
                                postModel.getId(),
                                postModel.getFile_name(),
                                postModel.getFile_type(),
                                postModel.getFile_size(),
                                postModel.getUpload_date());

                        fileResponses.add(response);
                    }

                    return ResponseEntity.ok(fileResponses);
                }
            }
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }

        return ResponseEntity.notFound().build();
    }
}
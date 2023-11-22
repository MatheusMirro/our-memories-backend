package br.com.matheusmirro.ourmemories.service.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.matheusmirro.ourmemories.controllers.post.FileResponse;
import br.com.matheusmirro.ourmemories.model.post.PostModel;
import br.com.matheusmirro.ourmemories.model.user.UserModel;
import br.com.matheusmirro.ourmemories.repository.post.IPostRepository;
import br.com.matheusmirro.ourmemories.repository.user.IUserRepository;

@Service
public class UserService {

    private final IUserRepository userRepository;
    private final IPostRepository postRepository;

    public UserService(IUserRepository userRepository, IPostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public ResponseEntity<String> createUser(UserModel userModel) {
        var findUser = userRepository.findByUsername(userModel.getUsername());

        var hashedPassword = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());
        userModel.setPassword(hashedPassword);

        if (findUser == null) {
            userRepository.save(userModel);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Usuário criado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe!");
        }
    }

    public List<FileResponse> getListOfPostsByUsername(String username, Authentication authentication) {
        List<FileResponse> fileResponses = new ArrayList<>();

        try {
            if (authentication != null && authentication.isAuthenticated()) {
                UserModel authenticatedUser = (UserModel) authentication.getPrincipal();
                if (authenticatedUser.getUsername().equals(username)) {
                    List<PostModel> postModels = postRepository.findAllByUser_Username(username);

                    for (PostModel postModel : postModels) {
                        FileResponse response = new FileResponse(
                                postModel.getId(),
                                postModel.getFile_name(),
                                postModel.getFile_type(),
                                postModel.getFile_size(),
                                postModel.getUpload_date());
                        fileResponses.add(response);
                    }
                }
            }
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
        return fileResponses;
    }
}

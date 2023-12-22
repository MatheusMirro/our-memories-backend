package br.com.matheusmirro.ourmemories.controllers.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheusmirro.ourmemories.auth.domain.user.RegisterDTO;
import br.com.matheusmirro.ourmemories.auth.domain.user.UserRole;
import br.com.matheusmirro.ourmemories.controllers.post.FileResponse;
import br.com.matheusmirro.ourmemories.model.user.UserModel;
import br.com.matheusmirro.ourmemories.service.user.UserService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> create(@RequestBody UserModel userModel, RegisterDTO registerDTO) {

        if (registerDTO.role() == null) {
            registerDTO = new RegisterDTO(registerDTO.username(), registerDTO.password(), UserRole.USER);
        }
        return userService.createUser(userModel);
    }

    @GetMapping(value = "/{username}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> listOfPosts(@PathVariable String username) {
        List<FileResponse> fileResponses = userService.getListOfPostsByUsername(username);

        // Extraia o byte array da imagem
        byte[] imageData = fileResponses.get(0).getFileData();

        return new ResponseEntity<>(imageData, HttpStatus.OK);
    }
}
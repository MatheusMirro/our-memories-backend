package br.com.matheusmirro.ourmemories.controllers.user;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheusmirro.ourmemories.controllers.post.FileResponse;
import br.com.matheusmirro.ourmemories.model.user.UserModel;
import br.com.matheusmirro.ourmemories.service.user.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> create(@RequestBody UserModel userModel) {
        return userService.createUser(userModel);
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<FileResponse>> listOfPosts(@PathVariable String username,
            Authentication authentication) {
        List<FileResponse> fileResponses = userService.getListOfPostsByUsername(username, authentication);
        return ResponseEntity.ok(fileResponses);
    }
}
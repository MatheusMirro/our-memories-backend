package br.com.matheusmirro.ourmemories.controllers.user;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/{username}")
    public ResponseEntity<HashMap<Object, Object>> getUserInfoName(@PathVariable String username) {
        UserModel userInfo = userService.getUserInfo(username);

        var mainInfo = new HashMap<>();
        mainInfo.put("name", userInfo.getName());
        mainInfo.put("lastname", userInfo.getLastname());
        mainInfo.put("email", userInfo.getEmail());
        mainInfo.put("username", userInfo.getUsername());

        return ResponseEntity.ok().body(mainInfo);
    }

    @GetMapping(value = "/posts/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> listOfPosts(@PathVariable String username) {
        List<FileResponse> fileResponses = userService.getListOfPostsByUsername(username);

        if (fileResponses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        List<String> imageBase64List = fileResponses.stream()
                .map(fileResponse -> Base64.getEncoder().encodeToString(fileResponse.getFileData()))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(imageBase64List);
    }
}
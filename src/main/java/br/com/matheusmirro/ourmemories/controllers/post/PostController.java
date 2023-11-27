package br.com.matheusmirro.ourmemories.controllers.post;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import br.com.matheusmirro.ourmemories.service.post.PostService;

@RestController
@RequestMapping("/post")
public class PostController {
    
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/picture")
    public ResponseEntity<String> postPicture(@RequestPart("uploadingFiles") MultipartFile[] uploadingFiles, Authentication authentication){
      return postService.uploadingPost(uploadingFiles, authentication);
    }

    
    public ResponseEntity<String> uploadingPost(@RequestPart("uploadingFiles") MultipartFile[] uploadingFiles,
            Authentication authentication) {

        return postService.uploadingPost(uploadingFiles, authentication);
    }
}

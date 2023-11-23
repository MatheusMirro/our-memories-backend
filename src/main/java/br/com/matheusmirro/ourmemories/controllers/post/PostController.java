package br.com.matheusmirro.ourmemories.controllers.post;

<<<<<<< HEAD
import java.time.LocalDateTime;

import org.apache.tomcat.util.http.fileupload.UploadContext;
import org.springframework.beans.factory.annotation.Autowired;
=======
>>>>>>> 34fa38c7e63244e04cdba5ff4b93bd6d13c45c56
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

<<<<<<< HEAD

=======
>>>>>>> 34fa38c7e63244e04cdba5ff4b93bd6d13c45c56
import br.com.matheusmirro.ourmemories.service.post.PostService;

@RestController
@RequestMapping("/post")
public class PostController {
<<<<<<< HEAD
    @Autowired
    private PostService postService;
=======
    private final PostService postService;
>>>>>>> 34fa38c7e63244e04cdba5ff4b93bd6d13c45c56

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/picture")
<<<<<<< HEAD
    public ResponseEntity<String> postPicture(@RequestPart("uploadingFiles") MultipartFile[] uploadingFiles, Authentication authentication){
      return postService.uploadingPost(uploadingFiles, authentication);
    }

    
=======
    public ResponseEntity<String> uploadingPost(@RequestPart("uploadingFiles") MultipartFile[] uploadingFiles,
            Authentication authentication) {

        return postService.uploadingPost(uploadingFiles, authentication);
    }
>>>>>>> 34fa38c7e63244e04cdba5ff4b93bd6d13c45c56
}

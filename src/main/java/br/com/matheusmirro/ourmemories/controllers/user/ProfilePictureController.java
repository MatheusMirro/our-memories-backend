package br.com.matheusmirro.ourmemories.controllers.user;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.matheusmirro.ourmemories.controllers.post.FileResponse;
import br.com.matheusmirro.ourmemories.service.user.ProfilePictureService;

@CrossOrigin
@RestController
@RequestMapping("/api/profile-picture")
public class ProfilePictureController {

    private final ProfilePictureService profilePictureService;

    public ProfilePictureController(ProfilePictureService profilePictureService) {
        this.profilePictureService = profilePictureService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadProfilePicture(@RequestParam("files") MultipartFile[] uploadingFiles,
            Authentication authentication) {
        try {
            return profilePictureService.saveProfilePicture(uploadingFiles, authentication);
        } catch (Exception e) {
            // Trate o erro de forma adequada
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<FileResponse>> getProfilePicture(@PathVariable String username) {
        try {
            List<FileResponse> fileResponses = profilePictureService.getProfilePicture(username);
            return ResponseEntity.ok().body(fileResponses);
        } catch (Exception e) {
            // Trate o erro de forma adequada
            return ResponseEntity.internalServerError().build();
        }
    }
}

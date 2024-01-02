package br.com.matheusmirro.ourmemories.service.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.matheusmirro.ourmemories.controllers.post.FileResponse;
import br.com.matheusmirro.ourmemories.messages.success.PostSuccess;
import br.com.matheusmirro.ourmemories.model.user.ProfilePictureModel;
import br.com.matheusmirro.ourmemories.model.user.UserModel;
import br.com.matheusmirro.ourmemories.repository.user.IProfilePictureRepository;

@Service
public class ProfilePictureService {
    @Autowired
    private final IProfilePictureRepository profilePictureRepository;

    public ProfilePictureService(IProfilePictureRepository profilePictureRepository) {
        this.profilePictureRepository = profilePictureRepository;
    }

    public ResponseEntity<String> saveProfilePicture(MultipartFile[] uploadingFiles,
            Authentication authentication) throws IOException {

        var authenticatedUser = (UserModel) authentication.getPrincipal();

        for (MultipartFile file : uploadingFiles) {
            // validacoes para a imagem de perfil seguindo os padroes do instagram

            ProfilePictureModel profilePicture = new ProfilePictureModel();
            profilePicture.setFile_name(file.getOriginalFilename());
            profilePicture.setFile_type(file.getContentType());
            profilePicture.setFile_size(file.getSize());
            profilePicture.setUser(authenticatedUser);
            profilePicture.setData(file.getBytes());

            profilePictureRepository.save(profilePicture);

        }

        return ResponseEntity.accepted().body(PostSuccess.UPLOAD_SUCCESSFULY);
    }

    public List<FileResponse> getProfilePicture(String username) {
        List<FileResponse> fileResposes = new ArrayList<>();

        try {
            List<ProfilePictureModel> profilePictureModels = profilePictureRepository.findAllByUser_Username(username);

            for (ProfilePictureModel profilePicture : profilePictureModels) {
                FileResponse response = new FileResponse(
                        profilePicture.getId(),
                        profilePicture.getFile_name(),
                        profilePicture.getFile_type(),
                        profilePicture.getFile_size(),
                        null,
                        profilePicture.getData());

                fileResposes.add(response);
            }
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
        return fileResposes;
    }

    public void updateProfilePicture(ProfilePictureModel profilePictureModel) {
        profilePictureRepository.save(profilePictureModel);
    }

    public void deleteProfilePicture(UUID userId) {
        profilePictureRepository.deleteById(userId);
    }
}

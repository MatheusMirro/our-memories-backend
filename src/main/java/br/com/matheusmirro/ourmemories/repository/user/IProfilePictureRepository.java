package br.com.matheusmirro.ourmemories.repository.user;

import br.com.matheusmirro.ourmemories.model.user.ProfilePictureModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IProfilePictureRepository extends JpaRepository<ProfilePictureModel, UUID> {
    List<ProfilePictureModel> findAllByUser_Username(String username);
}

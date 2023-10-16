package br.com.matheusmirro.ourmemories.repository.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.matheusmirro.ourmemories.model.user.UserModel;

public interface IUserRepository extends JpaRepository<UserModel, UUID> {
    UserModel findByUsername(String username);
}

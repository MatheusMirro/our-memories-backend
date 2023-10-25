package br.com.matheusmirro.ourmemories.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.matheusmirro.ourmemories.model.user.UserModel;

@Repository
public interface IUserRepository extends JpaRepository<UserModel, String> {
    UserModel findByUsername(String username);
}

package br.com.matheusmirro.ourmemories.repository.post;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.matheusmirro.ourmemories.model.post.PostModel;

@Repository
public interface IPostRepository extends JpaRepository<PostModel, UUID> {
    List<PostModel> findAllByUser_Username(String username);
}

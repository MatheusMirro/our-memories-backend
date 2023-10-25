package br.com.matheusmirro.ourmemories.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import br.com.matheusmirro.ourmemories.model.post.PostModel;

@Repository
public interface IPostRepository extends JpaRepository<PostModel, MultipartFile> {

}

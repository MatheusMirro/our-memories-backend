package br.com.matheusmirro.ourmemories.repository.post;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.matheusmirro.ourmemories.model.post.CommentModel;

@Repository
public interface ICommentRepository extends JpaRepository<CommentModel, UUID> {
    List<CommentModel> findAllByPost_Id(UUID postId);
}

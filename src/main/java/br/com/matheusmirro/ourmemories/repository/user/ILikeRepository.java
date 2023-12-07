package br.com.matheusmirro.ourmemories.repository.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.matheusmirro.ourmemories.model.user.LikeModel;

@Repository
public interface ILikeRepository extends JpaRepository<LikeModel, UUID> {
    List<LikeModel> findAllByUser_Username(String username);

    boolean existsByUserIdAndPostId(@Param("userId") UUID userId, @Param("postId") UUID postId);

    Optional<LikeModel> findByUserIdAndPostId(@Param("userId") UUID userId, @Param("postId") UUID postId);
}

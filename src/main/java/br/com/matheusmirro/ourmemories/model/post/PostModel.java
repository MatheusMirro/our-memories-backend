package br.com.matheusmirro.ourmemories.model.post;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.matheusmirro.ourmemories.model.user.CommentModel;
import br.com.matheusmirro.ourmemories.model.user.UserModel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;

@Data
@Entity(name = "tb_users_uploads")
@Getter
public class PostModel {
    @Id
    @GeneratedValue(generator = "UUID")

    private UUID id;
    private String file_name;
    private String file_type;
    private long file_size;
    private LocalDateTime upload_date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private CommentModel comment;

    @Lob
    private byte[] file_data;
}

package br.com.matheusmirro.ourmemories.model.post;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;

@Data
@Entity(name = "tb_users_uploads")
@Getter
public class PostModel {
    @Id
    @GeneratedValue(generator = "UUID")

    private UUID id;

    private String fileName;
    private String fileType;
    private long fileSize;
    private String file_location;
    private LocalDateTime uploadDate;

}

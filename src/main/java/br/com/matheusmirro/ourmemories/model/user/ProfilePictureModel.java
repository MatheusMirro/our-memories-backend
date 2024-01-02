package br.com.matheusmirro.ourmemories.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.UUID;

@Data
@Entity(name = "tb_profile_picture")
public class ProfilePictureModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String file_name;
    private String file_type;
    private long file_size;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @Lob
    private byte[] data;

}

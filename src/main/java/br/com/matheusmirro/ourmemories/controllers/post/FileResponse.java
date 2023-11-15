package br.com.matheusmirro.ourmemories.controllers.post;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class FileResponse {
    private UUID id;
    private String fileName;
    private String fileType;
    private long fileSize;
    private LocalDateTime uploadDate;

    public FileResponse(UUID id, String fileName, String fileType, long fileSize, LocalDateTime uploadDate) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.uploadDate = uploadDate;
    }
}

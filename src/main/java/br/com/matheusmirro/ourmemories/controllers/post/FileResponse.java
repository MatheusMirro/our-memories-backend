package br.com.matheusmirro.ourmemories.controllers.post;

import java.time.LocalDateTime;
import java.util.UUID;

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

    // Getters e Setters (ou métodos acessores) para cada campo
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }
}

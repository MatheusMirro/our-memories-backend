package br.com.matheusmirro.ourmemories.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;


@ResponseStatus(value=HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)

public class ValidFileSizeException extends Exception {
    public ValidFileSizeException(String message) {
        super(message);
    }
    
}

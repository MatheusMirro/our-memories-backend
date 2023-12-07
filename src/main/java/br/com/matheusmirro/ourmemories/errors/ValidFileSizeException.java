package br.com.matheusmirro.ourmemories.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)

public class ValidFileSizeException extends Exception {
    public ValidFileSizeException(String message) {
        super(message);
    }

}

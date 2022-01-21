package br.com.pablo.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsupportedPersonException extends RuntimeException {

    public UnsupportedPersonException(String message) {
        super(message);
    }

}

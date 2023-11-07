package id.aone.blog.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PageNotFoundException extends ResponseStatusException {
    public PageNotFoundException(HttpStatus status) {
        super(status);
    }

    public PageNotFoundException() {
        this(HttpStatus.NOT_FOUND);
    }
}

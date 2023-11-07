package id.aone.blog.service.interfaces;

import id.aone.blog.model.Author;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AuthorService {

    Optional<Author> getAuthor(String authorUsername);

}

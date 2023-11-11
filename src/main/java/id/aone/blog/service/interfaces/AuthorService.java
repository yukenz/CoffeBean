package id.aone.blog.service.interfaces;

import id.aone.blog.model.Author;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AuthorService {


    /**
     * Method untuk mendapatkan data author dari /markdown/author
     *
     * @param authorUsername Nama dari file akan digunakan sebagai identifikasi Username
     * @return Mengembalikan entitas Author namun di Wrap dengan Optional untuk handle jika tidak ditemukan
     * @see id.aone.blog.service.interfaces.ResourceService.PATH
     */
    Optional<Author> getAuthor(String authorUsername);

}

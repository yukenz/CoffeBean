package id.aone.blog.service.interfaces;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;

@Service
public interface ResourceService {

    enum PATH {
        POST("markdown/post/"),
        AUTHOR("markdown/author/");

        PATH(String path) {
            this.path = path;
        }

        private String path;

        public String path() {
            return this.path;
        }
    }

    enum EXT {
        MD(".md");

        EXT(String path) {
            this.ext = path;
        }

        private String ext;

        public String ext() {
            return this.ext;
        }
    }


    /**
     * Mengembalikan Reader untuk membaca file char
     *
     * @param pathResource relative path dari Resources direktori
     * @return <b>Reader</b> Jika berhasil instansiasi BufferedReader
     */
    Optional<BufferedReader> getBufferReader(String pathResource);

    /**
     * Mengembalikan InputStream untuk membaca file bytes
     *
     * @param pathResource relative path dari Resources direktori
     * @return <b>InputStream</b> Jika berhasil instansiasi InputStream
     */
    Optional<InputStream> getInputStream(String pathResource);

    /**
     * Mengembalikan BufferedWriter untuk menulis file char
     *
     * @param pathResource relative path dari Resources direktori
     * @return <b>BufferedWriter</b> Jika berhasil instansiasi BufferedWriter
     */
    Optional<BufferedWriter> getBufferedWriter(String pathResource);

    /**
     * Mengembalikan OutputStream untuk menulis file bytes
     *
     * @param pathResource relative path dari Resources direktori
     * @return <b>OutputStream</b> Jika berhasil instansiasi OutputStream
     */

    Optional<OutputStream> getOutputStream(String pathResource);


}

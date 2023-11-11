package id.aone.blog.service;

import id.aone.blog.service.interfaces.ResourceService;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class ResourceServiceImpl implements ResourceService {

    /**
     * Mengembalikan path jika pathResource tersebut ada dan bukan direktori
     *
     * @param pathResource relative path dari Resources direktori
     * @return <b>Path</b> yang merepresentasikan <b>pathResource</b>
     * @throws NullPointerException Jika file tidak ada
     * @throws URISyntaxException   Jika gagal konversi resource ke URI
     * @throws IOException          Jika path tidak mengarah ke file, melainkan Direktori
     */
    private Path getFilePath(String pathResource) throws URISyntaxException, IOException, NullPointerException {

        URI uri = this.getClass().getClassLoader()
                .getResource(pathResource).toURI();
        Path path = Paths.get(uri);

        if (!Files.isRegularFile(path)) {
            throw new IOException("Path tidak mengarah ke file");
        }

        return path;

    }


    @Override
    public Optional<BufferedReader> getBufferReader(String pathResource) {

        try {
            Path path = this.getFilePath(pathResource);
            return Optional.of(Files.newBufferedReader(path));
        } catch (Exception e) {
            return Optional.empty();
        }

    }


    @Override
    public Optional<InputStream> getInputStream(String pathResource) {
        try {
            Path path = this.getFilePath(pathResource);
            return Optional.of(Files.newInputStream(path));
        } catch (Exception e) {
            return Optional.empty();
        }
    }


    @Override
    public Optional<BufferedWriter> getBufferedWriter(String pathResource) {
        try {
            Path path = this.getFilePath(pathResource);
            return Optional.of(Files.newBufferedWriter(path));
        } catch (Exception e) {
            return Optional.empty();
        }
    }


    @Override
    public Optional<OutputStream> getOutputStream(String pathResource) {
        try {
            Path path = this.getFilePath(pathResource);
            return Optional.of(Files.newOutputStream(path));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}

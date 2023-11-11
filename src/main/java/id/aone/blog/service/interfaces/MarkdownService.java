package id.aone.blog.service.interfaces;

import id.aone.blog.model.Author;
import id.aone.blog.model.BlogPostMetadata;
import org.commonmark.node.Node;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.util.List;
import java.util.Map;

@Service
public interface MarkdownService {


    /**
     * Membaca markdown melalui InputStream
     *
     * @param inputStream Stream Resource yang menuju ke file MarkDown
     * @return Object Node yang berisikan data dan metadata dari markdown
     */
    Node readMarkdown(BufferedReader inputStream);

    /**
     * Mendapatkan konten di Node
     *
     * @param node Didapatkan melalui method {@code readMarkdown()}
     * @return Konten HTML yang sudah berbentuk String siap di inject ke Model
     */
    public String getContent(Node node);

    /**
     * Mendapatkan konten di Node
     *
     * @param mapMetaData Didapatkan melalui method {@code getData()}
     * @return Model BlogPostMetaData
     */
    BlogPostMetadata createBlogPostMetaData(Map<String, List<String>> mapMetaData);

    /**
     * Mendapatkan seluruh data yang ada pada markdown Author
     * Termasuk front matter dan content
     * @return Model Author
     * @see Author
     */
    Author createAuthorMetaData(Map<String, List<String>> mapMetaData);

}

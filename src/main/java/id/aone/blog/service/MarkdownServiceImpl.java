package id.aone.blog.service;

import id.aone.blog.model.Author;
import id.aone.blog.model.BlogPostMetadata;
import id.aone.blog.service.interfaces.MarkdownService;
import lombok.AllArgsConstructor;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@AllArgsConstructor
public class MarkdownServiceImpl implements MarkdownService {

    private Parser parser;
    private HtmlRenderer htmlRenderer;


    /**
     * @throws RuntimeException Ketika tidak berhasil membaca Buffer
     * @see id.aone.blog.service.interfaces.ResourceService
     * @see Node
     */
    @Override
    public Node readMarkdown(BufferedReader inputStream) {

        StringBuffer content = new StringBuffer();

        char[] buffer = new char[1024];
        int length;

        try {
            while ((length = inputStream.read(buffer)) != -1) {
                content.append(new String(buffer, 0, length));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return parser.parse(content.toString());


    }

    @Override
    public String getContent(Node node) {

        String render = htmlRenderer.render(node);
        return render;

    }

    /**
     * @throws ParseException Jika gagal melakukan parse Date pada markdown dengan variabel publish_date
     * @see BlogPostMetadata
     * @see org.commonmark.ext.front.matter.YamlFrontMatterVisitor
     */
    @Override
    public BlogPostMetadata createBlogPostMetaData(Map<String, List<String>> mapMetaData) {

        BlogPostMetadata.BlogPostMetadataBuilder builder = BlogPostMetadata.builder();

        List<String> title = mapMetaData.get("title");
        if (Objects.nonNull(title)) {
            title.stream().findFirst()
                    .ifPresent(builder::title);
        }


        List<String> author = mapMetaData.get("author");
        if (Objects.nonNull(author)) {
            author.stream().findFirst()
                    .ifPresent(builder::author);
        }

        List<String> image = mapMetaData.get("image");

        if (Objects.nonNull(image)) {
            image.stream().findFirst()
                    .ifPresent(builder::linkFeatureImage);
        }

        List<String> categories = mapMetaData.get("categories");
        if (Objects.nonNull(categories) && !categories.isEmpty()) {
            builder.categories(categories);
        }

        List<String> tags = mapMetaData.get("tags");
        if (Objects.nonNull(tags) && !tags.isEmpty()) {
            builder.tags(tags);
        }

        List<String> summary = mapMetaData.get("summary");
        if (Objects.nonNull(summary)) {
            summary.stream().findFirst()
                    .ifPresent(builder::summary);
        }

        List<String> publishDate = mapMetaData.get("publish_date");
        if (Objects.nonNull(publishDate)) {
            publishDate
                    .stream()
                    .findFirst().ifPresent(dateString -> {

                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

                        try {
                            Date parse = sdf.parse(dateString.trim());
                            builder.publishDate(sdf.format(parse));
                        } catch (ParseException e) {
                            //throw new RuntimeException(e);
                            e.printStackTrace();
                        }

                    });
        }

//        builder.permalink(); Set later
        return builder.build();
    }

    @Override
    public Author createAuthorMetaData(Map<String, List<String>> mapMetaData) {

        Author.AuthorBuilder builder = Author.builder();

        List<String> name = mapMetaData.get("name");
        if (Objects.nonNull(name)) {
            name.stream().findFirst()
                    .ifPresent(builder::name);
        }

        List<String> photo = mapMetaData.get("photo");
        if (Objects.nonNull(photo)) {
            photo.stream().findFirst()
                    .ifPresent(builder::photo);
        }

        HashMap<String, String> socialsMedia = new HashMap<>();
        /* String split to be Map<K,V>*/
        List<String> socials = mapMetaData.get("social");
        if (Objects.nonNull(socials)) {

            socials.forEach(social -> {

                /*
                 * Pastikan jika array bisa di akses pada index 0 dan satu
                 * jika tidak maka tidak usah ditambah map sosmed nya
                 * */
                try {

                    String[] socialMedia = social
                            .replace(" ", "")
                            .split(":");

                    String platform = socialMedia[0];
                    String link = socialMedia[1];
                    socialsMedia.put(platform, link);

                } catch (ArrayIndexOutOfBoundsException ignored) {

                }

            });
        }
        builder.socials(socialsMedia);


        return builder.build();
    }
}

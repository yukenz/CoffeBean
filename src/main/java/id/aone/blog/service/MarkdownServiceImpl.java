package id.aone.blog.service;

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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@AllArgsConstructor
public class MarkdownServiceImpl implements MarkdownService {

    private Parser parser;
    private HtmlRenderer htmlRenderer;


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
    public BlogPostMetadata createMetadata(Map<String, List<String>> mapMetaData) {

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
}

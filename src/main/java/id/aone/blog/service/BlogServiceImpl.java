package id.aone.blog.service;

import id.aone.blog.model.BlogPost;
import id.aone.blog.model.BlogPostMetadata;
import id.aone.blog.service.interfaces.BlogService;
import id.aone.blog.service.interfaces.MarkdownService;
import id.aone.blog.service.interfaces.ResourceService;
import lombok.AllArgsConstructor;
import org.commonmark.ext.front.matter.YamlFrontMatterVisitor;
import org.commonmark.node.Node;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public class BlogServiceImpl implements BlogService {

    private MarkdownService markdownService;
    private ResourceService resourceService;


    @Override
    public BlogPostMetadata getMetadata(Node node) {

        YamlFrontMatterVisitor visitor = new YamlFrontMatterVisitor();
        node.accept(visitor);

        BlogPostMetadata metadata = markdownService.createBlogPostMetaData(visitor.getData());

        return metadata;
    }

    @Override
    public BlogPost getBlogPost(String fileName, String permalink) {

        /* resources/markdown/post/file.md */
        BufferedReader input = resourceService.getBufferReader(
                ResourceService.PATH.POST.path() + fileName + ResourceService.EXT.MD.ext()
        ).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Node node = markdownService.readMarkdown(input);

        String content = markdownService.getContent(node);
        BlogPostMetadata metadata = this.getMetadata(node);
        metadata.setPermalink(permalink);

        return BlogPost.builder()
                .content(content)
                .metadata(metadata)
                .build();
    }

    @Override
    public List<BlogPost> getAllBlogPostByCategory(String categoryName) {

        // filterStream for non RegularFile
        Stream<Path> pathStream = resourceService
                .streamDirList(ResourceService.PATH.POST.path())
                .filter(Files::isRegularFile);

        return pathStream
                /* Transform Path Stream to Full BlogPost */
                .map(path -> {
                    try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {

                        Node node = markdownService.readMarkdown(bufferedReader);
                        YamlFrontMatterVisitor visitor = new YamlFrontMatterVisitor();
                        node.accept(visitor);

                        BlogPostMetadata metadata = markdownService.createBlogPostMetaData(visitor.getData());
                        String content = markdownService.getContent(node);

                        return BlogPost.builder()
                                .content(content)
                                .metadata(metadata)
                                .build();

                    } catch (IOException e) {
                        return null;
                    }
                })
                /* Filter : loop jika nama kategori ada yang sama dengan syarat maka include kan */
                .filter(blogPost ->
                        blogPost.getMetadata()
                                .getCategories().stream()
                                .anyMatch(categoryPost -> categoryPost.equalsIgnoreCase(categoryName))
                )
                .collect(Collectors.toList());

    }
}

package id.aone.blog.service;

import id.aone.blog.model.BlogPost;
import id.aone.blog.model.BlogPostMetadata;
import id.aone.blog.service.interfaces.AuthorService;
import id.aone.blog.service.interfaces.BlogService;
import id.aone.blog.service.interfaces.MarkdownService;
import id.aone.blog.service.interfaces.ResourceService;
import lombok.AllArgsConstructor;
import org.commonmark.ext.front.matter.YamlFrontMatterVisitor;
import org.commonmark.node.Node;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;

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
}

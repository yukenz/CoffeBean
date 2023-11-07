package id.aone.blog.service;

import id.aone.blog.model.Author;
import id.aone.blog.service.interfaces.AuthorService;
import id.aone.blog.service.interfaces.MarkdownService;
import id.aone.blog.service.interfaces.ResourceService;
import lombok.AllArgsConstructor;
import org.commonmark.ext.front.matter.YamlFrontMatterVisitor;
import org.commonmark.node.Node;

import java.io.BufferedReader;
import java.util.Optional;

@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    MarkdownService markdownService;
    ResourceService resourceService;

    @Override
    public Optional<Author> getAuthor(String authorUsername) {

        Optional<BufferedReader> input = resourceService.getBufferReader(
                ResourceService.PATH.AUTHOR.path() + authorUsername + ResourceService.EXT.MD.ext()
        );

        if (!input.isPresent()) {
            return Optional.empty();
        }

        Node node = markdownService.readMarkdown(input.get());

        YamlFrontMatterVisitor visitor = new YamlFrontMatterVisitor();
        node.accept(visitor);

        Author author = markdownService.createAuthorMetaData(visitor.getData());
        author.setUsername(authorUsername);
        author.setDescription(markdownService.getContent(node));

        return Optional.of(author);
    }
}

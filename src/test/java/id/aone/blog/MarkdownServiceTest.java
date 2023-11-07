package id.aone.blog;

import id.aone.blog.model.Author;
import id.aone.blog.service.interfaces.MarkdownService;
import id.aone.blog.service.interfaces.ResourceService;
import org.commonmark.ext.front.matter.YamlFrontMatterVisitor;
import org.commonmark.node.Node;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MarkdownServiceTest {

    @Autowired
    MarkdownService markdownService;
    @Autowired
    ResourceService resourceService;


    @Test
    void mdServiceTest() {

        BufferedReader bufferedReader = resourceService.getBufferReader("markdown/author/yukenz.md").get();
        Node node = markdownService.readMarkdown(bufferedReader);


        YamlFrontMatterVisitor visitor = new YamlFrontMatterVisitor();
        node.accept(visitor);

        Map<String, List<String>> data = visitor.getData();

        Author authorMetaData = markdownService.createAuthorMetaData(data);


        System.out.println();
    }
}

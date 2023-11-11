package id.aone.blog;

import id.aone.blog.service.interfaces.BlogService;
import id.aone.blog.service.interfaces.ResourceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@SpringBootTest
public class ResourceServiceTest {


    @Autowired
    ResourceService resourceService;

    @Autowired
    BlogService blogService;

    @Test
    void test() {


        Assertions.assertNotNull(resourceService.getBufferedWriter("markdown/post/Hello World.md"));
        Assertions.assertNotNull(resourceService.getInputStream("markdown/post/Hello World.md"));
        Assertions.assertNotNull(resourceService.getBufferedWriter("markdown/post/Hello World.md"));
        Assertions.assertNotNull(resourceService.getOutputStream("markdown/post/Hello World.md"));

    }

    @Test
    void testBlogService() {

        Assertions.assertThrows(ResponseStatusException.class, () -> {
            blogService.getBlogPost("Hello Wsorld", "/asdwd");
        });

        Assertions.assertDoesNotThrow(() -> {
            blogService.getBlogPost("Hello World", "/asdwd");
        });

    }

    @Test
    void testDirList() {
        Stream<Path> pathStream = resourceService.streamDirList(ResourceService.PATH.POST.path());

        Assertions.assertNotNull(pathStream);

        Stream<Path> streamPosts = pathStream.filter(Files::isRegularFile);

        streamPosts.forEach(path -> System.out.println(path.toString()));


    }
}

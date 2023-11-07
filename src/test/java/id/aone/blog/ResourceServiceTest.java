package id.aone.blog;

import id.aone.blog.service.interfaces.BlogService;
import id.aone.blog.service.interfaces.ResourceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URISyntaxException;

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
}

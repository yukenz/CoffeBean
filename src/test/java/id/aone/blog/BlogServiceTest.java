package id.aone.blog;

import id.aone.blog.model.BlogPost;
import id.aone.blog.service.interfaces.BlogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BlogServiceTest {

    @Autowired
    BlogService blogService;

    @Test
    void testGetByCategory() {

        List<BlogPost> blogPostByCategory = blogService.getAllBlogPostByCategory("Tas");

        for (BlogPost blogPost : blogPostByCategory) {
            System.out.println(blogPost.getMetadata().getTitle());
        }

    }
}

package id.aone.blog.service.interfaces;

import id.aone.blog.model.BlogPost;
import id.aone.blog.model.BlogPostMetadata;
import org.commonmark.node.Node;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BlogService {

    BlogPostMetadata getMetadata(Node node);

    BlogPost getBlogPost(String fileName, String permalink);

    List<BlogPost> getAllBlogPostByCategory(String categoryName);
}

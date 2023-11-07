package id.aone.blog.service.interfaces;

import id.aone.blog.model.BlogPost;
import id.aone.blog.model.BlogPostMetadata;
import org.commonmark.node.Node;
import org.springframework.stereotype.Service;

@Service
public interface BlogService {

    String getContent(Node node);

    BlogPostMetadata getMetadata(Node node);

    BlogPost getBlogPost(String fileName, String permalink);

}

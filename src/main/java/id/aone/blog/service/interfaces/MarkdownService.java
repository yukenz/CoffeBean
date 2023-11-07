package id.aone.blog.service.interfaces;

import id.aone.blog.model.BlogPostMetadata;
import org.commonmark.node.Node;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.util.List;
import java.util.Map;

@Service
public interface MarkdownService {

    Node readMarkdown(BufferedReader inputStream);

    BlogPostMetadata createMetadata(Map<String, List<String>> mapMetaData);

}

package id.aone.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogPostMetadata {

    private String title;
    private String author;
    private String publishDate;
    private List<String> categories;
    private List<String> tags;
    private String summary;
    private String linkFeatureImage;
    private String permalink;
}

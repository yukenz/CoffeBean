package id.aone.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    private String username;
    private String name;
    private String photo;
    private String description;

    /*
    * facebook : fb.com
    * facebook : fb.com
    * */
    private Map<String, String> socials;

}

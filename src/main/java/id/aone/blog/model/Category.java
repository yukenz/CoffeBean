package id.aone.blog.model;

import id.aone.blog.model.interfaces.HrefLink;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category implements HrefLink {

    private String name;
    private String permalink;

    @Override
    public String getText() {
        return this.name;
    }

    @Override
    public String getLink() {
        return this.permalink;
    }
}

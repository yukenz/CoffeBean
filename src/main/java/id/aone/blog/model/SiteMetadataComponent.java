package id.aone.blog.model;


import id.aone.blog.Factory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;


/*
Set To Component :

@PropertySource(value = "classpath:site.yaml", factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "site")
@Data

Set To Configuration Root :
@EnableConfigurationProperties({
        SiteMetadataComponent.class
}
* */

@Component
@PropertySource(
        value = "classpath:site.yaml",
        factory = Factory.YamlPropertySourceFactory.class
)
@ConfigurationProperties
@Data
public class SiteMetadataComponent {

    private String name;
    private String slug;
    private String url;
    private List<Category> categories;

}

package id.aone.blog;

import id.aone.blog.model.SiteMetadataComponent;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
@EnableConfigurationProperties({
        SiteMetadataComponent.class
})
public class BlogProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogProjectApplication.class, args);
    }

}

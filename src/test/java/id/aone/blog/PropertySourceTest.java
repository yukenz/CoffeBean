package id.aone.blog;

import id.aone.blog.model.SiteMetadataComponent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PropertySourceTest {

    @Autowired
    SiteMetadataComponent siteMetadataComponent;


    @Test
    void testPropSource() {

        Assertions.assertNotNull(siteMetadataComponent);
        System.out.println(siteMetadataComponent.getCategories().size());

    }
}

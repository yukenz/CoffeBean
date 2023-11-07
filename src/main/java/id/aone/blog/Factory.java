package id.aone.blog;

import id.aone.blog.model.interfaces.HrefLink;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class Factory {

    public static class YamlPropertySourceFactory implements PropertySourceFactory {

        @Override
        public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {

            /* Buat Bean Factory untuk parse YAML ke Properties */
            YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();

            /* masukan Resource ke factory untuk di proses menjadi Properties Tree */
            factory.setResources(resource.getResource());

            /* Meminta Properties tree yang telah di produksi */
            Properties properties = factory.getObject();

            /* YAML Filename yang di daftarkan */
            String filename = resource.getResource().getFilename();

            /* Object final untuk di inject ke Component */
            return new PropertiesPropertySource(filename, properties);

        }
    }

    /**
     * Class untuk membuat processor untuk memproses link href berdasarkan template
     *
     * @author yukenz
     */
    public static class MenuLinkFactory {

        public static HrefLinkProcessor buildProcessor(String template) {
            return new HrefLinkProcessor(template);
        }

        public static class HrefLinkProcessor {

            private final String TEMPLATE;

            private HrefLinkProcessor(final String TEMPLATE) {
                this.TEMPLATE = TEMPLATE;
            }

            public List<String> getMenuList(List<? extends HrefLink> links) {

                return links.stream().map(hrefLink -> {

                    return TEMPLATE
                            .replace("$text", hrefLink.getText())
                            .replace("$link", hrefLink.getLink());

                }).collect(Collectors.toList());

            }


        }

    }

}




package id.aone.blog.config;

import org.commonmark.Extension;
import org.commonmark.ext.front.matter.YamlFrontMatterExtension;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MarkdownConfig {

    @Bean
    Parser mdParser(
            @Qualifier("parserExtention")
            List<Extension> extensions
    ) {
        return Parser.builder()
                .extensions(extensions)
                .build();
    }

    @Bean
    HtmlRenderer htmlRenderer() {
        return HtmlRenderer.builder().build();
    }

    @Bean
    List<Extension> parserExtention() {
        ArrayList<Extension> extensions = new ArrayList<>();
        extensions.add(YamlFrontMatterExtension.create());
        return extensions;
    }
}

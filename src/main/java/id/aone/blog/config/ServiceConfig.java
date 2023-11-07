package id.aone.blog.config;

import id.aone.blog.model.SiteMetadataComponent;
import id.aone.blog.service.*;
import id.aone.blog.service.interfaces.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    BlogService blogService(
            MarkdownService markdownService,
            ResourceService resourceService
    ) {
        return new BlogServiceImpl(markdownService, resourceService);
    }

    @Bean
    MarkdownService markdownService(
            Parser parser,
            HtmlRenderer htmlRenderer
    ) {
        return new MarkdownServiceImpl(parser, htmlRenderer);
    }

    @Bean
    UrlParserService urlParserService() {
        return new UrlParserService() {
        };
    }

    @Bean
    CategoryService categoryService(SiteMetadataComponent siteMetadataComponent) {
        return new CategoryServiceImpl(siteMetadataComponent);
    }

    @Bean
    ResourceService resourceService() {
        return new ResourceServiceImpl();
    }

    @Bean
    AuthorService authorService(
            MarkdownService markdownService,
            ResourceService resourceService
    ) {

        return new AuthorServiceImpl(markdownService, resourceService);
    }
}

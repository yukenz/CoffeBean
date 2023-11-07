package id.aone.blog.controller;

import id.aone.blog.controller.interfaces.BlogController;
import id.aone.blog.model.Author;
import id.aone.blog.model.BlogPost;
import id.aone.blog.model.SiteMetadataComponent;
import id.aone.blog.service.interfaces.AuthorService;
import id.aone.blog.service.interfaces.BlogService;
import id.aone.blog.service.interfaces.CategoryService;
import id.aone.blog.service.interfaces.UrlParserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@AllArgsConstructor
@Controller
public class BlogControllerImpl implements BlogController {

    private BlogService blogService;
    private SiteMetadataComponent siteMetadataComponent;
    private UrlParserService urlParserService;
    private CategoryService categoryService;
    private AuthorService authorService;

    @Override
    public String blog(
            @PathVariable String postName,
            HttpServletRequest request,
            Model model
    ) {

        BlogPost blogPost = blogService.getBlogPost(
                urlParserService.parseUrl(postName),
                request.getRequestURI().toString()
        );

        model.addAttribute("siteMetadata", siteMetadataComponent);
        model.addAttribute("navMenu", categoryService.createMenuList());

        model.addAttribute("postContent", blogPost.getContent());
        model.addAttribute("postMetadata", blogPost.getMetadata());

        /*
         * Dapatkan username author dari blogPost.getMetadata()
         * Jika author tidak ada di folder maka buatkan author kosong
         * */
        model.addAttribute("author",
                authorService.getAuthor(blogPost.getMetadata().getAuthor()
                ).orElse(null)
        );


        return "singlepost";
    }

}

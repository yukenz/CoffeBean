package id.aone.blog.controller;

import id.aone.blog.controller.interfaces.CategoryController;
import id.aone.blog.model.BlogPost;
import id.aone.blog.service.interfaces.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@AllArgsConstructor
@Controller
public class CategoryControllerImpl implements CategoryController {

    private BlogService blogService;

    @Override
    public String category(
            String categoryName,
            HttpServletRequest request,
            Model model
    ) {

        List<BlogPost> posts = blogService.getAllBlogPostByCategory(categoryName);

        model.addAttribute("posts", posts);


        return "category";
    }
}

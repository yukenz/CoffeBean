package id.aone.blog.controller.interfaces;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public interface CategoryController {

    @GetMapping(
            path = "/category/{categoryName}"
    )
    String category(
            @PathVariable String categoryName,
            HttpServletRequest request,
            Model model
    );

}

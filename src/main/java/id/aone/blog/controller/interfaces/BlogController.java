package id.aone.blog.controller.interfaces;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public interface BlogController {

    @GetMapping(
            path = "/post/{postName}.html"
    )
    public String blog(
            @PathVariable String postName,
            HttpServletRequest request,
            Model model
    );
}

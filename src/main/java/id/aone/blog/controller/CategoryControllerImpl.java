package id.aone.blog.controller;

import id.aone.blog.controller.interfaces.CategoryController;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

public class CategoryControllerImpl implements CategoryController {

    @Override
    public String category(
            String categoryName,
            HttpServletRequest request,
            Model model
    ) {



        return null;
    }
}

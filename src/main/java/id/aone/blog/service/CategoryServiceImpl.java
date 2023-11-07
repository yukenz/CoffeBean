package id.aone.blog.service;

import id.aone.blog.Factory;
import id.aone.blog.model.SiteMetadataComponent;
import id.aone.blog.service.interfaces.CategoryService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private SiteMetadataComponent siteMetadataComponent;

    @Override
    public List<String> createMenuList() {

        List<String> menuList = Factory.MenuLinkFactory
                .buildProcessor("<li class=\"nav-item\"> <a class=\"nav-link color-green-hover\" href=\"$link\">$text</a> </li>")
                .getMenuList(siteMetadataComponent.getCategories());

        return menuList;
    }
}

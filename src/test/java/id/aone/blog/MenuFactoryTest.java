package id.aone.blog;

import id.aone.blog.model.Category;
import id.aone.blog.model.interfaces.HrefLink;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MenuFactoryTest {

    @Test
    void testMenuGenerate() {


        List<HrefLink> str = new ArrayList<>();
        str.add(new Category("asdw", "dawd"));

        List<String> menuList = Factory.MenuLinkFactory
                .buildProcessor("<li class=\"nav-item\"> <a class=\"nav-link color-green-hover\" href=\"$link\">$text</a> </li>")
                .getMenuList(str);

        System.out.println(menuList);

    }
}

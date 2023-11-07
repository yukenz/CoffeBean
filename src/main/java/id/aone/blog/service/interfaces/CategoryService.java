package id.aone.blog.service.interfaces;

import id.aone.blog.model.interfaces.HrefLink;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    List<String> createMenuList();

}

package id.aone.blog.service.interfaces;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public interface UrlParserService {

    default String parseUrl(String postName) {

        return Arrays.stream(postName.split("-")).map(word -> {
            char[] wordArr = word.toCharArray();
            String firstCharUpperCase = String.valueOf(wordArr[0]).toUpperCase();
            wordArr[0] = firstCharUpperCase.toCharArray()[0];

            return String.valueOf(wordArr);
        }).collect(Collectors.joining(" "));

    }

}

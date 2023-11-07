package id.aone.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class HttpTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void testGet() throws Exception {

        mockMvc.perform(
                get("/post/hello-world.html")
        ).andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void testStatic() throws Exception {

        mockMvc.perform(
                get("/static/style.css")
        ).andExpect(MockMvcResultMatchers.status().isOk());

    }
}

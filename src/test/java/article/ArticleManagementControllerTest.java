package article;

import article.domain.Article;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class ArticleManagementControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createArticle_Authorized() throws Exception {
        // 创建一个测试文章对象
        Article article = new Article();
        article.setTitle("权限认证测试");
        article.setAuthorId(1L);
        // 将对象转换为 JSON 字符串
        String articleJson = objectMapper.writeValueAsString(article);

        mockMvc.perform(MockMvcRequestBuilders.post("/articles/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(articleJson))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void createArticle_UnAuthorized() throws Exception {
        // 创建一个测试文章对象
        Article article = new Article();
        article.setTitle("Test Article Title");
        article.setAuthorId(99999L);
        // 将对象转换为 JSON 字符串
        String articleJson = objectMapper.writeValueAsString(article);

        mockMvc.perform(MockMvcRequestBuilders.post("/articles/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(articleJson))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

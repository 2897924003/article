package article;

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
class ArticleQueryTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void findArticle_Exist() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/article/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }
    @Test
    //TODO 这个怎么要1秒多？以后查询测试要记时，超时也要算不通过。
    void findArticle_NExist() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/article/4000000000")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }
    @Test
    void findArticle_Invalid_Input() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/article/a")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }
    @Test
    void findArticles() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/article")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
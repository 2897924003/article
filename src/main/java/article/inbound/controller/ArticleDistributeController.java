package article.inbound.controller;

import article.domain.Article;
import article.domain.ro.ArticleShowAggregationRO;
import article.usecase.ArticleDistributeUseCase;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * 文章分发服务接口
 */
@RestController
@CrossOrigin("http://localhost:9000")
public class ArticleDistributeController {
    private final ArticleDistributeUseCase articleDistributeUseCase;
    public ArticleDistributeController(ArticleDistributeUseCase articleDistributeUseCase) {
        this.articleDistributeUseCase = articleDistributeUseCase;
    }

    /**
     * 精确查找-获取文章详情信息。
     * <p>bug：此接口的可以越权查询他人未发布文章 state：已解决</p>
     * @param id 文章唯一ID。
     * @author jianhao
     */
    @GetMapping("/article/{id}")
    public ArticleShowAggregationRO findArticle(@PathVariable("id") Long id){
        return articleDistributeUseCase.byId(id);
    }

    /**
     * 规则查找-获取文章概览信息(分页)
     * @return
     */
    @GetMapping("/articles")
    public ResponseEntity<List<Article>> findArticles(@RequestParam("page") long page,@RequestParam("size") long size){
        Page<Article> pageable = new Page<>(page, size);
        List<Article> articles = articleDistributeUseCase.byDateAndVote(pageable);
        return  ResponseEntity.ok().body(articles);
    }

}

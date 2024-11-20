package article.inbound.controller;

import article.domain.Article;
import article.service.ArticleService;
import article.usecase.ArticleDistributeUseCase;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
/**
 * 文章查询
 */
@RestController
@CrossOrigin("http://localhost:9000")

public class ArticleDistributeController {

    private final ArticleService articleService;
    private final ArticleDistributeUseCase articleDistributeUseCase;
    public ArticleDistributeController(ArticleService articleService, ArticleDistributeUseCase articleDistributeUseCase) {
        this.articleService = articleService;

        this.articleDistributeUseCase = articleDistributeUseCase;
    }


    /**
     * 精确查找-获取文章详情信息。
     * @param id 文章唯一ID。
     */
    @GetMapping("/article/{id}")
    public Article findArticle(@PathVariable("id") Long id){
        return articleService.findArticle(id);
    }

    /**
     * 规则查找-获取文章概览信息(分页)
     * @param pageable
     * @return
     */
    @GetMapping("/articles")
    public ResponseEntity<List<Article>> findArticles(@PageableDefault(page = 1, size = 10) Pageable pageable){
        Page<Article> page = new Page<>(pageable.getPageNumber(), pageable.getPageSize());
        List<Article> articles = articleDistributeUseCase.byDateAndVote(page);

        return  ResponseEntity.ok().body(articles);
    }

}

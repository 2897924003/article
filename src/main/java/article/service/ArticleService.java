package article.service;

import article.domain.Article;
import article.infrastructure.ArticleRepository;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;



import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    public Article findArticle(Long id){
        return articleRepository.getById(id);
    }

    public Page<Article> findArticles(Page<Article> pageable) {
        return articleRepository.page(pageable);
    }


}

package article.infrastructure;

import article.domain.Article;

import article.domain.co.ArticleContent;
import com.baomidou.mybatisplus.extension.service.IService;


import java.util.List;

public interface ArticleRepository extends IService<Article> {


    /**
     * 分页查询
     * @param userId
     * @param page
     * @param size
     * @return
     */
    List<Article> query(long userId, int page, int size,boolean isPublished);
    void saveArticleContent(ArticleContent articleContent);
    void saveUserArticle(long userId, long articleId);

    void removeArticleContent(long articleId);

    void removeUserArticle(long articleId);

    void updateArticleContent(ArticleContent articleContent);
}

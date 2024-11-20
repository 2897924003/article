package article.domain;

import article.infrastructure.ArticleRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 模板方法，排行榜控制器
 */
@Service
public class ArticleRankProcessManager {

    private final ArticleRanker articleRankers;
    private final ArticleRepository articleRepository;
    private final ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();

    public ArticleRankProcessManager(ArticleRanker rankWarmers, ArticleRepository articleRepository) {
        this.articleRankers = rankWarmers;
        this.articleRepository = articleRepository;
    }

    /**
     * 初始化方法，将文章排序加入缓存
     */
    @PostConstruct
    public void rank() {
        List<Article> articles = allArticles();
        articles.forEach(article ->
                executorService.submit(() -> articleRankers.rankAll(article))

        );
    }


    @PreDestroy
    public void store() {
        System.out.println("数据全部被持久化");
    }

    public List<Article> allArticles() {
        return articleRepository.list();
    }

}

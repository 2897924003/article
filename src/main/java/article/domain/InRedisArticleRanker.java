package article.domain;

import article.domain.policy.ArticleScoreCalculator;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InRedisArticleRanker implements ArticleRanker {

    private final RedisTemplate<String, Long> redisRankTemplate;
    private final List<ArticleScoreCalculator> calculators;
    public InRedisArticleRanker(RedisTemplate<String, Long> redisTemplate, List<ArticleScoreCalculator> calculators) {
        this.redisRankTemplate = redisTemplate;
        this.calculators = calculators;
    }



    @Override
    public void rankAll(Article article) {
        calculators.forEach(calculator -> {
            calculator.calculate(article);
            redisRankTemplate.opsForZSet()
                    .add(
                            calculator.rank,
                            article.getId(),
                            article.getScore());

        });
    }

    @Override
    public void update(Article article, String rank) {

        redisRankTemplate.opsForZSet().add(rank, article.getId(), article.getScore());
    }

    @Override
    public void delete(long articleId,String rank) {
        redisRankTemplate.opsForZSet().remove(rank, articleId);
    }


}

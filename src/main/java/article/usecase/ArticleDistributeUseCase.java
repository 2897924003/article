package article.usecase;

import article.domain.Article;
import article.domain.RankCommand;
import article.infrastructure.ArticleRepository;

import article.domain.policy.RankNameSpec;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * DDD-应用层-文章分发-用户阅读文章服务
 */
@Service
public class ArticleDistributeUseCase {

    private final ArticleRepository articleRepository;

    private final RedisTemplate<String,Long> redisRankTemplate;
    public ArticleDistributeUseCase(ArticleRepository articleRepository, RedisTemplate<String, Long> redisRankTemplate) {
        this.articleRepository = articleRepository;
        this.redisRankTemplate = redisRankTemplate;
    }

    /**
     * 文章推送
     * @param co 推送指令对象
     */
    public void distribute(RankCommand co) {

        //解析命令对象-发起人-目标...

        //从redis中查看是否存在文章
          //1.不存在：说明redis失败。（启动应用前应执行 分数更新，缓存初始化）
          //2.存在：
           // 1. 选择推送引擎
           //- 使用默认推送引擎(无差异推送-排名）

           //- 使用个性化推送引擎

    }

    public List<Article> byDateAndVote(Page<Article> page) {

        //从redis里获取默认排行榜的文章集（一篇文章想要被优先展示，需要获取足够点赞，抵消时间衰减）
        Set<Long> id = redisRankTemplate.opsForZSet()
                .reverseRangeByScore(RankNameSpec.ARTICLE_RANK_DATE_VOTE.toString(),
                        0L,
                        Long.MAX_VALUE
                ,page.offset()
                ,page.getSize());


        if(id.isEmpty()){
            return Collections.emptyList();
        }
        //调用仓储服务，获取对应文章
        List<Article> articles = articleRepository.listByIds(id);

        // 按 Redis 排序结果对查询结果进行排序
        Map<Long, Article> articleMap = articles.stream()
                .collect(Collectors.toMap(Article::getId, Function.identity()));

        return id.stream()
                .map(articleMap::get)
                .filter(Objects::nonNull)
                .toList();

    }


}

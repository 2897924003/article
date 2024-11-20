package article.infrastructure.repository;

import article.domain.ArticleRank;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ArticleRankRepositoryImpl extends ServiceImpl<ArticleRankMapper, ArticleRank> implements ArticleRankRepository {
}

package article.domain.policy;

import article.domain.Article;

import java.time.Duration;
import java.time.LocalDateTime;

public class ByDateAndVote extends ArticleScoreCalculator{

    protected ByDateAndVote() {
        super(RankNameSpec.ARTICLE_RANK_DATE_VOTE.toString());
    }

    @Override
    public void calculate(Article article) {
        long votes = article.getVotes()*432;
        long seconds = Duration.between(article.getPublishDate(), LocalDateTime.now()).toSeconds();
        long score = votes-seconds;
        article.setScore(score>0?score:0);
    }
}

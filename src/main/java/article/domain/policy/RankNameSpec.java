package article.domain.policy;

public enum RankNameSpec {
    ARTICLE_RANK_DATE("article:rank:date"),
    ARTICLE_RANK_VOTE("article:rank:vote"),
    ARTICLE_RANK_COMMENT("article:rank:comment"),
    ARTICLE_RANK_DATE_VOTE("article:rank:date:vote");

    private final String key;

    RankNameSpec(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return key;
    }
}

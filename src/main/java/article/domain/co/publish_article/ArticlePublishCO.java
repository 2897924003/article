package article.domain.co.publish_article;

import article.domain.co.CommandObject;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class ArticlePublishCO extends CommandObject {

    public long articleId;

    public ArticlePublishCO() {}

    @Override
    public Long verifyCOSecurity() {
        Long authContext = (Long) RequestContextHolder.currentRequestAttributes().getAttribute("authContext", RequestAttributes.SCOPE_REQUEST);
        if (authContext==null||authContext != actorId) {
            throw new RuntimeException("你正在进行违法操作");
        }
        return authContext;
    }
}

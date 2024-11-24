package article.domain.dto;

/**
 * 文章内容：封装文章的正文部分
 */
public class ArticleContent {

    public Long id;

    public String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

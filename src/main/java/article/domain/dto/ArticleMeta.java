package article.domain.dto;

import article.domain.Article;
import java.time.LocalDateTime;

/**
 * 文章元数据：封装文章不能自动生成的基本信息与。
 */
public class ArticleMeta {

    private Long authorId;
    private String authorImg;
    private String authorName;
    private String img;
    /**
     * 必须
     */
    private String title;           // 文章标题
    private String summary;         // 文章摘要
    private String link;            // 文章链接

    public ArticleMeta() {
    }

    // 构造函数
    public ArticleMeta(Long authorId, String authorImg, String authorName, String img,
                       String title, String summary, String link) {
        this.authorId = authorId;
        this.authorImg = authorImg;
        this.authorName = authorName;
        this.img = img;
        this.title = title;
        this.summary = summary;
        this.link = link;
        System.out.println("ArticleMeta...");
    }

    // Getters 和 Setters
    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorImg() {
        return authorImg;
    }

    public void setAuthorImg(String authorImg) {
        this.authorImg = authorImg;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    // 将 ArticleMeta 转换为 Article 实体
    public Article toEntity() {
        return new Article(authorId, authorImg, authorName, title, img, summary, link, 0L, 0L, 0L,
                LocalDateTime.now(), LocalDateTime.now(), false);
    }
}

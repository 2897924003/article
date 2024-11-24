package article.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


import java.beans.JavaBean;
import java.time.LocalDateTime;

@TableName("article_meta")

public class Article {

    /**
     * 文章唯一ID，自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long authorId;
    private String authorImg;
    private String authorName;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章封面图的URL
     */
    private String img;
    /**
     * 文章摘要
     */
    private String summary;
    /**
     * 文章链接
     */
    private String link;
    /**
     * 文章评分
     */
    private Long score;
    /**
     * 文章浏览次数
     */
    private Long views;
    /**
     * 文章点赞数
     */
    private Long votes;
    /**
     * 文章发布时间
     */
    private LocalDateTime publishDate;
    /**
     * 文章编辑时间，表示最后一次编辑时间
     */
    private LocalDateTime editDate;
    /**
     * 发布状态，true表示已发布，false表示未发布
     */
    @TableField("is_published")
    private Boolean isPublished;
    public Article() {
    }

    public Article(Long id, Long authorId, String authorImg, String authorName, String title, String img, String summary, String link, Long score, Long views, Long votes, LocalDateTime publishDate, LocalDateTime editDate, Boolean isPublished) {
        this.id = id;
        this.authorId = authorId;
        this.authorImg = authorImg;
        this.authorName = authorName;
        this.title = title;
        this.img = img;
        this.summary = summary;
        this.link = link;
        this.score = score;
        this.views = views;
        this.votes = votes;
        this.publishDate = publishDate;
        this.editDate = editDate;
        this.isPublished = isPublished;
    }

    public Article(Long authorId, String authorImg, String authorName, String title, String img, String summary, String link, Long score, Long views, Long votes, LocalDateTime publishDate, LocalDateTime editDate, Boolean isPublished) {
        this.authorId = authorId;
        this.authorImg = authorImg;
        this.authorName = authorName;
        this.title = title;
        this.img = img;
        this.summary = summary;
        this.link = link;
        this.score = score;
        this.views = views;
        this.votes = votes;
        this.publishDate = publishDate;
        this.editDate = editDate;
        this.isPublished = isPublished;
    }

    /**
     * 更新浏览量
     * @param delta Δ
     */
    public void updateViews(Long delta){
        if (delta==null){return;}
        views+=delta;
    }
    /**
     * 更新点赞量
     * @param delta Δ
     */
    public void updateVotes(Long delta){
        if (delta==null){return;}
        votes+=delta;
    }
    /*----------------------------------------------------------------------------*/
    /*JavaBean规范*/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Long getVotes() {
        return votes;
    }

    public void setVotes(Long votes) {
        this.votes = votes;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

    public LocalDateTime getEditDate() {
        return editDate;
    }

    public void setEditDate(LocalDateTime editDate) {
        this.editDate = editDate;
    }

    public Boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(Boolean published) {
        isPublished = published;
    }

}

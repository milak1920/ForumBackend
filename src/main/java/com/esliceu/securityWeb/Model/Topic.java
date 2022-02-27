package com.esliceu.securityWeb.Model;


import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer _id;
    private String title;
    private String content;
    private Integer views;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;


    @ManyToOne()
    @JoinColumn(name="category", nullable=false)
    private Category category;

    @ManyToOne()
    @JoinColumn(name="user", nullable=false)
    private User user;

    @OneToMany(mappedBy="topic")
    private Set<Reply> replies;


    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Reply> getReplies() {
        return replies;
    }

    public void setReplies(Set<Reply> replies) {
        this.replies = replies;
    }


    @Override
    public String toString() {
        return "Topic{" +
                "_id=" + _id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", views=" + views +
                ", createdAt=" + createdAt +
                ", updateAt=" + updateAt +
                ", category=" + category +
                ", user=" + user +
                ", replies=" + replies +
                '}';
    }
}

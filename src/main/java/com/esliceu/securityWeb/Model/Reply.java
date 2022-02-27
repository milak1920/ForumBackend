package com.esliceu.securityWeb.Model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer _id;

    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @ManyToOne()
    @JoinColumn(name="user", nullable=false)
    private User user;

    @ManyToOne()
    @JoinColumn(name="category", nullable=false)
    private Category category;

    @ManyToOne()
    @JoinColumn(name="topic", nullable=false)
    private Topic topic;

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "_id=" + _id +
                ", content='" + content + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", user=" + user +
                ", category=" + category +
                ", topic=" + topic +
                '}';
    }
}

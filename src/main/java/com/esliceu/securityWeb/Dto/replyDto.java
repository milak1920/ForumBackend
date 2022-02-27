package com.esliceu.securityWeb.Dto;

import com.esliceu.securityWeb.Model.Topic;

import java.util.ArrayList;


public class replyDto {
    private Categories category;
    private String content;
    private String createdAt;
    private Integer _id;
    private ArrayList<replyDto> replies;
    private String title;
    private String updatedAt;
    private getprofile user;
    private String topic;
    public replyDto(Categories category, Topic topic, getprofile user,
                    ArrayList<replyDto> replies) {
        this.category = category;
        this.content = topic.getContent();
        this.createdAt = String.valueOf(topic.getCreatedAt());
        this._id = topic.get_id();
        this.replies = replies;
        this.title = topic.getTitle();
        this.updatedAt = String.valueOf(topic.getUpdateAt());
        this.user = user;
    }



    public replyDto(){
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return _id;
    }

    public void setId(Integer id) {
        this._id = id;
    }

    public ArrayList<replyDto> getReplies() {
        return replies;
    }

    public void setReplies(ArrayList<replyDto> replies) {
        this.replies = replies;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public getprofile getUser() {
        return user;
    }

    public void setUser(getprofile user) {
        this.user = user;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}

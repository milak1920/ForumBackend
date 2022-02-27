package com.esliceu.securityWeb.Dto;




public class topicDto {
    private Integer category;
    private String content;
    private String createdAt;
    private Integer _id;
    private Long numberOfReplies;
    private getprofile user;
    private String title;
    private String updatedAt;


    public topicDto(Integer category, String content, String createdAt, Integer id,
                    Long numberOfReplies, getprofile user, String title, String updatedAt) {
        this.category = category;
        this.content = content;
        this.createdAt = createdAt;
        this._id = id;
        this.numberOfReplies = numberOfReplies;
        this.user = user;
        this.title = title;
        this.updatedAt = updatedAt;
    }



    public topicDto(){
    }
    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAT() {
        return createdAt;
    }

    public void setCreatedAT(String createdAT) {
        this.createdAt = createdAT;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return _id;
    }

    public void setId(Integer id) {
        this._id = id;
    }

    public Long getNumberOfReplies() {
        return numberOfReplies;
    }

    public void setNumberOfReplies(Long numberOfReplies) {
        this.numberOfReplies = numberOfReplies;
    }

    public getprofile getUser() {
        return user;
    }

    public void setUser(getprofile user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}

package com.esliceu.securityWeb.Dto;

import com.esliceu.securityWeb.Model.Category;

public class Categories {

    private int _id;
    private String slug;
    private String title;
    private String description;
    private String color;
    private String moderators;

    public Categories( Category category) {
        this._id = category.getId();
        this.slug = category.getSlug();
        this.title = category.getTitle();
        this.description = category.getDescription();
        this.color = category.getColor();
        this.moderators = category.getModerators();
    }

    public Categories() {
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModerators() {
        return moderators;
    }

    public void setModerators(String moderators) {
        this.moderators = moderators;
    }
}

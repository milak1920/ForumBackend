package com.esliceu.securityWeb.Dto;

import com.esliceu.securityWeb.Model.User;


public class getprofile {
    private String avatar;
    private String avartUrl;
    private String id;
    private String name;
    private String email;
    private String role;
    private Permissions permissions;

    public getprofile(User user) {
        this.id = String.valueOf(user.getId());
        this.name = user.getName();
        this.email = user.getEmail();
        this.role = String.valueOf(user.getRole());
    }
    public getprofile() {

    }
    public String getAvartUrl() {
        return avartUrl;
    }

    public void setAvartUrl(String avartUrl) {
        this.avartUrl = avartUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Permissions getPermissions() {
        return permissions;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}

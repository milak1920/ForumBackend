package com.esliceu.securityWeb.Dto;


import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtDto {

    private String token;
    private String bearer = "Bearer";
    private String nameUser; //email
    private Collection<? extends GrantedAuthority> authorities;
    private getprofile user;

    public JwtDto(String token, String nameUser, Collection<? extends GrantedAuthority> authorities, getprofile user) {
        this.token = token;
        this.nameUser = nameUser;
        this.authorities = authorities;
        this.user = user;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBearer() {
        return bearer;
    }

    public void setBearer(String bearer) {
        this.bearer = bearer;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public getprofile getUser() {
        return user;
    }

    public void setUser(getprofile user) {
        this.user = user;
    }
}
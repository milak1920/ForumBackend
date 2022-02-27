package com.esliceu.securityWeb.Model;


import com.sun.istack.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.annotation.Resource;
import javax.persistence.*;

import java.util.*;

/**
 * Clase para la base de datos
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Column(unique = true, length = 100)
    private String name;
    @NotNull
    @Column(unique = true, length = 100)
    private String email;
    @NotNull
    private String password;
    @Column(columnDefinition = "longblob")
    private byte[] avatar;
    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
    }


    @OneToMany(mappedBy="user")
    private Set<Category> categories;

    @OneToMany(mappedBy="user")
    private Set<Topic> topics;

    @OneToMany(mappedBy="user")
    private Set<Reply> replies;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;

    }

    public Collection<? extends GrantedAuthority> getRoles(Role role) {
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(role.toString()));
        return roles;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Topic> getTopics() {
        return topics;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics;
    }

    public Set<Reply> getReplies() {
        return replies;
    }

    public void setReplies(Set<Reply> replies) {
        this.replies = replies;
    }
}
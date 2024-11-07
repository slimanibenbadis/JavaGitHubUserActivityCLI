package com.github.user.activity.java.cli.model;

import com.google.gson.annotations.SerializedName;

public class Actor {
    private long id;
    private String login;
    
    @SerializedName("display_login")
    private String displayLogin;
    
    @SerializedName("gravatar_id")
    private String gravatarId;
    
    private String url;
    
    @SerializedName("avatar_url")
    private String avatarUrl;

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDisplayLogin() {
        return displayLogin;
    }

    public void setDisplayLogin(String displayLogin) {
        this.displayLogin = displayLogin;
    }

    public String getGravatarId() {
        return gravatarId;
    }

    public void setGravatarId(String gravatarId) {
        this.gravatarId = gravatarId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
} 
package com.github.user.activity.java.cli.model;

import com.google.gson.annotations.SerializedName;

public class Event {
    private String id;
    private String type;
    private Actor actor;
    private Repository repo;
    private Payload payload;
    private boolean isPublic;
    
    @SerializedName("created_at")
    private String createdAt;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Repository getRepo() {
        return repo;
    }

    public void setRepo(Repository repo) {
        this.repo = repo;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
} 
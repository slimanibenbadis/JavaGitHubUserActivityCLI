package com.github.user.activity.java.cli.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Payload {
    private String action;
    
    @SerializedName("push_id")
    private Long pushId;
    
    private Integer size;
    
    @SerializedName("distinct_size")
    private Integer distinctSize;
    
    private String ref;
    private String head;
    private String before;
    private List<Commit> commits;

    // Getters and Setters
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Long getPushId() {
        return pushId;
    }

    public void setPushId(Long pushId) {
        this.pushId = pushId;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getDistinctSize() {
        return distinctSize;
    }

    public void setDistinctSize(Integer distinctSize) {
        this.distinctSize = distinctSize;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public List<Commit> getCommits() {
        return commits;
    }

    public void setCommits(List<Commit> commits) {
        this.commits = commits;
    }
} 
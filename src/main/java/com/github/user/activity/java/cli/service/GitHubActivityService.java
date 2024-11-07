package com.github.user.activity.java.cli.service;

import java.util.List;

import com.github.user.activity.java.cli.client.GitHubApiClient;
import com.github.user.activity.java.cli.exception.GitHubApiException;
import com.github.user.activity.java.cli.model.Event;

public class GitHubActivityService {
    private final GitHubApiClient apiClient;

    public GitHubActivityService(String authToken) {
        this.apiClient = new GitHubApiClient(authToken);
    }

    public List<Event> getUserRecentActivity(String username) throws GitHubApiException {
        return apiClient.getAllUserPublicEvents(username);
    }
} 
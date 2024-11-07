package com.github.user.activity.java.cli.client;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.github.user.activity.java.cli.config.GitHubConfig;
import com.github.user.activity.java.cli.exception.GitHubApiException;
import com.github.user.activity.java.cli.model.Event;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GitHubApiClient {
    private final OkHttpClient client;
    private final Gson gson;
    private final String authToken;

    public GitHubApiClient(String authToken) {
        this.client = new OkHttpClient();
        this.gson = new Gson();
        this.authToken = authToken;
    }

    protected String getBaseUrl() {
        return GitHubConfig.API_BASE_URL;
    }

    public List<Event> getUserPublicEvents(String username, int page) throws GitHubApiException {
        String url = String.format("%s/users/%s/events/public?page=%d", 
            getBaseUrl(), username, page);

        Request.Builder requestBuilder = new Request.Builder()
            .url(url)
            .header("Accept", "application/vnd.github+json")
            .header("X-GitHub-Api-Version", GitHubConfig.API_VERSION)
            .header("User-Agent", GitHubConfig.USER_AGENT);

        if (authToken != null && !authToken.isEmpty()) {
            requestBuilder.header("Authorization", "Bearer " + authToken);
        }

        try (Response response = client.newCall(requestBuilder.build()).execute()) {
            if (!response.isSuccessful()) {
                throw new GitHubApiException("API call failed with code: " + response.code() +
                    " and message: " + response.message());
            }

            String responseBody = response.body().string();
            Type eventListType = new TypeToken<List<Event>>(){}.getType();
            return gson.fromJson(responseBody, eventListType);
        } catch (IOException e) {
            throw new GitHubApiException("Failed to make API request", e);
        }
    }

    public List<Event> getAllUserPublicEvents(String username) throws GitHubApiException {
        // GitHub API typically limits to 10 pages
        return getUserPublicEvents(username, 1);
    }
} 
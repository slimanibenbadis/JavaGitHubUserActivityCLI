package com.github.user.activity.java.cli.client;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.user.activity.java.cli.exception.GitHubApiException;
import com.github.user.activity.java.cli.model.Event;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

class GitHubApiClientTest {
    private MockWebServer mockWebServer;
    private GitHubApiClient client;
    private String baseUrl;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        baseUrl = mockWebServer.url("/").toString();
        client = new GitHubApiClient("test-token") {
            @Override
            protected String getBaseUrl() {
                return baseUrl;
            }
        };
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void getUserPublicEvents_Success() throws GitHubApiException {
        // Prepare mock response
        String mockResponse = """
            [
                {
                    "id": "1234567890",
                    "type": "PushEvent",
                    "actor": {
                        "id": 1,
                        "login": "testuser",
                        "display_login": "testuser",
                        "gravatar_id": "",
                        "url": "https://api.github.com/users/testuser",
                        "avatar_url": "https://avatars.githubusercontent.com/u/1?"
                    },
                    "repo": {
                        "id": 1,
                        "name": "testuser/repo",
                        "url": "https://api.github.com/repos/testuser/repo"
                    },
                    "public": true,
                    "created_at": "2024-01-20T12:00:00Z"
                }
            ]
            """;

        mockWebServer.enqueue(new MockResponse()
            .setBody(mockResponse)
            .addHeader("Content-Type", "application/json"));

        // Test
        List<Event> events = client.getUserPublicEvents("testuser", 1);

        // Verify
        assertNotNull(events);
        assertEquals(1, events.size());
        Event event = events.get(0);
        assertEquals("1234567890", event.getId());
        assertEquals("PushEvent", event.getType());
        assertEquals("testuser", event.getActor().getLogin());
        assertEquals("testuser/repo", event.getRepo().getName());
    }

    @Test
    void getUserPublicEvents_ApiError() {
        // Prepare mock response for error
        mockWebServer.enqueue(new MockResponse()
            .setResponseCode(404)
            .setBody("{\"message\": \"Not Found\"}"));

        // Test and verify
        GitHubApiException exception = assertThrows(GitHubApiException.class, 
            () -> client.getUserPublicEvents("nonexistentuser", 1));
        assertTrue(exception.getMessage().contains("404"), 
            "Error message should contain status code 404");
    }
} 
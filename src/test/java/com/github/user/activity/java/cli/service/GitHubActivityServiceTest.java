package com.github.user.activity.java.cli.service;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.user.activity.java.cli.client.GitHubApiClient;
import com.github.user.activity.java.cli.exception.GitHubApiException;
import com.github.user.activity.java.cli.model.Event;

@ExtendWith(MockitoExtension.class)
class GitHubActivityServiceTest {
    
    @Mock
    private GitHubApiClient apiClient;
    
    private GitHubActivityService service;

    @BeforeEach
    void setUp() {
        service = new GitHubActivityService("test-token") {
            @Override
            protected GitHubApiClient createApiClient(String token) {
                return apiClient;
            }
        };
    }

    @Test
    void getUserRecentActivity_Success() throws GitHubApiException {
        // Prepare
        List<Event> mockEvents = new ArrayList<>();
        Event mockEvent = new Event();
        mockEvent.setId("1234567890");
        mockEvent.setType("PushEvent");
        mockEvents.add(mockEvent);

        when(apiClient.getAllUserPublicEvents("testuser")).thenReturn(mockEvents);

        // Test
        List<Event> events = service.getUserRecentActivity("testuser");

        // Verify
        assertNotNull(events);
        assertEquals(1, events.size());
        assertEquals("PushEvent", events.get(0).getType());
        verify(apiClient).getAllUserPublicEvents("testuser");
    }

    @Test
    void getUserRecentActivity_EmptyResponse() throws GitHubApiException {
        // Prepare
        when(apiClient.getAllUserPublicEvents("testuser")).thenReturn(new ArrayList<>());

        // Test
        List<Event> events = service.getUserRecentActivity("testuser");

        // Verify
        assertNotNull(events);
        assertTrue(events.isEmpty());
        verify(apiClient).getAllUserPublicEvents("testuser");
    }

    @Test
    void getUserRecentActivity_ApiError() throws GitHubApiException {
        // Prepare
        when(apiClient.getAllUserPublicEvents("testuser"))
            .thenThrow(new GitHubApiException("API Error"));

        // Test and verify
        GitHubApiException exception = assertThrows(GitHubApiException.class, 
            () -> service.getUserRecentActivity("testuser"));
        assertEquals("API Error", exception.getMessage());
    }
} 
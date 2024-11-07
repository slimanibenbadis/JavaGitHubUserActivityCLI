package com.github.user.activity.java.cli;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.user.activity.java.cli.model.Event;
import com.github.user.activity.java.cli.model.Repository;
import com.github.user.activity.java.cli.service.GitHubActivityService;

@ExtendWith(MockitoExtension.class)
class GitHubActivityCLITest {
    
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void printEvent_Success() {
        // Prepare
        Event event = new Event();
        event.setType("PushEvent");
        event.setCreatedAt("2024-01-20T12:00:00Z");
        
        Repository repo = new Repository();
        repo.setName("test/repo");
        event.setRepo(repo);

        // Test
        GitHubActivityCLI.printEvent(event);

        // Verify
        String output = outContent.toString();
        assertTrue(output.contains("PushEvent"));
        assertTrue(output.contains("test/repo"));
    }
} 
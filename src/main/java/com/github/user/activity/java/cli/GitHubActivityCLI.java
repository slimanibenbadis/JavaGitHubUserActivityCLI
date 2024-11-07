package com.github.user.activity.java.cli;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.github.user.activity.java.cli.config.GitHubConfig;
import com.github.user.activity.java.cli.exception.GitHubApiException;
import com.github.user.activity.java.cli.model.Event;
import com.github.user.activity.java.cli.service.GitHubActivityService;

public class GitHubActivityCLI {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            .withZone(ZoneId.systemDefault());

    public static void main(String[] args) {
        String username;
        
        // Get username from command line args or prompt user
        if (args.length > 0) {
            username = args[0];
        } else {
            System.out.print("Enter GitHub username: ");
            Scanner scanner = new Scanner(System.in);
            username = scanner.nextLine().trim();
            scanner.close();
        }

        if (username.isEmpty()) {
            System.err.println("Error: Username cannot be empty");
            System.exit(1);
        }

        GitHubActivityService service = new GitHubActivityService(GitHubConfig.GITHUB_TOKEN);

        try {
            System.out.println("\nFetching recent activities for user: " + username + "\n");
            List<Event> events = service.getUserRecentActivity(username);

            if (events.isEmpty()) {
                System.out.println("No recent activities found for user: " + username);
                return;
            }

            // Print events in a formatted way
            events.forEach(GitHubActivityCLI::printEvent);

        } catch (GitHubApiException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }

    protected static void printEvent(Event event) {
        String timestamp = formatter.format(Instant.parse(event.getCreatedAt()));
        
        System.out.printf("Time: %s%n", timestamp);
        System.out.printf("Type: %s%n", event.getType());
        System.out.printf("Repository: %s%n", event.getRepo().getName());
        
        if (event.getPayload() != null && event.getPayload().getAction() != null) {
            System.out.printf("Action: %s%n", event.getPayload().getAction());
        }
        
        System.out.println("-".repeat(50) + "\n");
    }
} 
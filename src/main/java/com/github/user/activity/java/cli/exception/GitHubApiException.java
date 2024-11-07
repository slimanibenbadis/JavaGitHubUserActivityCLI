package com.github.user.activity.java.cli.exception;

public class GitHubApiException extends Exception {
    public GitHubApiException(String message) {
        super(message);
    }

    public GitHubApiException(String message, Throwable cause) {
        super(message, cause);
    }
} 
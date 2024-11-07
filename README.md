# GitHub User Activity CLI

A command-line interface tool that fetches and displays recent GitHub activities for any user.

## Features

- Fetch recent GitHub activities for any public user
- Display activities in a formatted, easy-to-read manner
- Support for various GitHub event types
- Token-based authentication for API access
- Simple command-line interface

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- GitHub Personal Access Token (for authentication)

## Installation

1. Clone the repository:
```bash
git clone https://github.com/yourusername/github_user_activity_java_cli.git
cd github_user_activity_java_cli
```

2. Configure GitHub token:
   - Rename `GitHubConfig.template` to `GitHubConfig.java`

   - Edit `GitHubConfig.java` and replace `your_github_token_here` with your GitHub Personal Access Token

3. Build the project:
```bash
mvn clean package
```

## Usage

### Running from Command Line

1. Using the JAR file directly:
```bash
java -jar target/github_user_activity_java_cli-1.0-SNAPSHOT.jar <username>
```

2. Examples:
   - With username as argument:
   ```bash
   java -jar target/github_user_activity_java_cli-1.0-SNAPSHOT-jar-with-dependencies.jar octocat
   ```
   - Interactive mode (will prompt for username):
   ```bash
   java -jar target/github_user_activity_java_cli-1.0-SNAPSHOT-jar-with-dependencies.jar
   ```

### Output Format

The tool displays activities in the following format:

```
Time: YYYY-MM-DD HH:mm:ss
Type: EventType
Repository: repository/name
Action: action_type (if applicable)
--------------------------------------------------
```

## Development

### Project Structure
src/
├── main/java/com/github/user/activity/java/cli/
│ ├── client/ # API client implementation
│ ├── config/ # Configuration files
│ ├── exception/ # Custom exceptions
│ ├── model/ # Data models
│ ├── service/ # Business logic
│ └── GitHubActivityCLI.java # Main application
└── test/ # Test files

### Running Tests

To run the tests, use the following command:
```bash
mvn test
```

### Building
```bash
mvn clean package
```

The built JAR will be located at `target/github_user_activity_java_cli-1.0-SNAPSHOT-jar-with-dependencies.jar`

## Configuration

### GitHub Token

1. Generate a GitHub Personal Access Token:
   - Go to GitHub Settings → Developer settings → Personal access tokens
   - Generate a new token (no specific scopes needed for public repositories)

2. Configure the token in the application:
   - Copy the template file as mentioned in Installation step 2
   - Replace the token placeholder with your actual token

### API Configuration

The following can be configured in `GitHubConfig.java`:
- `API_BASE_URL`: GitHub API base URL
- `API_VERSION`: GitHub API version
- `USER_AGENT`: User agent string for API requests
- `GITHUB_TOKEN`: Your GitHub Personal Access Token

## Supported Event Types

The tool supports various GitHub event types including:
- PushEvent
- CreateEvent
- DeleteEvent
- IssuesEvent
- IssueCommentEvent
- PullRequestEvent
- WatchEvent
- ForkEvent
- And more...

## Troubleshooting

### Common Issues

1. **401 Unauthorized Error**
   - Verify your GitHub token is valid
   - Check if the token is properly configured in `GitHubConfig.java`

2. **404 Not Found Error**
   - Verify the username exists
   - Check if the user has any public activities

3. **Rate Limit Exceeded**
   - GitHub API has rate limits
   - Authenticated requests have higher limits
   - Wait for the rate limit to reset

### Getting Help

If you encounter any issues:
1. Check the troubleshooting section above
2. Look for existing issues in the GitHub repository
3. Create a new issue with:
   - Description of the problem
   - Steps to reproduce
   - Error messages
   - Your environment details

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## Security Notes

- Never commit your GitHub token to version control
- The `GitHubConfig.java` file is included in `.gitignore` to prevent accidental commits
- Always use tokens with minimal required permissions
- Regularly rotate your tokens for better security

## Acknowledgments

- GitHub API Documentation
- OkHttp for HTTP client
- Gson for JSON parsing
- JUnit and Mockito for testing
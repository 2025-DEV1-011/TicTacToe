# Tic Tac Toe Kata – 2025-DEV1-011/TicTacToe

## Overview
This project is a clean, test-driven implementation of the classic Tic Tac Toe game, built with Java and Spring Framework. It demonstrates best practices in software craftsmanship, including TDD, clear domain modeling, and RESTful API design. The codebase is structured for clarity, maintainability, and extensibility.

## Project Goals
- Provide a robust backend for playing Tic Tac Toe via a REST API and a web UI
- Showcase clean code, TDD, and modern Java practices
- Serve as a reference for well-structured Spring Boot applications

## Requirements
- Java 21
- Maven 3.6+
- (Optional) Lombok plugin for your IDE (for code generation)

## How to Build and Run
1. **Clone the repository:**
   ```sh
   git clone https://github.com/2025-DEV1-011/TicTacToe.git
   cd TicTacToe
   ```
2. **Build the project:**
   ```sh
   ./mvnw clean install
   ```
3. **Run the application:**
   ```sh
   ./mvnw spring-boot:run
   ```

## Accessing the Web UI
- Open your browser and go to: [http://localhost:8080/](http://localhost:8080/)
- The web interface allows you to start a new game, make moves, and see the game state visually.
- No additional setup is required; the UI is served automatically by Spring Boot.

## API Documentation
### Start a New Game
- **Endpoint:** `POST /api/game`
- **Description:** Creates a new Tic Tac Toe game.
- **Response:** `201 Created` with the new `Game` object in the response body.

### Make a Move
- **Endpoint:** `POST /api/game/{gameId}/move`
- **Description:** Makes a move in the specified game.
- **Request Body:**
  ```json
  {
    "row": 0,
    "col": 1
  }
  ```
- **Response:** `200 OK` with the updated `Game` object in the response body.

## Project Structure
- `src/main/java/com/game/tictactoe/` – Main application code
  - `controller/` – REST API controllers (`WebController`), and `PageController` for serving the web UI
  - `model/` – Domain models (Game, Player, GameState, etc.)
  - `service/` – Service layer for game orchestration and state management (see below)
- `src/main/resources/templates/index.html` – Web UI
- `src/test/java/com/game/tictactoe/` – Unit and integration tests
- `pom.xml` – Maven build file

## Service Layer Rationale
While the main game logic (rules, moves, state transitions) is encapsulated in the `Game` domain model, the `GameService` class serves important architectural purposes:
- **Orchestration:** Manages the lifecycle of multiple games, mapping game IDs to game instances.
- **Separation of Concerns:** Keeps controllers thin and focused on HTTP concerns, while the service handles application-level coordination.
- **Testability:** Allows injection of custom game suppliers for testing, and centralizes game management for easier mocking and verification.
- **Future Extensibility:** Provides a clear place to add features like persistence, statistics, or multi-user support without polluting the domain model or controllers.

## Key Practices & Choices
- **TDD:** All features were developed using a test-first approach. See the commit history for incremental progress.
- **Lombok:** Used to reduce boilerplate in domain models (e.g., getters).
- **Spring Boot:** For rapid REST API development and easy testing.
- **Spring Boot Actuator:** Actuator endpoints are enabled to provide production-ready features such as health checks and application metrics, supporting better monitoring and operational insight.
- **Domain-Driven Design:** The core logic is encapsulated in the `Game` domain model and supporting classes.
- **Immutability & Encapsulation:** Where possible, fields are `final` and access is restricted.
- **Constructor Visibility:** Some Constructors used only for testing are package-private and clearly documented.
- **Comprehensive Testing:** All logic is covered by unit tests. See the `test` package.
- **Commit History:** Each step of the solution is committed separately to show the development and thought process.

## Running Tests
To run all tests:
```sh
./mvnw test
```

## Notes
- Please ensure your IDE has Lombok support enabled.
- The code is designed for clarity, maintainability, and extensibility.

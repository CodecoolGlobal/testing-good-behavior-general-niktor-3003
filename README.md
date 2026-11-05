# Testing Good Behavior

This project focuses on high-level black-box testing of the **Gotta Fetch 'Em All** application using BDD (Behavior-Driven Development).

This repository contains the test automation project and testing documentation. The application's source code is maintained in a separate repository.

## System Under Test (SUT)

- **Name:** Gotta Fetch 'Em All
- **Development team:** Dugtrio
- **Repository:** [gotta-fetch-em-all-react-dugtrio-1652](https://github.com/CodecoolGlobal/gotta-fetch-em-all-react-dugtrio-1652)

## Running the Application Locally

### Prerequisites

- Node.js and npm installed.
- Git installed if you want to clone the application repository.
- A web browser to interact with the application.
- An internet connection to retrieve Pokémon and location data from PokéAPI.

### 1. Download the Application

Clone the application into a separate directory alongside the test project:

```bash
git clone --branch development https://github.com/CodecoolGlobal/gotta-fetch-em-all-react-dugtrio-1652.git
cd gotta-fetch-em-all-react-dugtrio-1652
```

If you download the repository as a ZIP file, extract it and open a terminal in the extracted application directory.

The tested branch is `development`, at commit `e6796516b1d4d4d667828099e104969a9d4f1ce8`. See the [testing environment](docs/testing-environment.md) for the recorded versions.

### 2. Install Dependencies

Run the following command in the application directory:

```bash
npm i
```

Do this before starting the application for the first time and whenever its dependencies change.

### 3. Start the Backend Server

In the application root directory, run:

```bash
npm run server
```

The backend runs at `http://localhost:4000`. To check that it is available, open [the health endpoint](http://localhost:4000/api/health) in your browser. It should return:

```json
{"status":"ok"}
```

Keep this terminal running. The backend manages the player's Pokémon collection. Its data is stored in memory, so restarting the backend resets the collection to the three starting Pokémon: Bulbasaur, Charizard, and Poliwhirl.

### 4. Start the Frontend Development Server

Open a **second terminal** in the same application root directory and run:

```bash
npm run dev
```

Open the local URL displayed in the terminal in your browser. Vite normally uses `http://localhost:5173/`. If that port is already in use, it may select another port; always check the printed URL and make sure your tests use the same address.

Both the backend and frontend must remain running while using the application or executing the full automated suite. API-only tests require the backend. To stop the servers, press `Ctrl+C` in each terminal.

### Startup Troubleshooting

- If port `4000` is already in use, stop the conflicting process before starting the backend. The frontend expects the backend at `http://localhost:4000`.
- If an earlier Pokémon project is still running on port `5173`, stop it before starting this frontend so that you do not accidentally test the wrong application.
- If Pokémon or location data does not load, check your internet connection and PokéAPI availability as well as the backend health endpoint.

## Test Automation

The test project uses the following technologies:

- **Java and Maven:** test project development and builds.
- **Selenium WebDriver:** browser automation.
- **Cucumber and Gherkin:** behavior-driven test scenarios.
- **JUnit 5:** test execution and assertions.

UI tests follow the Page Object Model (POM) pattern, keeping Selenium locators and browser operations separate from Cucumber steps. API communication is handled by a separate `ApiClient` using Java's `HttpClient`. JUnit assertions are used only in `Then` step methods.

### Test Prerequisites

- JDK 27, selected both as the project SDK and the Maven runtime.
- Maven, either installed on the command line or available through IntelliJ IDEA.
- Google Chrome for the `@ui` scenarios.
- The application servers started as described above.
- Network access to PokéAPI and, when required, browser driver downloads.

The UI base URL is currently set in `LocationsPage.open()` to `http://localhost:5173/`. The API base URL is set in `api.feature` to `http://localhost:4000`. If the application uses different ports, update these values before running the tests.

### Running the Tests

In IntelliJ IDEA, import this repository as a Maven project, reload the Maven dependencies, and run:

```text
src/test/java/com/codecool/pokemon/runners/RunCucumberTest.java
```

This is the execution method used for the archived results. With Maven installed and JDK 27 configured, the command-line entry point from the test repository root is:

```bash
mvn test
```

Run a single test group using Cucumber tags:

```bash
mvn "-Dcucumber.filter.tags=@api" test
mvn "-Dcucumber.filter.tags=@ui" test
```

`@ui` scenarios launch a fresh Chrome session and close it afterward. Failed UI scenarios attach a screenshot to the report before the browser closes. `@api` scenarios send HTTP requests without starting a browser.

### Latest Recorded Results

The archived run on **2026-11-05** contains **8 scenarios: 7 passed and 1 failed**.

| Group | Scenarios | Passed | Failed |
| --- | --- | --- | --- |
| UI | 6 | 5 | 1 |
| API | 2 | 2 | 0 |
| Total | 8 | 7 | 1 |

The failed scenario, **Display the required no-encounter message**, detects [BUG-002](docs/bug-reports/BUG-002-no-encounter-message-text.md): the displayed message differs from the exact text in requirement 2.3. The suite therefore returns a nonzero exit code. The requirement assertion remains in place to expose the discrepancy.

The separate scenario for returning from `spring-path` passes. Post-battle return failure, [BUG-001](docs/bug-reports/BUG-001-locations-not-shown-after-victory.md), was found manually and is not covered by the current automated scenarios.

### Reports

Each run updates:

- `target/cucumber-report.html`: interactive HTML report.
- `target/cucumber-report.json`: machine-readable results.

Open the HTML file in a browser to inspect steps, assertion errors and failure screenshots. `target/` is ignored by Git and removed by `mvn clean`.

The final run is preserved separately:

- [Archived HTML report](docs/test-results/cucumber-report-2026-11-05.html) — download or open locally in a browser; GitHub does not render it as the interactive report.
- [Archived JSON report](docs/test-results/cucumber-report-2026-11-05.json).
- [Result summary](docs/test-results/README.md).

### Project Structure

```text
src/test/java/com/codecool/pokemon/
  api/          HTTP request handling
  context/      Scenario-scoped WebDriver sharing
  hooks/        Browser lifecycle and failure screenshots
  pages/        Page Objects and Selenium operations
  runners/      JUnit Platform / Cucumber suite configuration
  steps/        Gherkin step implementations and Then assertions
src/test/resources/features/
  api.feature
  locations.feature
docs/           Exploratory notes, findings, environment and assessment
```

## Testing Documentation

- [Exploratory testing notes](docs/exploratory-testing.md).
- [Testing environment](docs/testing-environment.md).
- [BUG-001: Missing location list after victory or defeat](docs/bug-reports/BUG-001-locations-not-shown-after-victory.md).
- [BUG-002: No-encounter message text](docs/bug-reports/BUG-002-no-encounter-message-text.md).
- [IMP-001: Owned Pokémon statistics before battle](docs/improvements/IMP-001-player-pokemon-statistics.md).
- [End-of-sprint assessment and coverage limits](docs/final-assessment.md).

The exact damage formula, the identities of the first 20 locations, and complete sprite-source compliance are not established by this suite. See the assessment for details and the earlier intermittent UI timeout.

### Submission Follow-up

- Record the actual exploratory testing duration; the required 2–3 hour timebox has not been evidenced by a recorded duration.
- Commit and push the final code, documentation and archived reports.
- Share the bug reports and final assessment with the developers, and record the sharing date/channel. Sharing has not yet been confirmed.

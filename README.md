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
git clone https://github.com/CodecoolGlobal/gotta-fetch-em-all-react-dugtrio-1652.git
cd gotta-fetch-em-all-react-dugtrio-1652
```

If you download the repository as a ZIP file, extract it and open a terminal in the extracted application directory.

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

Both the backend and frontend must remain running while using the application or executing automated tests. To stop them, press `Ctrl+C` in each terminal.

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

Tests will follow the Page Object Model (POM) pattern, keeping page interaction logic separate from test steps. JUnit assertions will be used only in `Then` steps.

**Current status:** Maven dependencies are configured in `pom.xml`; automated tests have not been implemented yet. Instructions for running the tests will be added once the implementation is ready.

## Testing Documentation

Exploratory testing findings, bug reports with reproduction steps, and the end-of-sprint assessment will be documented in this repository. Bug reports and the final assessment will also be shared with the application's developers.

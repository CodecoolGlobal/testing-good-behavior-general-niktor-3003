# Testing environment

Environment snapshot collected from the local machine and application
checkout on **2026-11-04**. The manual observations were reported on this
machine; environment versions were collected afterward rather than captured
separately at the time of each observation.

The application commit was rechecked on **2026-11-05** and remained
`e6796516b1d4d4d667828099e104969a9d4f1ce8`, with no local changes reported by
Git. The [final archived run](test-results/README.md) was produced on
2026-11-05. The version table below remains the 2026-11-04 environment
snapshot, rather than a claim that every component was remeasured per run.

## Application environment

| Item | Value |
| --- | --- |
| Application | Gotta Fetch 'Em All — Dugtrio |
| Repository | https://github.com/CodecoolGlobal/gotta-fetch-em-all-react-dugtrio-1652 |
| Branch | `development` |
| Commit | `e6796516b1d4d4d667828099e104969a9d4f1ce8` |
| Checkout status at collection | No local changes reported by Git |
| Operating system | Windows 10 Pro 22H2, build 19045.6466, x64 |
| Browser | Google Chrome 156.0.8078.80 |
| Node.js available in the inspection shell | 24.19.0 |
| npm available in the inspection shell | 11.17.0 |
| Frontend | Vite 8.0.5; `npm run dev` |
| Frontend URL used by the tests | `http://localhost:5173/` |
| Backend | Express 5.2.1; `npm run server` |
| Backend URL | `http://localhost:4000` |
| Backend health endpoint | `http://localhost:4000/api/health` |
| External data dependency | PokéAPI, `https://pokeapi.co/api/v2` |

Node.js and npm values describe the shell used to collect this snapshot;
the versions in the tester's server terminals were not independently checked.

## Automation environment

| Item | Value |
| --- | --- |
| Installed test JDK checked | OpenJDK 27.0.1.0.3 |
| Selenium Java configured in `pom.xml` | 4.51.0 |
| Cucumber Java / JUnit Platform Engine / PicoContainer | 7.20.1 |
| JUnit Jupiter API | 5.11.3 |
| JUnit Platform Suite | 1.11.3 |
| API request client | Java `java.net.http.HttpClient` |
| API JSON parsing | Selenium `org.openqa.selenium.json.Json` |
| Scenario groups | `@ui` and `@api`; browser hooks apply only to `@ui` |

The archived suite was executed through `RunCucumberTest` in IntelliJ IDEA.
A separate command-line Maven execution was not performed as part of the
documentation update.

## Test data considerations

- Opponents are selected randomly, so exact Pokémon can vary across encounters.
- The backend stores the owned Pokémon collection in memory. Restarting the
  backend resets it to Bulbasaur, Charizard and Poliwhirl; reloading the page
  alone should not be assumed to reset the backend collection.
- The victory and defeat screenshots show later session states, including
  Pokémon captured earlier in the session.

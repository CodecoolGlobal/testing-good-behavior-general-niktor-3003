# Exploratory testing notes

## Scope and evidence

Application: Gotta Fetch 'Em All — Dugtrio.

The manual observations below were reported by the tester during the
guided testing session and are supported by supplied screenshots. Automated
results are taken from the tester's archived report, which was inspected
after execution. The assistant did not independently rerun the suite for
this documentation update.

The application lets players choose locations, encounter Pokémon, select
owned Pokémon and battle opponents. A victory can add the opponent to the
owned collection. The frontend and backend must both be running.

The tested version is expected to remain unchanged during this testing
period, according to the tester. See the [testing environment](testing-environment.md)
for the machine, browser and checkout snapshot collected on 2026-11-04.
The elapsed exploratory testing time was not tracked; these notes do not
establish completion of the required 2–3 hour timebox.

## EXP-001: Victory and capture

- Requirement: 4.2, 4.4.
- Player Pokémon: Charizard.
- Opponent: Pelipper.
- Final HP: Charizard **30/78**, Pelipper **0/60**.
- Status: `pelipper was defeated and captured.`
- Pelipper appeared in the owned Pokémon list.
- The tester confirmed that the location list did not return after waiting.
- Result: Victory and capture were observed; return to location selection failed.
- Finding: [BUG-001](bug-reports/BUG-001-locations-not-shown-after-victory.md).
- [Screenshot](bug-reports/evidence/BUG-001-victory-screen.png).

## EXP-002: Defeat

- Requirement: 4.3, 4.4.
- Player Pokémon: Wingull.
- Opponent: Bronzor.
- Final HP: Wingull **0/40**, Bronzor **33/57**.
- Status: `wingull fainted. The encounter ends.`
- Bronzor was absent from the displayed owned Pokémon list.
- The tester confirmed that the battle log stopped updating.
- The location list did not return.
- Result: The battle ended after defeat; return to location selection failed.
- Finding: [BUG-001](bug-reports/BUG-001-locations-not-shown-after-victory.md).
- [Screenshot](bug-reports/evidence/BUG-001-defeat-screen.png).

## EXP-003: Location without a Pokémon encounter

- Requirement: 2.3.
- Location: `spring-path`.

The tester reported that `spring-path` was the only location without a
Pokémon encounter among the locations explored in this session.

### Actions

1. Select `spring-path` from the location list.
2. Observe the message displayed instead of an opponent.
3. Click **Back, run away!**.

### Observed result

The message `This location doesn't seem to have any pokemon.` was displayed.
The tester confirmed that the Back button returned to the location list.

Result: The no-encounter message and return navigation worked in this trial.
The wording differs from the requirement's exact text,
`This location doesn't seem to have any pokémon`, in the accent and final
period. This discrepancy is documented in
[BUG-002](bug-reports/BUG-002-no-encounter-message-text.md).

![Spring-path with the no-encounter message](evidence/spring-path-no-encounter.png)

## EXP-004: Attempt to fight without an opponent

- Location: `spring-path`.
- Additional exploratory check; no explicit requirement specifies this status message.

### Actions

1. Open the no-encounter view for `spring-path`.
2. Click **Fight!!!**.

### Observed result

The status `No encountered pokemon to fight.` appeared. No battle was
observed to start. The page remained usable in the reported trial.

Result: The application handled the attempted action with a status message.
Whether an owned Pokémon had been selected immediately before this action
was not confirmed.

![Status after attempting to fight without an opponent](evidence/spring-path-fight-status.png)

## Other findings

- [IMP-001: Display player Pokémon statistics before battle](improvements/IMP-001-player-pokemon-statistics.md).
- The inspected opponent image used a Showdown GIF URL. The requirement
  specifies `front_default`; compliance with the required sprite source
  has not been established by the existing image visibility checks.

## Final automated results

The archived run on **2026-11-05** contains **8 scenarios: 7 passed and
1 failed**. Both API scenarios passed; five of the six UI scenarios passed.
The JSON report was inspected to confirm the statuses and failure reason.

- [Scenario-by-scenario results](test-results/README.md).
- [Archived HTML report](test-results/cucumber-report-2026-11-05.html).
- [Archived JSON report](test-results/cucumber-report-2026-11-05.json).

**Display the required no-encounter message** fails at the exact-text
assertion, detecting [BUG-002](bug-reports/BUG-002-no-encounter-message-text.md).
**Return from a location without Pokemon** is a separate scenario and passes,
including navigation back to 20 displayed locations and disappearance of
the encounter section. The text discrepancy no longer prevents that
navigation check from running.

The two API scenarios verify the backend health response and a non-empty
owned Pokémon list containing string entries with the expected PokéAPI URL
prefix. They do not check POST behavior or fetch each returned URL.

## Automation stability observation

An earlier run timed out while waiting for the no-encounter message at
`spring-path`, although the next scenario using that location passed.
The final archived run reached the expected text comparison instead.
The earlier timeout's cause has not been established. Failure screenshots
are now attached by the UI teardown hook to help investigate recurrence;
the later screenshot does not explain the earlier timeout.

## Coverage limits

These checks do not establish complete coverage of the application.
In particular, the location count check does not verify the identities
of the first 20 locations, and the exact damage formula has not yet
been verified.

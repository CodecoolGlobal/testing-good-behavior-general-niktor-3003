# IMP-001: Display player Pokémon statistics before battle

- Type: Usability improvement
- Status: Proposed
- Application: Gotta Fetch 'Em All — Dugtrio

## Steps to observe

1. Open the application with the frontend and backend running.
2. Select a location that starts an encounter, such as `canalave-city`.
3. Review the available owned Pokémon.
4. Select an owned Pokémon, such as Bulbasaur.

## Observed behavior

The opponent's HP, Attack and Defense are displayed. The owned Pokémon's
statistics are not shown, including after selection. Selecting an owned
Pokémon highlights it with a border.

## User impact

Players cannot compare their Pokémon's statistics with the opponent's when
choosing which Pokémon to use in battle. This makes it harder to make an
informed choice.

## Suggested improvement

Display HP, Attack and Defense for each owned Pokémon before battle, or show
these statistics when a Pokémon is selected. Use labels consistent with the
opponent's statistics so that players can compare them easily.

## Requirement context

The provided requirements do not explicitly require displaying the owned
Pokémon's statistics. This finding is therefore recorded as an improvement
suggestion pending discussion with the developers.

## Evidence

The screenshot supplied during exploratory testing shows Tentacruel's
statistics (`HP: 80 | ATK: 70 | DEF: 65`) and Bulbasaur selected with a border.
The owned Pokémon's statistics are not displayed.

## Developer communication

Not yet shared with the developers. Add the sharing date and a link to the
discussion or issue after sharing this suggestion through the agreed channel.

package com.codecool.pokemon.steps;

import com.codecool.pokemon.context.TestContext;
import com.codecool.pokemon.pages.EncounterPage;
import com.codecool.pokemon.pages.LocationsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class LocationSteps {
    private final TestContext context;
    private LocationsPage locationsPage;

    public LocationSteps(TestContext context) {
        this.context = context;
    }

    private EncounterPage encounterPage() {
        return new EncounterPage(context.getDriver());
    }

    @Given("I am on the location selection page")
    public void openLocationSelectionPage() {
        locationsPage = new LocationsPage(context.getDriver());
        locationsPage.open();
    }


    @Then("I should see {int} locations")
    public void verifyLocationCount(int expectedCount) {
        int actualCount = locationsPage.getLocationCount();

        assertEquals(expectedCount,
                actualCount,
                "The number of displayed locations should match the expected count."
        );
    }

    @When("I select the location {string}")
    public void selectLocation(String locationName) {
        locationsPage.selectLocation(locationName);
    }

    @Then("the location list should not be visible")
    public void verifyLocationListIsNotVisible() {
        assertTrue(locationsPage.waitForLocationListToDisappear(),
                "The location list should disappear after selecting a location.");
    }

    @Then("the encountered Pokemon's name and image should be visible")
    public void verifyEncounteredPokemonNameAndImage() {
        String pokemonName = encounterPage().getEncounteredPokemonName();

        assertFalse(pokemonName.isBlank(),
                "The encountered Pokemon's name should be visible.");

        assertTrue(encounterPage().waitForEncounteredPokemonImage(),
                "The encountered Pokemon's image should be visible and loaded.");
    }

    @When("I select my Pokemon {string}")
    public void selectMyPokemon(String pokemonName) {
        encounterPage().selectOwnedPokemon(pokemonName);
    }

    @Then("my Pokemon {string} should be selected")
    public void verifyMyPokemonIsSelected(String pokemonName) {
        assertTrue(encounterPage().waitForPokemonSelection(pokemonName),
                "The chosen Pokemon should be selected: " + pokemonName);
    }

    @When("I leave the encounter")
    public void leaveEncounter() {
        encounterPage().leaveEncounter();
    }

    @Then("the encounter should not be visible")
    public void verifyEncounterIsNotVisible() {
        assertTrue(encounterPage().waitForEncounterToDisappear(),
                "The encounter should disappear after leaving it.");
    }

    @Then("the no-encounter message should match the requirement")
    public void verifyNoEncounterMessage() {
        String actualMessage = encounterPage().getNoEncounterMessage();

        assertEquals("This location doesn't seem to have any pokémon",
                actualMessage,
                "The no-encounter message should match the requirement.");
    }

    @Then("a no-encounter message should be visible")
    public void verifyNoEncounterMessageIsVisible() {
        String actualMessage = encounterPage().getNoEncounterMessage();

        assertFalse(
                actualMessage.isBlank(),
                "A visible, non-empty no-encounter message should be displayed."
        );
    }
}

package com.codecool.pokemon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EncounterPage extends BasePage{
    private final By encounteredPokemonHeading = By.cssSelector
            (".encounter-section .preview-card h2");
    private final By encounteredPokemonImage = By.cssSelector
            (".encounter-section .preview-card img.pokemon-sprite");
    private final By leaveEncounterButton =
            By.xpath("//button[normalize-space(.)='Back, run away!']");
    private final By encounterSection =
            By.cssSelector(".encounter-section");
    private final By noEncounterMEssage = By.cssSelector(
            ".encounter-section > div:not(.preview-card)");

    public EncounterPage(WebDriver driver) {
        super(driver);
    }

    public String getEncounteredPokemonName() {
        String headingText = wait.until(ExpectedConditions
                        .visibilityOfElementLocated(encounteredPokemonHeading))
                .getText();
        return headingText.replace("You have to fight with:", "").trim();
    }

    public boolean waitForEncounteredPokemonImage() {
        return wait.until(driver -> {
            WebElement image = driver.findElement(encounteredPokemonImage);
            String naturalWidth = image.getDomProperty("naturalWidth");

            return image.isDisplayed()
                    && Boolean.parseBoolean(image.getDomProperty("complete"))
                    && naturalWidth != null
                    && Integer.parseInt(naturalWidth) > 0;
        });
    }

    private By ownedPokemonByName(String pokemonName) {
        return By.xpath("//button[contains(@class, 'user-pokemon-btn')]" +
                "[.//img[@alt='" + pokemonName + "']]");
    }

    public void selectOwnedPokemon(String pokemonName) {
        wait.until(ExpectedConditions
                .elementToBeClickable(ownedPokemonByName(pokemonName)))
                .click();
    }

    public boolean waitForPokemonSelection(String pokemonName) {
        return wait.until(ExpectedConditions
                .attributeContains(ownedPokemonByName(pokemonName),
                        "class",
                        "selected"));
    }

    public void leaveEncounter() {
        wait.until(ExpectedConditions.elementToBeClickable(leaveEncounterButton)).click();
    }

    public boolean waitForEncounterToDisappear() {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(encounterSection));
    }

    public String getNoEncounterMessage() {
        return wait.until(ExpectedConditions
                .visibilityOfElementLocated(noEncounterMEssage))
                .getText();
    }
}